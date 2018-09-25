export default {
  name: 'informationCenterConfig',
  data () {
    return {
      informationModal: false,
      informationTitle: '',
      modalType: '',
      loading: false,
      total: 0,
      page: 1,
      rows: 10,
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static'
      },
      // 接收选中行数据
      informationModelAdd: {
        // 模块类型
        moduleType: '资料中心',
        // 模块名称
        moduleName: '',
        // id
        id: ''
      },
      // 规范
      informationRules: {},
      informationTable: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: '参数',
          key: 'moduleName',
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
                    this.informationEdit(params.row)
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
                    this.remove(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      informationData: []
    }
  },
  methods: {
    // 新增
    informationAdd () {
      this.informationModal = true
      this.informationTitle = '新增参数'
      this.modalType = 1
      this.$refs['informationModelAdd'].resetFields()
    },
    // 编辑
    informationEdit (row) {
      this.informationModal = true
      this.informationTitle = '编辑参数'
      this.modalType = 2
      this.informationModelAdd = JSON.parse(JSON.stringify(row))
    },
    // 查看
    viewData (row) {
      this.informationModal = true
      this.modalType = 3
      this.informationTitle = '查看标准'
      this.informationModelAdd = JSON.parse(JSON.stringify(row))
    },
    // 批量删除
    // informationBatchDel () {
    //   if (this.selectNum === '' || this.selectNum.length === 0) {
    //     this.instance('warning', '请选择一条数据进行删除')
    //   } else {
    //     let delIds = []
    //     for (let i = 0; i < this.selectNum.length; i++) {
    //       delIds.push(this.selectNum[i].id)
    //     }
    //     delIds = delIds.join(',')
    //     const url = 'sys/dictype/deleteArr'
    //     this.confirm('确认删除该这些数据', delIds, url)
    //   }
    // },
    // 删除
    remove (id) {
      this.handleSelectAll(false)
      let url = '/lawss/msgModule/delete'
      let type = 1
      this.confirm('确定删除这一条数据', id, url, type)
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
        id: id
      }
      this.$Modal.confirm({
        title: '请选择',
        content: content,
        onOk: () => {
          this.$http.delete(url, type !== 1 ? paramId : paramIds, {
            _this: this
          }, res => {
            this.selectInformation()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    //  全选
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    // 非全选
    handleSelectone (row) {
      this.selectNum = row
    },
    pageChange (page) {
      this.page = page
      this.selectInformation()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectInformation()
    },
    closeModal () {
      this.classModal = false
    },
    // 提交
    saveInformation () {
      let data = {
        moduleType: '资料中心',
        moduleName: this.informationModelAdd.moduleName
      }
      if (this.modalType === 1) {
        this.$http.postData('lawss/msgModule', data, {
          _this: this
        }, res => {
          this.selectInformation()
          this.informationModal = false
        }, e => {
        })
      } else {
        this.$http.putData('lawss/msgModule', {
          moduleType: '资料中心',
          moduleName: this.informationModelAdd.moduleName,
          id: this.informationModelAdd.id
        }, {
          _this: this
        }, res => {
          this.selectInformation()
          this.informationModal = false
        }, e => {
        })
      }
    },
    // 加载表格
    selectInformation () {
      this.$http.get('lawss/msgModule/page', {
        pageNo: this.page,
        pageSize: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.informationData = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  mounted () {
    this.selectInformation()
  }
}
