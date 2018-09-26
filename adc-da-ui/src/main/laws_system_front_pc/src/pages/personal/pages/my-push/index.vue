<!-- 我的推送 -->
<template>
  <div class="my-push">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="search.resTitle" placeholder="请输入推送信息" label="推送搜索"></label-input>
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
              <span >{{item.resId}} | {{item.resTitle}}</span>
            </Col>
            <Col span="4">{{item.creationTime}}</Col>
          </Row>
        </Card>
      </div>
      <has-no-data pClass="content-detail" v-if="total === 0"></has-no-data>
      <loading :loading="loading">数据获取中</loading>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
  </div>
</template>

<script>
export default {
  name: 'my-push',
  data () {
    return {
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      search: {
        resTitle: ''
      },
      pushList: []
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
    // 检索
    pushSelect () {
      this.$http.get('person/personShare/page', {
        pageNo: this.page,
        pageSize: this.rows,
        resTitle: this.search.resTitle
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.pushList = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  mounted () {
    this.pushSelect()
  }
}
</script>

<style lang="less" scoped>
  .my-push{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .content .content-detail {
      width: 100%;
      height: calc(100% + 2px);
      overflow-y: auto;
      padding: 0.1rem;
      background-color: white;
    }
  }
</style>
