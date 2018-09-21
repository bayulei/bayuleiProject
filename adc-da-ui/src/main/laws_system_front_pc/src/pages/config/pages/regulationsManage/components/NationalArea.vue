<!-- 国家/地区-->
<template>
 <div id="national-area">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
       <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
       <Button type="info" class="query-button" @click="selectRegion">查询</Button>
     </div>
     <div slot="right">
       <Button type="info" @click="regionAdd">增加</Button>
       <Button type="error"  @click="regionBatchDel">删除</Button>
       <!--显示模态框-->
       <Modal v-model="regionModal" :title="regionTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"
              @on-ok="saveRegion">
         <Form :model="regionModelAdd" label-position="right" :label-width="80">
           <input v-model="regionModelAdd.id" v-show="false">
           <FormItem label="选项">
             <Input v-model="regionModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
           <FormItem label="数据编码">
             <Input v-model="regionModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
         </Form>
       </Modal>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="regionTable" :data="regionData" @on-selection-change=" handleSelectone">
     </Table>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script>
export default {
  name: 'national-area',
  data () {
    return {
      modalType: '',
      regionTitle: '', // 模态框标题
      selectNum: '', // 接收选中行数据
      standardForm: {
        standName: '', // 选项
        standCode: '', // 数据编码
        id: '' // 数据id
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      regionModelAdd: {
        parts: '', // 模态框标准
        coding: ''// 数据编码
      },
      regionModal: false, // 模态框是否打开
      // 表格表头
      regionTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '选项',
          key: 'dicTypeName',
          align: 'center'
        },
        {
          title: '数据编码',
          key: 'dicTypeCode',
          width: 300,
          align: 'center'
        },
        {
          title: '创建日期',
          key: 'creationDate',
          align: 'center'
        },
        {
          title: '创建人',
          key: 'founder',
          align: 'center'
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.regionEdit(params.row)
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.viewData(params.row)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.regionDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 表格内容
      regionData: []
    }
  },
  methods: {
    //  全选
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    // 非全选
    handleSelectone (row) {
      this.selectNum = row
    },
    // 选择提示框
    instance (type, content) {
      const title = '请选择'
      switch (type) {
        case 'warning':
          this.$Modal.warning({
            title: title,
            content: content
          })
          break
      }
    },
    // 新增
    regionAdd () {
      this.regionModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.regionTitle = '新增标准'
      this.regionModelAdd.parts = ''
      this.regionModelAdd.coding = ''
      this.regionModelAdd.id = ''
    },
    // 编辑
    regionEdit (item) {
      this.regionModal = true
      this.modalType = 2
      this.regionTitle = '编辑标准'
      this.regionModelAdd.parts = item.dicTypeName
      this.regionModelAdd.coding = item.dicTypeCode
      this.regionModelAdd.id = item.id
    },
    // 查看
    viewData (row) {
      this.regionModal = true
      this.modalType = 3
      this.regionTitle = '查看标准'
      this.regionModelAdd.parts = row.dicTypeName
      this.regionModelAdd.coding = row.dicTypeCode
      this.regionModelAdd.id = row.id
    },
    // 删除
    regionDel (id) {
      this.handleSelectAll(false)
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.delete('sys/dictype/delete', {
            dicTypeEOId: id
          }, {
            _this: this
          }, res => {
            this.selectRegion()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 批量删除
    regionBatchDel () {
      if (this.selectNum === '' || this.selectNum.length === 0) {
        this.instance('warning', '请选择一条数据进行删除')
      } else {
        let delIds = []
        for (let i = 0; i < this.selectNum.length; i++) {
          delIds.push(this.selectNum[i].id)
        }
        delIds = delIds.join(',')
        this.$Modal.confirm({
          title: '确认删除',
          content: '<p>确认删除该这些数据？</p>',
          onOk: () => {
            this.$http.delete('sys/dictype/deleteArr', {
              ids: delIds
            }, {
              _this: this
            }, res => {
              this.selectRegion()
            }, e => {
            })
          },
          onCancel: () => {
          }
        })
      }
    },
    pageChange (page) {
      this.page = page
      this.selectRegion()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectRegion()
    },
    // 加载表格
    selectRegion () {
      this.$http.get('sys/dictype/page', {
        page: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'RFRFRFSCXVB'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.regionData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveRegion () {
      let data = {
        dicTypeName: this.regionModelAdd.parts,
        dicTypeCode: this.regionModelAdd.coding,
        dicId: 'RFRFRFSCXVB'
      }
      if (this.modalType === 1) {
        this.$http.postData('sys/dictype/create', data, {
          _this: this
        }, res => {
          this.selectRegion()
        }, e => {

        })
      } else if (this.modalType === 2) {
        this.$http.putData('sys/dictype', {
          dicTypeName: this.regionModelAdd.parts,
          dicTypeCode: this.regionModelAdd.coding,
          dicId: 'RFRFRFSCXVB',
          id: this.regionModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectRegion()
        }, e => {

        })
      }
    }
  },
  mounted () {
    this.selectRegion()
  }
}
</script>

<style lang="less">
   #national-area{}
</style>
