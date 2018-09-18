export default {
  name: 'EnterpriseStandardLibrary',
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
                    this.sarBussionessStandEO = params.row
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
                    this.sarBussionessStandEO.id = params.row.id
                    this.deleteStand()
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      standinfoList: [],
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
      addOrUPdateFlag: 1, // 新增：1， 修改：2
      itemAddOrUPdateFlag: 1, // 新增：1， 修改：2
      sarBussionessStandEO: {
        id: '',
        standGenera: '',
        standSubclass: '',
        classifyCode: '', //分类代码
        standCode: '',
        standName: '',
        standEnName: '',
        applyArctic: '',
        energyKind: '',
        issueTime: '',
        putTime: '',
        putYear: '',
        firstPutTime: '',
        quoteStand: '',
        replaceStandNum: '',
        replacedStandNum: '',
        standStatus: '',
        tags: '',
        standFile: '',
        responsibleUnit: '',
        citationUser: ''
      }, // 新增过程中用到的对象
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
        menuId: '',
        classifyCode: '',
        standCode: '',
        standName: '',
        standGenera: '',
        standSubclass: '',
        issueTime: '',
        putTime: '',
        responsibleUnit: ''
      }, // 分页查询过程中用到的对象
      standItemSearch: {
        standId: ''
      },
      sarBussionessStandRules: {
        standSort: [
          { required: true, message: '标准类别不能为空', trigger: 'blur' }
        ],
        applyArctic: [
          { required: true, message: '适用车型不能为空', trigger: 'blur' }
        ],
        standNumber: [
          { required: true, message: '标准编号不能为空', trigger: 'blur' }
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
          { required: true, message: '标准性质不能为空', trigger: 'blur' }
        ],
        replaceStandNum: [],
        replacedStandNum: [],
        interStandNum: [],
        adoptExtent: [],
        energyKind: [
          { required: true, message: '能源种类不能为空', trigger: 'blur' }],
        applyAuth: [],
        issueTime: [
          { required: true, message: '发布日期不能为空', trigger: 'blur' }
        ],
        putTime: [
          { required: true, message: '实施日期不能为空', trigger: 'blur' }
        ],
        newcarPutTime: [],
        productPutTime: [],
        newproductPutTime: [],
        draftingUnit: [],
        draftUser: [],
        standFile: [],
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
      applyArcticOptions: [], // 适用车型下拉框
      standStateOptions: [], // 标准状态下拉框
      energyKindOptions: [], // 能源种类下拉框
      issueTimeOptions: [{ label: '本月', value: '1' }, { label: '近三个月', value: '2' }, { label: '近一年', value: '3' }, { label: '近三年', value: '4' }, { label: '三年以上', value: '5' }], // 高级搜索条件中的发布日期
      putTimeOptions: [{ label: '本月', value: '1' }, { label: '近三个月', value: '2' }, { label: '近一年', value: '3' }, { label: '近三年', value: '4' }, { label: '三年以上', value: '5' }], // 高级搜索条件中的发布日期
      // 二级菜单对象
      sarMenu: {
        id: '',
        parentId: '',
        menuName: '',
        sorDivide: 'INLAND_STAND',
        displaySeq: '',
        parentIds: ''
      },
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
      }
    }
  },
  methods: {
    // 分页查询企业标准
    getBussionStandTable () {
      this.$http.get('lawss/sarBussionessStand/getSarBussionStandPage', this.sarStandardsSearch, {
        _this: this, loading: 'loading'
      }, res => {
        this.standinfoList = res.data.list
        this.total = res.data.count
      }, e => {
      })
    },
    // 分页点击后方法
    pageChange (page) {
      this.sarStandardsSearch.page = page
      this.getBussionStandTable()
    },
    // 分页每页显示数改变后方法
    pageSizeChange (pageSize) {
      this.sarStandardsSearch.pageSize = pageSize
      this.getBussionStandTable()
      // 此处需要调用接口，修改个人配置
    },
    // 点击新增按钮弹出新增模态框
    addModal () {
      this.modalshowflag = true
      this.formdisableflag = false
      this.addOrUPdateFlag = 1
      this.sarBussionessStandEO = {}
    },
    // 保存或修改标准
    saveOrUpdateStands () {
      // 时间格式修改
      this.sarBussionessStandEO.issueTime = this.$dateFormat(this.sarBussionessStandEO.issueTime, 'yyyy-MM-dd')
      this.sarBussionessStandEO.putYear = this.$dateFormat(this.sarBussionessStandEO.putYear, 'yyyy-MM-dd')
      this.sarBussionessStandEO.putTime = this.$dateFormat(this.sarBussionessStandEO.putTime, 'yyyy-MM-dd')
      this.sarBussionessStandEO.firstPutTime = this.$dateFormat(this.sarBussionessStandEO.firstPutTime, 'yyyy-MM-dd')
      // 新增
      if (this.addOrUPdateFlag === 1) {
        this.$http.post('lawss/sarBussionessStand/addSarBussionessStand', this.sarBussionessStandEO, {
          _this: this
        }, res => {
          this.getBussionStandTable()
          this.modalshowflag = false
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarBussionessStand/updateSarBussionessStand', this.sarBussionessStandEO, {
          _this: this
        }, res => {
          this.getBussionStandTable()
          this.modalshowflag = false
        }, e => {
        })
      }
    },
    // 删除标准
    deleteStand () {
      this.$http.post('lawss/sarBussionessStand/deleteSarStandards', {id: this.sarBussionessStandEO.id}, {
        _this: this
      }, res => {
        this.getBussionStandTable()
      }, e => {
      })
    },
    // 需求中操作栏中操作函数
    // 查看标准属性
    selectStandardPro (item, state) {
      this.sarBussionessStandEO = item
      this.modalshowflag = true
      let a = []
      if (this.sarBussionessStandEO.applyArctic != null && !(this.sarBussionessStandEO.applyArctic instanceof Array)) {
        a = item.applyArctic.split(',')
        this.sarBussionessStandEO.applyArctic = a // 适用车型
      }
      if (this.sarBussionessStandEO.energyKind != null && !(this.sarBussionessStandEO.energyKind instanceof Array)) {
        a = item.energyKind.split(',')
        this.sarBussionessStandEO.energyKind = a // 能源种类
      }
      if (this.sarBussionessStandEO.applyAuth != null && !(this.sarBussionessStandEO.applyAuth instanceof Array)) {
        a = item.applyAuth.split(',')
        this.sarBussionessStandEO.applyAuth = a // 适用认证
      }
      if (this.sarBussionessStandEO.category != null && !(this.sarBussionessStandEO.category instanceof Array)) {
        a = item.category.split(',')
        this.sarBussionessStandEO.category = a // 所属类别
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
      /* this.$http.post('', {id: this.sarBussionessStandEO.id}, {
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
      this.$http.get('lawss/sarBussStandItems/getSarBussStandItemsList', {standId: standid}, {
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
    // 点击导入按钮 flag:1 导入标准  flag:2 导入标准条目
    addImportModal (flag) {
      this.importModalshowflag = true
      this.$refs.importfile.clearFiles()
      if (flag === 1) {
        this.importExcelUrl = '/api/lawss/sarBussionessStand/importSarBussionessStand'
      } else {
        this.importExcelUrl = '/api/lawss/sarBussStandItems/importSarBussStandItemsList?standId=' + this.standItemSearch.standId
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
        this.getBussionStandTable()
      }
    },
    // 二级菜单新建，编辑，删除
    clickDropMenu (name) {
      if (name === 'newMenu') {
        this.menuModalFlag = true
      } else if (name === 'editMenu') {
      } else {
        // deleteMenu 删除二级菜单，先判断是否选中，选中项目，然后调用删除方法
      }
    },
    newMenu () {
      this.$http.post('lawss/sarMenu/addSarMenu', this.sarMenu, {
        _this: this, loading: 'loading'
      }, res => {
      }, e => {
      })
    },
    // 输入代替标准号后，和数据库做验证
    testReplaceStandNum () {
      this.sarBussionessStandEO.replaceStandNum = this.sarBussionessStandEO.replaceStandNum.replace(/，/ig, ',')
      this.$http.post('lawss/sarBussionessStand/selectReplaceStandNum', {'replaceStandNum': this.sarBussionessStandEO.replaceStandNum, 'standType': this.sarBussionessStandEO.standType}, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    // 现在配置标准要求查询处于游离状态的数据
    configurationStandard () {
      this.sarStandardsSearch.menuId = 'nomenu'
      this.$http.post('lawss/sarBussionessStand/selectStandardsInfoByMenu', this.sarStandardsSearch, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    // 清空搜索框
    clearAllSearch () {
      this.sarStandardsSearch.classifyCode = ''
      this.sarStandardsSearch.standCode = ''
      this.sarStandardsSearch.standName = ''
      this.sarStandardsSearch.standGenera = ''
      this.sarStandardsSearch.standSubclass = ''
      this.sarStandardsSearch.issueTime = ''
      this.sarStandardsSearch.putTime = ''
      this.sarStandardsSearch.responsibleUnit = ''
    },
    // 导出选中的标准  此处因为复选框没有设置好，所以先设置导出所有数据
    exportStandard () {
      this.$http.get('lawss/sarBussionessStand/exportStandardsInfoExcel', this.sarStandardsSearch, {
        _this: this
      }, res => {
        console.log(res)
      }, e => {
      })
    },
    addItemModal () {
      this.modalItemaddShowflag = true
      this.itemAddOrUPdateFlag = 1
      this.standItemEO = {}
    },
    // 标准条目新增
    saveOrUpdateStandItem () {
      this.standItemEO.tackTime = this.$dateFormat(this.standItemEO.tackTime, 'yyyy-MM-dd')
      // 新增
      if (this.itemAddOrUPdateFlag === 1) {
        this.standItemEO.standId = this.standItemSearch.standId
        this.$http.post('lawss/sarBussStandItems/addSarBussStandItemsList', this.standItemEO, {
          _this: this
        }, res => {
          this.selectSarStandItems(this.standItemSearch.standId)
        }, e => {
        })
      } else {
        // 修改
        this.$http.post('lawss/sarBussStandItems/updateSarBussStandItems', this.standItemEO, {
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
      this.$http.post('lawss/sarBussStandItems/deleteSarBussStandItems', {id: ids}, {
        _this: this
      }, res => {
        this.selectSarStandItems(this.standItemSearch.standId)
      }, e => {
      })
    },
    // 导出标准条目
    exportStandardItem () {
      console.log(this.standItemSearch)
      this.$http.get('lawss/sarBussStandItems/exportStandardItemExcel', this.standItemSearch, {
        _this: this
      }, res => {
        console.log(res)
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
        for (let i = 0; i < this.standinfoList.length; i++) {
          this.$set(this.standinfoList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.standinfoList[i].id)
        }
        // 全部取消选中
      } else {
        // 1.遍历数据,把每一项的checkbox置为取消选中状态
        for (let i = 0; i < this.standinfoList.length; i++) {
          this.$set(this.standinfoList[i], 'checked', false)
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
    handleCardClick (item) {
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
    }
  },
  components: {
    draggable
  },
  props: {},
  watch: {
    standinfoList: {
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
      }
    },
    // 已选择的列表
    selectedList (newVal, oldVal) {
      if (oldVal.length === this.standinfoList.length && newVal.length !== 0) {
        this.checkAll = false
        this.indeterminate = true
      } else if (newVal.length === 0) {
        this.checkAll = false
        this.indeterminate = false
      }
    }
  },
  mounted () {
    this.getBussionStandTable()
    // 查询各下拉框数据
    this.$http.get('sys/dictype/getDicTypeListCode', '', {
      _this: this
    }, res => {
      this.applyArcticOptions = res.data.PRODUCTTYPE // 根据需求文档，产品类别对应标准属性中的“适用车型”
      this.standStateOptions = res.data.STANDSTATE
      this.energyKindOptions = res.data.ENERGYTYPES
    }, e => {
    })
  }
}
