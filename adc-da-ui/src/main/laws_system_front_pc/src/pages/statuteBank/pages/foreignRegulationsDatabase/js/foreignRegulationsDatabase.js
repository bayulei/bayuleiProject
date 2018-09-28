import 'zTree/js/jquery.ztree.core.js'
import 'zTree/js/jquery.ztree.excheck.js'
import 'zTree/js/jquery.ztree.exedit.js'
export default {
  name: 'ForeignRegulationsDatabase',
  data () {
    return {
      setting: '',
      MoveTest: '',
      zNodes: [],
      saveSelectedNodes: '',
      isAdvancedSearch: false, // 高级检索窗口是否打开
      checkAll: false, // 是否全选
      indeterminate: false, // 是否半选
      selectedList: [],
      modal2: false,
      showMenuModal: false,
      deleteMenuModal: false,
      showLawsInfoModal: false,
      showLawsItemsModal: false,
      addLawsItemsModal: false,
      importItemsModal: false,
      showLawsInfoTitle: '',
      addLawsItemsTitle: '',
      showMenuTitle: '',
      saveInfoBtn: true,
      saveLawsItemsBtn: true,
      lawsInfo: {
        lawsNum: '',
        lawsName: '',
        issueTime: '',
        menuId: ''
      },
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static'
      },
      SarMenuEO: {
        id: '',
        menuName: '',
        displaySeq: ''
      },
      SarLawsInfoEO: {
        editLawsId: '',
        country: '',
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
                    this.addLawsItemsTitle = '查看法规条目'
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
                    this.addLawsItemsTitle = '修改法规条目'
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
      browseList: [],
      lawsInfoImport: {},
      lawsItemsImport: {},
      lawsPropertyOptions: '',
      lawsStatusOptions: '',
      applyArcticOptions: '',
      energyKindOptions: '',
      applyAuthOptions: '',
      countryOptions: '',
      SarMenuEOFormRules: {},
      lawsInfoRules: {},
      lawsInfoFormRules: {
        country: [],
        lawsProperty: [],
        lawsNumber: [
          { type: 'string', max: 100, message: '文件号长度不能超过100个字符', trigger: 'blur' }
        ],
        lawsName: [
          { required: true, message: '文件名称不能为空', trigger: 'blur' },
          { type: 'string', max: 500, message: '文件名称长度不能超过500个字符', trigger: 'blur' }
        ],
        issueUnit: [
          { type: 'string', max: 500, message: '发布部门长度不能超过500个字符', trigger: 'blur' }
        ],
        lawsState: [
          { required: true, message: '文件状态不能为空', trigger: 'blur' }
        ],
        issueTime: [
          { required: true, type: 'date', message: '发布日期不能为空', trigger: 'change' }
        ],
        putTime: [
          { required: true, type: 'date', message: '实施日期不能为空', trigger: 'change' }
        ],
        replaceLawsNum: [
          { type: 'string', max: 100, message: '代替文件号长度不能超过100个字符', trigger: 'blur' }
        ],
        applyArctic: [],
        energyKind: [],
        applyAuth: [],
        responsibleUnit: [],
        linkUri: [
          { type: 'string', max: 1000, message: '链接长度不能超过1000个字符', trigger: 'blur' }
        ]
      },
      addLawsItemsFormRules: {
        itemsNum: [
          { required: true, message: '条目号不能为空', trigger: 'blur' },
          { type: 'string', max: 100, message: '条目号长度不能超过100个字符', trigger: 'blur' }
        ],
        itemsName: [
          { type: 'string', max: 500, message: '条目名称长度不能超过500个字符', trigger: 'blur' }
        ],
        parts: [
          { required: true, message: '涉及零部件不能为空', trigger: 'blur' }
        ],
        responsibleUnit: [
          { required: true, message: '责任部门不能为空', trigger: 'blur' }
        ],
        remarks: [
          { type: 'string', max: 5000, message: '备注长度不能超过5000个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 法规信息相关方法
    // 加载树形结构
    loadInfoTree () {
      this.$http.get('lawss/sarMenu/selectmenu', {
        sorDivide: 'FOREIGN_LAWS'
      }, {
        _this: this
      }, res => {
        let cur = res.data
        let str = []
        let option = {}
        for (var i = 0; i < cur.length; i++) {
          option = { id: cur[i].id,
            pId: cur[i].parentId,
            name: cur[i].menuName,
            sorDivide: cur[i].sorDivide,
            displaySeq: cur[i].displaySeq}
          str.push(option)
        }
        this.zNodes = str
        let treeObj = $.fn.zTree.init($('#lawsInfoTree'), this.setting, this.zNodes)
        treeObj.expandAll(true)
        // 获取节点
        let nodes = treeObj.getNodes()
        treeObj.selectNode(nodes[0], true)
      }, e => {
      })
    },
    // 点击节点
    clickOneNode (event, treeId, treeNode) {
      this.saveSelectedNodes = treeNode
      this.SarMenuEO.parentId = treeNode.id
      this.searchLawsInfo()
    },
    clickDropMenu (name) {
      if (name === 'addMenu') {
        this.showMenuModal = true
        this.showMenuTitle = '新增菜单'
        this.SarMenuEO.id = ''
        this.SarMenuEO.menuName = ''
        this.SarMenuEO.displaySeq = ''
        this.SarMenuEO.sorDivide = 'FOREIGN_LAWS'
      } else if (name === 'editMenu') {
        this.showMenuModal = true
        this.showMenuTitle = '修改菜单'
        this.SarMenuEO = this.saveSelectedNodes
        this.SarMenuEO.menuName = this.saveSelectedNodes.name
      } else {
        this.tipDeleteSarMenu(this.saveSelectedNodes)
      }
    },
    cancelAddMenu () {
      this.showMenuModal = false
    },
    saveMenu () {
      if (this.SarMenuEO.id == null || this.SarMenuEO.id === '') {
        this.$http.post('lawss/sarMenu/addSarMenu', this.SarMenuEO, {
          _this: this
        }, res => {
          this.loadInfoTree()
        }, e => {
        })
      } else {
        this.$http.post('lawss/sarMenu/updateSarMenu', this.SarMenuEO, {
          _this: this
        }, res => {
          this.loadInfoTree()
        }, e => {})
      }
    },
    tipDeleteSarMenu (sarMenu) {
      if (sarMenu.pId == null || sarMenu.pId === '') {
        this.$Message.error('不能删除根级节点')
      } else {
        this.$http.get('lawss/sarMenu/judgequeryMenuByPid', sarMenu, {
          _this: this
        }, res => {
          if (res.data === true) {
            this.deleteMenuModal = true
          } else {
            this.sureDeleteSarMenu()
          }
        }, e => {})
      }
    },
    sureDeleteSarMenu () {
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.post('lawss/sarMenu/deleteMenuAndChildren', this.saveSelectedNodes, {
            _this: this
          }, res => {
            this.loadInfoTree()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 分页查询法规信息
    searchLawsInfo () {
      let menuId = this.saveSelectedNodes.id
      let SarLawsInfoEOPage = this.lawsInfo
      SarLawsInfoEOPage.page = this.page
      SarLawsInfoEOPage.pageSize = this.rows
      SarLawsInfoEOPage.lawsType = 'FOREIGN'
      if (this.saveSelectedNodes.pId == null || this.saveSelectedNodes.pId === '') {
        SarLawsInfoEOPage.menuId = ''
      } else {
        SarLawsInfoEOPage.menuId = menuId
      }
      if (SarLawsInfoEOPage.issueTime != null && SarLawsInfoEOPage.issueTime !== '') {
        SarLawsInfoEOPage.issueTime = this.$dateFormat(SarLawsInfoEOPage.issueTime, 'yyyy-MM-dd')
      }
      this.$http.get('lawss/sarLawsInfo/page', SarLawsInfoEOPage, {
        _this: this,
        loading: 'loading'
      }, res => {
        for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i].checked = false
        }
        this.browseList = res.data.list
        this.total = res.data.count
        // window.open('/static/pdfjs-1.9.426-dist/viewer.html?file=E:/1.pdf')
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
      this.$nextTick(() => {
        this.$refs['SarLawsInfoEO'].resetFields()
      })
      this.showLawsInfoTitle = '新增法规'
      this.showLawsInfoModal = true
      this.saveInfoBtn = true
    },
    // 点击编辑按钮触发
    editLawsInfo (row, optType) {
      if (optType === 'edit') {
        this.showLawsInfoTitle = '修改法规'
        this.saveInfoBtn = true
      } else {
        this.showLawsInfoTitle = '查看法规'
        this.saveInfoBtn = false
      }
      this.showLawsInfoModal = true
      this.SarLawsInfoEO = row
      this.SarLawsInfoEO.editLawsId = row.id
      this.SarLawsInfoEO.applyArctic = this.combineToArray(this.SarLawsInfoEO.applyArctic)
      this.SarLawsInfoEO.energyKind = this.combineToArray(this.SarLawsInfoEO.energyKind)
      this.SarLawsInfoEO.applyAuth = this.combineToArray(this.SarLawsInfoEO.applyAuth)
    },
    // 提交新增/修改法规信息
    saveLawsInfo () {
      this.$refs['SarLawsInfoEO'].validate((valid) => {
        if (valid) {
          this.SarLawsInfoEO.applyArctic = this.breakMultiSelect(this.SarLawsInfoEO.applyArctic)
          this.SarLawsInfoEO.energyKind = this.breakMultiSelect(this.SarLawsInfoEO.energyKind)
          this.SarLawsInfoEO.applyAuth = this.breakMultiSelect(this.SarLawsInfoEO.applyAuth)
          this.SarLawsInfoEO.issueTime = this.$dateFormat(this.SarLawsInfoEO.issueTime, 'yyyy-MM-dd')
          this.SarLawsInfoEO.putTime = this.$dateFormat(this.SarLawsInfoEO.putTime, 'yyyy-MM-dd')
          this.SarLawsInfoEO.lawsType = 'FOREIGN'
          if (this.SarLawsInfoEO.editLawsId == null || this.SarLawsInfoEO.editLawsId === '') {
            if (this.saveSelectedNodes.pId != null && this.saveSelectedNodes.pId !== '') {
              this.SarLawsInfoEO.menuId = this.saveSelectedNodes.id
            }
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
        } else {
          this.$Message.error('请检查表单是否填写正确')
        }
      })
    },
    cancelAdd () {
      this.showLawsInfoModal = false
    },
    // 删除法规信息
    indeterminateremoveLawsInfo (id) {
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
    // 导入法规信息
    importLawsInfo () {
      let file = this.$refs.lawsInfoFile.files[0]
      let pageType = 'FOREIGN'
      this.$http.post('lawss/sarLawsInfo/importLawsInfos', {
        file: file,
        pageType: pageType
      }, {
        _this: this
      }, res => {
        this.searchLawsInfo()
      }, e => {

      })
    },
    // 导出法规信息
    exportLawsInfo () {
      if (this.selectedList.length === 0) {
        this.$Message.error('请选择要导出的数据')
      } else {
        window.location.href = '/api/lawss/sarLawsInfo/exportLawsInfos?ids=' + this.selectedList.join(',')
      }
    },
    // 法规条目相关方法
    // 分页查询条目
    searchLawsItems (lawsId) {
      this.showLawsItemsModal = true
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
      // this.SarLawsItemsEO = ''
      this.addLawsItemsModal = true
      this.addLawsItemsTitle = '新增法规条目'
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
      this.$refs['SarLawsItemsEO'].validate((valid) => {
        if (valid) {
          if (this.SarLawsItemsEO.applyArctic != null && this.SarLawsItemsEO.applyArctic !== '' && this.SarLawsItemsEO.applyArctic !== undefined) {
            this.SarLawsItemsEO.applyArctic = this.breakMultiSelect(this.SarLawsItemsEO.applyArctic)
          }
          if (this.SarLawsItemsEO.energyKind != null && this.SarLawsItemsEO.energyKind !== '' && this.SarLawsItemsEO.energyKind !== undefined) {
            this.SarLawsItemsEO.energyKind = this.breakMultiSelect(this.SarLawsItemsEO.energyKind)
          }
          if (this.SarLawsItemsEO.tackTime != null) {
            this.SarLawsItemsEO.tackTime = this.$dateFormat(this.SarLawsItemsEO.tackTime, 'yyyy-MM-dd')
          }
          this.SarLawsItemsEO.lawsId = this.saveLawsId
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
        } else {
          this.$Message.error('请检查表单是否填写正确')
        }
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
            this.searchLawsItems(this.saveLawsId)
          }, e => {
          })
        },
        onCancel: () => {
        }
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
      if (value != null && value !== '' && value !== undefined) {
        let stringValue = ''
        for (let i = 0; i < value.length; i++) {
          stringValue += value[i] + ','
        }
        return stringValue
      } else {
        return value
      }
    },
    // 多选合并为数组显示
    combineToArray (value) {
      if (value != null && value !== '' && value !== undefined) {
        let arrayValue = value.split(',')
        return arrayValue
      } else {
        return value
      }
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
    },
    loadDicTypeDatas6 () {
      this.$http.get('sys/dictype/getDicTypeByDicCode', {
        dicCode: 'COUNTRY'
      }, {
        _this: this
      }, res => {
        if (res.data != null) {
          this.countryOptions = res.data
        }
      }, e => {
      })
    },
    // 列表相关方法
    handleSelectAll (checked) {
      // 全部选中
      if (checked) {
        // 1.遍历数据,把每一项的checkbox置为选中状态
        this.selectedList = []
        for (let i = 0; i < this.browseList.length; i++) {
          this.$set(this.browseList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.browseList[i].id)
        }
        // 全部取消选中
      } else {
        // 1.遍历数据,把每一项的checkbox置为取消选中状态
        for (let i = 0; i < this.browseList.length; i++) {
          this.$set(this.browseList[i], 'checked', false)
          // 2.清空selectedList数组
          this.selectedList = []
        }
      }
    },
    handleCardClick (item, event) {
      item.checked = !item.checked
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {
    browseList: {
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
      if (oldVal.length === this.browseList.length && newVal.length !== 0) {
        this.checkAll = false
        this.indeterminate = true
      } else if (newVal.length === 0) {
        this.checkAll = false
        this.indeterminate = false
      }
    }
  },
  mounted () {
    this.setting = {
      edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
        // drag: {
        //   prev: MoveTest.prevTree,
        //   next: MoveTest.nextTree,
        //   inner: MoveTest.innerTree
        // }
      },
      data: {
        keep: {
          parent: true,
          leaf: true
        },
        simpleData: {
          enable: true
        }
      },
      callback: {
        // beforeDrag: MoveTest.dragTree2Dom,
        // onDrop: MoveTest.dropTree2Dom,
        // onDragMove: MoveTest.dragMove,
        // onMouseUp: MoveTest.dom2Tree,
        onClick: this.clickOneNode
      },
      view: {
        selectedMulti: false
      }
    }
    this.searchLawsInfo()
    this.loadInfoTree()
    this.loadDicTypeDatas6()
    this.loadDicTypeDatas1()
    this.loadDicTypeDatas2()
    this.loadDicTypeDatas3()
    this.loadDicTypeDatas4()
    this.loadDicTypeDatas5()
  }
}