export default {
  name: 'DomesticRegulationsDatabase',
  data () {
    return {
      modal2: false,
      showLawsInfoModal: false,
      showLawsItemsModal: false,
      addLawsItemsModal: false,
      importItemsModal: false,
      saveInfoBtn: true,
      saveLawsItemsBtn: true,
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
      SarLawsItemsEO: {
        id: '',
        lawsId: '',
        itemsNum: '',
        itemsName: '',
        parts: '',
        tackTime: '',
        applyArctic: '',
        energyKind: '',
        responsibleUnit: '',
        remarks: ''
      },
      saveLawsId: '',
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
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
          key: 'applyArcticShow'
        },
        {
          title: '能源种类',
          key: 'energyKindShow'
        },
        {
          title: '适用认证',
          key: 'applyAuthShow'
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
          key: 'applyArcticShow'
        },
        {
          title: '能源类型',
          key: 'energyKindShow'
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
                    this.editLawsItems(params.row)
                    this.saveLawsItemsBtn = false
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
                    this.editLawsItems(params.row)
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
      lawsInfoImport: {},
      lawsItemsImport: {},
      lawsPropertyOptions: '',
      lawsStatusOptions: '',
      applyArcticOptions: '',
      energyKindOptions: '',
      applyAuthOptions: '',
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
      },
      addLawsItemsFormRules: {}
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
      this.SarLawsInfoEO = ''
      this.showLawsInfoModal = true
      this.saveInfoBtn = true
    },
    // 点击编辑按钮触发
    edit (row) {
      this.showLawsInfoModal = true
      this.saveInfoBtn = true
      this.SarLawsInfoEO = row
      this.SarLawsInfoEO.editLawsId = row.id
      this.SarLawsInfoEO.applyArctic = this.combineToArray(this.SarLawsInfoEO.applyArctic)
      this.SarLawsInfoEO.energyKind = this.combineToArray(this.SarLawsInfoEO.energyKind)
      this.SarLawsInfoEO.applyAuth = this.combineToArray(this.SarLawsInfoEO.applyAuth)
    },
    // 提交新增/修改
    saveLawsInfo () {
      this.SarLawsInfoEO.applyArctic = this.breakMultiSelect(this.SarLawsInfoEO.applyArctic)
      this.SarLawsInfoEO.energyKind = this.breakMultiSelect(this.SarLawsInfoEO.energyKind)
      this.SarLawsInfoEO.applyAuth = this.breakMultiSelect(this.SarLawsInfoEO.applyAuth)
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
      this.saveLawsId = lawsId
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
    searchLawsItemsByUnit () {
      let lawsId = this.saveLawsId
      this.searchLawsItems(lawsId)
    },
    // 打开新增条目模态框
    openAddItemsModal () {
      this.SarLawsItemsEO = ''
      this.addLawsItemsModal = true
      this.saveLawsItemsBtn = true
    },
    // 打开编辑条目模态框
    editLawsItems (row) {
      this.addLawsItemsModal = true
      this.saveLawsItemsBtn = true
      this.SarLawsItemsEO = row
      this.SarLawsItemsEO.applyArctic = this.combineToArray(this.SarLawsItemsEO.applyArctic)
      this.SarLawsItemsEO.energyKind = this.combineToArray(this.SarLawsItemsEO.energyKind)
    },
    cancelAddItems () {
      this.addLawsItemsModal = false
    },
    // 保存条目数据
    saveLawsItems () {
      this.SarLawsItemsEO.applyArctic = this.breakMultiSelect(this.SarLawsItemsEO.applyArctic)
      this.SarLawsItemsEO.energyKind = this.breakMultiSelect(this.SarLawsItemsEO.energyKind)
      this.SarLawsItemsEO.lawsId = this.saveLawsId
      if (this.SarLawsItemsEO.tackTime != null) {
        this.SarLawsItemsEO.tackTime = this.$dateFormat(this.SarLawsItemsEO.tackTime, 'yyyy-MM-dd')
      }
      if (this.SarLawsItemsEO.id == null || this.SarLawsItemsEO.id === '') {
        this.$http.post('lawss/sarLawsItems/addLawsItems', this.SarLawsItemsEO, {
          _this: this
        }, res => {
          this.addLawsItemsModal = false
          this.searchLawsItems(this.saveLawsId)
        }, e => {
        })
      } else {
        this.$http.put('lawss/sarLawsItems/updateLawsItems', this.SarLawsItemsEO, {
          _this: this
        }, res => {
          this.addLawsItemsModal = false
          this.searchLawsItems(this.saveLawsId)
        }, e => {})
      }
    },
    // 删除条目
    removeLawsItems (id) {
      this.$http.put('lawss/sarLawsItems/deleteLawsItems', {
        id: id
      }, {
        _this: this
      }, res => {
        this.searchLawsItems(this.saveLawsId)
      }, e => {
      })
    },
    // 导入条目
    importLawsItems () {
      let file = this.$refs.lawsItemsFile.files[0]
      let lawsId = this.saveLawsId
      this.$http.post('lawss/sarLawsItems/importLawsItems', {
        file: file,
        lawsId: lawsId
      }, {
        _this: this
      }, res => {
        this.searchLawsItems(this.saveLawsId)
      }, e => {

      })
    },
    // 分解多选下拉
    breakMultiSelect (value) {
      let stringValue = ''
      for (let i = 0; i < value.length; i++) {
        stringValue += value[i] + ','
      }
      return stringValue
    },
    // 多选合并为数组显示
    combineToArray (value) {
      let arrayValue = value.split(',')
      return arrayValue
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
    },
    loadDicTypeDatas3 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'PRODUCTTYPE'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.applyArcticOptions = res.data
        }
      }, e => {
      })
    },
    loadDicTypeDatas4 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'ENERGYTYPES'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.energyKindOptions = res.data
        }
      }, e => {
      })
    },
    loadDicTypeDatas5 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'PROVETYPE'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.applyAuthOptions = res.data
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
    this.loadDicTypeDatas3()
    this.loadDicTypeDatas4()
    this.loadDicTypeDatas5()
  }
}
