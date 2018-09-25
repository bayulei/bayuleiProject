<!-- 机构管理 -->
<template>
  <div id="mechanism-manage">
    <div class="mechanism-manage-left">
      <!-- 组织机构图 -->
      <ul id="treeDemo" class="ztree"></ul>
      <Modal
        class="tree-modal"
        v-model="view.treeView"
        :title="treeTitle"
        @on-ok="treeOk"
        @on-cancel="treeCancel">
        <Form ref="treeForm" :model="treeForm" :rules="treeFormRules" class="label-input-form">
          <FormItem label="父节点" prop="pNode">
            <Input v-model="treeForm.pNode" disabled />
          </FormItem>
          <FormItem label="节点名称" prop="nodeName">
            <Input v-model="treeForm.nodeName" placeholder="请输入节点名称" clearable></Input>
          </FormItem>
        </Form>
      </Modal>
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
        <loading :loading="loading">数据加载中</loading>
        <Table border ref="selection" :columns="deptEmpColumns" :data="deptEmpData"></Table>
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
        treeFlag: 1 // 1新增 2编辑
      },
      zTree: '',
      zNodes: [],
      setting: '',
      // 组织机构表单
      treeForm: {
        orgName: '',
        pNodeName: '',
        pId: '',
        remarks: '',
        shotName: ''
      },
      // 组织机构表单规则
      treeFormRules: {},
      total: 0,
      deptEmpColumns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: 'Name',
          key: 'name'
        },
        {
          title: 'Age',
          key: 'age'
        },
        {
          title: 'Address',
          key: 'address'
        }
      ],
      deptEmpData: [],
      loading: false
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
        _this: this
      }, res => {
        if (res.ok) {
          let zNodes = []
          for (let i = 0; i < res.data.length; i++) {
            let zObj = {}
            zObj.id = res.data[i].id
            zObj.pId = res.data[i].pId
            zObj.name = res.data[i].orgName
            zObj.shotName = res.data[i].shotName
            zObj.remarks = res.data[i].remarks
            if (res.data[i].pId == null) {
              zObj.isParent = true
            }
            zNodes[i] = zObj
          }
          this.zNodes = zNodes
        }
      }, e => {})
    },
    /**
     * @description: 组织结构树弹窗确认
     * @author: chenxiaoxi
     * @date: 2018-09-21 09:17:04
     */
    treeOk () {},
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
     * @description: zTree方法
     * @author: chenxiaoxi
     * @date: 2018-09-21 15:37:30
     */
    selectAll () {}
  },
  computed: {
    treeTitle () {
      return this.view.treeFlag === 1 ? '组织机构节点新增' : '组织机构节点维护'
    }
  },
  watch: {
    zNodes: {
      deep: true,
      handler () {
        this.$nextTick(() => {
          $.fn.zTree.init($('#treeDemo'), this.setting, this.zNodes)
          $('#selectAll').bind('click', this.selectAll)
        })
      }
    }
  },
  mounted () {
    let _this = this
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
        showRenameBtn: showRenameBtn
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
        beforeRename: beforeRename,
        onRemove: onRemove,
        onRename: onRename
      }
    }
    this.setting = setting
    let log = ''
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
      _this.treeForm.pNode = pNode.name
      _this.treeForm.pId = treeNode.pId
      _this.treeForm.orgName = treeNode.name
      _this.treeForm.shotName = treeNode.shotName
      _this.treeForm.remarks = treeNode.remarks
      return false
    }
    function beforeRemove (treeId, treeNode) {
      className = (className === 'dark' ? '' : 'dark')
      showLog('[ ' + getTime() + ' beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      zTree.selectNode(treeNode)
      return confirm('确认删除 节点 -- ' + treeNode.name + ' 吗？')
    }
    function onRemove (e, treeId, treeNode) {
      showLog('[ ' + getTime() + ' onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
    }
    function beforeRename (treeId, treeNode, newName, isCancel) {
      className = (className === 'dark' ? '' : 'dark')
      showLog((isCancel ? "<span style='color:red'>" : '') + '[ ' + getTime() + ' beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name + (isCancel ? '</span>' : ''))
      if (newName.length === 0) {
        setTimeout(function () {
          var zTree = $.fn.zTree.getZTreeObj('treeDemo')
          zTree.cancelEditName()
          alert('节点名称不能为空.')
        }, 0)
        return false
      }
      return true
    }
    function onRename (e, treeId, treeNode, isCancel) {
      showLog((isCancel ? "<span style='color:red'>" : '') + '[ ' + getTime() + ' onRename ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name + (isCancel ? '</span>' : ''))
    }
    function showRemoveBtn (treeId, treeNode) {
      return treeNode.pId !== null
    }
    function showRenameBtn (treeId, treeNode) {
      return treeNode.pId !== null
    }
    function showLog (str) {
      if (!log) log = $('#log')
      log.append("<li class='" + className + "'>" + str + '</li>')
      if (log.children('li').length > 8) {
        log.get(0).removeChild(log.children('li')[0])
      }
    }
    function getTime () {
      let now = new Date()
      let h = now.getHours()
      let m = now.getMinutes()
      let s = now.getSeconds()
      let ms = now.getMilliseconds()
      return (h + ':' + m + ':' + s + ' ' + ms)
    }

    // var newCount = 1
    function addHoverDom (treeId, treeNode) {
      var sObj = $('#' + treeNode.tId + '_span')
      if (treeNode.editNameFlag || $('#addBtn_' + treeNode.tId).length > 0) return
      var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='add node' onfocus='this.blur();'></span>"
      sObj.after(addStr)
      var btn = $('#addBtn_' + treeNode.tId)
      if (btn) {
        btn.bind('click', function () {
          console.log(treeNode)
          className = (className === 'dark' ? '' : 'dark')
          var zTree = $.fn.zTree.getZTreeObj('treeDemo')
          this.zTree = zTree
          zTree.selectNode(treeNode)
          _this.view.treeView = true
          _this.view.treeFlag = 1
          _this.treeForm.pId = treeNode.id
          _this.treeForm.orgName = treeNode.orgName
          return false
        })
      }
    };
    function removeHoverDom (treeId, treeNode) {
      $('#addBtn_' + treeNode.tId).unbind().remove()
    };
    function selectAll () {
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      this.zTree = zTree
      zTree.setting.edit.editNameSelectAll = $('#selectAll').attr('checked')
    }
    this.selectAll = selectAll
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
  }
  .mechanism-tree-modal{
    .ivu-input-wrapper{
      width: 90%;
    }
  }
</style>
