<!-- 用户管理 -->
<template>
  <div id="user-manage">
    <table-tools-bar>
      <div slot="left">
        <label-select v-model="search.type" :options="search.typeOptions" placeholder="请选择类型" label="类型"></label-select>
        <label-input v-model="search.userName" placeholder="请输入用户名" label="用户名称"></label-input>
        <label-select v-model="search.roleName" :options="search.roleOptions" placeholder="请选择角色" label="角色名称"></label-select>
        <label-select v-model="search.userStatus" :options="search.statusOptions" placeholder="请选择角色" label="用户状态"></label-select>
      </div>
      <div slot="right">
      </div>
    </table-tools-bar>
    <div class="content">
      <div class="btn-group">
        <Button type="info" @click="userSelect">查询</Button>
        <Button type="success" @click="userReset">重置</Button>
      </div>
      <div class="btn-function-group">
        <Button type="error">新增</Button>
        <Button type="primary">设置角色</Button>
        <Button type="info">查看</Button>
        <Button type="warning">删除</Button>
      </div>
    </div>
  </div>
</template>

<script>
import tableToolsBar from 'pages/components/TableToolsBar'
export default {
  name: 'user-manage',
  data () {
    return {
      // 搜索框
      search: {
        type: '',
        userName: '',
        roleName: '',
        userStatus: '',
        typeOptions: [{
          label: '类型1',
          value: 1
        }, {
          label: '类型2',
          value: 2
        }],
        roleOptions: [{
          label: '管理员',
          value: 1
        }, {
          label: '普通用户',
          value: 2
        }],
        statusOptions: [{
          label: '状态1',
          value: 1
        }, {
          label: '状态2',
          value: 2
        }]
      }
    }
  },
  methods: {
    userSelect () {

    },
    userReset () {
      this.search.type = ''
      this.search.userName = ''
      this.search.roleName = ''
      this.search.userStatus = ''
    }
  },
  components: {
    tableToolsBar
  }
}
</script>

<style lang="less" scoped>
  #user-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .content .btn-group{
      float: right;
    }
    .content .btn-function-group{
      clear: both;
      float: left;
    }
  }
</style>
