<!-- 我的推送 -->
<template>
  <div class="my-push">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="search.pushName" placeholder="请输入推送信息" label="推送搜索"></label-input>
        <Button type="info" @click="pushSelect">查询</Button>
      </div>
      <div slot="right"></div>
    </table-tools-bar>
    <div class="content">
      <div v-for="(item,index) in pushList" :key="index">
        <Card :bordered="false"  style="border-bottom: 1px solid #e8eaec;">
          <Row>
            <Col span="19"  offset="1">
              <Icon type="md-bookmark" />
              <span >{{item.text}}</span>
            </Col>
            <Col span="4">{{item.creationTime}}</Col>
          </Row>
        </Card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'my-push',
  data () {
    return {
      search: {
        pushName: ''
      },
      pushList: [{
        text: 'XX标准已更新',
        creationTime: '2018年/09/13  12：00'
      }, {
        text: 'XX标准已更新',
        creationTime: '2018年/09/13  12：00'
      }, {
        text: 'XX标准已更新',
        creationTime: '2018年/09/13  12：00'
      }]
    }
  },
  methods: {
    // 检索
    pushSelect () {}
  }
}
</script>

<style lang="less" scoped>
  .my-push{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
</style>
