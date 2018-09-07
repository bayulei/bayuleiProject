<!-- 国内标准库 -->
<template>
 <div id="DomesticStandardLibrary">
   <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false">
     <div slot="left">
       <Input v-model="keywords1" placeholder="根据用户名查找" clearable class="my-input" />
       <Input v-model="keywords2" placeholder="根据描述查找" clearable class="my-input" />
       <Button type="primary" icon="ios-search" :loading="searching" @click="searchData"></Button>
       <Button type="primary" icon="ios-add" :loading="searching" @click="modal5=true">新增</Button>
     </div>
     <div slot="right">
       <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
     </div>
   </table-tools-bar>
   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="tableColumn" :data="stahndinfoList"></Table>
   </div>
   <pagination :total="total"></pagination>

   <Modal v-model="modal5" title="新增标准" width="800" height="500" @ok-text ="保存成功" @on-ok="saveStands" @on-cancel="cancel" >
     <div class="user-info-form">
       <Form ref="sarStandardsInfoEO" :model="sarStandardsInfoEO" :rules="userInfoRules" :label-width="80"  >
         <FormItem label="用户名" prop="username" class="user-info-item">
           <Input v-model="sarStandardsInfoEO.standNumber"></Input>
         </FormItem>
         <FormItem label="姓 名" prop="cname" class="user-info-item">
           <Input v-model="sarStandardsInfoEO.standName"></Input>
         </FormItem>
         <FormItem label="任职部门" prop="department" class="user-info-item">
           <Input v-model="sarStandardsInfoEO.standNature"></Input>
         </FormItem>
         <FormItem label="电 话" prop="phone" class="user-info-item">
           <Input v-model="sarStandardsInfoEO.standState"></Input>
         </FormItem>
       </Form>
     </div>
   </Modal>
 </div>
</template>

<script>
import TableToolsBar from 'pages/components/TableToolsBar'
import Pagination from 'pages/components/Pagination'
export default {
  name: 'DomesticStandardLibrary',
  data () {
    return {
      isAdvancedSearch: false, // 高级检索窗口是否打开
      keywords1: '',
      keywords2: '',
      searching: false,
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
        }
      ],
      stahndinfoList: [],
      modal5: false,
      sarStandardsInfoEO: {
        standNumber: '', // 用户名
        standName: '', // 姓名
        standNature: '', // 任职部门
        standState: ''
      },
      userInfoRules: {
        standNumber: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        standName: [
          { required: true, message: '个人邮箱不能为空', trigger: 'blur' }
        ],
        standNature: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' }
        ],
        standState: [
          { required: true, message: '传真不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 分页查询国内标准
    getDomesticStandardTable () {
      this.$http.get('lawss/sarStandardsInfo/getSarStandardsInfoPage', {}, {
        _this: this
      }, res => {
        this.stahndinfoList = res.data.list
      }, e => {
      })
    },
    saveStands () {
      console.log('aaaaaaaaaaaaaaaaaa' + this.sarStandardsInfoEO.standNumber)
      this.$http.post('lawss/sarStandardsInfo/addarStandardsInfo', this.sarStandardsInfoEO, {
        _this: this
      }, res => {
        alert('新增成功')
      }, e => {
      })
      /* this.axios.post('http://localhost:8888/api/lawss/sarStandardsInfo/addarStandardsInfo',{
        sarStandardsInfoEO:this.sarStandardsInfoEO
      }).then().catch() */
    },
    cancel () {},
    searchData () {}
  },
  components: {
    TableToolsBar,
    Pagination
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.getDomesticStandardTable()
  }
}
</script>

<style lang="less">
   #DomesticStandardLibrary{}
</style>
