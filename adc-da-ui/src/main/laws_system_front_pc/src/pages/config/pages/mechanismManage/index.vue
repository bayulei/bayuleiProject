<!-- 机构管理 -->
<template>
  <div id="mechanism-manage">
    <div class="mechanism-manage-left">
      <ul id="treeDemo" class="ztree"></ul>
    </div>
    <div class="mechanism-manage-right">
      <table-tools-bar>
        <div slot="left">
          <label-select v-model="mechanismSearch.type" :options="mechanismSearch.typeOptions" placeholder="请选择" label="状态"></label-select>
          <label-input v-model="mechanismSearch.userName" placeholder="请输入用户名" label="用户名称"></label-input>
          <label-select v-model="mechanismSearch.roleName" :options="mechanismSearch.roleOptions" placeholder="按角色查找" label="角色名称"></label-select>
          <label-select v-model="mechanismSearch.state" :options="mechanismSearch.stateOptions" placeholder="按状态查找" label="用户状态"></label-select>
        </div>
        <div slot="right">
          <Button type="info">查询</Button>
          <Button type="info">查询</Button>
        </div>
      </table-tools-bar>
      <div class="content">
        <div class="btn-group">
          <Button type="success" >增加</Button>
          <Button type="warning" style="margin-left: 0.3rem">编辑</Button>
          <Button type="error"  style="margin-left: 0.3rem">配置角色信息</Button>
        </div>
      </div>
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
      }
    }
  },
  methods: {},
  computed: {},
  mounted () {
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

    var zNodes = [
      {id: 1, pId: 0, name: '父节点 1', open: true},
      {id: 11, pId: 1, name: '叶子节点 1-1'},
      {id: 12, pId: 1, name: '叶子节点 1-2'},
      {id: 13, pId: 1, name: '叶子节点 1-3'},
      {id: 2, pId: 0, name: '父节点 2', open: true},
      {id: 21, pId: 2, name: '叶子节点 2-1'},
      {id: 22, pId: 2, name: '叶子节点 2-2'},
      {id: 23, pId: 2, name: '叶子节点 2-3'},
      {id: 3, pId: 0, name: '父节点 3', open: true},
      {id: 31, pId: 3, name: '叶子节点 3-1'},
      {id: 32, pId: 3, name: '叶子节点 3-2'},
      {id: 33, pId: 3, name: '叶子节点 3-3'}
    ]
    let log = ''
    let className = 'dark'
    function beforeDrag (treeId, treeNodes) {
      return false
    }
    function beforeEditName (treeId, treeNode) {
      className = (className === 'dark' ? '' : 'dark')
      showLog('[ ' + getTime() + ' beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      zTree.selectNode(treeNode)
      setTimeout(function () {
        if (confirm('进入节点 -- ' + treeNode.name + ' 的编辑状态吗？')) {
          setTimeout(function () {
            zTree.editName(treeNode)
          }, 0)
        }
      }, 0)
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
      if (newName.length == 0) {
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
      return !treeNode.isFirstNode
    }
    function showRenameBtn (treeId, treeNode) {
      return !treeNode.isLastNode
    }
    function showLog (str) {
      if (!log) log = $('#log')
      log.append("<li class='" + className + "'>" + str + '</li>')
      if (log.children('li').length > 8) {
        log.get(0).removeChild(log.children('li')[0])
      }
    }
    function getTime () {
      let now = new Date(),
        h = now.getHours(),
        m = now.getMinutes(),
        s = now.getSeconds(),
        ms = now.getMilliseconds()
      return (h + ':' + m + ':' + s + ' ' + ms)
    }

    var newCount = 1
    function addHoverDom (treeId, treeNode) {
      var sObj = $('#' + treeNode.tId + '_span')
      if (treeNode.editNameFlag || $('#addBtn_' + treeNode.tId).length > 0) return
      var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='add node' onfocus='this.blur();'></span>"
      sObj.after(addStr)
      var btn = $('#addBtn_' + treeNode.tId)
      if (btn) {
        btn.bind('click', function () {
          var zTree = $.fn.zTree.getZTreeObj('treeDemo')
          zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: 'new node' + (newCount++)})
          return false
        })
      }
    };
    function removeHoverDom (treeId, treeNode) {
      $('#addBtn_' + treeNode.tId).unbind().remove()
    };
    function selectAll () {
      var zTree = $.fn.zTree.getZTreeObj('treeDemo')
      zTree.setting.edit.editNameSelectAll = $('#selectAll').attr('checked')
    }
    this.$nextTick(() => {
      $(document).ready(function () {
        $.fn.zTree.init($('#treeDemo'), setting, zNodes)
        $('#selectAll').bind('click', selectAll)
      })
    })
  }
}
</script>

<style lang="less">
  @import '../../../../assets/zTree/css/zTreeStyle/zTreeStyle.css';
  #mechanism-manage{
    display: flex;
    background: #FFF;
    .mechanism-manage-left{
      width: 6rem;
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
      flex: 1;
      height: 100%;
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
  }
  .mechanism-tree-modal{
    .ivu-input-wrapper{
      width: 90%;
    }
  }
</style>
