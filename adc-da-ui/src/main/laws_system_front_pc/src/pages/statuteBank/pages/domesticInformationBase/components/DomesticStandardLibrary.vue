<!-- 国内标准库 -->
<template>
 <div id="DomesticStandardLibrary">
   <table-tools-bar :isAdvancedSearch="isAdvancedSearch" @toggleSearch="isAdvancedSearch = false" class="label-input-form">
     <div slot="left">
       <label-input v-model="keywords1" placeholder="根据用户名查找" clearable label="用户名"  />
       <label-input v-model="keywords2" placeholder="根据描述查找" clearable label="描述" class="my-input" />
       <Button type="primary" icon="ios-search" :loading="searching" @click="searchData"></Button>
     </div>
     <div slot="right">
       <Button type="primary" icon="ios-add" :loading="searching" @click="addModal">新增标准</Button>
       <Button type="primary" icon="ios-add" :loading="searching" @click="addImportModal">导入标准</Button>
       <Button type="primary" @click="isAdvancedSearch = true">高级检索</Button>
     </div>
   </table-tools-bar>

   <div class="content">
     <loading :loading="loading">数据获取中</loading>
     <!--<Table border ref="selection" :columns="tableColumn" :data="stahndinfoList"></Table>-->

     <Card style="width:98%;padding:2px;margin: 5px 5px 5px 5px;align-items: center"  v-for="(item, index) in stahndinfoList" :key="index">
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

   <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
   <!-- 新增、编辑模态窗 -->
   <full-modal v-model="modalshowflag" v-if="modalshowflag" ref="modalshow">
     <!--    新增样式     -->
     <div class="standards-info-form" >
       <Form ref="sarStandardsInfoEO" :model="sarStandardsInfoEO" :rules="sarStandardsInfoRules" class="label-input-form">
         <Row>
           <Col span="8">
           <FormItem label="国家/地区" prop="country" class="standards-info-item">
             <Input v-model="sarStandardsInfoEO.country" disabled="disabled"></Input>
           </FormItem>
           </Col>
           <Col span="8">
          <FormItem label="标准类别" prop="standSort" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.standSort" :options="standSortOptions">
               <Option v-for="opt in standSortOptions" :key="opt.value" :valu="opt.value">{{ opt.label }}</Option>
             </Select>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="适用车型" prop="applyArctic" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.applyArctic" multiple>
               <Option v-for="item in applyArcticOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
             </Select>
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
           <FormItem label="标准状态" prop="standState" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.standState">
               <Option v-for="opt in standStateOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
             </Select>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="标准性质" prop="standNature" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.standNature">
               <Option v-for="opt in standNatureOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
             </Select>
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
             <Select v-model="sarStandardsInfoEO.adoptExtent">
               <Option v-for="opt in adoptExtentOptions" :key="opt.value" :value="opt.value">{{ opt.label }}</Option>
             </Select>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="能源种类" prop="emergyKind" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.emergyKind" multiple>
               <Option v-for="item in emergyKindOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
             </Select>
           </FormItem>
           </Col>
           <Col span="8">
           <FormItem label="适用认证" prop="applyAuth" class="standards-info-item">
             <Select v-model="sarStandardsInfoEO.applyAuth" multiple>
               <Option v-for="item in applyAuthOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
             </Select>
           </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="发布日期" prop="issueTime" class="standards-info-item">
               <DatePicker v-model="sarStandardsInfoEO.issueTime"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="实施日期" prop="putTime" class="standards-info-item">
               <DatePicker v-model="sarStandardsInfoEO.putTime"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="新定型车实施日期" prop="newcarPutTime" class="standards-info-item">
               <DatePicker v-model="sarStandardsInfoEO.newcarPutTime"></DatePicker>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
              <FormItem label="在产车实施日期" prop="productPutTime" class="standards-info-item">
                <DatePicker v-model="sarStandardsInfoEO.productPutTime"></DatePicker>
              </FormItem>
           </Col>
           <Col span="8">
              <FormItem label="新生产车实施日期" prop="newproductPutTime" class="standards-info-item">
                <DatePicker v-model="sarStandardsInfoEO.newproductPutTime"></DatePicker>
              </FormItem>
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
             <Select v-model="sarStandardsInfoEO.category" multiple>
               <Option v-for="item in categoryOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
             </Select>
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
       <Button type="primary" @click="saveOrUpdateStands">保存修改</Button>
     </div>
   </full-modal>
   <!-- 导入模态窗 -->
   <Modal v-model="importModalshowflag" title="导入文件" >
     <Upload action="/api/lawss/sarStandardsInfo/importStandardsInfo" ref="importfile" name="file" :format="['xlsx']" :on-format-error="handleFormatError" :on-success="importFileSuccess">
       <Button icon="ios-cloud-upload-outline">选择文件</Button>
     </Upload>
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
      importModalshowflag: false,
      modalshowtitle: '新增标准',
      addOrUPdateFlag: 1, // 新增：1， 修改：2
      sarStandardsInfoEO: {
        id: '',
        standType: 'INLAND_STAND', // 标准分类
        country: '中国',
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
        tags: '',
        synopsis: '',
        responsibleUnit: '',
        category: '',
        remark: ''
      }, // 新增过程中用到的对象
      sarStandardsSearch: {page: 1, pageSize: 10}, // 分页查询过程中用到的对象
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
      standSortOptions: [], // 标准类别下拉框
      applyArcticOptions: [], // 适用车型下拉框
      standStateOptions: [], // 标准状态下拉框
      standNatureOptions: [], // 标准性质下拉框
      adoptExtentOptions: [], // 采标程度下拉框
      emergyKindOptions: [], // 能源种类下拉框
      applyAuthOptions: [], // 适用认证下拉框
      categoryOptions: [] // 所属类别下拉框
    }
  },
  methods: {
    // 分页查询国内标准
    getDomesticStandardTable () {
      this.$http.get('lawss/sarStandardsInfo/getSarStandardsInfoPage', this.sarStandardsSearch, {
        _this: this, loading: 'loading'
      }, res => {
        this.stahndinfoList = res.data.list
        this.total = res.data.count
      }, e => {
      })
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
    // 点击新增按钮弹出新增模态框
    addModal () {
      this.modalshowflag = true
      this.modalshowtitle = '新增标准'
      this.addOrUPdateFlag = 1
      // 查询各下拉框数据
      this.$http.get('/sys/dictype/getDicTypeListCode', this.sarStandardsInfoEO, {
        _this: this
      }, res => {
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
    },
    // 保存或修改标准
    saveOrUpdateStands () {
      // 新增
      if (this.addOrUPdateFlag === 1) {
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
    },
    // 关闭新增模态模态框
    closeModal () {
      this.$refs.modalshow.toggleClose()
    },
    // 点击导入标准
    addImportModal () {
      this.importModalshowflag = true
      this.$refs.importfile.clearFiles()
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
       /*width: 6rem;*/
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
