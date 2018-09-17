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
          key: 'prodectCode'
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
      localProTableSearch: {
        page: 1,
        pageSize: 10
      },
      modalProductAddShowflag: false,
      formdisableflag: false,
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
      emergyKindOptions: []
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
    }, e => {
    })
  }
}
