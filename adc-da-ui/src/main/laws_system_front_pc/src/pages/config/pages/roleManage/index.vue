<!-- 角色管理 -->
<template>
  <div class="role-manage">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="roleSearch.roleName" placeholder="请输入选项" label="角色名称"></label-input>
        <label-select v-model="roleSearch.type" :options="roleSearch.typeOptions" label="状态"></label-select>
      </div>
      <div slot="right">
        <Button type="info" @click="selectRoleBtn">查询</Button>
        <Button type="primary" @click="resetRole">重置</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <div class="btn-group">
      <Button type="success" @click="openRoleAddModel">增加</Button>
      <Button type="error" @click="roleConfigure" style="margin-left: 0.3rem">配置角色资源</Button>
      </div>
      <Table :columns="roleColumns" :data="roleList" border ref="selection"></Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
    <div>
      <Drawer :title="roleTitle" v-model="showRoleInfoModal" @on-close="closeDrawer" width="720" :mask-closable="false" >
        <div>
          <Form ref="roleVO" :model="roleVO" :rules="roleVOFormRules" label-position="right" class="label-input-form">
            <input v-model="roleVO.id" v-show="false">
            <Row>
              <Col span="8">
                <FormItem label="角色名称" prop="name" class="laws-info-item">
                  <Input  v-model="roleVO.name" />
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="8">
                <FormItem lable="角色类型" prop="roleType" class="laws-info-item" >
                  <Select  v-model="roleVO.roleType" clearable style="width:200px">
                    <Option v-for="item in roleTypeOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
                  </Select>
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="8">
                <FormItem label="角色状态" prop="useFlag" class="laws-info-item">
                  <Switch v-model="roleVO.useFlag" size="large" :true-value="Number(0)" :false-value="Number(1)" >
                    <span slot="open">启用</span>
                    <span slot="close">禁用</span>
                  </Switch>
                </FormItem>
              </Col>
            </Row>
            <Row>
              <Col span="8">
                <FormItem label="角色描述" prop="remarks"  class="laws-info-item">
                  <Input v-model="roleVO.remarks" type="textarea" :rows="4" placeholder="角色描述......" />
                </FormItem>
              </Col>
            </Row>
          </Form>

        </div>
        <div id="roleFormButton" class="demo-drawer-footer">
          <Button type="primary" @click="saveRoleInfo">提交</Button>
          <Button @click="cancelRoleModel">取消</Button>
        </div>
      </Drawer>
      <Drawer :closable="false" width="640" :title="roleTitle" v-model="roleInfoModel" >
        <div class="demo-drawer-profile">
          <Row>
            <Col span="12">
                角色名称:{{roleVO.name}}
            </Col>
          </Row>
          <Row>
            <Col span="12">
                角色类型:{{roleVO.roleType === 'AUTHBODY'? '认证机构':roleVO.roleType ==='CITEK' ? '检测机构':roleVO.roleType === 'CONADMIN' ? '配置管理员':'暂无'}}
            </Col>
          </Row>
          <Row>
            <Col span="12">
                角色状态:{{roleVO.useFlag ===0 ? '启用':'禁用'}}
            </Col>
          </Row>
          <Row>
            <Col span="12">
              角色描述:{{roleVO.remarks}}
            </Col>
          </Row>
        </div>

      </Drawer>
    </div>
    <loading :loading="roleDataLoading">执行中</loading>
  </div>
</template>

<script>
export default {
  name: 'role-manage',
  data () {
    return {
      roleDataLoading: false,
      roleInfoModel: false,
      total: 0,
      roleTitle: '',
      roleSearch: {
        roleName: '',
        type: '',
        typeOptions: [{label: '全部', value: '-1'}, {label: '有效', value: '0'}, {label: '无效', value: '1'}]
      },
      pageNo: 0,
      pageSize: 20,
      userList: [],
      showRoleInfoModal: false,
      roleVO: {
        id: '',
        name: '',
        remarks: '',
        useFlag: 0,
        roleType: ''
      },
      roleVOFormRules: {
        name: [{required: true, message: '角色名称不能为空', trigger: 'blur'}]
      },
      roleTypeOptions: [{label: '暂无', value: ''}, {label: '认证机构', value: 'AUTHBODY'}, {label: '检测机构', value: 'CITEK'}, {label: '配置管理员', value: 'CONADMIN'}],
      roleList: [],
      roleColumns: [{
        type: 'selection',
        width: 60,
        align: 'center'
      }, {
        title: '角色名称',
        key: 'name',
        align: 'center'
      }, {
        title: '角色描述',
        key: 'remarks',
        align: 'center'
      }, {
        title: '状态',
        key: 'useFlag',
        align: 'center',
        render: (h, params) => {
          // let _this = this
          let texts = ''
          if (params.row.useFlag === 0) {
            texts = '启用'
          } else {
            texts = '禁用'
          }
          return h('div', {
            props: {}
          }, texts)
        }
      }, {
        title: '类型',
        key: 'roleType',
        align: 'center',
        render: (h, params) => {
          let roleTypeValue = ''
          switch (params.row.roleType) {
            case 'AUTHBODY' :
              roleTypeValue = '认证机构'
              break
            case 'CITEK' :
              roleTypeValue = '检测机构'
              break
            case 'CONADMIN' :
              roleTypeValue = '配置管理员'
              break
            default :
              roleTypeValue = '暂无'
          }
          return h('div', {props: {}}, roleTypeValue)
        }
      }, {
        title: '操作时间',
        key: 'modifyTime',
        align: 'center'
      }, {
        title: '操作人',
        key: 'operUserName',
        align: 'center'
      }, {
        title: '操作',
        key: 'action',
        align: 'center',
        render: (h, params) => {
          return h('div', [
            h('Button', {
              props: {
                type: 'primary'
              },
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.showRole(params.index)
                }
              }
            }, '查看'),
            h('Button', {
              props: {
                type: 'primary'
              },
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.roleEdit(params.index)
                }
              }
            }, '编辑'),
            h('Button', {
              props: {
                type: 'primary'
              },
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.roleDel(params.index)
                }
              }
            }, '删除')
          ])
        }
      }]
    }
  },
  methods: {
    // 查询、加载表格
    roleSelectPage () {
      let useFlag = 0
      if (this.roleSearch.type === '-1') {
        useFlag = null
      } else {
        useFlag = this.roleSearch.type
      }
      this.$http.get('sys/role/page',
        {pageNo: this.pageNo, pageSize: this.pageSize, roleName: this.roleSearch.roleName, useFlag: useFlag},
        {
          _this: this,
          loading: 'roleDataLoading'
        },
        res => {
          console.log(res.data.list)
          this.roleList = res.data.list
          this.total = res.data.count
        })
    },
    // 根据条件查询角色信息
    selectRoleBtn () {
      this.pageNo = 0
      this.roleSelectPage()
    },
    // 重置
    resetRole () {
      this.roleSearch.roleName = ''
      this.roleSearch.type = ''
    },
    // 添加角色信息
    openRoleAddModel () {
      this.roleTitle = '添加角色信息'
      this.showRoleInfoModal = true
      // this.cleanRoleValue()
    },
    // 编辑角色信息
    roleEdit (index) {
      this.roleVO = this.roleList[index]
      this.roleTitle = '编辑角色信息'
      this.showRoleInfoModal = true
    },
    // 删除角色
    roleDel (index) {
      // 此处需要confirm功能
      this.$confirm({
        title: '删除角色',
        tips: '是否删除角色?',
        confirm: () => {
          this.$http.delete('sys/role/' + this.roleList[index].id, {},
            { _this: this
            }, res => {
              if (res.ok) {
                this.executeSuccess('删除成功')
                // 此处调用新增页面关闭代码 并刷新页面
                this.roleSelectPage()
              } else {
                this.executeError('删除失败! 失败原因:' + res.message)
              }
            })
          this.$Modal.remove()
        }})
    },
    // 保存角色信息
    saveRoleInfo () {
      let roleInfoVO = JSON.parse(JSON.stringify(this.roleVO))
      if (roleInfoVO.id != null && roleInfoVO.id !== '') {
        // 修改角色信息
        // 此处暂未实现
        this.$http.putData('sys/role', this.roleVO,
          {
            _this: this, loading: 'roleDataLoading'
          }, res => {
            if (res.ok) {
              this.executeSuccess('编辑成功')
              // 此处调用新增页面关闭代码 并刷新页面
              this.roleSelectPage()
            } else {
              this.executeError('编辑失败! 失败原因:' + res.message)
            }
          }
        )
      } else {
        this.$http.postData('sys/role', this.roleVO,
          {
            _this: this,
            loading: 'roleDataLoading'
          }, res => {
            console.log('进入新增返回结果，进行处理！')
            console.log(res)
            if (res.ok) {
              this.executeSuccess('保存成功')
              // 此处调用新增页面关闭代码 并刷新页面
              this.roleSelectPage()
            } else {
              this.executeError('保存失败! 失败原因:' + res.message)
            }
          })
      }
    },
    // 查看角色信息
    showRole (index) {
      this.roleTitle = '查看角色信息'
      this.roleVO = this.roleList[index]
      this.roleInfoModel = true
    },
    // 关闭模态框
    cancelRoleModel () {
      this.cleanRoleValue()
      // this.showRoleInfoModal = false
    },
    // 配置角色信息
    roleConfigure () {
      this.roleTitle = '配置角色信息'
      // 此处加载资源列表数据
      // 然后获取选中角色ID集合
    },
    pageChange (page) {
      this.pageNo = page
      this.roleSelectPage()
    },
    pageSizeChange (pageSize) {
      this.pageSize = pageSize
      this.roleSelectPage()
    },
    cleanRoleValue () {
      this.showRoleInfoModal = false
      this.$nextTick(() => {
        this.$refs['roleVO'].resetFields()
      })
    },
    executeSuccess (message) {
      this.$Message.success(message)
    },
    executeError (message) {
      this.$Message.error(message)
    },
    closeDrawer () {
      console.log('关闭抽屉页面')
      this.cleanRoleValue()
    }
  },
  watch: {
    showRoleInfoModal (newVal, oldVal) {
      if (!newVal) {
        this.cleanRoleValue()
      }
    }
  },
  mounted () {
    this.pageNo = 1
    this.roleSearch.roleName = null
    this.roleSearch.type = '-1'
    this.roleSelectPage()
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
