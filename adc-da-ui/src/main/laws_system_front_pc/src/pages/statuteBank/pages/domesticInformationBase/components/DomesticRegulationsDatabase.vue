<!-- 国内法规库 -->
<template>
 <div class="personal-data">
     <div class="laws-info-form">
       <Form ref="lawsInfo" :model="lawsInfo" :rules="lawsInfoRules" :label-width="80">
         <FormItem label="文件号" prop="fileNum" class="laws-info-item">
           <Input v-model="lawsInfo.fileNum"></Input>
         </FormItem>
         <Button type="primary" icon="ios-search" :loading="searching" @click="searchLawsInfo"></Button>
       </Form>
     </div>
    <div class="content">
      <loading :loading="loading">数据获取中</loading>
      <Table border ref="selection" :columns="tableColumn" :data="data"></Table>
    </div>
    <pagination :total="total" @pageChange="pageChange"></pagination>
   </div>
</template>

<script>
import Pagination from 'pages/components/Pagination'
export default {
  name: 'DomesticRegulationsDatabase',
  data () {
    return {
      lawsInfo: {
        fileNum: '' // 文件号
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
        }
      ],
      data: [],
      lawsInfoRules: {}
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
