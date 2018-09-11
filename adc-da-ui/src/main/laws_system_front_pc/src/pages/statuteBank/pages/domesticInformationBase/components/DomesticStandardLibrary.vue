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
    <!-- <loading :loading="loading">数据获取中</loading>-->
     <!--<Table border ref="selection" :columns="tableColumn" :data="stahndinfoList"></Table>
     <Card style="width:98%;padding:2px;margin: 5px 5px 5px 5px;align-items: center"  v-for="item in stahndinfoList">
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
     </Card>-->
     <!--    新增样式     -->
     <div class="standards-info-form" >
       <Form ref="sarStandardsInfoEO" :model="sarStandardsInfoEO" :rules="sarStandardsInfoRules" :label-width="80" class="label-input-form">
         <Row>
           <Col span="8">
            <FormItem label="国家/地区" prop="country" class="standards-info-item">
              <Input v-model="sarStandardsInfoEO.country" disabled="disabled"></Input>
            </FormItem>
           </Col>
           <Col span="8">
           <!--<FormItem label="标准类别" prop="standSort" class="standards-info-item">-->
             <label-select v-model="sarStandardsInfoEO.standSort" :options="standSortOptions" label="标准类别"></label-select>
           <!--</FormItem>-->
           </Col>
           <Col span="8">
           <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.applyArctic"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="标准编号" prop="standNumber" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standNumber"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准年份" prop="standYear" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standYear"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准名称" prop="standName" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standName"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="标准英文名称" prop="standEnName" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standEnName"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <!--<FormItem label="标准状态" prop="standState" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standState"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.standState" :options="standStateOptions" label="标准状态"></label-select>
           </Col>
           <Col span="8">
           <FormItem label="标准性质" prop="standNature" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standNature"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="代替标准号" prop="replaceStandNum" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.replaceStandNum"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="被代替标准号" prop="replacedStandNum" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.replacedStandNum"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="采用国际标准号" prop="interStandNum" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.interStandNum"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="采标程度" prop="adoptExtent" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.adoptExtent"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="能源种类" prop="emergyKind" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.emergyKind"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="适用认证" prop="applyAuth" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.applyAuth"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <!--<FormItem label="发布日期" prop="issueTime" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.issueTime"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.issueTime"  label="发布日期" type="datePicker"></label-select>
           </Col>
           <Col span="8">
           <!--<FormItem label="实施日期" prop="putTime" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.putTime"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.putTime"  label="实施日期" type="datePicker"></label-select>
           </Col>
           <Col span="8">
           <!--<FormItem label="新定型车实施日期" prop="newcarPutTime" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.newcarPutTime"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.newcarPutTime"  label="新定型车实施日期" type="datePicker"></label-select>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <!--<FormItem label="在产车实施日期" prop="productPutTime" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.productPutTime"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.productPutTime"  label="在产车实施日期" type="datePicker"></label-select>
           </Col>
           <Col span="8">
           <!--<FormItem label="新生产车实施日期" prop="newproductPutTime" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.newproductPutTime"></Input>
           </FormItem>-->
           <label-select v-model="sarStandardsInfoEO.newproductPutTime"  label="新生产车实施日期" type="datePicker"></label-select>
           </Col>
           <Col span="8">
           <FormItem label="起草单位" prop="draftingUnit" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.draftingUnit"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="起草人" prop="draftUser" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.draftUser"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准文本" prop="standFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standFile"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准修改单" prop="standModifyFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standModifyFile"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="草案" prop="draftFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.draftFile"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="征求意见稿" prop="opinionFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.opinionFile"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="送审稿" prop="sentScreenFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.sentScreenFile"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="报批稿" prop="approvalFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.approvalFile"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="关联文件" prop="relevanceFile" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.relevanceFile"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="关键词" prop="tags" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.tags"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="内容摘要" prop="synopsis" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.synopsis"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="责任部门" prop="responsibleUnit" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.responsibleUnit"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="所属类别" prop="category" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.category"></Input>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
           <FormItem label="备注" prop="remark" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.remark"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准分解单" prop="cname" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.standName"></Input>
           </FormItem>
           </Col>
           <Col span="8">
           </Col>
         </Row>
       </Form>
       <input type="button" value="保存修改" class="save primary-btn" :onclick="saveOrUpdateStands">
     </div>
   </div>
   <!--<pagination :total="total"></pagination>-->

   <!--<Modal v-model="modalshowflag"  :title="modalshowtitle" width="800" height="500" @ok-text ="保存成功" @on-ok="saveOrUpdateStands" @on-cancel="cancel" >
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
         <FormItem label="时 间" prop="phone" class="user-info-item">
           <DatePicker v-model="sarStandardsInfoEO.putTime" type="datetime" placeholder="Select date and time" style="width: 200px"></DatePicker>
         </FormItem>
       </Form>
     </div>
   </Modal>-->
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
        standType: 'INLAND_STAND', // 标准分类
        country: '中国',
        standSort: '',
        applyArctic:'',
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
    tags: '',
    synopsis: '',
    responsibleUnit: '',
    category: '',
    remark: ''
      },
      sarStandardsInfoRules: {
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
        replaceStandNum: [
        ],
        replacedStandNum: [
        ],
        interStandNum: [
        ],
        adoptExtent: [
        ],
        emergyKind: [
          { required: true, message: '能源种类不能为空', trigger: 'blur' }
        ],
        applyAuth: [
        ],
        issueTime: [
          { required: true, message: '发布日期不能为空', trigger: 'blur' }
        ],
        putTime: [
          { required: true, message: '实施日期不能为空', trigger: 'blur' }
        ],
        newcarPutTime: [
        ],
        productPutTime: [
        ],
        newproductPutTime: [
        ],
        draftingUnit: [
        ],
        draftUser: [
        ],
        standFile: [
        ],
        standModifyFile: [
        ],
        draftFile: [
        ],
        opinionFile: [
        ],
        sentScreenFile: [
        ],
        approvalFile: [
        ],
        relevanceFile: [
        ],
        tags: [
        ],
        synopsis: [
        ],
        responsibleUnit: [
        ],
        category: [
        ],
        remark: [
        ]
      },
      standSortOptions: [{ label: '类别1' ,value: '类别1' }],  //标准类别下拉框
      standStateOptions: [{ label: '状态1' ,value: '状态2' }]   //标准状态下拉框
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
      if (this.addOrUPdateFlag === 1) {
        alert("aaaaa")
        this.$http.post('lawss/sarStandardsInfo/addarStandardsInfo', this.sarStandardsInfoEO, {
          _this: this
        }, res => {
          this.getDomesticStandardTable()
        }, e => {
        })
      } else {
        // 修改
        console.log(JSON.stringify(this.sarStandardsInfoEO))
        alert(this.sarStandardsInfoEO.id)
        /* let ogje;
        ogje.id = this.sarStandardsInfoEO.id
        ogje.standNumber = this.sarStandardsInfoEO.standNumber  // 用户名
        ogje.standName =this.sarStandardsInfoEO.standName // 姓名
        ogje.standNature = this.sarStandardsInfoEO.standNature // 任职部门
        ogje.standState = this.sarStandardsInfoEO.standState */
        // console.log(JSON.stringify(ogje))
        this.$http.post('lawss/sarStandardsInfo/updateSarStandardsInfo', {id: this.sarStandardsInfoEO.id, putTime: '2018-08-11 11:12:12'}, {
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
    searchData () {},
    // 需求中操作栏中操作函数
    // 查看标准属性
    selectStandardPro () {
      this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 收藏标准
    collectStandard () {
      this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 分享
    shareStandard () {
      this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    },
    // 跳转流程节点
    queryProcess () {
      this.$http.post('', {id: this.sarStandardsInfoEO.id}, {
        _this: this
      }, res => {
      }, e => {
      })
    }
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
   .standards-info-item{
     .ivu-form-item-content{
       width: 6rem;
     }
   }
   .standards-info-form{
     min-height: 400px;
     overflow : auto;
     .save{
       width: 4.24rem;
       height: 0.72rem;
       line-height: 0.72rem;
       margin: 0.5rem 0 0 0.2rem;
     }
   }
</style>
