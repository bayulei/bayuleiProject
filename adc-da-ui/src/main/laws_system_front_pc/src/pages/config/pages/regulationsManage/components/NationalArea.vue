<template>
 <div id="national-area">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
       <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
       <Button type="info" class="query-button" @click="selectClass">查询</Button>
     </div>
     <div slot="right">
       <Button type="info" @click="classAdd">增加</Button>
       <Button type="error"  @click="classBatchDel">删除</Button>
       <!--显示模态框-->
       <Modal v-model="classModal" :title="classTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"
              @on-ok="saveClass">
         <Form :model="classModelAdd" label-position="right" :label-width="80">
           <input v-model="classModelAdd.id" v-show="false">
           <FormItem label="选项">
             <Input v-model="classModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
           <FormItem label="数据编码">
             <Input v-model="classModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
           </FormItem>
         </Form>
       </Modal>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="classTable" :data="classData" @on-selection-change=" handleSelectone">
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
      classTitle: '', // 模态框标题
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
      classModelAdd: {
        parts: '', // 模态框标准
        coding: ''// 数据编码
      },
      classModal: false, // 模态框是否打开
      // 表格表头
      classTable: [
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
                    this.classEdit(params.row)
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
                    this.classDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 表格内容
      classData: []
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
    classAdd () {
      this.classModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.classTitle = '新增标准'
      this.classModelAdd.parts = ''
      this.classModelAdd.coding = ''
      this.classModelAdd.id = ''
    },
    // 编辑
    classEdit (item) {
      this.classModal = true
      this.modalType = 2
      this.classTitle = '编辑标准'
      this.classModelAdd.parts = item.dicTypeName
      this.classModelAdd.coding = item.dicTypeCode
      this.classModelAdd.id = item.id
    },
    // 查看
    viewData (row) {
      this.classModal = true
      this.modalType = 3
      this.classTitle = '查看标准'
      this.classModelAdd.parts = row.dicTypeName
      this.classModelAdd.coding = row.dicTypeCode
      this.classModelAdd.id = row.id
    },
    // 删除
    classDel (id) {
      this.handleSelectAll(false)
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.delete('sys/dictype/deleteArr', {
            ids: id
          }, {
            _this: this
          }, res => {
            this.selectClass()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 批量删除
    classBatchDel () {
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
              this.selectClass()
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
      this.selectClass()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectClass()
    },
    // 加载表格
    selectClass () {
      this.$http.get('sys/dictype/page', {
        pageNo: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'FDFDFDVFTGR'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.classData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveClass () {
      let data = {
        dicTypeName: this.classModelAdd.parts,
        dicTypeCode: this.classModelAdd.coding,
        dicId: 'FDFDFDVFTGR'
      }
      if (this.modalType === 1) {
        this.$http.postData('sys/dictype/create', data, {
          _this: this
        }, res => {
          this.selectClass()
        }, e => {

        })
      } else if (this.modalType === 2) {
        this.$http.putData('sys/dictype', {
          dicTypeName: this.classModelAdd.parts,
          dicTypeCode: this.classModelAdd.coding,
          dicId: 'FDFDFDVFTGR',
          id: this.classModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectClass()
        }, e => {

        })
      }
    }
  },
  mounted () {
    this.selectClass()
  }
}
</script>

<style lang="less">
   #national-area{}
</style>
