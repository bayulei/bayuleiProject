export default {
  name: 'DomesticRegulationsDatabase',
  data () {
    return {
      modal2: false,
      showLawsInfoModal: false,
      showLawsItemsModal: false,
      saveInfoBtn: true,
      lawsInfo: {
        lawsNum: '',
        lawsName: '',
        issueTime: ''
      },
      SarLawsInfoEO: {
        editLawsId: '',
        country: '中国',
        lawsProperty: '',
        lawsNumber: '',
        lawsName: '',
        issueUnit: '',
        lawsState: '',
        issueTime: '',
        putTime: '',
        replaceLawsNum: '',
        applyArctic: '',
        energyKind: '',
        applyAuth: '',
        responsibleUnit: '',
        linkUri: ''
      },
      lawsItemsSearch: {
        responsibleUnit: ''
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      lawsInfoImport: {},
      tableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '文件号',
          key: 'lawsNumber'
        },
        {
          title: '文件性质',
          key: 'propertyName'
        },
        {
          title: '文件名称',
          key: 'lawsName'
        },
        {
          title: '发布单位',
          key: 'issueUnit'
        },
        {
          title: '文件状态',
          key: 'stateName'
        },
        {
          title: '发布日期',
          key: 'issueTime'
        },
        {
          title: '实施日期',
          key: 'putTime'
        },
        {
          title: '适用车型',
          key: 'applyArctic'
        },
        {
          title: '能源种类',
          key: 'energyKind'
        },
        {
          title: '适用认证',
          key: 'applyAuth'
        },
        {
          title: '修改时间',
          key: 'modifyTime'
        },
        {
          title: '操作',
          key: 'action',
          // fixed: 'right',
          width: 250,
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
                    this.showLawsItemsModal = true
                    this.searchLawsItems(params.row.id)
                  }
                }
              }, '查看表单'),
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
                    this.edit(params.row)
                    this.saveInfoBtn = false
                  }
                }
              }, '属性'),
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
                    this.edit(params.row)
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
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
      itemsTotal: 0,
      itemPage: 1,
      itemPageSize: 10,
      itemsTableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '条目号',
          key: 'itemsNum'
        },
        {
          title: '条目名称',
          key: 'itemsName'
        },
        {
          title: '涉及零部件',
          key: 'parts'
        },
        {
          title: '特殊生效时间',
          key: 'tackTime'
        },
        {
          title: '适用车型',
          key: 'applyArctic'
        },
        {
          title: '能源类型',
          key: 'energyKind'
        },
        {
          title: '责任部门',
          key: 'responsibleUnit'
        },
        {
          title: '备注',
          key: 'remarks'
        },
        {
          title: '操作',
          key: 'action',
          // fixed: 'right',
          width: 200,
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
                    this.edit(params.row)
                    this.saveInfoBtn = false
                  }
                }
              }, '属性'),
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
                    this.edit(params.row)
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.removeLawsItems(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      itemsData: [],
      data: [],
      lawsPropertyOptions: '',
      lawsStatusOptions: '',
      lawsInfoRules: {},
      lawsInfoFormRules: {
        lawsName: [
          { required: true, message: '文件名称不能为空', trigger: 'blur' }
        ],
        lawsState: [
          { required: true, message: '文件状态不能为空', trigger: 'blur' }
        ],
        issueTime: [
          { required: true, message: '发布日期不能为空', trigger: 'blur' }
        ],
        putTime: [
          { required: true, message: '实施日期不能为空', trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    // 分页查询
    searchLawsInfo () {
      let SarLawsInfoEOPage = this.lawsInfo
      SarLawsInfoEOPage.page = this.page
      SarLawsInfoEOPage.pageSize = this.rows
      if (SarLawsInfoEOPage.issueTime != null && SarLawsInfoEOPage.issueTime !== '') {
        SarLawsInfoEOPage.issueTime = this.$dateFormat(SarLawsInfoEOPage.issueTime, 'yyyy-MM-dd')
      }
      this.$http.get('lawss/sarLawsInfo/page', SarLawsInfoEOPage, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.data = res.data.list
        this.total = res.data.count
      }, e => {

      })
    },
    pageChange (page) {
      this.page = page
      this.searchLawsInfo()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.searchLawsInfo()
    },
    // 打开新增模态框
    openLawsModal () {
      this.showLawsInfoModal = true
      this.saveInfoBtn = true
    },
    // 点击编辑按钮触发
    edit (row) {
      this.showLawsInfoModal = true
      this.saveInfoBtn = true
      this.SarLawsInfoEO = row
      this.SarLawsInfoEO.editLawsId = row.id
    },
    // 提交新增/修改
    saveLawsInfo () {
      this.SarLawsInfoEO.issueTime = this.$dateFormat(this.SarLawsInfoEO.issueTime, 'yyyy-MM-dd')
      this.SarLawsInfoEO.putTime = this.$dateFormat(this.SarLawsInfoEO.putTime, 'yyyy-MM-dd')
      if (this.SarLawsInfoEO.editLawsId == null || this.SarLawsInfoEO.editLawsId === '') {
        this.$http.post('lawss/sarLawsInfo/createLawsInfo', this.SarLawsInfoEO, {
          _this: this
        }, res => {
          this.showLawsInfoModal = false
          this.searchLawsInfo()
        }, e => {

        })
      } else {
        this.$http.put('lawss/sarLawsInfo/updateLawsInfo', this.SarLawsInfoEO, {
          _this: this
        }, res => {
          this.showLawsInfoModal = false
          this.searchLawsInfo()
        }, e => {})
      }
    },
    cancelAdd () {
      this.$refs.showLawsInfoModal.toggleClose()
    },
    // 删除
    remove (id) {
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.put('lawss/sarLawsInfo/deleteLawsInfos', {
            id: id
          }, {
            _this: this
          }, res => {
            this.searchLawsInfo()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    openFile () {
      $('#lawsInfoFile').click()
    },
    lawsInfoFileBeforeUpload (val) {
      let file = this.$refs.lawsInfoFile.files[0].name
      console.log(file)
    },
    // 导入
    importLawsInfo () {
      let file = this.$refs.lawsInfoFile.files[0]
      this.$http.post('lawss/sarLawsInfo/importLawsInfos', {
        file: file
      }, {
        _this: this
      }, res => {
        this.searchLawsInfo()
      }, e => {

      })
    },
    // 分页查询条目
    searchLawsItems (lawsId) {
      this.$http.get('lawss/sarLawsItems/page', {
        lawsId: lawsId,
        page: this.itemPage,
        pageSize: this.itemPageSize,
        responsibleUnit: this.lawsItemsSearch.responsibleUnit
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.itemsData = res.data.list
        this.itemsTotal = res.data.count
      }, e => {

      })
    },
    // 删除条目
    removeLawsItems (id) {
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.put('lawss/sarLawsItems/deleteLawsItems', {
            id: id
          }, {
            _this: this
          }, res => {
            this.searchLawsItems()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 加载数据字典
    loadDicTypeDatas1 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'SARPROPERTY'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.lawsPropertyOptions = res.data
        }
      }, e => {

      })
    },
    loadDicTypeDatas2 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'STANDSTATE'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.lawsStatusOptions = res.data
        }
      }, e => {
      })
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {
    page (newVal, oldVal) {
      //
    }
  },
  mounted () {
    this.searchLawsInfo()
    this.loadDicTypeDatas1()
    this.loadDicTypeDatas2()
  }
}
