import ComHeader from '@/pages/components/Header'
import SideBar from '@/pages/components/SideBar'
export default {
  name: 'index',
  data () {
    return {
      selectKey: '',
      selectValue: '',
      selectinfoList: [], // 记录查询出来的结果
      searchInfoEO: {
        page: 1,
        pageSize: 10
      }, // 记录查询条件
      total: 0,
      loading: false,
      logoSm: require('assets/images/home/logo_sm.png'),
      standStateOptions: [] // 标准状态数据记录，用来修改，标准，法规的状态颜色
    }
  },
  methods: {
    // 分页查询标准法规
    getStandardLawsTable () {
      this.$http.post('search/searchCenter/searchSarBykey', this.searchInfoEO, {
        _this: this, loading: 'loading'
      }, res => {
        console.log(res)
        this.selectinfoList = res.data.list
        this.total = res.data.count
      }, e => {
      })
    },
    // 分页点击后方法
    pageChange (page) {
      this.searchInfoEO.page = page
      this.getStandardLawsTable()
    },
    // 分页每页显示数改变后方法
    pageSizeChange (pageSize) {
      this.searchInfoEO.pageSize = pageSize
      this.getStandardLawsTable()
      // 此处需要调用接口，修改个人配置
    }
  },
  components: {
    ComHeader,
    SideBar
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.searchInfoEO.selectKey = this.$route.params.selectKey
    this.searchInfoEO.selectValue = this.$route.params.selectValue
    this.getStandardLawsTable()

    // 从数据字典中查询相关数据
    this.$http.get('sys/dictype/getDicTypeListCode', '', {
      _this: this
    }, res => {
      this.standStateOptions = res.data.STANDSTATE
    }, e => {
    })
  }
}
