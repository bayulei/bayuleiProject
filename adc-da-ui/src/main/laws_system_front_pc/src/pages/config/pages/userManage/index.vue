<!-- 用户管理 -->
<template>
  <div id="user-manage">
    <table-tools-bar>
      <div slot="left">
        <label-select v-model="search.type" :options="search.typeOptions" placeholder="按类型查找" label="类型"></label-select>
        <label-input v-model="search.userName" placeholder="请输入用户名" label="用户名称"></label-input>
        <label-select v-model="search.roleName" :options="search.roleOptions" placeholder="按角色查找" label="角色名称"></label-select>
        <label-select v-model="search.state" :options="search.stateOptions" placeholder="按状态查找" label="用户状态"></label-select>
        <Button type="primary" icon="ios-search" :loading="search.searching" title="搜索"></Button>
        <Button type="primary" title="重置" @click="resetSearch">重置</Button>
      </div>
      <div slot="right">
        <Button type="primary" icon="ios-add" title="新增" @click="openFullModal">新增</Button>
        <Button type="error" icon="md-trash" title="批量删除">批量删除</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table :columns="userColumns" :data="userList" border ref="selection"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    <!-- 新增、编辑模态窗 -->
    <full-modal v-model="showFullModal" v-if="showFullModal" ref="showFullModal">
      <Button @click="closeModal">关闭</Button>
    </full-modal>
  </div>
</template>

<script>
import expandRow from './components/TableExpand'
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
        }],
        state: '',
        stateOptions: [{
          label: '已启用',
          value: 1
        }, {
          label: '已停用',
          value: 2
        }],
        searching: false
      },
      // 分页
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      userColumns: [
        {
          type: 'expand',
          width: 50,
          render: (h, params) => {
            return h(expandRow, {
              props: {
                row: params.row
              }
            })
          }
        },
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
      userList: [
        {
          name: 'John Brown',
          age: 18,
          address: 'New York No. 1 Lake Park',
          job: 'Data engineer',
          interest: 'badminton',
          birthday: '1991-05-14',
          book: 'Steve Jobs',
          movie: 'The Prestige',
          music: 'I Cry'
        },
        {
          name: 'Jim Green',
          age: 25,
          address: 'London No. 1 Lake Park',
          job: 'Data Scientist',
          interest: 'volleyball',
          birthday: '1989-03-18',
          book: 'My Struggle',
          movie: 'Roman Holiday',
          music: 'My Heart Will Go On'
        },
        {
          name: 'Joe Black',
          age: 30,
          address: 'Sydney No. 1 Lake Park',
          job: 'Data Product Manager',
          interest: 'tennis',
          birthday: '1992-01-31',
          book: 'Win',
          movie: 'Jobs',
          music: 'Don’t Cry'
        },
        {
          name: 'Jon Snow',
          age: 26,
          address: 'Ottawa No. 2 Lake Park',
          job: 'Data Analyst',
          interest: 'snooker',
          birthday: '1988-7-25',
          book: 'A Dream in Red Mansions',
          movie: 'A Chinese Ghost Story',
          music: 'actor'
        },
        {
          name: 'Jon Snow',
          age: 26,
          address: 'Ottawa No. 2 Lake Park',
          job: 'Data Analyst',
          interest: 'snooker',
          birthday: '1988-7-25',
          book: 'A Dream in Red Mansions',
          movie: 'A Chinese Ghost Story',
          music: 'actor'
        },
        {
          name: 'Jon Snow',
          age: 26,
          address: 'Ottawa No. 2 Lake Park',
          job: 'Data Analyst',
          interest: 'snooker',
          birthday: '1988-7-25',
          book: 'A Dream in Red Mansions',
          movie: 'A Chinese Ghost Story',
          music: 'actor'
        }
      ],
      showFullModal: false // 显示大弹窗(新增、编辑)
    }
  },
  methods: {
    /**
     * @description: 搜索框重置
     * @author: chenxiaoxi
     * @date: 2018-09-10 14:18:19
     */
    resetSearch () {
      this.search.type = ''
      this.search.userName = ''
      this.search.roleName = ''
      this.search.state = ''
    },
    // 修改页数
    pageChange () {},
    // 修改长度
    pageSizeChange () {},
    // 打开新增模态框
    openFullModal () {
      this.showFullModal = true
    },
    // 关闭模态框
    closeModal () {
      this.$refs.showFullModal.toggleClose()
    }
  },
  components: {},
  watch: {}
}
</script>

<style lang="less" scoped>
  #user-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
</style>
