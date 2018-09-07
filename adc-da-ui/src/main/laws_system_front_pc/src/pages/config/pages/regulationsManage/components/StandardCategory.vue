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
        <Button type="success" @click="categoryAdd">增加</Button>
        <Button type="warning" @click="categoryEdit">编辑</Button>
        <Button type="error">删除</Button>
        <!--显示模态框-->
        <Modal v-model="categoryModal" :title="categoryTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="400">
          <!--编辑/查看-->
          <div v-if="modalType === 2 || modalType === 3">
            <Form :model="formLeft" label-position="right" :label-width="80">
              <FormItem label="标准">
                <Input v-model="formLeft.input1" style="width: 200px" :disabled='modalType === 3'></Input>
              </FormItem>
              <FormItem label="描述">
                <Input v-model="formLeft.input2" style="width: 200px" :disabled='modalType === 3'></Input>
              </FormItem>
              <FormItem label="创建人">
                <Input v-model="formLeft.input3" style="width: 200px" :disabled='modalType === 3'></Input>
              </FormItem>
            </Form>
          </div>
          <!--新增-->
          <div v-else-if="modalType === 1">
            我是新增的内容
          </div>
        </Modal>
      </div>
    </table-tools-bar>
    <Table border ref="selection" :columns="categoryTable" :data="data1"></Table>
  </div>
</template>

<script>
import tableToolsBar from 'pages/components/TableToolsBar'
export default {
  name: 'standard-classification',
  data () {
    return {
      modalType: '',
      categoryTitle: '',
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
      categoryModal: false,
      value1: '',
      value2: '',
      categoryTable: [
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
    // 新增
    categoryAdd () {
      this.categoryModal = true
      this.modalType = 1
      this.categoryTitle = '新增标准'
    },
    // 编辑
    categoryEdit () {
      this.categoryModal = true
      this.modalType = 2
      this.categoryTitle = '编辑标准'
    },
    // 查看
    viewData () {
      this.categoryModal = true
      this.modalType = 3
      this.categoryTitle = '查看标准'
      // $('.ivu-modal-footer').addClass('isDisplay')
    }
  },
  components: {
    tableToolsBar
  },
  computed: {
  }
}
</script>

<style lang="less">
  .standard-classification {}
  .hide-modal-footer{
    .ivu-modal-footer{
      display: none;
    }
  }
  .unable-edit{
    disabled:disabled
  }
</style>
