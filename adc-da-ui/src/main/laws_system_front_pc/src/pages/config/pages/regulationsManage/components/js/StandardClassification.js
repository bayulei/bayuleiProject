export default {
  name: 'standard-classification',
  data () {
    return {
      modalType: '',
      // 模态框标题
      classTitle: '',
      selectNum: '',
      // 接收选中行数据
      standardForm: {
        // 选项
        standName: '',
        // 数据编码
        standCode: '',
        // 数据id
        id: ''
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static'
      },
      //  规范
      classRules: {},
      // 发送数据
      classModelAdd: {
        dicTypeName: '', // 模态框标准
        dicTypeCode: '', // 数据编码
        dicId: 'FDFDFDVFTGR' // 唯一辨识
      },
      classModal: false, // 模态框是否打开
      // 表格表头
      classTable: [
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
                    this.classEdit(params.row)
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
                    this.classDel(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      // 表格内容
      classData: []
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
    // 清除弹窗内容
    cleanValue () {

    },
    // 新增
    classAdd () {
      this.$nextTick(() => {
        this.$refs['classModelAdd'].resetFields()
      })
      this.classModal = true
      this.modalType = 1
      // 取消所有的选中效果
      this.handleSelectAll(false)
      this.classTitle = '新增标准'
    },
    // 编辑
    classEdit (row) {
      console.log(row)
      this.classModal = true
      this.modalType = 2
      this.classTitle = '编辑标准'
      this.classModelAdd = row
    },
    // 查看
    viewData (row) {
      this.classModal = true
      this.modalType = 3
      this.classTitle = '查看标准'
      this.classModelAdd.parts = row.dicTypeName
      this.classModelAdd.coding = row.dicTypeCode
      this.classModelAdd.id = row.id
    },
    // 删除
    classDel (id) {
      this.handleSelectAll(false)
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.delete('sys/dictype/delete', {
            dicTypeEOId: id
          }, {
            _this: this
          }, res => {
            this.selectClass()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 批量删除
    classBatchDel () {
      if (this.selectNum === '' || this.selectNum.length === 0) {
        this.instance('warning', '请选择一条数据进行删除')
      } else {
        let delIds = []
        for (let i = 0; i < this.selectNum.length; i++) {
          delIds.push(this.selectNum[i].id)
        }
        delIds = delIds.join(',')
        this.$Modal.confirm({
          title: '确认删除',
          content: '<p>确认删除该这些数据？</p>',
          onOk: () => {
            this.$http.delete('sys/dictype/deleteArr', {
              ids: delIds
            }, {
              _this: this
            }, res => {
              this.selectClass()
            }, e => {
            })
          },
          onCancel: () => {
          }
        })
      }
    },
    closeModal () {
      this.classModal = false
    },
    pageChange (page) {
      this.page = page
      this.selectClass()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectClass()
    },
    // 加载表格
    selectClass () {
      let DicTypeEOPage = this.classModelAdd
      DicTypeEOPage.page = this.page
      DicTypeEOPage.pageSize = this.rows
      DicTypeEOPage.dicTypeName = this.standardForm.standName
      DicTypeEOPage.dicTypeCode = this.standardForm.standCode
      this.$http.get('sys/dictype/page', DicTypeEOPage, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.classData = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    // 提交新增/修改
    saveClass () {
      let data = this.classModelAdd
      if (this.modalType === 1) {
        this.$http.postData('sys/dictype/create', data, {
          _this: this
        }, res => {
          this.selectClass()
          this.classModal = false
        }, e => {
        })
      } else if (this.modalType === 2) {
        this.$http.putData('sys/dictype', data, {
          _this: this
        }, res => {
          this.selectClass()
          this.classModal = false
        }, e => {

        })
      }
    }
  },
  mounted () {
    this.selectClass()
  }
}
