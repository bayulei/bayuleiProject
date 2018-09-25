export default {
  name: 'enterpriseStandardAnnualPlan',
  data () {
    return {
      showStandPlanModal: false,
      saveStandPlanBtn: true,
      reviewSubmitTime: false,
      reviewMeetTime: false,
      reviewModifyTime: false,
      startFlowTime: false,
      showStandPlanTitle: '',
      saveSelectedDatas: '',
      standPlan: {
        standName: '',
        makeRevisonType: '',
        replaceStandNum: '',
        compileUnit: ''
      },
      submitStandPlan: {
        id: '',
        standName: '',
        makeRevisonType: '',
        replaceStandNum: '',
        compileUnit: '',
        reviewLevel: '',
        compilersUser: '',
        finishReviewTime: ''
      },
      standPlanTotal: 0,
      page: 1,
      rows: 10,
      loading: false,
      standPlanTable: [
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
          title: '标准名称',
          width: 100,
          key: 'standName'
        },
        {
          title: '制修订类别',
          width: 120,
          key: 'makeRevisonType'
        },
        {
          title: '代替标准代号',
          width: 120,
          key: 'replaceStandNum'
        },
        {
          title: '编制部门',
          width: 120,
          key: 'compileUnit'
        },
        {
          title: '完成部门评审提交时间',
          width: 120,
          key: 'finishReviewTime'
        },
        {
          title: '报批稿完成时间',
          width: 120,
          key: 'approvalDraftTime'
        },
        {
          title: '评审稿提交日期',
          width: 120,
          key: 'reviewSubmitTime'
        },
        {
          title: '开始流程日期',
          width: 120,
          key: 'startFlowTime'
        },
        {
          title: '距评审稿提交天数',
          width: 120,
          key: 'reviewSubmitDays'
        },
        {
          title: '提交延迟天数',
          width: 120,
          key: 'submitDelayDays'
        },
        {
          title: '距计划发布天数',
          width: 120,
          key: 'planReleaseDays'
        },
        {
          title: '发布延迟天数',
          width: 120,
          key: 'releaseDelayDays'
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
                    this.openPlanModal(params.row, 'editOpt')
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
                    this.deleteStandPlan(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      standPlanListDatas: [],
      makeRevisonTypeOptions: [
        { label: '修订', value: '1' },
        { label: '勘误', value: '2' }
      ],
      reviewLevelOptions: [
        { label: '部门级', value: '1' },
        { label: '科室级', value: '2' },
        { label: '院级', value: '3' }
      ],
      standPlanRules: {},
      submitStandPlanFormRules: {
        standName: [
          { required: true, message: '标准名称不能为空', trigger: 'blur' },
          { type: 'string', max: 100, message: '标准名称长度不能超过100个字符', trigger: 'blur' }
        ],
        makeRevisonType: [
          { required: true, message: '制修订类别不能为空', trigger: 'blur' }
        ],
        replaceStandNum: [
          { required: true, message: '代替标准代号不能为空', trigger: 'blur' },
          { type: 'string', max: 100, message: '代替标准代号长度不能超过100个字符', trigger: 'blur' }
        ],
        compileUnit: [
          { required: true, message: '编制部门不能为空', trigger: 'blur' },
          { type: 'string', max: 100, message: '编制部门长度不能超过100个字符', trigger: 'blur' }
        ],
        reviewLevel: [
          { required: true, message: '评审级别不能为空', trigger: 'blur' }
        ],
        compilersUser: [
          { required: true, message: '主要编制者不能为空', trigger: 'blur' },
          { type: 'string', max: 100, message: '主要编制者长度不能超过100个字符', trigger: 'blur' }
        ],
        finishReviewTime: [
          { required: true, type: 'date', message: '完成部门评审提交时间不能为空', trigger: 'change' }
        ],
        approvalDraftTime: [
          { required: true, type: 'date', message: '报批稿完成时间不能为空', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    // 分页查询年度计划
    searchStandPlan () {
      this.standPlan.page = this.page
      this.standPlan.pageSize = this.rows
      this.$http.get('lawss/sarBussStandPlan/page', this.standPlan, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.standPlanListDatas = res.data.list
        this.standPlanTotal = res.data.count
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
    // 新增年度标准计划
    openPlanModal (row, optType) {
      if (optType === 'addOpt') {
        this.$refs['submitStandPlan'].resetFields()
        this.reviewSubmitTime = false
        this.reviewMeetTime = false
        this.reviewModifyTime = false
        this.startFlowTime = false
        this.showStandPlanTitle = '新增年度标准计划'
      } else {
        this.showStandPlanTitle = '修改年度标准计划'
        this.reviewSubmitTime = true
        this.reviewMeetTime = true
        this.reviewModifyTime = true
        this.startFlowTime = true
        this.submitStandPlan = row
      }
      this.showStandPlanModal = true
    },
    saveStandPlan () {
      this.$refs['submitStandPlan'].validate((valid) => {
        if (valid) {
          if (this.submitStandPlan.finishReviewTime != null) {
            this.submitStandPlan.finishReviewTime = this.$dateFormat(this.submitStandPlan.finishReviewTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.reviewSubmitTime != null) {
            this.submitStandPlan.reviewSubmitTime = this.$dateFormat(this.submitStandPlan.reviewSubmitTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.reviewMeetTime != null) {
            this.submitStandPlan.reviewMeetTime = this.$dateFormat(this.submitStandPlan.reviewMeetTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.reviewModifyTime != null) {
            this.submitStandPlan.reviewModifyTime = this.$dateFormat(this.submitStandPlan.reviewModifyTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.startFlowTime != null) {
            this.submitStandPlan.startFlowTime = this.$dateFormat(this.submitStandPlan.startFlowTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.approvalDraftTime != null) {
            this.submitStandPlan.approvalDraftTime = this.$dateFormat(this.submitStandPlan.approvalDraftTime, 'yyyy-MM-dd')
          }
          if (this.submitStandPlan.id == null || this.submitStandPlan.id === '') {
            this.$http.post('lawss/sarBussStandPlan/createStandPlan', this.submitStandPlan, {
              _this: this
            }, res => {
              this.showStandPlanModal = false
              this.searchStandPlan()
            }, e => {
            })
          } else {
            this.$http.put('lawss/sarBussStandPlan/updateStandPlan', this.submitStandPlan, {
              _this: this
            }, res => {
              this.showStandPlanModal = false
              this.searchStandPlan()
            }, e => {})
          }
        } else {
          this.$Message.error('请检查表单是否填写正确')
        }
      })
    },
    cancelSubmit () {
      this.showStandPlanModal = false
    },
    // 删除年度标准计划
    deleteStandPlan (id) {
      this.$Modal.confirm({
        title: '确认删除',
        content: '<p>确认删除该条数据？</p>',
        onOk: () => {
          this.$http.put('lawss/sarBussStandPlan/deleteStandPlan', {
            id: id
          }, {
            _this: this
          }, res => {
            this.searchStandPlan()
          }, e => {
          })
        },
        onCancel: () => {
        }
      })
    },
    // 导出年度标准计划
    getSelectedDatas (selection, row) {
      this.saveSelectedDatas = selection
    },
    exportStandPlan () {
      if (this.saveSelectedDatas != null && this.saveSelectedDatas !== '') {
        let jsonData = JSON.stringify(this.saveSelectedDatas)
        window.location.href = '/api/lawss/sarBussStandPlan/exportStandPlan?exportPlanDatas=' + jsonData
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
    this.searchStandPlan()
  }
}
