import 'zTree/js/jquery.ztree.core.js'
import 'zTree/js/jquery.ztree.excheck.js'
import 'zTree/js/jquery.ztree.exedit.js'
export default {
  name: 'DomesticStandardLibrary',
  data () {
    return {
      isAdvancedSearch: false, // 高级检索窗口是否打开
      searching: false,
      checkAll: false, // 是否全选
      indeterminate: false, // 是否半选
      selectedList: [],
      loading: false,
      total: 0,
      tableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '标准号',
          key: 'standNumber'
        },
        {
          title: '标准名称',
          key: 'standName'
        },
        {
          title: '标准性质',
          key: 'standNature'
        },
        {
          title: '标准状态',
          key: 'standState'
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
                    this.show(params.index)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.modalshowflag = true
                    this.sarStandardsInfoEO = params.row
                    this.modalshowtitle = '修改标准'
                    this.addOrUPdateFlag = 2
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.sarStandardsInfoEO.id = params.row.id
                    this.deleteStand()
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      stahndinfoList: [],
      standitemTotal: 0,
      standItemTableColumn: [
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
          title: '条目内容',
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
          key: 'emergyKindShow'
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
                    this.show(params.index)
                  }
                }
              }, '查看'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.modalItemaddShowflag = true
                    this.standItemEO = params.row
                    this.itemAddOrUPdateFlag = 2
                    let a = []
                    if (this.standItemEO.applyArctic != null && !(this.standItemEO.applyArctic instanceof Array)) {
                      a = this.standItemEO.applyArctic.split(',')
                      this.standItemEO.applyArctic = a // 适用车型
                    }
                    if (this.standItemEO.energyKind != null && !(this.standItemEO.energyKind instanceof Array)) {
                      a = this.standItemEO.energyKind.split(',')
                      this.standItemEO.energyKind = a // 能源种类
                    }
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.standItemEO.id = params.row.id
                    this.deleteStandItem()
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      standItemList: [],
      modalshowflag: false,
      importModalshowflag: false,
      menuModalFlag: false,
      formdisableflag: false,
      modalStandItemflag: false, // 标准条目显示
      modalItemaddShowflag: false, // 新增标准条目显示
      modalshowtitle: '新增标准',
      addOrUPdateFlag: 1, // 新增：1， 修改：2
      itemAddOrUPdateFlag: 1, // 新增：1， 修改：2
      menuAddOrUpdateFlag: 1, // 新增：1， 修改：2
      sarStandardsInfoEO: {
        id: '',
        standType: 'INLAND', // 标准分类
        country: 'CN',
        standSort: '',
        applyArctic: '',
        standNumber: '',
        standYear: '',
        standName: '',
        standEnName: '',
        standState: '',
        standNature: '',
        replaceStandNum: '',
        replacedStandNum: '',
        interStandNum: '',
        adoptExtent: '',
        emergyKind: '',
        applyAuth: '',
        issueTime: '',
        putTime: '',
        newcarPutTime: '',
        productPutTime: '',
        newproductPutTime: '',
        draftingUnit: '',
        draftUser: '',
        standFile: '',
        standModifyFile: '',
        draftFile: '',
        opinionFile: '',
        sentScreenFile: '',
        approvalFile: '',
        relevanceFile: '',
        standFileList: [],
        standModifyFileList: [],
        draftFileList: [],
        opinionFileList: [],
        sentScreenFileList: [],
        approvalFileList: [],
        relevanceFileList: [],
        tags: '',
        synopsis: '',
        responsibleUnit: '',
        category: '',
        remark: '',
        menuId: ''
      }, // 标准新增过程中用到的对象
      standItemEO: {
        id: '',
        standId: '',
        itemsNum: '',
        itemsName: '',
        parts: '',
        tackTime: '',
        applyArctic: '', // 试用车型
        energyKind: '', // 能源种类
        responsibleUnit: '',
        remarks: ''
      }, // 标准条目新增过程中用到对象
      sarStandardsSearch: {
        page: 1,
        pageSize: 10,
        standType: 'INLAND',
        menuId: '',
        country: '',
        standNumber: '',
        standName: '',
        standState: '',
        standNature: '',
        issueTime: '',
        applyArctic: '',
        replaceStandNum: '',
        replacedStandNum: ''
      }, // 分页查询过程中用到的对象
      standItemSearch: {
        standId: ''
      },
      sarStandardsInfoRules: {
        standSort: [
          { required: true, message: '标准类别不能为空', trigger: 'change' }
        ],
        applyArctic: [
          { required: true, type: 'array', message: '适用车型不能为空', trigger: 'change' }
        ],
        standNumber: [
          { required: true, message: '标准编号不能为空', trigger: 'change' }
        ],
        standYear: [
          { required: true, message: '标准年份不能为空', trigger: 'blur' }
        ],
        standName: [
          { required: true, message: '标准名称不能为空', trigger: 'blur' }
        ],
        standEnName: [
        ],
        standState: [
          { required: true, message: '标准状态不能为空', trigger: 'blur' }
        ],
        standNature: [
          { required: true, message: '标准性质不能为空', trigger: 'change' }
        ],
        replaceStandNum: [],
        replacedStandNum: [],
        interStandNum: [],
        adoptExtent: [],
        emergyKind: [
          { required: true, type: 'array', message: '能源种类不能为空', trigger: 'change' }],
        applyAuth: [],
        issueTime: [
          { required: true, type: 'date', message: '发布日期不能为空', trigger: 'change' }
        ],
        putTime: [
          { required: true, type: 'date', message: '实施日期不能为空', trigger: 'change' }
        ],
        newcarPutTime: [],
        productPutTime: [],
        newproductPutTime: [],
        draftingUnit: [],
        draftUser: [],
        /*standFileList: [
          { required: true, message: '文件不能为空', trigger: 'change' }
        ],*/
        standModifyFile: [],
        draftFile: [],
        opinionFile: [],
        sentScreenFile: [],
        approvalFile: [],
        relevanceFile: [],
        tags: [],
        synopsis: [],
        responsibleUnit: [],
        category: [],
        remark: []
      },
      sarMenuRules: {
        menuName: [
          { required: true, message: '二级菜单不能为空', trigger: 'blur' }
        ],
        displaySeq: [
          { required: true, message: '排序序号不能为空', trigger: 'blur' }
        ]
      },
      countryOptions: [],
      standSortOptions: [], // 标准类别下拉框
      applyArcticOptions: [], // 适用车型下拉框
      standStateOptions: [], // 标准状态下拉框
      standNatureOptions: [], // 标准性质下拉框
      adoptExtentOptions: [], // 采标程度下拉框
      emergyKindOptions: [], // 能源种类下拉框
      applyAuthOptions: [], // 适用认证下拉框
      categoryOptions: [], // 所属类别下拉框
      issueTimeOptions: [{ label: '本月', value: '1' }, { label: '近三个月', value: '2' }, { label: '近一年', value: '3' }, { label: '近三年', value: '4' }, { label: '三年以上', value: '5' }], // 高级搜索条件中的发布日期
      sarMenu: {
        id: '',
        parentId: '',
        menuName: '',
        sorDivide: 'INLAND_STAND',
        displaySeq: '',
        parentIds: ''
      }, // 二级菜单对象，主要用于新增和修改
      selectSarMenu: {}, // 用来记录当前选中的二级菜单对象
      importExcelUrl: '', // 导入EXCEL文档
      // 树形结构
      tree: [{
        title: 'parent 1',
        expand: true,
        render: (h, { root, node, data }) => {
          return h('span', {
            style: {
              display: 'inline-block',
              width: '100%'
            }
          }, [
            h('span', [
              h('Icon', {
                props: {
                  type: 'ios-folder-outline'
                },
                style: {
                  marginRight: '8px'
                }
              }),
              h('span', data.title)
            ]),
            h('span', {
              style: {
                display: 'inline-block',
                float: 'right',
                marginRight: '32px'
              }
            }, [
              h('Button', {
                props: Object.assign({}, this.buttonProps, {
                  icon: 'ios-add',
                  type: 'primary'
                }),
                style: {
                  width: '64px'
                },
                on: {
                  click: () => { this.append(data) }
                }
              })
            ])
          ])
        },
        children: [
          {
            title: 'child 1-1',
            expand: true,
            children: [
              {
                title: 'leaf 1-1-1',
                expand: true
              },
              {
                title: 'leaf 1-1-2',
                expand: true
              }
            ]
          },
          {
            title: 'child 1-2',
            expand: true,
            children: [
              {
                title: 'leaf 1-2-1',
                expand: true
              },
              {
                title: 'leaf 1-2-1',
                expand: true
              }
            ]
          }
        ]
      }
      ],
      buttonProps: {
        type: 'default',
        size: 'small'
      },
      // tree function
      MoveTest: '',
      // tree setting
      setting: '',
      // tree zNodes
      zNodes: [],
      // ztree 拖拽标志
      dragFlag: false,
      mousedown: '',
      mouoseup: '',
      uploadPath: '/api/att/attFile/upload',
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static'
      },
      // 标准文本文件名
      standFileName: '',
      standModifyFileName: '',
      draftFileName: '',
      opinionFileName: '',
      sentScreenFileName: '',
      approvalFileName: '',
      relevanceFileName: '',
      importModalshowflagtemp: false,
      file: null,
      loadingStatus: false
    }
  },
  methods: {
    // 分页查询国内标准
    getDomesticStandardTable () {
      this.$http.get('lawss/sarStandardsInfo/getSarStandardsInfoPage', this.sarStandardsSearch, {
        _this: this, loading: 'loading'
      }, res => {
       /*
       for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i]['collectIcontype'] = 'ios-star-outline'
          res.data.list[i]['collectIconcolor'] = '#5c6b77'
          res.data.list[i].checked = false
        }
        */
        this.stahndinfoList = res.data.list
        this.total = res.data.count
      }, e => {
      })
     /*
     this.stahndinfoList = [
        {
          checked: false,
          id: '1000',
          standSortShow: 'ABS2018',
          standNumber: 'BZ10000',
          standYear: '2018',
          standName: '驱动系统',
          standStateShow: 0,
          standNatureShow: '1',
          putTime: '2018/09/18',
          issueTime: '2018/10/01'
        },
        {
          checked: false,
          id: '1001',
          standSortShow: 'ACS2018',
          standNumber: 'BZ10001',
          standYear: '2018',
          standName: '排气系统',
          standStateShow: 1,
          standNatureShow: '1',
          putTime: '2018/09/17',
          issueTime: '2018/10/01'
        },
        {
          checked: false,
          id: '1002',
          standSortShow: 'ABCS2018',
          standNumber: 'BZ10002',
          standYear: '2018',
          standName: '轮胎性能测试',
          standStateShow: 0,
          standNatureShow: '1',
          putTime: '2018/08/01',
          issueTime: '2018/09/01'
        },
        {
          checked: false,
          id: '1003',
          standSortShow: 'ACS2018',
          standNumber: 'BZ10003',
          standYear: '2018',
          standName: '燃油测试',
          standStateShow: 2,
          standNatureShow: '2',
          putTime: '2018/08/15',
          issueTime: '2018/10/01'
        },
        {
          checked: false,
          id: '1004',
          standSortShow: 'ADP2018',
          standNumber: 'BZ10004',
          standYear: '2018',
          standName: '安全气囊',
          standStateShow: 1,
          standNatureShow: '1',
          putTime: '2018/07/15',
          issueTime: '2018/09/20'
        },
        {
          checked: false,
          id: '1005',
          standSortShow: 'ABS2018',
          standNumber: 'BZ10005',
          standYear: '2018',
          standName: '发动机性能测试',
          standStateShow: 3,
          standNatureShow: '2',
          putTime: '2018/06/26',
          issueTime: '2018/09/01'
        }
      ]
      */
    },
    // 分页点击后方法
    pageChange (page) {
      this.sarStandardsSearch.page = page
      this.getDomesticStandardTable()
    },
    // 分页每页显示数改变后方法
    pageSizeChange (pageSize) {
      this.sarStandardsSearch.pageSize = pageSize
      this.getDomesticStandardTable()
      // 此处需要调用接口，修改个人配置
    },
    // 表单清空
    resetForm () {
      this.$refs['sarStandardsInfoForm'].resetFields()
    },
    // 点击新增按钮弹出新增模态框
    addModal () {
      this.modalshowflag = true
      this.formdisableflag = false
      this.modalshowtitle = '新增标准'
      this.addOrUPdateFlag = 1
      this.sarStandardsInfoEO = {
        id: '',
        standType: 'INLAND', // 标准分类
        country: 'CN',
        standSort: '',
        applyArctic: '',
        standNumber: '',
        standYear: '',
        standName: '',
        standEnName: '',
        standState: '',
        standNature: '',
        replaceStandNum: '',
        replacedStandNum: '',
        interStandNum: '',
        adoptExtent: '',
        emergyKind: '',
        applyAuth: '',
        issueTime: '',
        putTime: '',
        newcarPutTime: '',
        productPutTime: '',
        newproductPutTime: '',
        draftingUnit: '',
        draftUser: '',
        standFile: '',
        standModifyFile: '',
        draftFile: '',
        opinionFile: '',
        sentScreenFile: '',
        approvalFile: '',
        relevanceFile: '',
        standFileList: [],
        standModifyFileList: [],
        draftFileList: [],
        opinionFileList: [],
        sentScreenFileList: [],
        approvalFileList: [],
        relevanceFileList: [],
        tags: '',
        synopsis: '',
        responsibleUnit: '',
        category: '',
        remark: '',
        menuId: ''
      } // 标准新增过程中用到的对象
    },
    // 保存或修改标准
    saveOrUpdateStands () {
      this.$refs['sarStandardsInfoForm'].validate((valid) => {
        if (valid) {
          // 时间格式修改
          this.sarStandardsInfoEO.issueTime = this.$dateFormat(this.sarStandardsInfoEO.issueTime, 'yyyy-MM-dd hh:mm:ss')
          this.sarStandardsInfoEO.putTime = this.$dateFormat(this.sarStandardsInfoEO.putTime, 'yyyy-MM-dd hh:mm:ss')
          this.sarStandardsInfoEO.newcarPutTime = this.$dateFormat(this.sarStandardsInfoEO.newcarPutTime, 'yyyy-MM-dd hh:mm:ss')
          this.sarStandardsInfoEO.productPutTime = this.$dateFormat(this.sarStandardsInfoEO.productPutTime, 'yyyy-MM-dd hh:mm:ss')
          this.sarStandardsInfoEO.newproductPutTime = this.$dateFormat(this.sarStandardsInfoEO.newproductPutTime, 'yyyy-MM-dd hh:mm:ss')
          let a = ''
          if (this.sarStandardsInfoEO.applyArctic != null && this.sarStandardsInfoEO.applyArctic instanceof Array) {
            a = this.sarStandardsInfoEO.applyArctic.join(',')
            this.sarStandardsInfoEO.applyArctic = a // 适用车型
          }
          if (this.sarStandardsInfoEO.emergyKind != null && (this.sarStandardsInfoEO.emergyKind instanceof Array)) {
            a = this.sarStandardsInfoEO.emergyKind.join(',')
            this.sarStandardsInfoEO.emergyKind = a // 能源种类
          }
          if (this.sarStandardsInfoEO.applyAuth != null && (this.sarStandardsInfoEO.applyAuth instanceof Array)) {
            a = this.sarStandardsInfoEO.applyAuth.join(',')
            this.sarStandardsInfoEO.applyAuth = a // 适用认证
          }
          if (this.sarStandardsInfoEO.category != null && (this.sarStandardsInfoEO.category instanceof Array)) {
            a = this.sarStandardsInfoEO.category.join(',')
            this.sarStandardsInfoEO.category = a // 所属类别
          }
          // addOrUPdateFlag 1:新增 2:修改
          if (this.addOrUPdateFlag === 1) {
            this.sarStandardsInfoEO.menuId = this.selectSarMenu.id // 新建过程中标准所属目录是当前目录
          }
          console.log(this.sarStandardsInfoEO)
          this.$http.postData(this.addOrUPdateFlag === 1 ? 'lawss/sarStandardsInfo/addarStandardsInfo' : 'lawss/sarStandardsInfo/updateSarStandardsInfo', this.sarStandardsInfoEO, {
            _this: this
          }, res => {
            this.getDomesticStandardTable()
            this.modalshowflag = false
          }, e => {})
        } else {
          this.$Message.error('请检查表单是否填写正确!')
        }
      })
    },
    // 删除标准
    deleteStand () {
      this.$http.post('lawss/sarStandardsInfo/deleteSarStandards', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
        this.getDomesticStandardTable()
      }, e => {
      })
    },
    // 需求中操作栏中操作函数
    // 查看标准属性
    selectStandardPro (item, state) {
      this.sarStandardsInfoEO = item
      this.modalshowflag = true
      let a = []
      if (this.sarStandardsInfoEO.applyArctic != null && !(this.sarStandardsInfoEO.applyArctic instanceof Array)) {
        a = item.applyArctic.split(',')
        this.sarStandardsInfoEO.applyArctic = a // 适用车型
      }
      if (this.sarStandardsInfoEO.emergyKind != null && !(this.sarStandardsInfoEO.emergyKind instanceof Array)) {
        a = item.emergyKind.split(',')
        this.sarStandardsInfoEO.emergyKind = a // 能源种类
      }
      if (this.sarStandardsInfoEO.applyAuth != null && !(this.sarStandardsInfoEO.applyAuth instanceof Array)) {
        a = item.applyAuth.split(',')
        this.sarStandardsInfoEO.applyAuth = a // 适用认证
      }
      if (this.sarStandardsInfoEO.category != null && !(this.sarStandardsInfoEO.category instanceof Array)) {
        a = item.category.split(',')
        this.sarStandardsInfoEO.category = a // 所属类别
      }
      if (state === 'show') {
        this.formdisableflag = true
      } else {
        this.addOrUPdateFlag = 2
        this.formdisableflag = false
      }
    },
    // 收藏标准
    collectStandard (i) {
      console.log(i)
      i.collectIconcolor = '#CD950C'
      i.collectIcontype = 'ios-star'
      /* this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      }) */
    },
    // 分享标准
    shareStandard (item) {
      this.$http.post('', {id: item.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 跳转流程节点
    goProcess (item) {
      this.$http.post('', {id: item.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 查看标准条目
    selectSarStandItems (standid) {
      this.modalStandItemflag = true
      this.standItemSearch.standId = standid
      this.$http.get('lawss/sarStandItems/getSarStandItemsList', {standId: standid}, {
        _this: this, loading: 'loading'
      }, res => {
        this.standItemList = res.data
        // this.standitemTotal = res.data.count // 分页需要
      }, e => {
      })
    },
    // 关闭新增模态模态框
    closeModal () {
      this.$refs.modalshow.toggleClose()
      this.$refs.menuRefModal.toggleClose()
    },
    // 点击导入标准
    addImportModal (flag) {
      this.importModalshowflag = true
      this.$refs.importfile.clearFiles()
      if (flag === 1) {
        this.importExcelUrl = '/api/lawss/sarStandardsInfo/importStandardsInfo?standType=INLAND'
      } else {
        this.importExcelUrl = '/api/lawss/sarStandItems/importSarStandItemsList?standId=' + this.standItemSearch.standId
      }
    },
    // 导入标准文件格式错误执行
    handleFormatError (file) {
      this.$Notice.warning({
        title: 'The file format is incorrect',
        desc: 'File format of ' + file.name + ' is incorrect, please select jpg or png.'
      })
    },
    // 导入标准数据成功后执行
    importFileSuccess (response, file) {
      // 使用字条目是否展示模态框判断导入的文件是标准，还是条目modalStandItemflag
      if (this.modalStandItemflag) {
        this.selectSarStandItems(this.standItemSearch.standId)
      } else {
        this.getDomesticStandardTable()
      }
    },
    // 二级菜单新建，编辑，删除
    clickDropMenu (name) {
      if (name === 'newMenu') {
        this.menuModalFlag = true
        this.menuAddOrUpdateFlag = 1
        this.sarMenu = {
          id: '',
          parentId: '',
          menuName: '',
          sorDivide: 'INLAND_STAND',
          displaySeq: '',
          parentIds: ''
        }
      } else if (name === 'editMenu') {
        this.menuModalFlag = true
        this.menuAddOrUpdateFlag = 2
        this.sarMenu = this.selectSarMenu // 修改过程中直接将sarMenu对象置为当前选中的对象
      } else {
        // deleteMenu 删除二级菜单，先判断是否选中，选中项目，然后调用删除方法
        // 先判断目录下是否有菜单
        this.$http.get('lawss/sarMenu/judgequeryMenuByPid', this.selectSarMenu, {
          _this: this, loading: 'loading'
        }, res => {
          let message = ''
          if (res.data) {
            message = '<p>该节点下有记录，是否删除?</p>'
          } else {
            message = '<p>是否删除?</p>'
          }
          this.$Modal.confirm({
            title: '提示',
            content: message,
            onOk: () => {
              this.$http.post('lawss/sarMenu/deleteMenuAndChildren', this.selectSarMenu, {
                _this: this, loading: 'loading'
              }, res => {
                this.selectMenu() // 删除成功后，更新二级菜单
              }, e => {
              })
            },
            onCancel: () => {
            }
          })
        }, e => {
        })
      }
    },
    // 点击二级菜单新增模态框中的保存
    newMenu () {
      if (this.menuAddOrUpdateFlag === 1) {
        this.sarMenu.parentId = this.selectSarMenu.id
        if (this.selectSarMenu.parentIds != null) {
          this.sarMenu.parentIds = this.selectSarMenu.parentIds + ',' + this.selectSarMenu.id
        } else {
          this.sarMenu.parentIds = this.selectSarMenu.id
        }
        this.$http.post('lawss/sarMenu/addSarMenu', this.sarMenu, {
          _this: this, loading: 'loading'
        }, res => {
          this.selectMenu() // 新增成功后，更新二级菜单
        }, e => {
        })
      } else {
        this.$http.post('lawss/sarMenu/updateSarMenu', this.sarMenu, {
          _this: this, loading: 'loading'
        }, res => {
          this.selectMenu() // 修改成功后，更新二级菜单
          this.selectSarMenu = res.data
        }, e => {
        })
      }
    },
    // 查询二级菜单
    selectMenu () {
      this.$http.get('lawss/sarMenu/selectmenu', {sorDivide: 'INLAND_STAND'}, {
        _this: this
      }, res => {
        this.zNodes = res.data
      }, e => {
      })
    },
    // 输入代替标准号后，和数据库做验证
    testReplaceStandNum () {
      this.sarStandardsInfoEO.replaceStandNum = this.sarStandardsInfoEO.replaceStandNum.replace(/，/ig, ',')
      this.$http.post('lawss/sarStandardsInfo/selectReplaceStandNum', {'replaceStandNum': this.sarStandardsInfoEO.replaceStandNum, 'standType': this.sarStandardsInfoEO.standType}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 现在配置标准要求查询处于游离状态的数据
    configurationStandard () {
      this.sarStandardsSearch.menuId = 'nomenu'
      this.$http.post('lawss/sarStandardsInfo/selectStandardsInfoByMenu', this.sarStandardsSearch, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 清空搜索框
    clearAllSearch () {
      this.sarStandardsSearch.country = ''
      this.sarStandardsSearch.standNumber = ''
      this.sarStandardsSearch.standName = ''
      this.sarStandardsSearch.standState = ''
      this.sarStandardsSearch.standNature = ''
      this.sarStandardsSearch.issueTime = ''
      this.sarStandardsSearch.applyArctic = ''
      this.sarStandardsSearch.replaceStandNum = ''
      this.sarStandardsSearch.replacedStandNum = ''
    },
    // 导出选中的标准  此处因为复选框没有设置好，所以先设置导出所有数据
    exportStandard () {
      this.$http.get('lawss/sarStandardsInfo/exportStandardsInfoExcel', this.sarStandardsSearch, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    addItemModal () {
      this.modalItemaddShowflag = true
      this.itemAddOrUPdateFlag = 1
    },
    // 标准条目新增
    saveOrUpdateStandItem () {
      this.standItemEO.tackTime = this.$dateFormat(this.standItemEO.tackTime, 'yyyy-MM-dd')
      // 新增
      if (this.itemAddOrUPdateFlag === 1) {
        this.standItemEO.standId = this.standItemSearch.standId
        this.$http.post('lawss/sarStandItems/addSarStandItemsList', this.standItemEO, {
          _this: this
        }, res => {
          this.selectSarStandItems(this.standItemSearch.standId)
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarStandItems/updateStandItem', this.standItemEO, {
          _this: this
        }, res => {
          this.selectSarStandItems(this.standItemSearch.standId)
        }, e => {
        })
      }
      this.modalItemaddShowflag = false
    },
    // 标准条目删除
    deleteStandItem () {
      let ids = []
      ids.push(this.standItemEO.id)
      this.$http.post('lawss/sarStandItems/deleteStandItem', {id: ids}, {
        _this: this
      }, res => {
        alert(this.standItemSearch.standId)
        this.selectSarStandItems(this.standItemSearch.standId)
      }, e => {
      })
    },
    // 导出标准条目
    exportStandardItem () {
      this.$http.get('lawss/sarStandItems/exportStandardItemExcel', this.standItemSearch, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    /**
     * @description: 全选
     * @author: chenxiaoxi
     * @date: 2018-09-13 20:40:31
     */
    handleSelectAll (checked) {
      // 全部选中
      if (checked) {
        // 1.遍历数据,把每一项的checkbox置为选中状态
        this.selectedList = []
        for (let i = 0; i < this.stahndinfoList.length; i++) {
          this.$set(this.stahndinfoList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.stahndinfoList[i].id)
        }
        // 全部取消选中
      } else {
        // 1.遍历数据,把每一项的checkbox置为取消选中状态
        for (let i = 0; i < this.stahndinfoList.length; i++) {
          this.$set(this.stahndinfoList[i], 'checked', false)
          // 2.清空selectedList数组
          this.selectedList = []
        }
      }
    },
    /**
     * @description: card点击
     * @author: chenxiaoxi
     * @date: 2018-09-15 10:47:57
     */
    handleMousedown (event) {
      console.log(event.screenX + '，' + event.screenY)//   IE浏览器兼容
    },
    handleMouseup (item, event) {
      item.checked = !item.checked
    },
    renderContent (h, { root, node, data }) {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span', [
          h('Icon', {
            props: {
              type: 'ios-paper-outline'
            },
            style: {
              marginRight: '8px'
            }
          }),
          h('span', data.title)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            float: 'right',
            marginRight: '32px'
          }
        }, [
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-add'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => { this.append(data) }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-remove'
            }),
            on: {
              click: () => { this.remove(root, node, data) }
            }
          })
        ])
      ])
    },
    append (data) {
      const children = data.children || []
      children.push({
        title: 'appended node',
        expand: true
      })
      this.$set(data, 'children', children)
    },
    remove (root, node, data) {
      const parentKey = root.find(el => el === node).parent
      const parent = root.find(el => el.nodeKey === parentKey).node
      const index = parent.children.indexOf(data)
      parent.children.splice(index, 1)
    },
    /**
     * @description: 上传成功回调
     * @params: value: 绑定的FormItem
     * @author: chenxiaoxi
     * @date: 2018-09-19 14:15:01
     */
    handleUploadSucc (res, file, fileList, value, name) {
      this[name] = ''
      for (let i = 0; i < fileList.length; i++) {
        this[name] = this[name] + ',' + fileList[i].name
      }
      if (res.ok) {
        let temp = []
        temp = this.sarStandardsInfoEO[value]
        temp.push(res.data)
        this.sarStandardsInfoEO[value] = temp
      } else {
        console.log('文件上传出错')
      }
    },
    handleUpload (file) {
      this.file = file
      //this.sarStandardsInfoEO[value] = []
      return false
    }
  },
  components: {},
  props: {},
  watch: {
    stahndinfoList: {
      deep: true,
      handler (newVal, oldVal) {
        this.selectedList = []
        for (let i = 0; i < newVal.length; i++) {
          if (newVal[i].checked) {
            this.selectedList.push(newVal[i].id)
          }
        }
        if (this.selectedList.length === newVal.length && newVal.length !== 0) {
          this.checkAll = true
          this.indeterminate = false
        } else if (this.selectedList.length === 0) {
          this.checkAll = false
        } else {
          this.checkAll = false
          this.indeterminate = false
        }
        /****
        this.$nextTick(() => {
          $.fn.zTree.init($('#treeDemo'), this.setting, this.zNodes)
          this.MoveTest.updateType()
          this.MoveTest.bindDom()
        })
        */
      }
    },
    // 已选择的列表
    selectedList (newVal, oldVal) {
      if (oldVal.length === this.stahndinfoList.length && newVal.length !== 0) {
        this.checkAll = false
        this.indeterminate = true
      } else if (newVal.length === 0) {
        this.checkAll = false
        this.indeterminate = false
      }
    },
    zNodes: {
      deep: true,
      handler () {
        let allthis = this
        this.$nextTick(() => {
          let _this = this
          $(document).ready(function () {
            $.fn.zTree.init($('#treeDemo'), _this.setting, _this.zNodes)
            _this.MoveTest.updateType()
            _this.MoveTest.bindDom()
            var treeObj = $.fn.zTree.getZTreeObj('treeDemo')
            // 获取节点
            var nodes = treeObj.getNodes()
            // treeObj.cancelSelectedNode() // 先取消所有的选中状态
            if (JSON.stringify(allthis.selectSarMenu) === '{}') {
              treeObj.selectNode(nodes[0], true) // 返回node对象，此处由于未用到，所以没有接受返回参数
              allthis.sarStandardsSearch.menuId = nodes[0].id // 将当前二级菜单的id传回后台做标准的条件查询
              allthis.selectSarMenu = nodes[0]  //设置当前选中的node
              // treeObj.expandNode(nodes[0], true, false) // 将指定ID节点展开
            } else {
              treeObj.selectNode(allthis.selectSarMenu, true)
            // treeObj.expandNode(allthis.selectSarMenu, true, false) // 将指定ID节点展开
            }
          })
        })
      }
    }
  },
  mounted () {
    let allthis = this
    // 进入页面后查询树形结构目录
    this.selectMenu()
    this.getDomesticStandardTable()
    // 从数据库中查询各下拉框数据
    this.$http.get('sys/dictype/getDicTypeListCode', '', {
      _this: this
    }, res => {
      this.countryOptions = res.data.COUNTRY
      this.standSortOptions = res.data.STANDCLASSIFY
      this.applyArcticOptions = res.data.PRODUCTTYPE // 根据需求文档，产品类别对应标准属性中的“适用车型”
      this.standStateOptions = res.data.STANDSTATE
      this.standNatureOptions = res.data.SARPROPERTY // 标准性质
      this.adoptExtentOptions = res.data.DEGREESTANDARD
      this.emergyKindOptions = res.data.ENERGYTYPES
      this.applyAuthOptions = res.data.PROVETYPE // 适用认证下拉框
      this.categoryOptions = res.data.CATEGORY
    }, e => {
    })

    /**
     * @description: zTree初始化
     * @author: chenxiaoxi
     * @date: 2018-09-17 11:07:53
     */
    let MoveTest = {
      errorMsg: '放错了...请选择正确的类别！',
      curTarget: null,
      curTmpTarget: null,
      noSel: function () {
        try {
          window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty()
        } catch (e) {}
      },
      dragTree2Dom: function (treeId, treeNodes) {
        return !treeNodes[0].isParent
      },
      prevTree: function (treeId, treeNodes, targetNode) {
        return !targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId
      },
      nextTree: function (treeId, treeNodes, targetNode) {
        return !targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId
      },
      innerTree: function (treeId, treeNodes, targetNode) {
        return targetNode != null && targetNode.isParent && targetNode.tId === treeNodes[0].parentTId
      },
      dragMove: function (e, treeId, treeNodes) {
        console.log('drapMove')
        console.log(treeId)
        console.log(treeNodes)
        let p = null
        let pId = 'dom_' + treeNodes[0].parentId
        if (e.target.id === pId) {
          p = $(e.target)
        } else {
          p = $(e.target).parent('#' + pId)
          if (!p.get(0)) {
            p = null
          }
        }

        $('.domBtn .active').removeClass('active')
        if (p) {
          p.addClass('active')
        }
      },
      dropTree2Dom: function (e, treeId, treeNodes, targetNode, moveType) {
        console.log(2)
        console.log(treeId)
        console.log(targetNode)
      },
      // 拖拽对象id,拖拽目标对象
      dom2Tree: function (e, treeId, treeNode) {
        console.log('点击')
        let pid = ''
        if (treeNode == null) {
          pid = treeNode.id
        }
        if (MoveTest.curTarget === null) return
        let id = MoveTest.curTarget.attr('domId')
        console.log(id)
        // 移除原来的节点
        MoveTest.curTarget.remove()
        let tmpTarget = MoveTest.curTmpTarget
        // 移除拖拽元素
        if (tmpTarget) tmpTarget.remove()
        MoveTest.updateType()
        MoveTest.curTarget = null
        MoveTest.curTmpTarget = null
      },
      updateType: function () {
        let zTree = $.fn.zTree.getZTreeObj('treeDemo')
        let nodes = zTree.getNodes()
        for (let i = 0, l = nodes.length; i < l; i++) {
          let num = nodes[i].children ? nodes[i].children.length : 0
          nodes[i].menuName = nodes[i].menuName.replace(/ \(.*\)/gi, '') + ' (' + num + ')'
          zTree.updateNode(nodes[i])
        }
      },
      bindDom: function () {
        // 如果已有这个事件，先去掉
        $('.domBtn').unbind('mousedown')
        $('.domBtn').bind('mousedown', MoveTest.bindMouseDown)
      },
      bindMouseDown: function (e) {
        let card = ''
        if (!$(e.target).hasClass('domBtn')) {
          // 向上查找包含domBtn的父节点
          card = $(e.target).closest('.domBtn')[0]
        } else {
          card = e.target
        }
        if (card != null && card.className.indexOf('domBtn') > -1) {
          let doc = $(document)
          card = $(card)
          let docScrollTop = doc.scrollTop()
          let docScrollLeft = doc.scrollLeft()
          card.addClass('domBtn_Disabled')
          card.removeClass('domBtn')
          let curDom = ''
          curDom = $("<div class='dom_tmp iconfont'>&#xe64b;</div>")
          curDom.appendTo('body')

          curDom.css({
            'top': (e.clientY + docScrollTop + 3) + 'px',
            'left': (e.clientX + docScrollLeft + 3) + 'px'
          })
          MoveTest.curTarget = card
          MoveTest.curTmpTarget = curDom

          doc.bind('mousemove', MoveTest.bindMouseMove)
          doc.bind('mouseup', MoveTest.bindMouseUp)
          doc.bind('selectstart', MoveTest.docSelect)
        }
        if (e.preventDefault) {
          e.preventDefault()
        }
      },
      bindMouseMove: function (e) {
        MoveTest.noSel()
        const doc = $(document)
        let docScrollTop = doc.scrollTop()
        let docScrollLeft = doc.scrollLeft()
        let tmpTarget = MoveTest.curTmpTarget
        if (tmpTarget) {
          tmpTarget.css({
            'top': (e.clientY + docScrollTop + 3) + 'px',
            'left': (e.clientX + docScrollLeft + 3) + 'px'
          })
        }
        return false
      },
      bindMouseUp: function (e) {
        var doc = $(document)
        doc.unbind('mousemove', MoveTest.bindMouseMove)
        doc.unbind('mouseup', MoveTest.bindMouseUp)
        doc.unbind('selectstart', MoveTest.docSelect)

        let target = MoveTest.curTarget
        let tmpTarget = MoveTest.curTmpTarget
        if (tmpTarget) {
          tmpTarget.remove()
        }
        if ($(e.target).parents('#treeDemo').length === 0) {
          if (target) {
            target.removeClass('domBtn_Disabled')
            target.addClass('domBtn')
          }
          MoveTest.curTarget = null
          MoveTest.curTmpTarget = null
        }
      },
      bindSelect: function () {
        return false
      },
      clickOneNode: function (event, treeId, treeNode) {
        allthis.selectSarMenu = treeNode // 记录当前选中的二级菜单
        allthis.sarStandardsSearch.menuId = treeNode.id // 将当前二级菜单的id传回后台做标准的条件查询
        allthis.getDomesticStandardTable() // 根据选中的二级菜单查询对应的标准列表
      }
    }
    this.MoveTest = MoveTest
    let setting = {
      edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false,
        drag: {
          prev: MoveTest.prevTree,
          next: MoveTest.nextTree,
          inner: MoveTest.innerTree
        }
      },
      data: {
        keep: {
          parent: true,
          leaf: true
        },
        key: {
          name: 'menuName'
        },
        simpleData: {
          enable: true,
          idKey: 'id',
          pIdKey: 'parentId'
        }
      },
      callback: {
        beforeDrag: MoveTest.dragTree2Dom,
        onDrop: MoveTest.dropTree2Dom,
        onDragMove: MoveTest.dragMove,
        onMouseUp: MoveTest.dom2Tree,
        onClick: MoveTest.clickOneNode
      },
      view: {
        selectedMulti: false
      }
    }
    this.setting = setting
    /* let zNodes = [
      {id: 1, parentId: 0, namee: '全部标准', isParent: true, open: true},
      {id: 11, parentId: 1, namee: '强制性标准', isParent: true, open: true},
      {id: 12, parentId: 1, namee: '推荐性标准', isParent: true, open: true}
    ]
    this.zNodes = zNodes */
  }
}
