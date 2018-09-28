<!-- 已收藏的法规 -->
<template>
 <div id="collection-laws">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.collectionLaws" placeholder="请输入动态信息" label="标准搜索"></label-input>
       <Button type="info" @click="selectLaws">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
     <div class="content">
       <div class="content-detail" v-if="total >0">
         <div v-for="(item,index) in lawsList" :key="index" >
           <div class="card domBtn">
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
                 <Button type="dashed"  @click="cancelCollection(item.id)">取消收藏</Button>
                 <Button type="dashed" @click="writeNotes(item)">书写笔记</Button>
               </Col>
             </Row>
           </div>
           <Button type="dashed" icon="md-add" v-show="item.check"  @click="createNote(item)">添加笔记</Button>
         </div>
       </div>
       <loading :loading="loading">数据获取中</loading>
     </div>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
 </div>
</template>

<script>
export default {
  name: 'collection-laws',
  data () {
    return {
      search: {
        collectionLaws: '',
        lawsList: []
      },
      total: 0,
      page: 1,
      rows: 10,
      loading: false
    }
  },
  methods: {
    // 分页
    pageChange (page) {
      this.page = page
      this.selectBrowsing()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectBrowsing()
    },
    // 搜索
    selectLaws () {
      this.$http.get('person/personCollect/page', {
        pageNo: this.page,
        pageSize: this.rows,
        collectTitle: this.search.collectType
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i].check = false
        }
        console.log(res)
        this.lawsList = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    //  取消收藏
    cancelCollection  (id) {
      console.log(id)
    },
    // 书写笔记
    writeNotes (item) {
      console.log(item.check)
      item.check = !item.check
      console.log(item.check)
    },
    // 添加笔记
    createNote (item) {
      console.log(item)
    }
  },
  mounted () {
    this.selectLaws()
  }
}
</script>
<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
   #collection-laws{}
  .content .content-detail {
    width: 100%;
    height: calc(100% - 8px);
    overflow-y: auto;
    padding: 0.1rem;
    background-color: white;
    .card{
      width: 100%;
      height: 1rem;
      border: 1px solid #EEE;
      margin-bottom: 10px;
      transition: all .2s ease-in-out;
      cursor: pointer;
      padding: 14px;
      background: #FFF;
      overflow: hidden;
      border-radius: 5px;
      .un-select();
      &:hover{
        box-shadow: 0px 1px 13px 0px #DDD;
      }
      &.selected{
        border: 1px solid @baseColor;
      }
      .card-btn{
        margin-right: 2px;
        &:last-child{
          margin-right: 0;
        }
      }
      .ivu-tag-dot{
        height: 24px;
        line-height: 24px;
        border: 1px solid #e8eaec !important;
        color: #515a6e !important;
        background: #fff !important;
        padding: 0 5px;
      }
    }
    /*.content .content-detail .top-add{*/
      /*height: 30px;*/
      /*line-height: 30px;*/
      /*width: 100%;*/
    /*}*/
  }
  .domBtn {border:1px gray solid;background-color:#FFE6B0}
</style>
