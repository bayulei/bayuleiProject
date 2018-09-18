<!-- 资料中心模块配置 -->
<template>
  <div class="InformationCenterConfig">
      <div class="container">
        <div class="header">
          <Button type="info" @click="informationAdd">新增</Button>
          <Button type="error" style="margin-left: 15px">删除</Button>
          <!-- 显示模态框 -->
          <Modal v-model="informationModal" :title="informationTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"
                 @on-ok="saveInformation">
            <Form :model="informationModelAdd" label-position="right" :label-width="80">
              <input v-model="informationModelAdd.id" v-show="false">
              <FormItem label="模块类型">
                <Input v-model="informationModelAdd.moduleType" :style="{width:6+'rem'}" disabled></Input>
              </FormItem>
              <FormItem label="模块名称">
                <Input v-model="informationModelAdd.moduleName" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
              </FormItem>
            </Form>
          </Modal>
        </div>
        <div class="content">
          <loading :loading="loading">数据获取中</loading>
          <Table border ref="selection" :columns="informationTable" :data="informationData" @on-selection-change="handleSelectone">
          </Table>
          <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
        </div>
      </div>
  </div>
</template>
<script>
export default {
  name: 'informationCenterConfig',
  data () {
    return {
      informationModal: false,
      informationTitle: '',
      modalType: '',
      loading: false,
      total: 0,
      page: 1,
      rows: 10,
      informationModelAdd: {
        moduleType: '资料中心', // 模块类型
        moduleName: '' // 模块名称
      },
      informationTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '参数',
          key: 'moduleName',
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
                    this.informationEdit(params.row)
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
                    this.remove(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      informationData: []
    }
  },
  methods: {
    // 新增
    informationAdd () {
      this.informationModal = true
      this.informationTitle = '新增参数'
      this.modalType = 1
      this.informationModelAdd.moduleName = ''
    },
    // 编辑
    informationEdit (row) {
      this.informationModal = true
      this.informationTitle = '编辑参数'
      this.modalType = 2
      this.informationModelAdd.moduleName = row.moduleName
      this.informationModelAdd.id = row.id
    },
    // 查看
    viewData (row) {
      this.informationModal = true
      this.modalType = 3
      this.informationTitle = '查看标准'
      this.informationModelAdd.moduleName = row.moduleName
      this.informationModelAdd.id = row.id
    },
    // 删除
    remove (id) {
      this.handleSelectAll(false)
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.delete('lawss/msgModule', {
            ids: id
          }, {
            _this: this
          }, res => {
            this.selectInformation()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 批量删除
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
    //  全选
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    // 非全选
    handleSelectone (row) {
      this.selectNum = row
    },
    pageChange (page) {
      this.page = page
      this.selectInformation()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectInformation()
    },
    // 提交
    saveInformation () {
      let data = {
        moduleType: '资料中心',
        moduleName: this.informationModelAdd.moduleName
      }
      if (this.modalType === 1) {
        this.$http.postData('lawss/msgModule', data, {
          _this: this
        }, res => {
          this.selectInformation()
        }, e => {
        })
      } else {
        this.$http.putData('lawss/msgModule', {
          moduleType: '资料中心',
          moduleName: this.informationModelAdd.moduleName,
          id: this.informationModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectInformation()
        }, e => {
        })
      }
    },
    // 加载表格
    selectInformation () {
      this.$http.get('lawss/msgModule/page', {
        pageNo: this.page,
        pageSize: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.informationData = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  mounted () {
    this.selectInformation()
  }
}
</script>

<style lang="less">
  .InformationCenterConfig{
    display: flex;
    background: #FFF;
    .container{
      width: 100%;
      margin: 1rem;
    }
    .header{
      margin-bottom:0.7rem;
    }
    .content{
      width: 100%;
      height: calc(100% - 20px);
      overflow-x: hidden;
      overflow-y: auto;
    }
  }
  .hide-modal-footer{
    .ivu-modal-footer{
      display: none;
    }
  }
  .ivu-modal-confirm .ivu-modal-confirm-footer{
    display: block;
  }
</style>
