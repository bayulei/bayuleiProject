<!-- 国内法规库 -->
<template>
 <div class="personal-data">
     <div class="laws-info-form">
       <Form ref="lawsInfo" :model="lawsInfo" :rules="lawsInfoRules" :label-width="80">
         <FormItem label="文件号" prop="fileNum" class="laws-info-item">
           <Input v-model="lawsInfo.fileNum"></Input>
         </FormItem>
         <Button type="primary" icon="ios-search" @click="searchLawsInfo"></Button>
         <Button type="primary" @click="modal1 = true">新增</Button>
       </Form>
     </div>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange"></pagination>

   <!--新增模态框-->
   <Modal v-model="modal1" title="新增法规信息" @on-ok="addLawsInfo" @on-cancel="cancelAdd">
     <Form ref="lawsInfoAdd" :model="lawsInfoAdd" :rules="lawsInfoAddRules" :label-width="80">
       <FormItem label="文件号" prop="lawsNum" class="laws-info-item">
         <Input v-model="lawsInfoAdd.lawsNum"></Input>
       </FormItem>
       <FormItem label="文件名称" prop="lawsName" class="laws-info-item">
         <Input v-model="lawsInfoAdd.lawsName"></Input>
       </FormItem>
     </Form>
   </Modal>
   </div>
</template>

<script>
import Pagination from 'pages/components/Pagination'
export default {
  name: 'DomesticRegulationsDatabase',
  data () {
    return {
      modal1: false,
      lawsInfo: {
        fileNum: '' // 文件号
      },
      lawsInfoAdd: {
        lawsNum: '', // 文件号
        lawsName: ''
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
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
                    this.show(params.index)
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
    searchLawsInfo () {
      this.$http.get('lawss/sarLawsInfo/page', {
        page: this.page,
        rows: this.rows
      }, {
        _this: this
      }, res => {
        this.data = res.data.list
      }, e => {

      })
    },
    pageChange (page) {
      this.page = page
    },
    addLawsInfo () {
      this.$http.post('lawss/sarLawsInfo/createLawsInfo', {
        lawsNumber: this.lawsInfoAdd.lawsNum,
        lawsName: this.lawsInfoAdd.lawsName
      }, {
        _this: this
      }, res => {
        alert(res)
      }, e => {

      })
    },
    cancelAdd () {
      alert(111)
    },
    show (index) {
      alert(111)
    },
    remove (id) {
      console.log(id)
      this.$http.post('lawss/sarLawsInfo/deleteLawsInfos', {
        id: id
      }, {
        _this: this
      }, res => {
        if (res.ok) {
          alert('删除成功')
          this.searchLawsInfo()
        }
      }, e => {

      })
    }
  },
  components: {
    Pagination
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
