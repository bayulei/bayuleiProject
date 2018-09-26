export default {
  name: 'enterpriseStandardLedger',
  data () {
    return {
      showSarCompileModal: false,
      saveStandPlanBtn: true,
      saveSelectedDatas: '',
      sarCompile: {
        busStandSubclass: ''
      },
      submitSarCompile: {
        id: ''
      },
      exportDataObj: {
        jsonData: ''
      },
      sarCompileListDatas: [],
      sarCompileTotal: 0,
      page: 1,
      pageSize: 10,
      loading: false,
      sarCompileTable: [
        {
          type: 'selection',
          width: 60,
          fixed: 'left',
          align: 'center'
        },
        {
          type: 'index',
          width: 60,
          align: 'center'
        },
        {
          title: '标准大类名称',
          width: 100,
          key: 'busStandClassify'
        },
        {
          title: '标准细类名称',
          width: 100,
          key: 'busStandSubclass'
        },
        {
          title: '分类代号',
          width: 100,
          key: 'classifyCode'
        },
        {
          title: '标准编号',
          width: 100,
          key: 'standNumber'
        },
        {
          title: '标准名称',
          width: 100,
          key: 'standName'
        },
        {
          title: '发布日期',
          width: 100,
          key: 'issueTime'
        },
        {
          title: '实施日期',
          width: 100,
          key: 'putTime'
        },
        {
          title: '实施年份',
          width: 100,
          key: 'putYear'
        },
        {
          title: '代替标准',
          width: 100,
          key: 'replaceStandNum'
        },
        {
          title: '责任部门',
          width: 100,
          key: 'dutyUnit'
        },
        {
          title: '备注',
          width: 100,
          key: 'remarks'
        },
        {
          title: '操作',
          key: 'action',
          fixed: 'right',
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
                    this.showSarCompileModal = true
                    this.showSarCompileTitle = '修改标准台账'
                    this.submitSarCompile = params.row
                  }
                }
              }, '编辑'),
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
                    this.deleteSarCompile(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      sarCompileRules: {},
      submitSarCompileFormRules: {},
      showSarCompileTitle: ''
    }
  },
  methods: {
    // 分页查询标准台账
    searchSarCompile () {
      this.sarCompile.page = this.page
      this.sarCompile.pageSize = this.rows
      this.$http.get('lawss/sarBusSarCompile/page', this.sarCompile, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.sarCompileListDatas = res.data.list
        this.sarCompileTotal = res.data.count
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
    // 新增或修改标准台账
    openAddModal () {
      this.showSarCompileModal = true
      this.showSarCompileTitle = '新增标准台账'
      this.$refs['submitSarCompile'].resetFields()
    },
    saveSarCompile () {
      if (this.submitSarCompile.issueTime != null) {
        this.submitSarCompile.issueTime = this.$dateFormat(this.submitSarCompile.issueTime, 'yyyy-MM-dd')
      }
      if (this.submitSarCompile.putTime != null) {
        this.submitSarCompile.putTime = this.$dateFormat(this.submitSarCompile.putTime, 'yyyy-MM-dd')
      }
      if (this.submitSarCompile.id == null || this.submitSarCompile.id === '') {
        this.$http.post('lawss/sarBusSarCompile/createSarBusSarCompile', this.submitSarCompile, {
          _this: this
        }, res => {
          this.showSarCompileModal = false
          this.searchSarCompile()
        }, e => {
        })
      } else {
        this.$http.put('lawss/sarBusSarCompile/updateSarBusSarCompile', this.submitSarCompile, {
          _this: this
        }, res => {
          this.showSarCompileModal = false
          this.searchSarCompile()
        }, e => {})
      }
    },
    cancelSubmit () {
      this.showSarCompileModal = false
    },
    // 删除标准台账
    deleteSarCompile (id) {
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.put('lawss/sarBusSarCompile/deleteSarBusSarCompile', {
            id: id
          }, {
            _this: this
          }, res => {
            this.searchSarCompile()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 导出标准台账
    getSelectedDatas (selection, row) {
      this.saveSelectedDatas = selection
    },
    exportSarCompile () {
      if (this.saveSelectedDatas != null && this.saveSelectedDatas !== '') {
        let jsonData = JSON.stringify(this.saveSelectedDatas)
        window.location.href = '/api/lawss/sarBusSarCompile/exportSarBusSarCompile?jsonData=' + jsonData
      } else {
        this.$Message.error('请选择要导出的数据')
      }
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.searchSarCompile()
  }
}
