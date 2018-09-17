<!-- 资料中心模块配置 -->
<template>
  <div class="InformationCenterConfig">
      <div class="container">
        <div class="header">
          <Button type="info" @click="informationAdd">新增</Button>
          <Button type="error" style="margin-left: 15px">删除</Button>
          <!-- 显示模态框 -->
          <Modal
            v-model="informationModal"
            :title="informationTitle">
            <div v-if="modalType === 1">
              <p>我是新增内容</p>
            </div>
            <div v-else>
              <p>我是编辑内容</p>
            </div>
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
      informationTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '参数',
          key: 'parameter',
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
                    this.categoryDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      informationData: [{
        parameter: '通知'
      }]
    }
  },
  methods: {
    // 新增
    informationAdd () {
      this.informationModal = true
      this.informationTitle = '新增参数'
      this.modalType = 1
    },
    // 编辑
    informationEdit (row) {
      this.informationModal = true
      this.informationTitle = '编辑参数'
      this.modalType = 2
    },
    show (index) {
      this.$Modal.info({
        title: '查看参数',
        content: `参数：${this.data1[index].parameter}`
      })
    },
    remove (index) {
      this.data1.splice(index, 1)
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
    // 加载表格
    selectInformation () {
      this.$http.get('sys/lawss/msgModule/page', {
        pageNo: this.page,
        pageSize: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.categoryData = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  mounted () {
    this.selectInformation()
  }
}
</script>

<style lang="less" scoped>
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
  }
</style>
