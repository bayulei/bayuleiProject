<!-- 用户管理 -->
<template>
  <div id="user-manage">
    <table-tools-bar>
      <div slot="left">
        <label-select v-model="search.userType" :options="search.userTypeOptions" placeholder="按类型查找" label="类型"></label-select>
        <label-input v-model="search.userName" placeholder="请输入用户名" label="用户名称"></label-input>
        <label-select v-model="search.roleId" :options="search.roleOptions" placeholder="按角色查找" label="角色名称"></label-select>
        <label-select v-model="search.disableFlag" :options="search.disableFlagOptions" placeholder="按状态查找" label="用户状态"></label-select>
        <Button type="primary" icon="ios-search" @click="searchUserPage" :loading="search.searching" title="搜索"></Button>
        <Button type="primary" title="重置" @click="resetSearch">重置</Button>
      </div>
      <div slot="right">
        <Button type="primary" icon="ios-add" title="新增" @click="openAddUserModal">新增</Button>
        <Button type="error" icon="md-trash" title="批量删除">批量删除</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table :columns="userColumns" :data="userList" border ref="selection"></Table>
    </div>
    <div>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
      <Drawer :title="userTitle" v-model="showUserModal" @on-close="closeDrawer" width="720" :mask-closable="false" >
        <div>
          <Form ref="userVO" :model="userVO" :rules="userVOFormRules" label-position="right" class="label-input-form">
            <input v-model="userVO.usid" v-show="false">
            <Row>
              <Col span="8">
                <Row>
                  <Col>
                    <FormItem label="用户名称" prop="uname" class="laws-info-item">
                      <Input  v-model="userVO.uname" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户账号" prop="account" class="laws-info-item">
                      <Input  v-model="userVO.account" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户密码" prop="password" class="laws-info-item">
                      <Input type="password" v-model="userVO.password" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="再次输入密码" prop="passwordCheck" class="laws-info-item">
                      <Input  v-model="userVO.passwordCheck" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户类型" prop="userType" class="laws-info-item">
                      <Input  v-model="userVO.userType" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户角色" prop="roleId" class="laws-info-item">
                      <Input  v-model="userVO.roleId" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="手机号码" prop="mobilePhone" class="laws-info-item">
                      <Input  v-model="userVO.mobilePhone" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="办公电话" prop="officePhone" class="laws-info-item">
                      <Input  v-model="userVO.officePhone" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户邮箱" prop="email" class="laws-info-item">
                      <Input  v-model="userVO.email" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户状态" prop="disableFlag" class="laws-info-item">
                      <Input  v-model="userVO.disableFlag" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户账号" prop="account" class="laws-info-item">
                      <Input  v-model="userVO.account" />
                    </FormItem>
                  </Col>
                </Row>
              </Col>
              <Col span="8" >
                <!-- 此处获取组织机构架构图 -->
                <FormItem label="组织机构" prop="orgId"  >
                  <Tree :data="orgList"></Tree>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </div>
        <div id="roleFormButton" class="demo-drawer-footer">
          <Button type="primary" @click="saveUserInfo">提交</Button>
          <Button @click="cancelUserModel">取消</Button>
        </div>
      </Drawer>
    </div>

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
        userType: '',
        userTypeOptions: [{
          label: '广汽研究院',
          value: 'GQYJY'
        }, {
          label: '广汽集团',
          value: 'GQJT'
        }, {
          label: '其他',
          value: 'OTHER'
        }],
        userName: '',
        roleId: '',
        roleOptions: [],
        disableFlag: '',
        disableFlagOptions: [{
          label: '已启用',
          value: 0
        }, {
          label: '已停用',
          value: 1
        }],
        searching: false
      },
      // 总数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 单页数量
      pageSize: 10,
      // 加载状态
      loading: false,
      // 用户模态框标题
      userTitle: '',
      // 用户木太狂
      showUserModal: false,
      // 用户列表列信息
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
          title: '类型',
          key: 'roleType'
        },
        {
          title: '用户名',
          key: 'uname'
        },
        {
          title: '账号',
          key: 'account'
        },
        {
          title: '工号',
          key: 'workNum'
        },
        {
          title: '角色',
          key: 'roleName'
        },
        {
          title: '所属部门',
          key: 'orgName'
        },
        {
          title: '状态',
          key: 'disableFlag'
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
                    this.showUser(params.index)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'smaill'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.userEdit(params.index)
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'smaill'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.userDel(params.index)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 用户信息
      userVO: {
        usid: '',
        account: '',
        password: '',
        passwordCheck: '',
        uname: '',
        roleId: '',
        userType: '',
        mobilePhone: '',
        officePhone: '',
        workNum: '',
        email: '',
        disableFlag: '',
        orgId: ''
      },
      // 用户列表数据
      userList: [],
      // 组织机构
      orgList: {}
    }
  },
  methods: {
    // 用户信息分页查询
    searchUserPage () {
      this.$http.get('sys/user',
        {
          pageNo: this.pageNo,
          pageSize: this.pageSize,
          userType: this.search.userType,
          userName: this.search.userName,
          roleId: this.search.roleId,
          disableFlag: this.search.disableFlag
        },
        {_this: this, loading: this.loading},
        res => {
          console.log(res)
          if (res.ok) {
            // 开始加载表格
            this.userList = res.data.list
          } else {
          }
        })
    },
    // 获取角色列表
    selectRoleList () {
      this.$http.get('/sys/role/findAll', {}, {_this: this}, res => {
        let roleList = res.data
        let roleOpList = []
        for (let index = 0; index < roleList.length; index++) {
          let role = roleList[index]
          let roleOption = {}
          roleOption.label = role.name
          roleOption.value = role.id
          roleOpList.push(roleOption)
        }
        this.search.roleOptions = roleOpList
      })
    },
    //  打开新增用户模态框
    openAddUserModal () {
      this.userTitle = '新增用户'
      this.cleanUserValue()
      this.showUserModal = true
    },
    // 保存用户信息
    saveUserInfo () {

    },
    // 获取组织机构数据
    getOrgTreeSource () {
      this.$http.get()
    },
    /**
     * @description: 搜索框重置
     * @author: chenxiaoxi
     * @date: 2018-09-10 14:18:19
     */
    resetSearch () {
      this.search.userType = 'OTHER'
      this.search.userName = ''
      this.search.roleId = ''
      this.search.disableFlag = ''
    },
    pageChange (page) {
      this.pageNo = page
      this.roleSelectPage()
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
      this.roleSelectPage()
    },
    // 清除弹窗内容
    cleanUserValue () {
      this.showUserModal = false
      this.$nextTick(() => {
        this.$refs['userVO'].resetFields()
      })
    },
    // 关闭弹窗
    closeDrawer () {
      this.showUserModal = false
      this.cleanUserValue()
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
  components: {},
  watch: {},
  mounted () {
    this.selectRoleList()
    this.searchUserPage()
  }
}
</script>

<style lang="less" scoped>
  #user-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
  .demo-drawer-footer{
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
  }
</style>
