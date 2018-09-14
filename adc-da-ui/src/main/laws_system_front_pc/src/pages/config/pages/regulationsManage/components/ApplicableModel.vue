<!-- 适用车型 -->
<template>
  <div class="applicable-model">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
        <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
        <Button type="info" class="query-button" @click="selectApp">查询</Button>
      </div>
      <div slot="right">
        <Button type="success" @click="appAdd">增加</Button>
        <Button type="warning" @click="appEdit">编辑</Button>
        <Button type="error"  @click="appDel">删除</Button>
        <!--显示模态框-->
        <Modal v-model="appModal" :title="appTitle" :class="{ 'hide-modal-footer': modalType === 3 }"
               @on-ok="saveApp">
          <Form :model="appModelAdd" label-position="right" :label-width="80">
            <input v-model="appModelAdd.id" v-show="false">
            <FormItem label="选项">
              <Input v-model="appModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
            </FormItem>
            <FormItem label="数据编码">
              <Input v-model="appModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
            </FormItem>
          </Form>
        </Modal>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="appTable" :data="appData" @on-selection-change=" handleSelectone">
      </Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'applicable-model',
  data () {
    return {
      modalType: '',
      appTitle: '', // 模态框标题
      selectNum: '', // 接收选中行数据
      standardForm: {
        standName: '', // 选项
        standCode: '', // 数据编码
        id: '' // 数据id
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      appModelAdd: {
        parts: '', // 模态框标准
        coding: ''// 数据编码
      },
      appModal: false, // 模态框是否打开
      // 表格表头
      appTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '选项',
          key: 'dicTypeName',
          align: 'center'
        },
        {
          title: '数据编码',
          key: 'dicTypeCode',
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
          title: '操作',
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
                    this.viewData(params.row)
                  }
                }
              }, '查看')
            ])
          }
        }
      ],
      // 表格内容
      appData: []
    }
  },
  methods: {
    //  全选
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    // 非全选
    handleSelectone (row) {
      this.selectNum = row
      console.log(this.selectNum)
      console.log(this.selectNum.length)
    },
    // 选择提示框
    instance (type, content) {
      const title = '请选择'
      switch (type) {
        case 'warning':
          this.$Modal.warning({
            title: title,
            content: content
          })
          break
      }
    },
    // 新增
    appAdd () {
      this.appModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.appTitle = '新增标准'
      this.appModelAdd.parts = ''
      this.appModelAdd.coding = ''
      this.appModelAdd.id = ''
    },
    // 编辑
    appEdit () {
      console.log(this.selectNum)
      if (this.selectNum === '' || this.selectNum.length === 0) {
        this.instance('warning', '请务必选择一条数据')
      } else {
        if (this.selectNum.length === 1) {
          this.appModal = true
          this.modalType = 2
          this.appTitle = '编辑标准'
          this.appModelAdd.parts = this.selectNum[0].dicTypeName
          this.appModelAdd.coding = this.selectNum[0].dicTypeCode
          this.appModelAdd.id = this.selectNum[0].id
        } else {
          this.instance('warning', '最多只能选择一条数据')
        }
      }
    },
    // 查看
    viewData (row) {
      this.appModal = true
      this.modalType = 3
      this.appTitle = '查看标准'
      this.appModelAdd.parts = row.dicTypeName
      this.appModelAdd.coding = row.dicTypeCode
      this.appModelAdd.id = row.id
    },
    // 删除
    appDel () {
      if (this.selectNum === '' || this.selectNum.length === 0) {
        this.instance('warning', '请选择一条数据进行删除')
      } else {
        let delIds = []
        for (let i = 0; i < this.selectNum.length; i++) {
          delIds.push(this.selectNum[i].id)
        }
        console.log(delIds)
        this.$Modal.confirm({
          title: '确认删除',
          content: '<p>确认删除该条数据？</p>',
          onOk: () => {
            this.$http.put('deleteArr/{ids}', {
              id: JSON.stringify(delIds)
            }, {
              _this: this
            }, res => {
              this.selectApp()
            }, e => {
            })
          },
          onCancel: () => {
          }
        })
      }
    },
    pageChange (page) {
      this.page = page
      this.selectApp()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectApp()
    },
    // 加载表格
    selectApp () {
      this.$http.get('sys/dictype/page', {
        page: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'JKSADFH564S'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.appData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveApp () {
      if (this.modalType === 1) {
        this.$http.post('sys/dictype/create', {
          dicTypeName: this.appModelAdd.parts,
          dicTypeCode: this.appModelAdd.coding,
          dicId: 'JKSADFH564S'
        }, {
          _this: this
        }, res => {
          this.selectApp()
        }, e => {

        })
      } else {
        this.$http.put('sys/dictype', {
          dicTypeName: this.appModelAdd.parts,
          dicTypeCode: this.appModelAdd.coding,
          dicId: 1
        }, {
          _this: this
        }, res => {
          this.selectApp()
        }, e => {

        })
      }
    }
  },
  components: {},
  computed: {
  },
  mounted () {
    this.selectApp()
  }
}
</script>

<style lang="less" scoped>
  .applicable-model{

  }
</style>
