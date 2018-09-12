<!-- 角色管理 -->
<template>
  <div class="role-manage">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="roleSearch.roleName" placeholder="请输入选项" label="角色名称"></label-input>
        <label-select v-model="roleSearch.type" :options="roleSearch.typeOptions" placeholder="请输入" label="状态"></label-select>
      </div>
      <div slot="right">
        <Button type="info" @click="selectRole">查询</Button>
        <Button type="primary" @click="resetRole">重置</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <div class="btn-group">
      <Button type="success" @click="roleAdd">增加</Button>
      <Button type="warning" @click="roleEdit" style="margin-left: 0.3rem">编辑</Button>
      <Button type="error" @click="roleConfigure" style="margin-left: 0.3rem">配置角色信息</Button>
        <Modal
          v-model="roleModel"
          :title="roleTitle">
          <div> 我是模态框内容</div>
        </Modal>
      </div>
      <Table :columns="roleColumns" :data="roleList" border ref="selection"></Table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'role-manage',
  data () {
    return {
      roleModel: false,
      roleTitle: '',
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
                  this.showRole(params.index)
                }
              }
            }, '查看')
          ])
        }
      }],
      roleList: []
    }
  },
  methods: {
    // 查询、加载表格
    selectRole () {
    },
    // 重置
    resetRole () {
      this.roleSearch.roleName = ''
      this.roleSearch.type = ''
    },
    // 添加角色信息
    roleAdd () {
      this.roleModel = true
      this.roleTitle = '添加角色信息'
    },
    // 编辑角色信息
    roleEdit () {
      this.roleModel = true
      this.roleTitle = '编辑角色信息'
    },
    // 查看角色信息
    showRole () {
      this.roleModel = true
      this.roleTitle = '查看角色信息'
    },
    // 配置角色信息
    roleConfigure () {
      this.roleModel = true
      this.roleTitle = '配置角色信息'
    }
  }
}
</script>

<style lang="less" scoped>
  .role-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .content .btn-group{
      margin-bottom: 0.5rem;
    }
  }
</style>
