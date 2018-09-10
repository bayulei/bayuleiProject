<!-- 用户管理 -->
<template>
  <div id="user-manage">
    <table-tools-bar>
      <div slot="left">
        <label-select v-model="search.type" :options="search.typeOptions" placeholder="请选择类型" label="类型"></label-select>
        <label-input v-model="search.userName" placeholder="请输入用户名" label="用户名称"></label-input>
        <label-select v-model="search.roleName" :options="search.roleOptions" placeholder="请选择角色" label="角色名称"></label-select>
        <!--<Form ref="searchForm" :model="search" inline>-->
          <!--<FormItem prop="type">-->
            <!--<Select v-model="search.type" placeholder="请选择类型">-->
              <!--<Option :value="opt.value" v-for="opt in search.typeOptions" :key="opt.value">{{ opt.label }}</Option>-->
              <!--<span slot="prepend">类型</span>-->
            <!--</Select>-->
          <!--</FormItem>-->
          <!--<FormItem prop="userName">-->
            <!--<Input v-model="search.userName" placeholder="请输入用户名" clearable>-->
              <!--<span slot="prepend">用户名</span>-->
            <!--</Input>-->
          <!--</FormItem>-->
        <!--</Form>-->
      </div>
      <div slot="right"></div>
    </table-tools-bar>
    <div class="content"></div>
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
        typeOptions: [{
          label: '类型1',
          value: 1
        }, {
          label: '类型2',
          value: 2
        }],
        userName: '',
        roleName: '',
        roleOptions: [{
          label: '管理员',
          value: 1
        }, {
          label: '普通用户',
          value: 2
        }]
      }
    }
  },
  methods: {},
  components: {
    tableToolsBar
  }
}
</script>

<style lang="less" scoped>
  #user-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
</style>
