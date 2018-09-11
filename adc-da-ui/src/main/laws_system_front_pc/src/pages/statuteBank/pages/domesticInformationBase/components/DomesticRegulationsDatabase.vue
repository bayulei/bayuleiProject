<!-- 国内法规库 -->
<template>
 <div class="personal-data">
    <table-tools-bar>
      <div class="laws-info-form" slot="left">
        <Form ref="lawsInfo" :model="lawsInfo" :rules="lawsInfoRules" :label-width="80" class="label-input-form">
          <FormItem label="文件号" prop="fileNum" class="laws-info-item">
            <Input v-model="lawsInfo.fileNum"></Input>
          </FormItem>
          <Button type="primary" icon="ios-search" @click="searchLawsInfo"></Button>
        </Form>
      </div>
      <div slot="right">
        <Button type="primary" @click="openLawsModal">新增</Button>
        <Button type="primary" @click="modal2 = true">导入</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data" :height="550"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>

   <!--新增修改模态框-->
   <full-modal v-model="showLawsInfoModal" v-if="showLawsInfoModal" ref="showLawsInfoModal">
     <Button @click="closeModal">关闭</Button>
     <div>
       <Form ref="SarLawsInfoEO" :model="SarLawsInfoEO" :rules="lawsInfoFormRules" class="label-input-form">
         <input v-model="SarLawsInfoEO.editLawsId" v-show="false">
         <Row>
           <Col span="8">
             <FormItem label="国家/地区" prop="country" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.country" disabled="disabled"></Input>
             </FormItem>
          </Col>
           <Col span="8">
             <label-select v-model="SarLawsInfoEO.lawsProperty" :options="lawsPropertyOptions" label="文件性质"></label-select>
           </Col>
           <Col span="8">
             <FormItem label="文件号" prop="lawsNumber" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.lawsNumber"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="文件名称" prop="lawsName" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.lawsName"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="发布单位" prop="issueUnit" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.issueUnit"></Input>
             </FormItem>
           </Col>
           <Col span="8">
              <label-select v-model="SarLawsInfoEO.lawsStatus" :options="lawsStatusOptions" label="文件状态"></label-select>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="发布日期" prop="issueTime" class="laws-info-item">
               <DatePicker type="date" v-model="SarLawsInfoEO.issueTime" format="yyyy-MM-dd"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="实施日期" prop="putTime" class="laws-info-item">
               <DatePicker v-model="SarLawsInfoEO.putTime"></DatePicker>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="代替文件号" prop="replaceLawsNum" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.replaceLawsNum"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="适用车型" prop="applyArctic" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.applyArctic"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="能源种类" prop="energyKind" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.energyKind"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="适用认证" prop="applyAuth" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.applyAuth"></Input>
             </FormItem>
           </Col>
         </Row>
         <Row>
           <Col span="8">
             <FormItem label="责任部门" prop="responsibleUnit" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.responsibleUnit"></Input>
             </FormItem>
           </Col>
           <Col span="8">
             <FormItem label="链接" prop="linkUri" class="laws-info-item">
               <Input v-model="SarLawsInfoEO.linkUri"></Input>
             </FormItem>
           </Col>
         </Row>
       </Form>

     </div>
     <div class="save-laws-btn">
       <Button type="primary" @click="saveLawsInfo">提交</Button>
       <Button @click="cancelAdd">取消</Button>
     </div>
   </full-modal>

   <!--导入模态框-->
   <Modal v-model="modal2" title="导入法规信息" @on-ok="importLawsInfo" @on-cancel="cancelAdd">
     <Form ref="lawsInfoImport" :model="lawsInfoImport" :label-width="80">
       <FormItem label="导入文件" prop="fileName" class="laws-info-item">
         <input type="file" ref="lawsInfoFile" id="lawsInfoFile" @change="lawsInfoFileBeforeUpload">
         <Button @click="openFile">导入文件</Button>
       </FormItem>
     </Form>
   </Modal>

   </div>
</template>

<script>
import Pagination from 'pages/components/Pagination'
import tableToolsBar from 'pages/components/TableToolsBar'
export default {
  name: 'DomesticRegulationsDatabase',
  data () {
    return {
      modal2: false,
      showLawsInfoModal: false,
      lawsInfo: {
        fileNum: '' // 文件号
      },
      SarLawsInfoEO: {
        editLawsId: '',
        country: '中国',
        lawsProperty: '',
        lawsNumber: '',
        lawsName: '',
        issueUnit: '',
        lawsStatus: '',
        issueTime: '',
        putTime: '',
        replaceLawsNum: '',
        applyArctic: '',
        energyKind: '',
        applyAuth: '',
        responsibleUnit: '',
        linkUri: ''
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      lawsInfoImport: {},
      tableColumn: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '文件号',
          key: 'lawsNumber'
        },
        {
          title: '文件性质',
          key: 'lawsProperty'
        },
        {
          title: '文件名称',
          key: 'lawsName'
        },
        {
          title: '发布单位',
          key: 'issueUnit'
        },
        {
          title: '文件状态',
          key: 'lawsStatus'
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
          title: '适用车型',
          key: 'applyArctic'
        },
        {
          title: '能源种类',
          key: 'energyKind'
        },
        {
          title: '适用认证',
          key: 'applyAuth'
        },
        {
          title: '修改时间',
          key: 'modifyTime'
        },
        {
          title: '操作',
          key: 'action',
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
                    this.edit(params.row)
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
                    this.remove(params.row.id)
                  }
                }
              }, '删除')
            ])
          }
        }
      ],
      data: [],
      lawsPropertyOptions: [{ label: '状态1', value: '状态2' }],
      lawsStatusOptions: [{ label: '状态1', value: '状态2' }],
      lawsInfoRules: {},
      lawsInfoFormRules: {
        lawsName: [
          { required: true, message: '文件名称不能为空', trigger: 'blur' }
        ],
        lawsStatus: [
          { required: true, message: '文件状态不能为空', trigger: 'blur' }
        ],
        issueTime: [
          { required: true, message: '发布日期不能为空', trigger: 'blur' }
        ],
        putTime: [
          { required: true, message: '实施日期不能为空', trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    // 分页查询
    searchLawsInfo () {
      this.$http.get('lawss/sarLawsInfo/page', {
        page: this.page,
        pageSize: this.rows,
        lawsNumber: this.lawsInfo.fileNum
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.data = res.data.list
        this.total = res.data.count
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
      this.showLawsInfoModal = true
    },
    // 点击编辑按钮触发
    edit (row) {
      this.showLawsInfoModal = true
      this.SarLawsInfoEO.editLawsId = row.id
      this.SarLawsInfoEO.lawsNumber = row.lawsNumber
      this.SarLawsInfoEO.lawsName = row.lawsName
    },
    // 提交新增/修改
    saveLawsInfo () {
      this.SarLawsInfoEO.issueTime = this.SarLawsInfoEO.issueTime.getTime()
      let SarLawsInfoEO = JSON.parse(JSON.stringify(this.SarLawsInfoEO))
      SarLawsInfoEO.issueTime = this.$dateFormat('yyyy-MM-dd', SarLawsInfoEO.issueTime)
      console.log(SarLawsInfoEO)
      if (this.SarLawsInfoEO.editLawsId == null || this.SarLawsInfoEO.editLawsId === '') {
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
        }, e => {

        })
      }
    },
    cancelAdd () {
    },
    // 关闭模态框
    closeModal () {
      this.$refs.showLawsInfoModal.toggleClose()
    },
    // 删除
    remove (id) {
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
    openFile () {
      $('#lawsInfoFile').click()
    },
    lawsInfoFileBeforeUpload (val) {
      let file = this.$refs.lawsInfoFile.files[0].name
      console.log(file)
    },
    // 导入
    importLawsInfo () {
      let file = this.$refs.lawsInfoFile.files[0]
      this.$http.post('lawss/sarLawsInfo/importLawsInfos', {
        file: file
      }, {
        _this: this
      }, res => {
        this.searchLawsInfo()
      }, e => {

      })
    }
  },
  components: {
    Pagination,
    tableToolsBar
  },
  props: {},
  computed: {},
  watch: {
    page (newVal, oldVal) {
      //
    }
  },
  mounted () {
    this.searchLawsInfo()
  }
}
</script>

<style lang="less">
  .personal-data{
    .searchBtn{
      width: 2rem;
      height: 0.72rem;
      line-height: 0.72rem;
      margin-left:0.2rem;
    }
    .laws-info-item{
      display:inline-block;
    }
  }
  .label-input-form{
    margin-top: 10px;
  }
  .save-laws-btn{
    text-align: center;
  }
</style>
