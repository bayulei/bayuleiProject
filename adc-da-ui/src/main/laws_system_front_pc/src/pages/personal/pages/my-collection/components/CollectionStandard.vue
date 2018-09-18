<!-- 已收藏的标准 -->
<template>
 <div id="CollectionStandard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.collectType" placeholder="请输入动态信息" label="标准搜索"></label-input>
       <Button type="info" @click="standardSelect">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
   <div class="content">
     <Collapse v-model="collapseValue" accordion simple>
       <Panel :name="item.id" v-for="(item,index) in collectionList" :key="index" hide-arrow :dispaly="isDis">
         <div>
         <Row>
           <Col span="3"  offset="1">
             <Icon type="md-bookmark" />
             <span >{{item.collectResId}}</span>
           </Col>
           <Col span="6">
             <strong>{{item.collectTitle}}</strong>
           </Col>
           <Col span="4">{{item.creationTime}}</Col>
           <Col span="4" offset="6">
             <Button type="dashed"  @click="cancelCollection">取消收藏</Button>
             <Button type="dashed" @click="writeNotes(item)">书写笔记</Button>
           </Col>
         </Row>
         </div>
         <div slot="content">
           <Input v-model="textarea" type="textarea" :autosize="{minRows: 5,maxRows: 7}"  placeholder="笔记内容..." :maxlength="200"  @input="descInput" style="width: 90%; margin: 0.5rem 1.5rem"></Input>
           <div class="btn-group">
             <span>{{remnant}}/200</span>
             <Button type="info" ghost size="small" style="margin-left: 0.5rem">保存</Button>
             <Button type="info" ghost size="small" style="margin-left: 0.5rem">删除</Button>
           </div>
         </div>
       </Panel>
     </Collapse>
     <loading :loading="loading">数据获取中</loading>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  isDis: false,
  data () {
    return {
      collapseValue: '0',
      remnant: 200,
      isDis: false,
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      search: {
        collectType: ''
      },
      textarea: '',
      inputType: false,
      collectionList: []
    }
  },
  methods: {
    // 分页
    pageChange (page) {
      this.page = page
      this.selectClass()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectClass()
    },
    // 计算数字
    descInput () {
      let txtVal = this.textarea.length
      this.remnant = 200 - txtVal
    },
    // 检索
    standardSelect () {
      this.$http.get('/person/personCollect/page', {
        pageNo: this.page,
        pageSize: this.rows,
        collectTitle: this.search.collectType
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.collectionList = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    cancelCollection () {

    },
    writeNotes (item) {
    }
  },
  mounted () {
    this.standardSelect()
  }
}
</script>

<style lang="less" scoped>
   #CollectionStandard{
     .btn-group{
       position: relative;
       top:-1.3rem;
       left: 23rem;
     }
   }
   .ivu-collapse > .ivu-collapse-item > .ivu-collapse-header{
     height: 93px;
     line-height: 87px;
     padding-left: 16px;
     color: #666;
     cursor: pointer;
     position: relative;
     border-bottom: 1px solid transparent;
     -webkit-transition: all 0.2s ease-in-out;
     transition: all 0.2s ease-in-out;
   }

</style>
