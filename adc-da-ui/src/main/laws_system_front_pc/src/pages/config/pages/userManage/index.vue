<!-- 用户管理 -->
<template>
  <div id="user-manage">
    <table-tools-bar>
      <div slot="left">
        <label-select v-model="search.userType" :options="search.userTypeOptions" placeholder="按类型查找" label="类型"></label-select>
        <label-input v-model="search.uname" placeholder="请输入用户名" label="用户名称"></label-input>
        <label-select v-model="search.roleName" :options="search.roleOptions" placeholder="按角色查找" label="角色名称"></label-select><br> <br>
        <label-select v-model="search.disableFlag" :options="search.disableFlagOptions" placeholder="按状态查找" label="用户状态"></label-select>
      </div>
      <div slot="right">
        <Button type="primary" icon="ios-search" @click="searchUserPage" :loading="search.searching" title="搜索"></Button>
        <Button type="primary" title="重置" @click="resetSearch">重置</Button>
        <Button type="primary" icon="ios-add" title="新增" @click="openAddUserModal">新增</Button>
        <Button type="error" icon="md-trash" title="批量删除" @click="batchUserDel" >批量删除</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table :columns="userColumns" :data="userList" border ref="selection" @on-selection-change="handleRowChange" ></Table>
    </div>
    <div>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
      <Drawer :title="userTitle" v-model="showUserModal"  width="720"  :styles="styles">
        <div>
          <Form ref="userVO" :model="userVO" :rules="userVOFormRules" label-position="right" class="label-input-form">
            <input v-model="userVO.usid" v-show="false">
            <Row>
              <Col span="12">
                <Row>
                  <Col>
                    <FormItem label="用户名称" prop="uname" class="laws-info-item">
                      <Input  v-model="userVO.uname"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem  label="用户账号" prop="account" class="laws-info-item">
                      <Input :disabled="createType" v-model="userVO.account" />
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem v-if="showPWD" label="用户密码" prop="password" class="laws-info-item">
                      <Input type="password" v-model="userVO.password"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem v-if="showPWD" label="再次输入密码" prop="passwordCheck" class="laws-info-item">
                      <Input type="password" v-model="userVO.passwordCheck"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户类型" prop="userType" class="laws-info-item">
                      <Select v-model="userVO.userType" style="width:200px" :disabled="usersType">
                        <Option v-for="item in search.userTypeOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户角色" prop="roleId" class="laws-info-item">
                      <Select v-model="userVO.roleId" style="width:200px" :disabled="usersType">
                        <Option v-for="item in search.roleOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="手机号码" prop="mobilePhone" class="laws-info-item">
                      <Input  v-model="userVO.mobilePhone"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="办公电话" prop="officePhone" class="laws-info-item">
                      <Input  v-model="userVO.officePhone"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户邮箱" prop="email" class="laws-info-item">
                      <Input  v-model="userVO.email"  :disabled="usersType"/>
                    </FormItem>
                  </Col>
                  <Col>
                    <FormItem label="用户状态" prop="disableFlag" class="laws-info-item">
                      <Select v-model="userVO.disableFlag" style="width:200px" :disabled="usersType">
                        <Option v-for="item in search.disableFlagOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
              </Col>
              <Col span="12" >
                <!-- 此处获取组织机构架构图 -->
                <FormItem label="组织机构" prop="roleIdList"  >
                  <ul id="orgTree" class="ztree" style="width: 200px;height: 500px;overflow: auto"></ul>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </div>
        <div id="roleFormButton" class="demo-drawer-footer" :class="{ 'disappear': usersType }">
          <Button type="primary" @click="saveUserInfo">提交</Button>
          <Button @click="closeDrawer">取消</Button>
        </div>
      </Drawer>
      <!--<Drawer :closable="false" width="640" :title="userTitle" v-model="userInfoModel" >-->
        <!--<div class="demo-drawer-profile">-->
          <!--<Form label-position="right" class="label-input-form">-->
          <!--<Row>-->
            <!--<Col span="12">-->
              <!--<FormItem label="用户名称" class="laws-info-item">-->
                <!--<Input  v-model="userVO.uname" />-->
              <!--</FormItem>-->
              <!--用户名称:{{userVO.uname}}-->
            <!--</Col>-->
            <!--<Col span="12" >账号:{{userVO.account}}</Col>-->
          <!--</Row>-->
          <!--<Row>-->
            <!--<Col span="12">-->
              <!--用户角色:{{userVO.roleName}}-->
            <!--</Col>-->
            <!--<Col span="12">-->
              <!--用户类型:{{userVO.userType ==='GQYJY' ? '广汽研究院':userVO.userType ==='GQJT'?'广汽集团':userVO.userType ==='OTHER'?'其他':''}};-->
            <!--</Col>-->
          <!--</Row>-->
          <!--<Row>-->
            <!--<Col span="12">-->
              <!--所属部门:{{userVO.orgName}}-->
            <!--</Col>-->
            <!--<Col span="12">手机号码:{{userVO.mobilePhone}}</Col>-->
          <!--</Row>-->
          <!--<Row>-->
            <!--<Col span="12">-->
              <!--办公电话:{{userVO.officePhone}}-->
            <!--</Col>-->
            <!--<Col span="12" >电子邮件:{{userVO.email}}</Col>-->
          <!--</Row>-->
          <!--<Row>-->
            <!--<Col span="12">用户状态:{{userVO.disableFlag==0? '启用':'禁用'}}</Col>-->
          <!--</Row>-->
          <!--</Form>-->
        <!--</div>-->
      <!--</Drawer>-->
    </div>

  </div>
</template>

<script>
import 'zTree/js/jquery.ztree.core.js'
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
        uname: '',
        roleName: '',
        roleOptions: [],
        disableFlag: '',
        disableFlagOptions: [{
          label: '已启用',
          value: '0'
        }, {
          label: '已停用',
          value: '1'
        }],
        searching: false
      },
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static'
      },
      usersType: false,
      createType: true,
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
      // 显示密码
      showPWD: true,
      // 账户禁用
      accountState: false,
      // 用户查看模态框
      userInfoModel: false,
      // 用户列表列信息
      userColumns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '类型',
          align: 'center',
          key: 'userType',
          width: 120,
          render: (h, params) => {
            // let _this = this
            let texts = ''
            switch (params.row.userType) {
              case 'GQYJY' :
                texts = '广汽研究院'
                break
              case 'GQJT' :
                texts = '广汽集团'
                break
              case 'OTHER' :
                texts = '其他'
                break
            }
            return h('div', {
              props: {}
            }, texts)
          }
        },
        {
          title: '用户名',
          align: 'center',
          key: 'uname'
        },
        {
          title: '账号',
          align: 'center',
          key: 'account'
        },
        {
          title: '工号',
          align: 'center',
          key: 'workNum',
          width: 80
        },
        {
          title: '角色',
          align: 'center',
          key: 'roleName'
        },
        {
          title: '所属部门',
          align: 'center',
          key: 'orgName',
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
          title: '状态',
          align: 'center',
          width: 80,
          key: 'disableFlag',
          render: (h, params) => {
            // let _this = this
            let texts = ''
            if (params.row.disableFlag === 0) {
              texts = '启用'
            } else {
              texts = '禁用'
            }
            return h('div', {
              props: {}
            }, texts)
          }
        }, {
          title: '操作',
          key: 'action',
          align: 'center',
          width: 200,
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
                    this.userEdit(params.row)
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
                    this.showUser(params.index)
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
        roleName: '',
        userType: '',
        mobilePhone: '',
        officePhone: '',
        workNum: '',
        email: '',
        disableFlag: '',
        roleIdList: '',
        orgName: ''
      },
      userVOFormRules: {
        account: [{required: true, message: '账户不能为空', trigger: 'blur'}],
        uname: [{required: true, messgae: '用户名称不能为空', trigger: 'blur'}]
        // 此处需要两次输入的密码是否一致
      },
      // 用户列表数据
      userList: [],
      clickUserList: [],
      // 组织机构
      orgList: [],
      orgTree: {},
      setting: {
        check: {
          enable: false
        },
        view: {
          showLine: true,
          nameIsHTML: true
        },
        data: {
          simpleData: {
            enable: true
          }
        },
        callback: {
          onClick: this.zTreeOnClick()
        }
      }
    }
  },
  methods: {
    // 用户信息分页查询
    searchUserPage () {
      this.$http.get('sys/user/',
        {
          pageNo: this.pageNo,
          pageSize: this.pageSize,
          userType: this.search.userType,
          uname: this.search.uname,
          roleName: this.search.roleName,
          disableFlag: this.search.disableFlag
        },
        {_this: this, loading: 'loading'},
        res => {
          if (res.ok) {
            this.userList = res.data.list
            this.pageNo = res.data.pageNo
            this.total = res.data.count
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
      this.showPWD = true
      this.accountState = false
      this.createType = false
      this.getOrgTreeSource()
      this.cleanUserValue()
      this.showUserModal = true
    },
    // 保存用户信息
    saveUserInfo () {
      // 更新
      if (this.userVO.usid !== null && this.userVO.usid !== '') {
        this.$http.putData('sys/user', this.userVO,
          {_this: this, loading: this.loading},
          res => {
            this.closeDrawer()
            this.searchUserPage()
          })
      } else {
        // 新增
        this.$http.postData('sys/user', this.userVO,
          {_this: this, loading: this.loading},
          res => {
            if (res.ok) {
              // this.executeSuccess('保存用户成功！')
              this.closeDrawer()
              this.searchUserPage()
            }
          })
      }
      this.searchUserPage()
    },
    // 获取组织机构数据
    getOrgTreeSource () {
      this.$http.get('sys/org/getTree', {}, {_this: this},
        res => {
          console.log(res)
          if (res.ok) {
            let orgTreeList = res.data
            let treeStr = []
            for (let i = 0; i < orgTreeList.length; i++) {
              let option = { id: orgTreeList[i].id,
                pId: orgTreeList[i].parentId,
                name: orgTreeList[i].orgName,
                open: orgTreeList[i].isShow,
                orgDesc: orgTreeList[i].orgDesc,
                orgCode: orgTreeList[i].orgCode,
                orgType: orgTreeList[i].orgType,
                isShow: orgTreeList[i].isShow,
                shortName: orgTreeList[i].shortName}
              treeStr.push(option)
            }
            this.orgTree = $.fn.zTree.init($('#orgTree'), this.setting, treeStr)
          }
        })
    },
    // 用户编辑
    userEdit (row) {
      this.userTitle = '编辑用户'
      this.usersType = false
      this.createType = true
      this.showPWD = false
      this.accountState = true
      this.userVO = JSON.parse(JSON.stringify(row))
      this.getOrgTreeSource()
      this.showUserModal = true
    },
    // 用户删除
    userDel (index) {
      console.log(this.userList[index].usid)
      this.$Modal.confirm({
        title: '请选择',
        content: '确定删除这些数据?',
        onOk: () => {
          this.$http.delete('sys/user/' + this.userList[index].usid, {},
            { _this: this
            }, res => {
              if (res.ok) {
                this.executeSuccess('删除成功')
                this.searchUserPage()
              } else {
                this.executeError('删除失败! 失败原因:' + res.message)
              }
            })
          this.$Modal.remove()
        }})
    },
    // 批量删除
    batchUserDel () {
      // 此处获取选中的数据
      if (this.clickUserList.length > 0) {
        let userIds = []
        for (let i = 0; i < this.clickUserList.length; i++) {
          let userId = this.clickUserList[i].usid
          userIds.push(userId)
        }
        let userIdsStr = userIds.join(',')
        this.$Modal.confirm({
          title: '请选择',
          content: '确定删除这些数据?',
          onOk: () => {
            this.$http.delete('sys/user/' + userIdsStr, {},
              { _this: this
              }, res => {
                if (res.ok) {
                  this.executeSuccess('删除成功')
                  this.searchUserPage()
                } else {
                  this.executeError('删除失败! 失败原因:' + res.message)
                }
              })
            this.$Modal.remove()
          }})
      } else {
        this.executeError('未选择用户，请选择')
      }
    },
    // 用户查看
    showUser (index) {
      this.userTitle = '查看用户信息'
      this.showPWD = false
      this.createType = true
      this.userVO = this.userList[index]
      // this.userInfoModel = true
      this.showUserModal = true
      this.usersType = true
    },
    /**
     * @description: 搜索框重置
     * @author: chenxiaoxi
     * @date: 2018-09-10 14:18:19
     */
    resetSearch () {
      this.search.userType = ''
      this.search.uname = ''
      this.search.roleName = ''
      this.search.disableFlag = ''
    },
    pageChange (page) {
      this.pageNo = page
      this.searchUserPage()
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
      this.searchUserPage()
    },
    // 清除弹窗内容
    cleanUserValue () {
      this.showUserModal = false
      this.$nextTick(() => {
        this.$refs['userVO'].resetFields()
        console.log(this.userVO)
      })
    },
    // 关闭弹窗
    closeDrawer () {
      this.showUserModal = false
      this.cleanUserValue()
    },
    handleRowChange (selection) {
      this.clickUserList = selection
    },
    // 成功弹框
    executeSuccess (message) {
      this.$Message.success(message)
    },
    // 失败弹框
    executeError (message) {
      this.$Message.error(message)
    },
    zTreeOnClick (event, treeId, treeNode, clickFlag) {

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

<style lang="less">
  @import '../../../../assets/zTree/css/zTreeStyle/zTreeStyle.css';
  #user-manage{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
  .disappear{
    display: none;
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
  .main .sub-container > div .ivu-table-wrapper{
    height: calc(100% - 8px);
  }
</style>
