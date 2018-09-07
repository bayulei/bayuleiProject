<!-- 机构管理 -->
<template>
  <div id="mechanism-manage">
    <div class="mechanism-manage-left">
      <Tree :data="deptTree" :render="renderContent"></Tree>
    </div>
    <div class="mechanism-manage-right"></div>
  </div>
</template>

<script>
export default {
  name: 'mechanism-manage',
  data () {
    return {
      deptTree: [{
        title: 'parent 1',
        expand: true,
        isEdit: false,
        render: (h, { root, node, data }) => {
          if (!data.isEdit) {
            return h('span', {
              style: {
                display: 'inline-block',
                width: '100%'
              }
            }, [
              h('span', [
                h('Icon', {
                  props: {
                    type: 'ios-folder-outline'
                  },
                  style: {
                    marginRight: '8px'
                  }
                }),
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
                    icon: 'ios-add',
                    type: 'primary'
                  }),
                  style: {
                    width: '64px'
                  },
                  on: {
                    click: () => {
                      // this.append(data)
                      data.isEdit = true
                    }
                  }
                })
              ])
            ])
          } else {
            return h('Input', {
              props: {
                value: data.title,
                autofocus: true,
                placeholder: 'Please enter your name...'
              },
              on: {
                input: (val) => {
                  data.title = val
                }
              }
            })
          }
        },
        children: [
          {
            title: 'child 1-1',
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
            title: 'child 1-2',
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
      buttonProps: {
        type: 'default',
        size: 'small'
      }
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
          h('Icon', {
            props: {
              type: 'ios-paper-outline'
            },
            style: {
              marginRight: '8px'
            }
          }),
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
              icon: 'ios-add'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => {
                this.$Modal.confirm({
                  render: (h) => {
                    return h('Input', {
                      props: {
                        value: data.title,
                        autofocus: true,
                        placeholder: 'Please enter your name...'
                      },
                      on: {
                        input: (val) => {
                          data.title = val
                        }
                      }
                    })
                  }
                })
              }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-remove'
            }),
            on: {
              click: () => { this.remove(root, node, data) }
            }
          })
        ])
      ])
    },
    append (data) {
      const children = data.children || []
      children.push({
        title: 'appended node',
        expand: true
      })
      this.$set(data, 'children', children)
    },
    remove (root, node, data) {
      const parentKey = root.find(el => el === node).parent
      const parent = root.find(el => el.nodeKey === parentKey).node
      const index = parent.children.indexOf(data)
      parent.children.splice(index, 1)
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
      padding: 0.2rem 0.3rem;
    }
    .mechanism-manage-right{
      flex: 1;
      height: 100%;
    }
  }
</style>
