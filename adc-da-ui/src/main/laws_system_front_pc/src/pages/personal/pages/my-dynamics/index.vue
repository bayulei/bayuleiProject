<!-- 我的动态 -->
<template>
  <div class="my-dynamics">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="search.dynamicsName" placeholder="请输入动态信息" label="动态搜索"></label-input>
        <Button type="info" @click="dynamicsSelect">查询</Button>
      </div>
      <div slot="right"></div>
    </table-tools-bar>
    <div class="content">
      <div v-for="(item,index) in dynamicsList" :key="index">
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
      <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'my-dynamics',
  data () {
    return {
      search: {
        dynamicsName: ''
      },
      total: 0,
      page: 1,
      rows: 10,
      dynamicsList: [{
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
    // 查找
    dynamicsSelect () {
    },
    selectDynamics () {
      this.$http.get('person/personMsg/page', {
        page: this.page,
        pageSize: this.rows,
        msgTitle: '',
        msgContent: ''
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        console.log(res)
      }, e => {})
    },
    pageChange (page) {
      this.page = page
      this.selectCategory()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectCategory()
    }
  },
  mounted () {
    this.selectDynamics()
  }
}
</script>

<style lang="less" scoped>
  .my-dynamics{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .content .ivu-card-body{
      padding: 12px;
    }
  }
</style>
