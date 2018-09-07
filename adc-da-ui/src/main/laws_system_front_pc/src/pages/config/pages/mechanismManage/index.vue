<!-- 机构管理 -->
<template>
  <div id="mechanism-manage">
    <div class="mechanism-manage-left">
      <Tree :data="deptTree" :render="renderContent"></Tree>
    </div>
    <div class="mechanism-manage-right"></div>
    <!-- tree弹窗 -->
    <Modal
      v-model="isShow.tree"
      :title="treeTitle"
      class="mechanism-tree-modal"
      @on-visible-change="resetTree"
      @on-ok="treeOk"
      @on-cancel="treeCancel">
      <Form ref="treeForm" :model="treeForm" :rules="treeRules">
        <FormItem label=" " prop="treeNodeTitle">
          <Input v-model="treeForm.treeNodeTitle" placeholder="请输入节点名称" clearable />
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
export default {
  name: 'mechanism-manage',
  data () {
    return {
      deptTree: [{
        expand: true,
        render: (h, { root, node, data }) => {
          return h('span', {
            style: {
              display: 'inline-block',
              width: '100%'
            }
          }, [
            h('span', [
              h('span', '广汽研究院')
            ]),
            h('span', {
              style: {
                display: 'inline-block',
                marginLeft: '32px'
              }
            }, [
              h('Button', {
                props: Object.assign({}, this.buttonProps, {
                  icon: 'ios-add',
                  type: 'primary'
                }),
                style: {
                  width: '64px'
                },
                on: {
                  click: () => {
                    // this.append(data)
                    this.treeFlag = 1
                    this.isShow.tree = true
                    this.treeForm.treeNodeTitle = ''
                    this.treeNode = data
                  }
                }
              })
            ])
          ])
        },
        children: [
          {
            title: '技术部',
            expand: true,
            children: [
              {
                title: 'leaf 1-1-1',
                expand: true
              },
              {
                title: 'leaf 1-1-2',
                expand: true
              }
            ]
          },
          {
            title: '认证科',
            expand: true,
            children: [
              {
                title: 'leaf 1-2-1',
                expand: true
              },
              {
                title: 'leaf 1-2-1',
                expand: true
              }
            ]
          }
        ]
      }],
      treeFlag: 1, // 1为新增 2为编辑
      buttonProps: {
        type: 'default',
        size: 'small'
      },
      // 显示条件
      isShow: {
        tree: false
      },
      // 树弹窗表单
      treeForm: {
        treeNodeTitle: '' // 树节点名称
      },
      // 树弹窗表单验证
      treeRules: {
        treeNodeTitle: [{
          required: true, message: '节点名称不能为空', trigger: 'blur'
        }]
      },
      // 当前节点
      treeNode: ''
    }
  },
  methods: {
    renderContent (h, { root, node, data }) {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span', [
          h('span', data.title)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            marginLeft: '32px'
          }
        }, [
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-create-outline'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => {
                this.treeFlag = 2
                this.isShow.tree = true
                this.treeForm.treeNodeTitle = data.title
                this.treeNode = data
              }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-add'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => {
                this.treeFlag = 1
                this.isShow.tree = true
                this.treeForm.treeNodeTitle = ''
                this.treeNode = data
              }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-remove'
            }),
            on: {
              click: () => { this.treeRemove(root, node, data) }
            }
          })
        ])
      ])
    },
    // 添加节点
    treeAppend () {
      console.log(this.treeFlag)
      if (this.treeFlag !== 1) {
        return false
      }
      const children = this.treeNode.children || []
      children.push({
        title: this.treeForm.treeNodeTitle,
        expand: true
      })
      this.$set(this.treeNode, 'children', children)
    },
    // 修改节点
    treeEditSave () {
      this.treeNode.title = this.treeForm.treeNodeTitle
    },
    // 移除节点
    treeRemove (root, node, data) {
      const parentKey = root.find(el => el === node).parent
      const parent = root.find(el => el.nodeKey === parentKey).node
      const index = parent.children.indexOf(data)
      parent.children.splice(index, 1)
    },
    // 树弹窗确认
    treeOk () {
      this.treeFlag === 1 ? this.treeAppend() : this.treeEditSave()
    },
    // 树弹窗取消
    treeCancel () {},
    // 重置树结构表单
    resetTree (show) {
      if (!show) {
        this.$refs.treeForm.resetFields()
      }
    }
  },
  computed: {
    // modal弹窗标题
    treeTitle () {
      return this.treeFlag === 1 ? '节点新增' : '节点维护'
    }
  }
}
</script>

<style lang="less">
  #mechanism-manage{
    display: flex;
    background: #FFF;
    .mechanism-manage-left{
      width: 6.1rem;
      height: 100%;
      border-right: 1px solid #DDD;
      .ivu-tree{
        width: 100%;
        height: 100%;
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
  }
  .mechanism-tree-modal{
    .ivu-input-wrapper{
      width: 90%;
    }
  }
</style>
