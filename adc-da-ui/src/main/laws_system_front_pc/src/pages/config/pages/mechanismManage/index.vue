<!-- 机构管理 -->
<template>
  <div id="mechanism-manage">
    <loading :loading="treeModalLoading">正在保存...</loading>
    <div class="mechanism-manage-left">
      <!-- 组织机构图 -->
      <ul id="treeDemo" class="ztree" v-show="zNodes.length > 0"></ul>
      <loading :loading="treeLoading">{{ treeLoadingTips }}</loading>
      <Modal
        class="tree-modal"
        v-model="view.treeView"
        :title="treeTitle"
        :loading="treeLoading"
        @on-ok="treeOk"
        @on-cancel="treeCancel">
        <Form ref="treeForm" :model="treeForm" :rules="treeFormRules" class="label-input-form">
          <FormItem label="所属上级" prop="pNodeName">
            <Input v-model="treeForm.pNodeName" disabled />
          </FormItem>
          <FormItem label="部门名称" prop="orgName">
            <Input v-model="treeForm.orgName" placeholder="请输入部门名称" clearable></Input>
          </FormItem>
          <FormItem label="部门简称" prop="shotName">
            <Input v-model="treeForm.shotName" placeholder="请输入部门简称" clearable />
          </FormItem>
          <FormItem label="部门简介" prop="remarks">
            <Input v-model="treeForm.remarks" type="textarea" placeholder="请输入部门描述" clearable />
          </FormItem>
        </Form>
      </Modal>
      <hasNoData v-show="zNodes.length === 0" pClass="mechanism-manage-left" tips="组织获取失败"></hasNoData>
    </div>
    <div class="mechanism-manage-right">
      <Form ref="mechanismSearch" :model="mechanismSearch" inline class="label-input-form mechanism-manage-right-search" :label-width="60">
        <FormItem prop="type" label="状态">
          <Select v-model="mechanismSearch.type" placeholder="状态">
            <Option v-for="opt in mechanismSearch.typeOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="userName" label="用户名">
          <Input v-model="mechanismSearch.userName" placeholder="用户名" />
        </FormItem>
        <FormItem prop="roleName" label="角色">
          <Select v-model="mechanismSearch.roleName" placeholder="角色">
            <Option v-for="opt in mechanismSearch.roleOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="state" label="用户状态">
          <Select v-model="mechanismSearch.state" placeholder="角色">
            <Option v-for="opt in mechanismSearch.stateOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
          </Select>
        </FormItem>
        <Button type="primary" icon="ios-search"></Button>
        <Button type="error" class="show-lg rt">批量删除</Button>
        <Button type="primary" class="show-lg rt" style="margin-right:5px">设置角色</Button>
        <Dropdown class="show-md rt">
          <Button type="primary">
            操作
            <Icon type="ios-arrow-down"></Icon>
          </Button>
          <DropdownMenu slot="list">
            <DropdownItem>设置角色</DropdownItem>
            <DropdownItem>批量删除</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </Form>
      <Divider></Divider>
      <div class="content">
        <loading :loading="loading">正在获取用户列表</loading>
        <Table border ref="selection" :height="tableHeight" :columns="deptEmpColumns" :data="deptEmpData" v-if="deptEmpData.length > 0"></Table>
        <hasNoData pClass="content" tips="未获取到组织机构" v-else></hasNoData>
      </div>
      <pagination :total="total"></pagination>
    </div>
  </div>
</template>

<script>
import 'zTree/js/jquery.ztree.core.js'
import 'zTree/js/jquery.ztree.excheck.js'
import 'zTree/js/jquery.ztree.exedit.js'
export default {
  name: 'mechanism-manage',
  data () {
    return {
      // 搜索组
      mechanismSearch: {
        type: '',
        userName: '',
        roleName: '',
        state: '',
        typeOptions: [
          {
            label: '类型1',
            value: 1
          }, {
            label: '类型2',
            value: 2
          }
        ],
        roleOptions: [
          {
            label: '管理员',
            value: 1
          },
          {
            label: '普通用户',
            value: 2
          }
        ],
        stateOptions: [
          {
            label: '已启用',
            value: 1
          },
          {
            label: '已停用',
            value: 2
          }
        ]
      },
      // 显示条件
      view: {
        treeView: false,
        treeFlag: 1, // 1新增 2编辑
        treeLoadingFlag: 1 // 获取为1 操作为2
      },
      zTree: '',
      zNodes: [],
      setting: '',
      // 组织机构表单
      treeForm: {
        id: '',
        orgName: '',
        pNodeName: '',
        pId: '',
        remarks: '',
        shotName: ''
      },
      // 组织机构表单规则
      treeFormRules: {
        pNodeName: [
          { required: true, message: '上级部门不能为空', trigger: 'blur' }
        ],
        orgName: [
          { required: true, message: '部门名称不能为空', trigger: 'blur' }
        ],
        shotName: [
          { required: true, message: '部门简称不能为空', trigger: 'blur' }
        ]
      },
      deptEmpColumns: [
        {
          type: 'selection',
          width: 60,
          align: 'center',
          fixed: 'left'
        },
        {
          title: '组织机构类型',
          key: 'orgName',
          width: 150,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.orgName
              }
            }, params.row.orgName)
          }
        },
        {
          title: '用户名称',
          key: 'uname',
          width: 100,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.uname
              }
            }, params.row.uname)
          }
        },
        {
          title: '账号',
          key: 'account',
          width: 120,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.account
              }
            }, params.row.account)
          }
        },
        {
          title: '工号',
          key: 'workNum',
          align: 'center',
          width: 100,
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.workNum
              }
            }, params.row.workNum)
          }
        },
        {
          title: '角色名称',
          key: 'roleName',
          width: 100,
          align: 'center'
        },
        {
          title: '部门',
          key: 'orgName',
          width: 140,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.orgName
              }
            }, params.row.orgName)
          }
        },
        {
          title: '邮箱',
          key: 'email',
          width: 130,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.email
              }
            }, params.row.email)
          }
        },
        {
          title: '电话',
          key: 'officePhone',
          width: 100,
          align: 'center'
        },
        {
          title: '手机',
          key: 'mobilePhone',
          width: 100,
          align: 'center'
        },
        {
          title: '传真',
          key: 'fax-address',
          width: 100,
          align: 'center'
        },
        {
          title: '通讯地址',
          key: 'address',
          width: 150,
          align: 'center',
          render: (h, params) => {
            return h('div', {
              class: {
                'text-overflow-hidden': true
              },
              attrs: {
                title: params.row.address
              }
            }, params.row.address)
          }
        },
        {
          title: '用户状态',
          key: 'disableFlag',
          width: 150,
          align: 'center',
          render: (h, params) => {
            if (params.row.disableFlag === 0) {
              return h('Tag', {
                props: {
                  type: 'dot',
                  color: 'primary',
                  name: '已启用'
                }
              }, '已启用')
            } else {
              return h('Tag', {
                props: {
                  type: 'dot',
                  name: '已停用'
                }
              })
            }
          }
        },
        {
          title: '修改时间',
          key: 'modifyTime',
          width: 150,
          align: 'center'
        },
        {
          title: '操作',
          key: 'Action',
          width: 150,
          align: 'center',
          fixed: 'right',
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
                  click: () => {}
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {}
                }
              }, '删除')
            ])
          }
        }
      ],
      deptEmpData: [], // 组织下的员工列表
      loading: false,
      treeModalLoading: false, // 新增、删除弹窗loading
      treeLoading: false,
      orgId: '', // 组织机构id
      page: 1,
      rows: 10,
      total: 0,
      tableHeight: 600
    }
  },
  methods: {
    /**
     * @description: 获取组织结构树结构
     * @author: chenxiaoxi
     * @date: 2018-09-21 09:27:27
     */
    getTree () {
      this.$http.get('sys/org/getTree', {}, {
        _this: this,
        loading: 'treeLoading'
      }, res => {
        if (res.ok) {
          this.orgId = ''
          let zNodes = []
          for (let i = 0; i < res.data.length; i++) {
            let zObj = {}
            zObj.id = res.data[i].id
            zObj.pId = res.data[i].pId
            zObj.name = res.data[i].orgName
            zObj.shotName = res.data[i].shotName
            zObj.remarks = res.data[i].remarks
            zObj.isParent = true
            if (res.data[i].pId === null && this.orgId === '') {
              this.orgId = res.data[i].id
              zObj.open = true
              zNodes[i] = zObj
            } else {
              zNodes[i] = zObj
            }
          }
          zNodes.push({
            id: '404',
            pId: null,
            name: '未分配人员',
            shotName: '',
            remarks: '',
            isParent: true
          })
          this.zNodes = zNodes
        }
      }, e => {})
    },
    /**
     * @description: 组织结构树弹窗确认
     * @author: chenxiaoxi
     * @date: 2018-09-21 09:17:04
     */
    treeOk () {
      this.$refs['treeForm'].validate((valid) => {
        if (valid) {
          this.$http.ajax(this.view.treeFlag === 1 ? 'post' : 'put', 'sys/org', this.treeForm, {
            _this: this,
            loading: 'treeModalLoading'
          }, res => {
            this.getTree()
            this.treeFormReset()
            this.view.treeFlag = 1
          }, e => {})
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    /**
     * @description: 组织结构树取消
     * @author: chenxiaoxi
     * @date: 2018-09-21 09:17:27
     */
    treeCancel () {
      this.treeFormReset()
    },
    /**
     * @description: 组织结构表单重置
     * @author: chenxiaoxi
     * @date: 2018-09-21 09:17:36
     */
    treeFormReset () {
      this.$refs.treeForm.resetFields()
    },
    /**
     * @description: 根据组织机构id查看员工列表
     * @author: chenxiaoxi
     * @date: 2018-09-25 15:59:40
     */
    findByOrg () {
      this.deptEmpData = []
      this.total = 0
      this.$http.get('sys/user/findByOrg', {
        id: this.orgId,
        page: this.page,
        rows: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.deptEmpData = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  computed: {
    treeTitle () {
      return this.view.treeFlag === 1 ? '组织机构节点新增' : '组织机构节点维护'
    },
    treeLoadingTips () {
      return this.view.treeLoadingFlag === 1 ? '组织机构获取中...' : '节点保存中...'
    }
  },
  watch: {
    zNodes: {
      deep: true,
      handler () {
        this.$nextTick(() => {
          $.fn.zTree.init($('#treeDemo'), this.setting, this.zNodes)
        })
      }
    },
    // orgId发生变化查询部门下的用户信息
    orgId (val) {
      if (val !== '') {
        this.findByOrg()
      }
    }
  },
  mounted () {
    let _this = this
    this.$nextTick(() => {
      let tableHeight = $('.content').css('height')
      let height = parseInt(tableHeight.substring(0, tableHeight.indexOf('p'))) - 130
      this.tableHeight = height
      window.onresize = function () {
        let tableHeight = $('.content').css('height')
        let height = parseInt(tableHeight.substring(0, tableHeight.indexOf('p'))) - 130
        _this.tableHeight = height
      }
    })
    // 获取组织结构树
    this.getTree()
    var setting = {
      view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
      },
      edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: showRemoveBtn,
        showRenameBtn: showRenameBtn,
        renameTitle: '节点名称维护',
        removeTitle: '节点删除'
      },
      data: {
        simpleData: {
          enable: true
        }
      },
      callback: {
        beforeDrag: beforeDrag,
        beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        onClick: handleClick
      }
    }
    this.setting = setting
    let className = 'dark'
    function beforeDrag (treeId, treeNodes) {
      return false
    }
    function beforeEditName (treeId, treeNode) {
      let pNode = treeNode.getParentNode()
      className = (className === 'dark' ? '' : 'dark')
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      zTree.selectNode(treeNode)
      _this.view.treeView = true
      _this.view.treeFlag = 2
      _this.treeForm.pNodeName = pNode.name
      _this.treeForm.pId = treeNode.pId
      _this.treeForm.orgName = treeNode.name
      _this.treeForm.shotName = treeNode.shotName
      _this.treeForm.remarks = treeNode.remarks
      _this.treeForm.id = treeNode.id
      return false
    }

    /**
     * @description: 树节点点击
     * @author: chenxiaoxi
     * @date: 2018-09-25 16:13:25
     */
    function handleClick (event, treeId, treeNode) {
      _this.orgId = treeNode.id
    }
    /**
     * @description: 树节点删除
     * @author: chenxiaoxi
     * @date: 2018-09-25 14:05:25
     */
    function beforeRemove (treeId, treeNode) {
      className = (className === 'dark' ? '' : 'dark')
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      zTree.selectNode(treeNode)
      _this.$Modal.confirm({
        title: '提示',
        content: '您是否要删除' + ' <span>' + treeNode.name + '</span> ?',
        onOk: () => {
          _this.treeFlag = 2
          _this.$http.delete('sys/org', {
            id: treeNode.id
          }, {
            _this: _this,
            loading: 'treeLoading'
          }, res => {
            _this.treeFlag = 1
            _this.getTree()
          }, e => {})
        },
        onCancel: () => {}
      })
      return false
    }
    // 是否显示移除按钮
    function showRemoveBtn (treeId, treeNode) {
      return treeNode.pId !== null
    }
    function showRenameBtn (treeId, treeNode) {
      return treeNode.pId !== null
    }
    function addHoverDom (treeId, treeNode) {
      var sObj = $('#' + treeNode.tId + '_span')
      if (treeNode.editNameFlag || $('#addBtn_' + treeNode.tId).length > 0 || treeNode.id === '404') return
      var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='节点新增' onfocus='this.blur();'></span>"
      sObj.after(addStr)
      var btn = $('#addBtn_' + treeNode.tId)
      if (btn) {
        btn.bind('click', function () {
          className = (className === 'dark' ? '' : 'dark')
          var zTree = $.fn.zTree.getZTreeObj('treeDemo')
          this.zTree = zTree
          zTree.selectNode(treeNode)
          _this.view.treeView = true
          _this.view.treeFlag = 1
          _this.treeForm.pId = treeNode.id
          _this.treeForm.pNodeName = treeNode.name
          _this.treeForm.orgName = treeNode.orgName
          return false
        })
      }
    };
    function removeHoverDom (treeId, treeNode) {
      $('#addBtn_' + treeNode.tId).unbind().remove()
    }
  }
}
</script>

<style lang="less">
  @import '../../../../assets/zTree/css/zTreeStyle/zTreeStyle.css';
  @import '~styles/mixins';
  #mechanism-manage{
    .flex();
    background: #FFF;
    .mechanism-manage-left{
      width: 240px;
      height: 100%;
      border-right: 1px solid #DDD;
      position: relative;
      .ivu-tree{
        width: calc(~'100% - 8px');
        height: calc(~'100% - 2px');
        position: absolute;
        top: 2px;
        left: 4px;
        overflow-x: auto;
      }
    }
    .mechanism-manage-right{
      width: calc(~'100% - 240px');
      display: inline-block;
      height: 100%;
      padding: 10px;
      position: relative;
      .content{
        height: calc(~'100% - 110px');
        background: #CCC;
        .hasNoData{
          background: #FFF;
          width: 100%;
          height: 100%;
          .no-data-icon{
            width: 150px;
          }
        }
      }
      .ivu-table-overflowX{
        overflow-x: auto;
      }
      .pagination{
        .ivu-divider{
          margin-bottom: 0;
        }
      }
    }
    .ivu-tree-children{
      .ivu-tree-arrow{
        margin-right: 5px;
      }
    }
    .content .btn-group{
      margin-bottom: 0.5rem;
    }
    .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    .mechanism-manage-right-search{
      .ivu-form-item-label{
        padding: 0;
      }
      .ivu-form-item-content{
        width: calc(~'100% - 60px');
        .ivu-select{
          width: 100%;
        }
      }
      @media screen and (max-width: 1699px) {
        .ivu-form-item{
          width: 165px !important;
          margin-right: 2px !important;
        }
      }
      .ivu-form-item{
        width: 200px;
        margin-right: 5px;
        margin-bottom: 0;
      }
      .ivu-select-selection{
        min-width: auto;
      }
      .ivu-btn{
        position: relative;
        top: 1px;
      }
    }
    .ivu-divider-horizontal{
      margin: 8px 0;
    }
    .ivu-table-body{
      overflow-x: auto;
    }
    .ivu-table-tip{
      width: 100%;
      height: calc(~'100% - 41px');
      table{
        display: block;
        tbody,tr,td{
          display: block;
          width: 100% !important;
          height: 100%;
        }
        td{
          .flex();
          justify-content: center;
          align-items: center;
        }
      }
    }
    .hasNoData{
      background: #FFF;
      .no-data-icon{
        width: 85px;
      }
    }
    .ivu-table-fixed,
    .ivu-table-fixed-right{
      height: 100%;
      .ivu-table-fixed-body{
        height: calc(~'100% - 41px');
      }
    }
  }
  .mechanism-tree-modal{
    .ivu-input-wrapper{
      width: 90%;
    }
  }
</style>
