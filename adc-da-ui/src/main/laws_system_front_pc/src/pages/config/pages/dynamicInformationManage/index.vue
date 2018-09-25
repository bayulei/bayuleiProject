<!-- 动态信息管理 -->
<template>
  <div class="dynamic-information-manage">
        <table-tools-bar>
          <div slot="left">
            <label-select v-model="search.msgType" :options="search.msgTypeOptions" placeholder="按模块查找" label="消息模块"></label-select>
            <label-input v-model="search.msgTitle" placeholder="请输入消息标题" label="消息标题"></label-input>
            <Button type="primary" icon="ios-search" @click="searchMSGPage" :loading="search.searching" title="搜索"></Button>
            <Button type="primary" title="重置" @click="resetSearch">重置</Button>
          </div>
          <div slot="right">
            <Button type="primary" icon="ios-add" title="新增" @click="openAddMSGModal">新增</Button>
            <Button type="error" icon="md-trash" title="批量删除" @click="batchMSGDel" >批量删除</Button>
          </div>
        </table-tools-bar>
<!--        <Button type="info" @click="dynamicAdd">新增</Button>
        <Button type="success" style="margin-left: 15px" @click="dynamicEdit">编辑</Button>
        <Button type="warning" style="margin-left: 15px">删除</Button>-->
        <!-- 显示模态框 -->
      <loading :loading="loading">数据获取中</loading>
      <Table border :columns="dynamicTable " :data="msgList" @on-selection-change="handleRowChange" ></Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
  </div>
</template>

<script>
export default {
  name: 'dynamic-information-manage',
  data () {
    return {
      search: {
        msgType: '',
        msgTitle: '',
        msgTypeOptions: [{
          label: '国内动态',
          value: 'INLAND'
        }, {
          label: '国外动态',
          value: 'FOREIGN'
        }, {
          label: '资料中心',
          value: 'RESOURCE'
        }]
      },
      loading: false,
      // 总数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 单页数量
      pageSize: 10,
      // 选中消息列表
      clickMsgList: [],
      dynamicTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '消息模块',
          key: 'msgType',
          align: 'center',
          render: (h, params) => {
            // let _this = this
            let texts = ''
            switch (params.row.msgType) {
              case 'INLAND' :
                texts = '国内动态'
                break
              case 'FOREIGN' :
                texts = '国外动态'
                break
              case 'RESOURCE' :
                texts = '资料中心'
                break
            }
            return h('div', {
              props: {}
            }, texts)
          }
        },
        {
          title: '标题',
          key: 'title',
          align: 'center',
          width: 300
        },
        {
          title: '发布日期',
          key: 'pubTime',
          align: 'center'
        },
        {
          title: '创建人',
          key: 'pubUserName',
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
                    this.editMsg(params.index)
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
                    this.showMsgPage(params.index)
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
                    this.delMsgInfo(params.index)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 消息列表
      msgList: []
    }
  },
  methods: {
    // 检索查询分页
    searchMSGPage () {
      this.$http.get('lawss/msgDynamicInfo/page',
        {
          pageNo: this.pageNo,
          pageSize: this.pageSize,
          msgType: this.search.msgType,
          msgTitle: this.search.msgTitle
        },
        {_this: this, loading: this.loading},
        res => {
          console.log(res)
          if (res.ok) {
            this.msgList = res.data.list
          }
        })
    },
    // 新增消息
    openAddMSGModal () {
      this.$router.push('/dynamicInformationManage/dynamicInfomationPage')
    },
    // 批量删除
    batchMSGDel () {
      // 此处获取选中的数据
      if (this.clickMsgList.length > 0) {
        let msgIds = []
        for (let i = 0; i < this.clickMsgList.length; i++) {
          let msgId = this.clickMsgList[i].id
          msgIds.push(msgId)
        }
        let msgIdsStr = msgIds.join(',')
        console.log(msgIdsStr)
        this.$Modal.confirm({
          title: '请选择',
          content: '确定删除这些数据?',
          onOk: () => {
            this.$http.delete('lawss/msgDynamicInfo/' + msgIdsStr, {},
              { _this: this
              }, res => {
                if (res.ok) {
                  this.executeSuccess('删除成功')
                  this.searchMSGPage()
                } else {
                  this.executeError('删除失败! 失败原因:' + res.message)
                }
              })
          }})
      } else {
        this.executeError('未选择动态，请选择')
      }
    },
    // 重置检索条件
    resetSearch () {
      this.search.msgType = ''
      this.search.msgTitle = ''
    },
    // 编辑消息
    editMsg (index) {
      this.$router.push('/dynamicInformationManage/dynamicInfomationPage/' + this.msgList[index].id)
    },
    // 删除消息
    delMsgInfo (index) {
      console.log(this.msgList[index].id)
      this.$Modal.confirm({
        title: '请选择',
        content: '确定删除这些数据?',
        onOk: () => {
          this.$http.delete('lawss/msgDynamicInfo/' + this.msgList[index].id, {},
            { _this: this
            }, res => {
              if (res.ok) {
                this.executeSuccess('删除成功')
                this.searchMSGPage()
              } else {
                this.executeError('删除失败! 失败原因:' + res.message)
              }
            })
          this.$Modal.remove()
        }})
    },
    // 查看消息内容
    showMsgPage (index) {

    },
    handleRowChange (selection) {
      this.clickMsgList = selection
    },
    pageChange (page) {
      this.pageNo = page
      this.searchMSGPage()
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
      this.searchMSGPage()
    },
    // 成功弹框
    executeSuccess (message) {
      this.$Message.success(message)
    },
    // 失败弹框
    executeError (message) {
      this.$Message.error(message)
    }
  },
  mounted () {
    this.searchMSGPage()
  }
}
</script>

<style lang="less">
  .dynamic-information-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .container{
      width: 100%;
      margin: 1rem;
    }
    .header{
      margin-bottom:0.7rem;
    }
  }
  .hide-modal-footer {
    .ivu-modal-footer {
      display: none;
    }
  }
</style>
