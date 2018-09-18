<!-- 我的动态 -->
<template>
  <div class="my-dynamics">
    <table-tools-bar>
      <div slot="left">
        <label-input v-model="search.dynamicsName" placeholder="请输入动态信息" label="动态搜索"></label-input>
        <Button type="info" @click="selectDynamics">查询</Button>
      </div>
      <div slot="right"></div>
    </table-tools-bar>
    <div class="content">
      <div v-for="(item,index) in dynamicsList" :key="index">
        <Card :bordered="false"  style="border-bottom: 1px solid #e8eaec;">
          <Row>
            <Col span="19"  offset="1">
              <Icon type="md-bookmark" />
              <span >{{item.msgTitle}}</span>
            </Col>
            <Col span="4">{{item.creationTime}}</Col>
          </Row>
        </Card>
      </div>
      <div v-if="total===0">暂无数据</div>
      <loading :loading="loading">数据获取中</loading>
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
      loading: false,
      total: 0,
      page: 1,
      rows: 10,
      dynamicsList: []
    }
  },
  methods: {
    // 查找
    selectDynamics () {
      this.$http.get('person/personMsg/page', {
        pageNo: this.page,
        pageSize: this.rows,
        msgTitle: this.search.dynamicsName
        // msgContent: this.search.dynamicsName
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.dynamicsList = res.data.list
        this.total = res.data.count
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
      padding: 1rem;
    }
  }
</style>
