<!-- 已收藏的标准 -->
<template>
 <div id="CollectionStandard">
   <table-tools-bar>
     <div slot="left">
       <label-input v-model="search.standardName" placeholder="请输入动态信息" label="标准搜索"></label-input>
       <Button type="info" @click="standardSelect">查询</Button>
     </div>
     <div slot="right"></div>
   </table-tools-bar>
   <div class="content">
     <Collapse v-model="value3" accordion simple>
       <Panel :name="item.id" v-for="(item,index) in collectionList" :key="index" hide-arrow :dispaly="isDis">
         <div>
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
             <Button type="dashed"  @click="cancelCollection" >{{item.cancelBtn}}</Button>
             <Button type="dashed" @click="writeNotes(item)">{{item.writeBtn}}</Button>
           </Col>
         </Row>
         </div>
         <div slot="content">
           <Input v-model="textarea" type="textarea" :autosize="{minRows: 5,maxRows: 7}"  placeholder="笔记内容..." style="width: 90%; margin: 0.5rem 1.5rem"></Input></div>
       </Panel>
     </Collapse>
   </div>
 </div>
</template>

<script>
export default {
  name: 'collectionStandard',
  isDis: false,
  data () {
    return {
      value3: '0',
      isDis: false,
      search: {
        standardName: ''
      },
      textarea: '',
      inputType: false,
      collectionList: [{
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记',
        id: '1'
      }, {
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记',
        id: '2'
      }, {
        number: 'GB-34660-2017',
        theme: '道路车辆 电磁兼容性要求和测验方法',
        time: '2018年/09/13  12：00',
        cancelBtn: '取消收藏',
        writeBtn: '书写笔记',
        id: '3'
      }]
    }
  },
  methods: {
    // 检索
    standardSelect () {},
    writeNotes (item) {
    }
  }
}
</script>

<style lang="less" scoped>
   #CollectionStandard{
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
