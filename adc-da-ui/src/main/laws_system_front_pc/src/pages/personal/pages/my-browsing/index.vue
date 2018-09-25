<!-- 我的浏览 -->
<template>
  <div class="my-browsing">
       <table-tools-bar>
         <div slot="left"></div>
         <div slot="right">
           <Button>清除所有浏览记录</Button>
         </div>
       </table-tools-bar>
    <Card style="width:100%" v-for="(item,index) in browseList" :key="index" :class="{ 'selected': item.checked }" @click="handleCardClick(item)" >
      <Row >
        <Col span="1" offset="1"><Checkbox v-model="item.checked" size="large"></Checkbox></Col>
        <Col span="2">{{item.openTime}}</Col>
        <Col span="6" offset="3">{{item.num}}</Col>
        <Col span="5">{{item.content}}</Col>
        <Col span="6"></Col>
      </Row>
    </Card>
  </div>
</template>

<script>
export default {
  name: 'my-browsing',
  data () {
    return {
      browseList: [
        {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }, {
          openTime: '上午10：27',
          num: ' CB-34662-2017',
          content: '法规智库管理系统'
        }
      ]
    }
  },
  methods: {
    handleCardClick (item) {
      item.checked = !item.checked
    }
  }
}
</script>

<style lang="less" scoped>
  .my-browsing{
    ul{
      margin: 0.5rem;
    }
    li{
      margin: 0.3rem;
    }
  }
</style>
