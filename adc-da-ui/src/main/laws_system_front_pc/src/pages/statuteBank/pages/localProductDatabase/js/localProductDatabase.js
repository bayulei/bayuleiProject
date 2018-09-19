export default {
  name: 'localProductDatabase',
  data () {
    return {
      loading: false,
      total: 0,
      localProTableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '产品/项目代号',
          key: 'prodectCode',
          render: (h, params) => {
            return h('div', [
              h('a', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.showLocalProductData(params.row)
                  }
                }
              }, params.row.prodectCode) ])
          }
        },
        {
          title: '产品系列',
          key: 'productSet'
        },
        {
          title: '产品名称',
          key: 'productName'
        },
        {
          title: '产品品牌',
          key: 'productBrand'
        },
        {
          title: '产品类别',
          key: 'productTypeShow'
        },
        {
          title: '能源种类',
          key: 'energyKindShow'
        },
        {
          title: '创建人',
          key: 'creationUser'
        },
        {
          title: '创建日期',
          key: 'creationTime'
        },
        {
          title: '操作',
          key: 'action',
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
                    this.updateOrLookClick(params.row, true)
                  }
                }
              }, '查看'),
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
                    this.updateOrLookClick(params.row, false)
                  }
                }
              }, '编辑')
            ])
          }
        }
      ],
      localProTableList: [],
      localProDataTableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '国家/地区',
          key: 'country'
        },
        {
          title: '标准号',
          key: 'shownumber'
        },
        {
          title: '标准名称',
          key: 'showname'
        },
        {
          title: '操作',
          key: 'action',
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
                    this.updateOrLookClick(params.row, true)
                  }
                }
              }, '查看'),
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
                    this.updateOrLookClick(params.row, false)
                  }
                }
              }, '编辑')
            ])
          }
        }
      ], // 本地产品库和标准法规的关联显示表格
      localProDataTableList: [],
      selectProDataTableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '国家/地区',
          key: 'country'
        },
        {
          title: '标准号',
          key: 'shownumber'
        },
        {
          title: '标准名称',
          key: 'showname'
        },
        {
          title: '操作',
          key: 'action',
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
                    this.updateOrLookClick(params.row, true)
                  }
                }
              }, '查看'),
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
                    this.updateOrLookClick(params.row, false)
                  }
                }
              }, '编辑')
            ])
          }
        }
      ], // 本地产品库和标准法规的关联显示表格
      selectProDataTableList: [],
      localProTableSearch: {
        page: 1,
        pageSize: 10
      },
      modalProductAddShowflag: false,
      modalProDataShowflag: false, // 产品项目与标准法规详细关联
      formdisableflag: false,
      modalProDataAddflag: false,
      addOrUpdateFlag: 1, // 1:新增  2：修改
      localProEO: {
        prodectCode: '',
        productSet: '',
        productName: '',
        productBrand: '',
        productType: '',
        energyKind: '',
        carModeFile: ''
      },
      productTypeOptions: [],
      emergyKindOptions: [],
      proveTypeOptions: [], // 认证类型
      productDataList: '', // 记录某个产品已经对应的所有的数据标准法规数据
      selectProDataList: [] // 记录给某个产品新增法规或标准数据时，选择的表格数据项目
    }
  },
  methods: {
    // 分页点击后方法
    pageChange (page) {
      this.localProTableSearch.page = page
      this.getLocalProductTable()
    },
    // 分页每页显示数改变后方法
    pageSizeChange (pageSize) {
      this.localProTableSearch.pageSize = pageSize
      this.getLocalProductTable()
      // 此处需要调用接口，修改个人配置
    },
    // 查询本地产品
    getLocalProductTable () {
      this.$http.get('lawss/sarProductInfo/getProductInfoPage', this.localProTableSearch, {
        _this: this, loading: 'loading'
      }, res => {
        console.log(res)
        this.localProTableList = res.data.list
        this.total = res.data.count
      }, e => {
      })
    },
    addProductModal () {
      this.modalProductAddShowflag = true
      this.addOrUpdateFlag = 1
      this.localProEO = {}
      this.formdisableflag = false
    },
    saveOrUpdateLocalPro () {
      // 新增
      if (this.addOrUpdateFlag === 1) {
        this.$http.post('lawss/sarProductInfo/addSarProductInfo', this.localProEO, {
          _this: this
        }, res => {
          this.modalProductAddShowflag = false
          this.getLocalProductTable()
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarProductInfo/updateSarProductInfo', this.localProEO, {
          _this: this
        }, res => {
          this.modalProductAddShowflag = false
          this.getLocalProductTable()
        }, e => {
        })
      }
    },
    deleteLocalProduct () {
    },
    updateOrLookClick (item, flag) {
      this.addOrUpdateFlag = 2
      this.modalProductAddShowflag = true
      this.formdisableflag = flag
      this.localProEO = item
      let a = []
      if (this.localProEO.productType != null && !(this.localProEO.productType instanceof Array)) {
        a = this.localProEO.productType.split(',')
        this.localProEO.productType = a // 产品类型
      }
      if (this.localProEO.energyKind != null && !(this.localProEO.energyKind instanceof Array)) {
        a = this.localProEO.energyKind.split(',')
        this.localProEO.energyKind = a // 能源种类
      }
    },
    // 点击产品/项目代号调用方法
    showLocalProductData (item) {
      this.modalProDataShowflag = true
      this.localProEO = item
      let a = []
      if (this.localProEO.productType != null && !(this.localProEO.productType instanceof Array)) {
        a = this.localProEO.productType.split(',')
        this.localProEO.productType = a // 产品类型
      }
      if (this.localProEO.energyKind != null && !(this.localProEO.energyKind instanceof Array)) {
        a = this.localProEO.energyKind.split(',')
        this.localProEO.energyKind = a // 能源种类
      }
      this.$http.post('lawss/sarProductInfo/selectProductLawAndStand', {id: this.localProEO.id}, {
        _this: this
      }, res => {
        this.productDataList = res.data
        this.localProDataTableList = this.productDataList[this.proveTypeOptions[0].value]
        console.log(this.localProDataTableList)
      }, e => {
      })
    },
    tabsClick (name) {
      this.localProDataTableList = this.productDataList[name]
    },
    // 根据产品属性（产品种类，能源种类），匹配标准中的属性，筛选出符合属性的标准
    selectStandLawsForProduct () {
      this.modalProDataAddflag = true
      this.$http.post('lawss/sarProductInfo/selectLawAndStandByPro', this.localProEO, {
        _this: this
      }, res => {
        console.log(res)
        this.selectProDataTableList = res.data
      }, e => {
      })
    },
    // 将符合条件数据保存到数据库
    saveProData () {
      this.modalProDataAddflag = false
      this.modalProDataShowflag = true
      this.$http.post('lawss/sarProductInfo/selectLawAndStandByPro', this.localProEO, {
        _this: this
      }, res => {
        console.log(res)
        this.selectProDataTableList = res.data
      }, e => {
      })
    },
    selectOneStand (alldata, nowdata) {
      let nowobje = {}
      nowobje.productId = this.localProEO.id
      nowobje.standType = nowdata.standType
      nowobje.standId = nowdata.standId
      this.selectProDataList.push(nowobje)
      console.log(this.selectProDataList)
    },
    selectAllStand (alldata) {
      this.selectProDataList = []
      for (let i = 0; i < alldata.length; i++) {
        let nowobje = {}
        nowobje.productId = this.localProEO.id
        nowobje.standType = alldata[i].standType
        nowobje.standId = alldata[i].standId
        this.selectProDataList.push(nowobje)
      }
    },
    cancleSelectStand (alldata, nowdata) {
      let nowobje = {}
      nowobje.productId = this.localProEO.id
      nowobje.standType = nowdata.standType
      nowobje.standId = nowdata.standId
      this.selectProDataList.pop(nowobje)
      console.log(this.selectProDataList)
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.getLocalProductTable()
    // 查询各下拉框数据
    this.$http.get('sys/dictype/getDicTypeListCode', '', {
      _this: this
    }, res => {
      this.productTypeOptions = res.data.PRODUCTTYPE
      this.emergyKindOptions = res.data.ENERGYTYPES
      this.proveTypeOptions = res.data.PROVETYPE
    }, e => {
    })
  }
}
