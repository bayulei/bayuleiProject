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
        standCode: ''
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
      classRules: {
        dicTypeName: [
          { required: true, message: '选项不能为空', trigger: 'blur' }
        ],
        dicTypeCode: [
          { required: true, message: '数据编码不能为空', trigger: 'blur' }
        ]
      },
      // 发送数据
      classModelAdd: {
        // 数据标准
        dicTypeName: '',
        // 数据编码
        dicTypeCode: '',
        // 唯一辨识
        dicId: 'FDFDFDVFTGR',
        // 数据id
        id: '',
        // 创建日期
        creationDate: new Date(),
        // 创建人
        founder: ''
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
            this.selectClass()
            this.page = 1
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 新增
    classAdd () {
      this.$refs['classModelAdd'].resetFields()
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
      this.classModelAdd = JSON.parse(JSON.stringify(row))
      // this.classModelAdd = row
    },
    // 查看
    viewData (row) {
      this.classModal = true
      this.modalType = 3
      this.classTitle = '查看标准'
      this.classModelAdd = JSON.parse(JSON.stringify(row))
    },
    // 删除
    classDel (id) {
      this.handleSelectAll(false)
      let url = 'sys/dictype/delete'
      let type = 1
      this.confirm('确定删除这一条数据', id, url, type)
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
        const url = 'sys/dictype/deleteArr'
        this.confirm('确认删除该这些数据', delIds, url)
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
      // let DicTypeEOPage = this.classModelAdd
      // DicTypeEOPage.page = this.page
      // DicTypeEOPage.pageSize = this.rows
      // DicTypeEOPage.dicTypeName = this.standardForm.standName
      // DicTypeEOPage.dicTypeCode = this.standardForm.standCode
      let DicTypeEOPage = {
        page: this.page,
        pageSize: this.rows,
        dicTypeName: this.standardForm.standName,
        dicTypeCode: this.standardForm.standCode,
        dicId: 'FDFDFDVFTGR'
      }
      this.$http.get('sys/dictype/page', DicTypeEOPage, {
        _this: this,
        loading: 'loading'
      }, res => {
        console.log(res)
        this.classData = res.data.list
        this.total = res.data.count
        this.page = 1
      }, e => {
      })
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
