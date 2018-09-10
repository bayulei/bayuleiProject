<!-- 资料中心模块配置 -->
<template>
  <div class="InformationCenterConfig">
      <div class="container">
        <div class="header">
          <Button type="info" @click="informationAdd">新增</Button>
          <Button type="success" style="margin-left: 15px" @click="informationEdit">编辑</Button>
          <Button type="warning" style="margin-left: 15px">删除</Button>
          <!-- 显示模态框 -->
          <Modal
            v-model="informationModal"
            :title="informationTitle">
            <div v-if="modalType === 1">
              <p>我是新增内容</p>
            </div>
            <div v-else>
              <p>我是编辑内容</p>
            </div>
          </Modal>
        </div>
        <Table border :columns="informationTable " :data="data1"></Table>
      </div>
  </div>
</template>
<script>
export default {
  name: 'informationCenterConfig',
  data () {
    return {
      informationModal: false,
      informationTitle: '',
      modalType:'',
      informationTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '参数',
          key: 'parameter',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Icon', {
                props: {
                  type: 'person'
                }
              }),
              h('strong', params.row.parameter)
            ])
          }
        },
        {
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
              }, '查看'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.remove(params.index)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      data1: [
        {
          parameter: '通知'
        },
        {
          parameter: '共享资料'
        },
        {
          parameter: '标准法规月报'
        },
        {
          parameter: '会议资料'
        }
      ]
    }
  },
  methods: {
    // 新增
    informationAdd () {
      this.informationModal = true
      this.informationTitle='新增参数'
      this.modalType=1
    },
    // 编辑
    informationEdit () {
      this.informationModal = true
      this.informationTitle='编辑参数'
      this.modalType=2
    },
    show (index) {
      this.$Modal.info({
        title: '查看参数',
        content: `参数：${this.data1[index].parameter}`
      })
    },
    remove (index) {
      this.data1.splice(index, 1)
    }
  }
}
</script>

<style lang="less" scoped>
    .InformationCenterConfig{
      display: flex;
      background: #FFF;
      .container{
        width: 100%;
        margin: 1rem;
      }
      .header{
        margin-bottom:0.7rem;

      }
    }
</style>
