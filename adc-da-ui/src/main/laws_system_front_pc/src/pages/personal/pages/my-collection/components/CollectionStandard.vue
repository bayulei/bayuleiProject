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
         <div slot="content">
           <Form ref="formDynamic" :model="formDynamic" :label-width="200">
             <FormItem>
               <Button type="dashed" class="btn" @click="handleAdd(num)" icon="md-add">添加笔记</Button>
             </FormItem>
             <FormItem
               v-for="(num, index) in formDynamic.items"
               :key="index"
               :label="'笔记 ' + num.index + ':'"
               :prop="'items.' + index + '.value'">
               <Row>
                 <Col span="12">
                   <span>{{num.value}}</span>
                 </Col>
                 <Col span="6" offset="1" align="right">
                   <Button type="info" size="small" @click="handleRender(num)">书写笔记</Button>
                   <Button type="info"  size="small" @click="handleSubmit('formDynamic')">保存笔记</Button>
                   <Button type="info"  size="small" @click="handleReset('formDynamic')" >删除笔记</Button>
                 </Col>
               </Row>
             </FormItem>
           </Form>
         </div>
       </Panel>
     </Collapse>
     <div v-if="total===0">暂无数据</div>
     <loading :loading="loading">数据获取中</loading>
     <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
   </div>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  data () {
    return {
      collapseValue: '0',
      // 字数统计
      remnant: 200,
      isDis: false,
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      search: {
        collectType: '' // 输入框内容
      },
      index: 1,
      formDynamic: {
        items: [
          {
            input: '',
            value: '',
            index: 1
          }
        ]
      },
      collectionList: [] // 内容
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
      this.$http.get('person/personCollect/page', {
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
      this.$http.get('', {
      }, {
        _this: this
      }, res => {
        this.standardSelect()
      }, e => {})
    },
    writeNotes (item) {
    },
    handleSubmit (name) {
      alert('已提交')
    },
    handleReset (name) {
      alert('已取消')
    },
    handleAdd (num) {
      console.log(num)
      this.index++
      this.formDynamic.items.push({
        value: '',
        index: this.index
      })
    },
    handleRender (num) {
      this.$Modal.confirm({
        render: (h) => {
          return h('Input', {
            props: {
              type: 'textarea',
              maxlength: 200,
              value: num.value,
              placeholder: '请书写笔记……'
            },
            on: {
              input: (val) => {
                num.input = val
              }
            }
          })
        },
        onOk: () => {
          num.value = num.input
        },
        onCancel: () => {
          num.input = ''
        }
      })
    }
  },
  mounted () {
    this.standardSelect()
  }
}
</script>

<style lang="less">
   #CollectionStandard{
   }
   .btn-group{
     position: relative;
     top:-1.3rem;
     left: 23rem;
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
