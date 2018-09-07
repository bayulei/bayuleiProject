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
        <Button type="primary" @click="modalAdd = true">新增</Button>
        <Button type="primary" @click="modal2 = true">导入</Button>
      </div>
    </table-tools-bar>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange"></pagination>

   <!--新增修改模态框-->
   <Modal v-model="modalAdd" title="新增法规信息" @on-ok="saveLawsInfo" @on-cancel="cancelAdd">
     <Form ref="lawsInfoAdd" :model="lawsInfoAdd" :rules="lawsInfoAddRules" :label-width="80">
       <input v-model="lawsInfoAdd.editLawsId" v-show="false">
       <FormItem label="文件号" prop="lawsNum" class="laws-info-item">
         <Input v-model="lawsInfoAdd.lawsNum"></Input>
       </FormItem>
       <FormItem label="文件名称" prop="lawsName" class="laws-info-item">
         <Input v-model="lawsInfoAdd.lawsName"></Input>
       </FormItem>
     </Form>
   </Modal>
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
      modalAdd: false,
      lawsInfo: {
        fileNum: '' // 文件号
      },
      lawsInfoAdd: {
        lawsNum: '',
        lawsName: '',
        editLawsId: ''
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
          title: '文件名称',
          key: 'lawsName'
        },
        {
          title: '发布单位',
          key: 'issueUnit'
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
      lawsInfoRules: {},
      lawsInfoAddRules: {}
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
    // 点击编辑按钮触发
    edit (row) {
      this.modalAdd = true
      this.lawsInfoAdd.editLawsId = row.id
      this.lawsInfoAdd.lawsNum = row.lawsNumber
      this.lawsInfoAdd.lawsName = row.lawsName
    },
    // 提交新增/修改
    saveLawsInfo () {
      if (this.lawsInfoAdd.editLawsId == null || this.lawsInfoAdd.editLawsId === '') {
        this.$http.post('lawss/sarLawsInfo/createLawsInfo', {
          lawsNumber: this.lawsInfoAdd.lawsNum,
          lawsName: this.lawsInfoAdd.lawsName
        }, {
          _this: this
        }, res => {
          alert('新增成功')
          this.searchLawsInfo()
        }, e => {

        })
      } else {
        this.$http.put('lawss/sarLawsInfo/updateLawsInfo', {
          id: this.lawsInfoAdd.editLawsId,
          lawsNumber: this.lawsInfoAdd.lawsNum,
          lawsName: this.lawsInfoAdd.lawsName
        }, {
          _this: this
        }, res => {
          alert('修改成功')
          this.searchLawsInfo()
        }, e => {

        })
      }
    },
    cancelAdd () {
    },
    // 删除
    remove (id) {
      this.$http.put('lawss/sarLawsInfo/deleteLawsInfos', {
        id: id
      }, {
        _this: this
      }, res => {
        alert('删除成功')
        this.searchLawsInfo()
      }, e => {

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
        alert('导入成功')
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
</style>
