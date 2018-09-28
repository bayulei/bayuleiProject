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
       <div class="content-detail">
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
                 <span>{{ item.check }}</span>
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
   <Modal v-model="lawsModal" width="450">
     <p slot="header">
       <span>书写笔记</span>
     </p>
     <div style="text-align:center">
       <Input v-model="lawsForm.textarea" type="textarea" :maxlength="200" :autosize="{minRows: 5,maxRows: 7}" placeholder="Enter something..." @input="descInput"></Input>
       <P style="float: right; ">您还可以输入{{remnant}}/200</P>
     </div>
     <div slot="footer">
       <Button v-if="flag" type="primary" size="large" @click="lawsAdd">添加笔记</Button>
       <Button v-else type="primary" size="large" @click="saveLawsNotes">提交笔记</Button>
     </div>
   </Modal>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
 </div>
</template>

<script>
export default {
  name: 'collection-laws',
  data () {
    return {
      search: {
        collectionLaws: ''
      },
      lawsForm: {
        textarea: ''
      },
      lawsList: [],
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      lawsModal: false
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
    // 计算数字
    descInput () {
      let txtVal = this.lawsForm.textarea.length
      this.remnant = 200 - txtVal
    },
    // 添加笔记
    lawsAdd () {},
    // 提交笔记
    saveLawsNotes () {
    },
    // 搜索
    selectLaws () {
      this.$http.get('person/personCollect/page', {
        pageNo: this.page,
        pageSize: this.rows,
        id: 'WQJ8JT5XTC5MNK3WTKNN',
        collectTitle: this.search.collectType
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i].check = false
        }
        this.lawsList = res.data.list
        this.total = res.data.count
      }, e => {})
    },
    //  取消收藏
    cancelCollection  (id) {
      this.$http.put('person/personCollect/updateByUserId ', {
        id: id
      }, {
        _this: this
      })
    },
    // 书写笔记
    writeNotes (item) {
      item.check = !item.check
    },
    // 添加笔记
    createNote (item) {
      console.log(item)
    }
  },
  watch: {
    lawsList: {
      deep: true,
      immediate: true,
      handler (val) {
        console.log('watch')
      }
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
    }
    /*.content .content-detail .top-add{*/
      /*height: 30px;*/
      /*line-height: 30px;*/
      /*width: 100%;*/
    /*}*/
  }
  .domBtn {border:1px gray solid;background-color:#FFE6B0}
</style>
