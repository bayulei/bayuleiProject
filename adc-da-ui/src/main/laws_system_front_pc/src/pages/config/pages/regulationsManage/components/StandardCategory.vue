<!-- 标准类别 -->
<template>
  <div class="standard-classification">
    <table-tools-bar>
      <div slot="left">
        <Form ref="formInline" :model="formInline" inline>
          <FormItem prop="option">
            <Input type="text" v-model="value1" placeholder="请输入选项">
            <span slot="prepend">选项</span>
            </Input>
          </FormItem>
          <FormItem prop="describe">
            <Input type="text" v-model="value2" placeholder="请输入选项">
            <span slot="prepend">描述</span>
            </Input>
          </FormItem>
          <Button type="info" class="query-button" @click="handleQuery">查询</Button>
        </Form>
      </div>
      <div slot="right">
        <Button type="success" @click="openAdd">增加</Button>
        <Button type="warning" @click="openEdit">编辑</Button>
        <Button type="error">删除</Button>
        <modal :show="modal" @cancel="modal = false" :title="title">
          <!--编辑/查看-->
          <div v-if="!isEdit">
            <Form :model="formLeft" label-position="right" :label-width="80">
              <FormItem label="标准">
                <Input v-model="formLeft.input1" style="width: 300px"></Input>
              </FormItem>
              <FormItem label="描述">
                <Input v-model="formLeft.input2" style="width: 300px"></Input>
              </FormItem>
            </Form>
          </div>
          <!--新增-->
          <div v-else>
            我是新增的内容
          </div>
        </modal>
      </div>
    </table-tools-bar>
    <Table border ref="selection" :columns="columns1" :data="data1"></Table>
  </div>
</template>

<script>
import tableToolsBar from 'pages/components/tableToolsBar'
import Modal from 'pages/components/Modal'
export default {
  name: 'standard-classification',
  data () {
    return {
      isEdit: false, // 是否为编辑
      title: '',
      formInline: {
        option: '',
        describe: ''
      },
      formLeft: {
        input1: '',
        input2: '',
        input3: ''
      },
      formRight: {
        input1: '',
        input2: '',
        input3: ''
      },
      modal: false,
      value1: '',
      value2: '',
      columns1: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '选项',
          key: 'option',
          align: 'center'
        },
        {
          title: '描述',
          key: 'describe',
          width: 300,
          align: 'center'
        },
        {
          title: '创建日期',
          key: 'creationDate',
          align: 'center'
        },
        {
          title: '创建人',
          key: 'founder',
          align: 'center'
        },
        {
          title: 'Action',
          key: 'action',
          width: 150,
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
                    this.viewData(params.index)
                  }
                }
              }, '查看')
            ])
          }
        }
      ],
      data1: [
        {
          option: '中国标准',
          describe: 'XXXXXXXXXXXXXXXXXXX',
          founder: 'Mr.li',
          creationDate: '2018-9-05'
        },
        {
          option: '企业标准',
          describe: 'XXXXXXXXXXXXXXXXXXX',
          founder: 'Mr.li',
          creationDate: '2018-9-05'
        },
        {
          option: '欧盟标准',
          describe: 'XXXXXXXXXXXXXXXXXXX',
          founder: 'Mr.li',
          creationDate: '2018-9-05'
        },
        {
          option: '美国标准',
          describe: 'XXXXXXXXXXXXXXXXXXX',
          founder: 'Mr.li',
          creationDate: '2018-9-05'
        }
      ]
    }
  },
  methods: {
    handleQuery () {

    },
    openAdd () {
      this.modal = true
      this.isEdit = true
      this.title = this.modalTitle
    },
    // 编辑
    openEdit () {
      this.modal = true
      this.isEdit = false
      this.title = '编辑标准'
    },
    // 查看
    viewData () {
      this.modal = true
      this.isEdit = false
      this.title = this.modalTitle
    }
  },
  components: {
    tableToolsBar,
    Modal
  },
  computed: {
    modalTitle () {
      return this.isEdit ? '新增标准' : '查看标准'
    }
  }
}
</script>

<style lang="less" scoped>
  .standard-classification {
  }

</style>
