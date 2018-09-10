<!-- 动态信息管理 -->
<template>
  <div class="dynamic-information-manage">
    <div class="container">
      <div class="header">
        <Button type="info" @click="dynamicAdd">新增</Button>
        <Button type="success" style="margin-left: 15px" @click="dynamicEdit">编辑</Button>
        <Button type="warning" style="margin-left: 15px">删除</Button>
        <!-- 显示模态框 -->
        <Modal
          v-model="dynamicModal"
          :title="dynamicTitle"
          :class="{ 'hide-modal-footer': modalType === 3 }" width="600">
          <div>
            <Form :model="dynamicFrom" label-position="right" :label-width="100">
              <FormItem label="动态标题">
                <Input v-model="dynamicFrom.title" style="width: 7rem"></Input>
              </FormItem>
              <FormItem label="消息模块">
                <Row>
                  <Col span="11">
                    <Select v-model="dynamicFrom.messageModule" >
                      <Option value="beijing">资料中心</Option>
                      <Option value="shanghai">London</Option>
                      <Option value="shenzhen">Sydney</Option>
                    </Select>
                  </Col>
                  <Col span="11" style="margin-left: 10px">
                    <Select v-model="dynamicFrom.messageModule" >
                      <Option value="beijing">消息</Option>
                      <Option value="shanghai">London</Option>
                      <Option value="shenzhen">Sydney</Option>
                    </Select>
                  </Col>
                </Row>
              </FormItem>
              <FormItem label="发布时间">
                <DatePicker type="datetime" placeholder="请选择发布时间" style="width: 200px"></DatePicker>
              </FormItem>
              <FormItem label="发布机构">
                <Input v-model="dynamicFrom.value" style="width: 7rem"></Input>
              </FormItem>
            <FormItem label="动态内容">
              <Input v-model="dynamicFrom.textarea" type="textarea" :autosize="{minRows: 4,maxRows: 7}"></Input>
            </FormItem>
              <FormItem label="相关附件">
                <Upload action="//jsonplaceholder.typicode.com/posts/">
                  <Button icon="ios-cloud-upload-outline">上传附件</Button>
                </Upload>
              </FormItem>
              <FormItem label="相关链接">
                <Input v-model="dynamicFrom.value" style="width: 7rem"></Input>
              </FormItem>
              <FormItem label="相关附件">
                <Upload action="//jsonplaceholder.typicode.com/posts/">
                  <Button icon="ios-cloud-upload-outline">上传图片</Button>
                </Upload>
              </FormItem>
            </Form>
          </div>
        </Modal>
      </div>
      <Table border :columns="dynamicTable " :data="data1"></Table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dynamic-information-manage',
  data () {
    return {
      dynamicModal: false,
      dynamicTitle: '',
      modalType: '',
      dynamicFrom: {
        title: '',
        messageModule: '',
        textarea: '',
        input1: '',
        input2: ''
      },
      dynamicTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '模块消息',
          key: 'moduleMessage',
          align: 'center'
        },
        {
          title: '标题',
          key: 'title',
          align: 'center',
          width: 300
        },
        {
          title: '发布日期',
          key: 'releaseDate',
          align: 'center'
        },
        {
          title: '创建人',
          key: 'founder',
          align: 'center'
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
          moduleMessage: '国内动态',
          title: '电动汽车首批三项强制性国家标准通过技术审查',
          releaseDate: '2018/9/7/17:16',
          founder: '管理员'
        },
        {
          moduleMessage: '图片新闻',
          title: '电动汽车首批三项强制性国家标准通过技术审查',
          releaseDate: '2018/9/7/17:16',
          founder: '管理员'
        },
        {
          moduleMessage: '国内动态',
          title: '电动汽车首批三项强制性国家标准通过技术审查',
          releaseDate: '2018/9/7/17:16',
          founder: '管理员'
        },
        {
          moduleMessage: '图片新闻',
          title: '电动汽车首批三项强制性国家标准通过技术审查',
          releaseDate: '2018/9/7/17:16',
          founder: '管理员'
        }
      ]
    }
  },
  methods: {
    // 新增
    dynamicAdd () {
      this.dynamicModal = true
      this.dynamicTitle = '新增模块'
      this.modalType = 'ADD'
    },
    // 编辑
    dynamicEdit () {
      this.dynamicModal = true
      this.dynamicTitle = '编辑模块'
      this.modalType = 'PUT'
    },
    show (index) {
      this.dynamicModal = true
      this.dynamicTitle = '查看模块信息'
      this.modalType = 3
    },
    remove (index) {
      this.data1.splice(index, 1)
    }
  }
}
</script>

<style lang="less">
  .dynamic-information-manage{
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
  .hide-modal-footer {
    .ivu-modal-footer {
      display: none;
    }
  }
</style>
