<!-- 我的浏览 -->
<template>
  <div class="my-browsing">
       <table-tools-bar>
         <div slot="left"></div>
         <div slot="right">
           <Button type="primary" @click="browsingBathDel">清除所有浏览记录</Button>
         </div>
       </table-tools-bar>
    <div class="content">
      <Card>
        <Row >
          <Col span="1" offset="1"><Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate">全选</Checkbox></Col>
        </Row>
      </Card>
      <Card style="width:100%" v-for="(item,index) in browseList" :key="index" :class="{ 'selected': item.checked }" @click="handleCardClick(item,$event)" >
        <Row >
          <Col span="1" offset="1"><Checkbox v-model="item.checked" size="large"></Checkbox></Col>
          <Col span="2">{{item.creationTime}}</Col>
          <Col span="6" offset="3">{{item.cookieType}}</Col>
          <Col span="5">{{item.resTitle}}</Col>
          <Col span="6"><Button type="error" @click="browsingDel(item.id)">删除</Button></Col>
        </Row>
      </Card>
      <has-no-data pClass="content-detail" v-if="total === 0"></has-no-data>
      <loading :loading="loading">数据获取中</loading>
    </div>
    <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
  </div>
</template>

<script>
export default {
  name: 'my-browsing',
  data () {
    return {
      checkAll: false, // 是否全选
      indeterminate: false, // 是否半选
      total: 0,
      page: 1,
      rows: 10,
      loading: false,
      browseList: []
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
    handleSelectAll (checked) {
      // 全部选中
      if (checked) {
        // 1.遍历数据,把每一项的checkbox置为选中状态
        this.selectedList = []
        for (let i = 0; i < this.browseList.length; i++) {
          this.$set(this.browseList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.browseList[i].id)
          console.log(this.selectedList)
        }
        // 全部取消选中
      } else {
        // 1.遍历数据,把每一项的checkbox置为取消选中状态
        for (let i = 0; i < this.browseList.length; i++) {
          this.$set(this.browseList[i], 'checked', false)
          // 2.清空selectedList数组
          this.selectedList = []
        }
      }
    },
    handleCardClick (item) {
      item.checked = !item.checked
    },
    browsingBathDel () {
      this.$http.putData('/person/personCookies', this.selectedList, {
        _this: this
      }, res => {
        this.selectBrowsing()
      }, e => {})
    },
    selectBrowsing () {
      // let browsingPage = {
      //   userId: '1',
      //   pageNo: this.page,
      //   pageSize: this.rows
      // }
      this.$http.get('/person/personCookies/page', {
        userId: '8',
        pageNo: this.page,
        pageSize: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        this.browseList = res.data.list
        this.total = res.data.count
      }, e => {})
    }
  },
  watch: {
    browseList: {
      deep: true,
      handler (newVal, oldVal) {
        this.selectedList = []
        for (let i = 0; i < newVal.length; i++) {
          if (newVal[i].checked) {
            this.selectedList.push(newVal[i].id)
          }
        }
        if (this.selectedList.length === newVal.length && newVal.length !== 0) {
          this.checkAll = true
          this.indeterminate = false
        } else if (this.selectedList.length === 0) {
          this.checkAll = false
        } else {
          this.checkAll = false
          this.indeterminate = false
        }
      }
    },
    // 已选择的列表
    selectedList (newVal, oldVal) {
      if (oldVal.length === this.browseList.length && newVal.length !== 0) {
        this.checkAll = false
        this.indeterminate = true
      } else if (newVal.length === 0) {
        this.checkAll = false
        this.indeterminate = false
      }
    }
  },
  mounted () {
    this.selectBrowsing()
  }
}
</script>

<style lang="less" scoped>
  .my-browsing{
  }
  .content .content-detail {
    width: 100%;
    height: calc(100% - 8px);
    overflow-y: auto;
    padding: 0.1rem;
    background-color: white;
  }
</style>
