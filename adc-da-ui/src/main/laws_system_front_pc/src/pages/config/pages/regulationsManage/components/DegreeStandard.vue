<!-- 采标程度 -->
<template>
 <div id="degree-standard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
       <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
       <Button type="info" class="query-button" @click="selectCollection">查询</Button>
     </div>
     <div slot="right">
       <Button type="info" @click="collectionAdd">增加</Button>
       <Button type="error"  @click="collectionBatchDel">删除</Button>
       <!--显示模态框-->
       <Modal v-model="collectionModal" :title="collectionTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"
              @on-ok="saveCollection">
         <Form :model="collectionModelAdd" label-position="right" :label-width="80">
           <input v-model="collectionModelAdd.id" v-show="false">
           <FormItem label="选项">
             <Input v-model="collectionModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
           <FormItem label="数据编码">
             <Input v-model="collectionModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
         </Form>
       </Modal>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="collectionTable" :data="collectionData" @on-selection-change=" handleSelectone">
     </Table>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script>
export default {
  name: 'degree-standard',
  data () {
    return {
      modalType: '',
      collectionTitle: '', // 模态框标题
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
      collectionModelAdd: {
        parts: '', // 模态框标准
        coding: ''// 数据编码
      },
      collectionModal: false, // 模态框是否打开
      // 表格表头
      collectionTable: [
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
                    this.collectionEdit(params.row)
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
                    this.collectionDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 表格内容
      collectionData: []
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
    collectionAdd () {
      this.collectionModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.collectionTitle = '新增标准'
      this.collectionModelAdd.parts = ''
      this.collectionModelAdd.coding = ''
      this.collectionModelAdd.id = ''
    },
    // 编辑
    collectionEdit (item) {
      this.collectionModal = true
      this.modalType = 2
      this.collectionTitle = '编辑标准'
      this.collectionModelAdd.parts = item.dicTypeName
      this.collectionModelAdd.coding = item.dicTypeCode
      this.collectionModelAdd.id = item.id
    },
    // 查看
    viewData (row) {
      this.collectionModal = true
      this.modalType = 3
      this.collectionTitle = '查看标准'
      this.collectionModelAdd.parts = row.dicTypeName
      this.collectionModelAdd.coding = row.dicTypeCode
      this.collectionModelAdd.id = row.id
    },
    // 删除
    collectionDel (id) {
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
            this.selectCollection()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 批量删除
    collectionBatchDel () {
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
              this.selectCollection()
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
      this.selectCollection()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectCollection()
    },
    // 加载表格
    selectCollection () {
      this.$http.get('sys/dictype/page', {
        pageNo: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'VBERTFDGGF'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.collectionData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveCollection () {
      let data = {
        dicTypeName: this.collectionModelAdd.parts,
        dicTypeCode: this.collectionModelAdd.coding,
        dicId: 'VBERTFDGGF'
      }
      if (this.modalType === 1) {
        this.$http.postData('sys/dictype/create', data, {
          _this: this
        }, res => {
          this.selectCollection()
        }, e => {

        })
      } else if (this.modalType === 2) {
        this.$http.putData('sys/dictype', {
          dicTypeName: this.collectionModelAdd.parts,
          dicTypeCode: this.collectionModelAdd.coding,
          dicId: 'VBERTFDGGF',
          id: this.collectionModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectCollection()
        }, e => {

        })
      }
    }
  },
  mounted () {
    this.selectCollection()
  }
}
</script>

<style lang="less">
   #degree-standard{}
</style>
