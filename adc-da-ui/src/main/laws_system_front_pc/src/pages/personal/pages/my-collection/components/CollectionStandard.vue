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
     <Collapse v-model="collapseValue" accordion>
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
         <div slot="content" class="note-list">
           <div class="top-add">
             <Button type="dashed" class="btn" @click="handleRender(0)" icon="md-add">添加笔记</Button>
           </div>
           <div class="note-content" v-for="(note, index) in item.formDynamic" :key="index">
             <div class="note-left">{{note}}</div>
             <div class="note-right">
               <Button type="dashed">查看</Button>
               <Button type="dashed">删除</Button>
             </div>
           </div>
           <!--<Form ref="item.formDynamic" :model="item.formDynamic" :label-width="200">-->
             <!--<FormItem>-->
               <!--<Button type="dashed" class="btn" @click="handleRender(0)" icon="md-add">添加笔记</Button>-->
             <!--</FormItem>-->
             <!--<FormItem v-for="(note, index) in item.formDynamic" :key="index" :label="'笔记 ' + note.index + ':'">-->
               <!--<Row>-->
                 <!--<Col span="12">-->
                   <!--<span>{{note.value}}</span>-->
                 <!--</Col>-->
                 <!--<Col span="6" offset="1" align="right">-->
                   <!--<Button type="info" size="small" @click="handleRender(1)">书写笔记</Button>-->
                   <!--<Button type="info"  size="small" @click="handleSubmit">修改笔记</Button>-->
                   <!--<Button type="info"  size="small" @click="handleReset" >删除笔记</Button>-->
                 <!--</Col>-->
               <!--</Row>-->
             <!--</FormItem>-->
           <!--</Form>-->
           <Modal v-model="standardModal" width="450">
             <p slot="header">
               <span>书写笔记</span>
             </p>
             <div style="text-align:center">
               <Input v-model="formValidate.desc" type="textarea" :maxlength="200" :autosize="{minRows: 5,maxRows: 7}" placeholder="Enter something..." @input="descInput"></Input>
               <P style="float: right; ">您还可以输入{{remnant}}/200</P>
             </div>
             <div slot="footer">
               <Button v-if="flag" type="primary" size="large" @click="comAdd()">添加笔记</Button>
               <Button v-else type="primary" size="large" @click="saveWriteNotes">提交笔记</Button>
             </div>
           </Modal>
         </div>
       </Panel>
     </Collapse>
     <has-no-data pClass="content-detail" v-if="total === 0"></has-no-data>
     <loading :loading="loading">数据获取中</loading>
   </div>
   <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  data () {
    return {
      flag: true,
      // 模态框是否显示
      standardModal: false,
      collapseValue: '0',
      // 字数统计
      remnant: 200,
      isDis: false,
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      search: {
        // 输入框内容
        collectType: ''
      },
      number: 2,
      // 笔记内容
      formValidate: {
        desc: ''
      },
      // 内容
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
      let txtVal = this.formValidate.desc.length
      this.remnant = 200 - txtVal
    },
    // 检索
    standardSelect () {
      this.$http.get('person/personCollect/page', {
        pageNo: this.page,
        pageSize: this.rows,
        collectTitle: this.search.collectType
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        console.log(res)
        // var listItem = res.data.list
        this.collectionList = res.data.list
        // console.log(res.data)
        // for (let i = 0; i < this.collectionList.length; i++) {
        //   this.collectionList[i].formDynamic = {
        //     input: '',
        //     value: '',
        //     index: i + 1
        //   }
        // }
        console.log(this.collectionList)
        this.total = res.data.count
      }, e => {})
    },
    // 取消收藏
    cancelCollection () {
      this.$http.get('', {
      }, {
        _this: this
      }, res => {
        this.standardSelect()
      }, e => {})
    },
    // 书写笔记
    writeNotes (item) {
    },
    // 保存笔记
    handleSubmit (name) {
      alert('已提交')
    },
    // 删除笔记
    handleReset (name) {
      alert('已取消')
    },
    // 增加笔记
    handleAdd (index) {
      this.collectionList.push({
        value: '',
        index: this.number++
      })
      // this.number++
      // this.formDynamic.items.push({
      //   value: '',
      //   index: this.number
      // })
    },
    // 书写笔记
    handleRender (flag) {
      if (flag === 0) {
        this.flag = true
      } else {
        this.flag = false
      }
      this.standardModal = true
      // this.$Modal.confirm({
      //   render: (h) => {
      //     return h('Input', {
      //       props: {
      //         type: 'textarea',
      //         maxlength: 200,
      //         value: num.value,
      //         placeholder: '请书写笔记……'
      //       },
      //       on: {
      //         input: (val) => {
      //           num.input = val
      //         }
      //       }
      //     })
      //   },
      //   onOk: () => {
      //     num.value = num.input
      //   },
      //   onCancel: () => {
      //     num.input = ''
      //   }
      // })
      // this.standardModal = true
    },
    // 提交笔记
    saveWriteNotes () {
      this.standardModal = false
    },
    // 添加笔记
    comAdd () {
      this.standardModal = false
    }
  },
  mounted () {
    this.standardSelect()
  }
}
</script>

<style lang="less">
   #CollectionStandard{
     .content .content-detail {
       width: 100%;
       height: calc(100% + 2px);
       overflow-y: auto;
       padding: 0.1rem;
       background-color: white;
       .note-list{
         .top-add{
           height: 30px;
           line-height: 30px;
           width: 100%;
         }
         .note-content{
           height: 30px;
           clear: both;
           .note-left{
             height: 30px;
             width: 80%;
             float: left;
           }
           .note-right {
             height: 30px;
             width: 20%;
             float: right;
           }
         }
       }
     }
   }
   .ivu-collapse > .ivu-collapse-item > .ivu-collapse-header{
     height: 38px;
     line-height: 36px;
     padding-left: 16px;
     color: #666;
     cursor: pointer;
     position: relative;
     border-bottom: 1px solid transparent;
     -webkit-transition: all 0.2s ease-in-out;
     transition: all 0.2s ease-in-out;
   }
   .ivu-collapse {
     background-color: #FFFFFF;
     border-radius: 3px;
     border: none;
   }
   .ivu-form-item {
     margin-bottom: 0.3rem;
     vertical-align: top;
     zoom: 1;
   }
   .ivu-form-item-content {
     position: relative;
     line-height: 32px;
     font-size: 12px;
     margin-left: 0 !important;
   }
</style>
