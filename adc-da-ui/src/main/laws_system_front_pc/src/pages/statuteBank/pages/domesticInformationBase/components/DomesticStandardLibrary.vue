<!-- 国内标准库 -->
<template>
 <div id="DomesticStandardLibrary">
   <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false" class="label-input-form">
     <div slot="left">
       <Input v-model="keywords1" placeholder="根据用户名查找" clearable  />
       <Input v-model="keywords2" placeholder="根据描述查找" clearable class="my-input" />
       <Button type="primary" icon="ios-search" :loading="searching" @click="searchData"></Button>
       <Button type="primary" icon="ios-add" :loading="searching" @click="addModal">新增</Button>
     </div>
     <div slot="right">
       <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
     </div>
   </table-tools-bar>

   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <Table border ref="selection" :columns="tableColumn" :data="stahndinfoList"></Table>
     <ul id="example-2">
       <Card style="width:98%;padding:2px;margin: 5px 5px 5px 5px;align-items: center"  v-for="(item, index) in stahndinfoList">
         <div style="text-align:center">
           <Row>
             <Col span="4">标准号:{{item.standNumber}} </Col>
             <Col span="4">标准名称:{{item.standName}} </Col>
             <Col span="4">标准性质:{{item.standNature}} </Col>
             <Col span="4"></Col>
             <Col span="4"></Col>
             <Col span="4" align="right"><Icon type="ios-star-outline" /><Icon type="ios-redo"  size="400px" /></Col>
           </Row>
           <br>
           <Row>
             <Col span="4">标准状态:{{item.standState}}</Col>
             <Col span="4">发布日期:{{item.issueTime}}</Col>
             <Col span="4">实施日期:{{item.putTime}}</Col>
             <Col span="4"></Col>
             <Col span="4"></Col>
             <Col span="4">col-12</Col>
           </Row>
         </div>
       </Card>
     </ul>
   </div>
   <pagination :total="total"></pagination>

   <Modal v-model="modalshowflag"  :title="modalshowtitle" width="800" height="500" @ok-text ="保存成功" @on-ok="saveOrUpdateStands" @on-cancel="cancel" >
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
         <FormItem label="电 话" prop="phone" class="user-info-item">
           <DatePicker v-model="sarStandardsInfoEO.putTime" type="datetime" placeholder="Select date and time" style="width: 200px"></DatePicker>
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
                    // this.updateStand()
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

      modalshowflag: false,
      modalshowtitle: '新增标准',
      addOrUPdateFlag: 1, // 新增：1， 修改：2
      sarStandardsInfoEO: {
        id: '',
        standNumber: '', // 用户名
        standName: '', // 姓名
        standNature: '', // 任职部门
        standState: '',
        putTime: 1536146794000
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
    addModal () {
      this.modalshowflag = true
      this.modalshowtitle = '新增标准'
      this.addOrUPdateFlag = 1
      this.$refs['sarStandardsInfoEO'].resetFields()
    },
    // 保存或修改标准
    saveOrUpdateStands () {
      // 新增
      if (this.addOrUPdateFlag == 1) {
        this.$http.post('lawss/sarStandardsInfo/addarStandardsInfo', this.sarStandardsInfoEO, {
          _this: this
        }, res => {
          this.getDomesticStandardTable()
        }, e => {
        })
      }
      // 修改
      else {
        console.log(JSON.stringify(this.sarStandardsInfoEO))
        alert(this.sarStandardsInfoEO.id)
        /* let ogje;
        ogje.id = this.sarStandardsInfoEO.id
        ogje.standNumber = this.sarStandardsInfoEO.standNumber  // 用户名
        ogje.standName =this.sarStandardsInfoEO.standName // 姓名
        ogje.standNature = this.sarStandardsInfoEO.standNature // 任职部门
        ogje.standState = this.sarStandardsInfoEO.standState */
        // console.log(JSON.stringify(ogje))
        this.$http.post('lawss/sarStandardsInfo/updateSarStandardsInfo', {id: this.sarStandardsInfoEO.id, putTime: null}, {
          _this: this
        }, res => {
          this.getDomesticStandardTable()
        }, e => {
        })
      }

      /* this.axios.post('http://localhost:8888/api/lawss/sarStandardsInfo/addarStandardsInfo',{
        sarStandardsInfoEO:this.sarStandardsInfoEO
      }).then().catch() */
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
    updateStand () {},
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
