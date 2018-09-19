export default {
  name: 'testItemDatabase',
  data () {
    return {
      showStandPlanModal: false,
      saveStandPlanBtn: true,
      reviewSubmitTime: false,
      reviewMeetTime: false,
      reviewModifyTime: false,
      startFlowTime: false,
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
          title: '报批稿完成时间',
          width: 120,
          key: 'approvalDraftTime'
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
                    this.openPlanModal(params.row,'editOpt')
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
      submitStandPlanFormRules: {}
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
        this.reviewSubmitTime = false
        this.reviewMeetTime = false
        this.reviewModifyTime = false
        this.startFlowTime = false
      } else {
        this.reviewSubmitTime = true
        this.reviewMeetTime = true
        this.reviewModifyTime = true
        this.startFlowTime = true
        this.submitStandPlan = row
      }
      this.showStandPlanModal = true
    },
    saveStandPlan () {
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
    },
    cancelSubmit () {
      this.showStandPlanModal = false
    },
    // 删除年度标准计划
    deleteStandPlan (id) {
      this.$http.put('lawss/sarBussStandPlan/deleteStandPlan', {
        id: id
      }, {
        _this: this
      }, res => {
        this.searchStandPlan()
      }, e => {
      })
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
