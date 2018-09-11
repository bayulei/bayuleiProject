<!-- 角色管理 -->
<template>
  <div class="role-manage">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="roleSearch.roleName" placeholder="请输入选项" label="角色名称"></label-input>
        <label-select v-model="roleSearch.type" :options="roleSearch.typeOptions" placeholder="请输入" label="状态"></label-select>
      </div>
      <div slot="right">
        <Button type="info">查询</Button>
        <Button type="primary">重置</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <Button type="success" @click="categoryAdd">增加</Button>
      <Button type="warning" @click="categoryEdit">编辑</Button>
      <Button type="error">删除</Button>
      <loading :loading="loading">数据获取中</loading>
      <Table :columns="roleColumns" :data="userList" border ref="selection"></Table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'role-manage',
  data () {
    return {
      roleSearch: {
        roleName: '',
        type: '',
        typeOptions: [{
          label: '类型1',
          value: 1
        }, {
          label: '类型2',
          value: 2
        }]
      },
      roleColumns: [{
        type: 'selection',
        width: 60,
        align: 'center'
      }, {
        title: '角色名称',
        key: 'roleTitle',
        align: 'center'
      }, {
        title: '角色描述',
        key: 'roleCode',
        align: 'center'
      }, {
        title: '状态',
        key: 'state',
        align: 'center'
      }, {
        title: '类型',
        key: 'type',
        align: 'center'
      }, {
        title: '操作人',
        key: 'operator',
        align: 'center'
      }, {
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
                  this.show(params.index)
                }
              }
            }, '查看')
          ])
        }
      }]
    }
  }
}
</script>

<style lang="less" scoped>
  .role-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
</style>
