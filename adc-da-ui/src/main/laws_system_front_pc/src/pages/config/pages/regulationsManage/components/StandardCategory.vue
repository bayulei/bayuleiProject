<!-- 标准类别 -->
<template>
  <div class="standard-category">
      <table-tools-bar>
        <div slot="left">
            <label-input v-model="standardForm.standName" placeholder="请输入选项" label="选项"></label-input>
            <label-input v-model="standardForm.standCode" placeholder="请输入选项" label="数据编码"></label-input>
            <Button type="info" class="query-button" @click="selectCategory">查询</Button>
        </div>
        <div slot="right">
          <Button type="info" @click="categoryAdd">增加</Button>
          <Button type="error"  @click="categoryBatchDel">删除</Button>
          <!--显示模态框-->
          <Modal v-model="categoryModal" :title="categoryTitle" :class="{ 'hide-modal-footer': modalType === 3 }" width="450"
                 @on-ok="saveCategory">
              <Form :model="categoryModelAdd" label-position="right" :label-width="80">
                <input v-model="categoryModelAdd.id" v-show="false">
                <FormItem label="选项">
                  <Input v-model="categoryModelAdd.parts" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
                </FormItem>
                <FormItem label="数据编码">
                  <Input v-model="categoryModelAdd.coding" :style="{width:6+'rem'}" :disabled='modalType === 3'></Input>
                </FormItem>
              </Form>
          </Modal>
        </div>
      </table-tools-bar>
      <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="categoryTable" :data="categoryData" @on-selection-change=" handleSelectone">
      </Table>
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'standard-category',
  data () {
    return {
      modalType: '',
      categoryTitle: '', // 模态框标题
      selectNum: '', // 接收选中行数据
      deleteType: '',
      standardForm: {
        standName: '', // 选项
        standCode: '', // 数据编码
        id: '' // 数据id
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      categoryModelAdd: {
        parts: '', // 模态框标准
        coding: ''// 数据编码
      },
      categoryModal: false, // 模态框是否打开
      // 表格表头
      categoryTable: [
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
                    this.categoryEdit(params.row)
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
                    this.viewData(params.row)
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
                    this.categoryDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 表格内容
      categoryData: []
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
    },
    // 对话框
    instance (type, content) {
      const title = '请选择'
      switch (type) {
        case 'info':
          this.$Modal.info({
            title: title,
            content: content
          })
          break
        case 'success':
          this.$Modal.success({
            title: title,
            content: content
          })
          break
        case 'warning':
          this.$Modal.warning({
            title: title,
            content: content
          })
          break
        case 'error':
          this.$Modal.error({
            title: title,
            content: content
          })
          break
      }
    },
    // 删除选择提示框
    confirm (content, id, url, type) {
      let paramId = {
        dicTypeEOId: id
      }
      let paramIds = {
        ids: id
      }
      this.$Modal.confirm({
        title: '请选择',
        content: content,
        onOk: () => {
          this.$http.delete(url, type === 1 ? paramId : paramIds, {
            _this: this
          }, res => {
            this.selectCategory()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 新增
    categoryAdd () {
      this.categoryModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.categoryTitle = '新增标准'
      this.categoryModelAdd.parts = ''
      this.categoryModelAdd.coding = ''
      this.categoryModelAdd.id = ''
    },
    // 编辑
    categoryEdit (item) {
      this.categoryModal = true
      this.modalType = 2
      this.categoryTitle = '编辑标准'
      this.categoryModelAdd.parts = item.dicTypeName
      this.categoryModelAdd.coding = item.dicTypeCode
      this.categoryModelAdd.id = item.id
    },
    // 查看
    viewData (row) {
      this.categoryModal = true
      this.modalType = 3
      this.categoryTitle = '查看标准'
      this.categoryModelAdd.parts = row.dicTypeName
      this.categoryModelAdd.coding = row.dicTypeCode
      this.categoryModelAdd.id = row.id
    },
    // 删除
    categoryDel (id) {
      this.handleSelectAll(false)
      let url = 'lawss/msgModule'
      let type = 1
      this.confirm('确定删除这一条数据', id, url, type)
    },
    // 批量删除
    categoryBatchDel () {
      if (this.selectNum === '' || this.selectNum.length === 0) {
        this.instance('warning', '请选择一条数据进行删除')
      } else {
        let delIds = []
        for (let i = 0; i < this.selectNum.length; i++) {
          delIds.push(this.selectNum[i].id)
        }
        delIds = delIds.join(',')
        const url = 'sys/dictype/deleteArr'
        this.confirm('确认删除该这些数据', delIds, url)
      }
    },
    pageChange (page) {
      this.page = page
      this.selectCategory()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectCategory()
    },
    // 加载表格
    selectCategory () {
      this.$http.get('sys/dictype/page', {
        pageNo: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'JKSADFH564S'
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.categoryData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveCategory () {
      let data = {
        dicTypeName: this.categoryModelAdd.parts,
        dicTypeCode: this.categoryModelAdd.coding,
        dicId: 'JKSADFH564S'
      }
      if (this.modalType === 1) {
        this.$http.postData('sys/dictype/create', data, {
          _this: this
        }, res => {
          this.selectCategory()
        }, e => {
        })
      } else if (this.modalType === 2) {
        this.$http.putData('sys/dictype', {
          dicTypeName: this.categoryModelAdd.parts,
          dicTypeCode: this.categoryModelAdd.coding,
          dicId: 'JKSADFH564S',
          id: this.categoryModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectCategory()
        }, e => {
        })
      }
    }
  },
  mounted () {
    this.selectCategory()
  }
}
</script>

<style lang="less">
  .standard-category {}
</style>
