<!-- 已收藏的标准 -->
<template>
 <div id="CollectionStandard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.standardName" placeholder="请输入动态信息" label="动态搜索"></label-input>
       <Button type="info" @click="standardSelect">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
   <div class="content">
     <div v-for="(item,index) in coollectionList" :key="index">
       <Card :bordered="false"  style="border-bottom: 1px solid #e8eaec;">
         <Row>
           <Col span="3"  offset="1">
             <Icon type="md-bookmark" />
             <span >{{item.number}}</span>
           </Col>
           <Col span="6">
             <strong>{{item.theme}}</strong>
           </Col>
           <Col span="4">{{item.time}}</Col>
           <Col span="4" offset="6">
             <Button type="dashed" >{{item.cancelBtn}}</Button>
             <Button type="dashed" @click="writeNotes">{{item.writeBtn}}</Button>
           </Col>
         </Row>
       </Card>
       <Input v-model="textarea" type="textarea" :autosize="{minRows: 5,maxRows: 7}"  placeholder="笔记内容..." style="width: 90%; margin: 0.5rem 1.5rem" v-show="inputType"></Input>
     </div>
   </div>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  data () {
    return {
      search: {
        standardName: ''
      },
      textarea: '',
      inputType: false,
      coollectionList: [{
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记'
      }, {
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记'
      }, {
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记'
      }]
    }
  },
  methods: {
    // 检索
    standardSelect () {},
    writeNotes () {
      if (this.inputType === false) {
        this.inputType = true
      } else {
        this.inputType = false
      }
    }
  }
}
</script>

<style lang="less">
   #CollectionStandard{
     .coStyle{
       width: 80%;
       margin: 0 auto;
     }
   }
</style>
