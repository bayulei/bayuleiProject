<!-- 我的浏览 -->
<template>
  <div class="my-browsing">
    <div class="content">
      <Card >
        <Row >
            <Checkbox :value="checkAll" size="large" @on-change="handleSelectAll" :indeterminate="indeterminate" style="padding: 6px ">全选</Checkbox>
          <Button type="primary" @click="browsingBathDel" style="float: right">清除所有浏览记录</Button>
        </Row>
      </Card>
      <div class="content-detail" v-if="total >0">
      <div class="card domBtn" style="width:100%" v-for="(item,index) in browseList" :key="index" :class="{ 'selected ': item.checked }" @click="handleCardClick(item,$event)" >
        <Row >
          <Col span="1" offset="1"><Checkbox v-model="item.checked" size="large"></Checkbox></Col>
          <Col span="2">{{item.creationTime}}</Col>
          <Col span="6" offset="3">{{item.cookieType}}</Col>
          <Col span="5">{{item.resTitle}}</Col>
          <Col span="6"><Button type="error" @click="browsingDel(item.id)" v-if=" item.checked">删除</Button></Col>
        </Row>
      </div>
      </div>
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
      this.selectBrowsing()
    },
    pageSizeChange (pageSize) {
      this.rows = pageSize
      this.selectBrowsing()
    },
    // 全选
    handleSelectAll (checked) {
      console.log(checked)
      // 全部选中
      if (checked) {
        // 1.遍历数据,把每一项的checkbox置为选中状态
        this.selectedList = []
        for (let i = 0; i < this.browseList.length; i++) {
          this.$set(this.browseList[i], 'checked', true)
          // 2.把每一项的id都放入selectedList数组中
          this.selectedList.push(this.browseList[i].id)
          // console.log(this.selectedList)
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
    // 复选框
    handleCardClick (item, event) {
      item.checked = !item.checked
      console.log(item.checked)
    },
    // 批量删除
    browsingBathDel () {
      let checked = true
      this.handleSelectAll(checked)
      let idList = this.selectedList
      this.$http.put('/person/personCookies/deleteBacth', {
        idList: idList
      }, {
        _this: this
      }, res => {
        this.selectBrowsing()
      }, e => {})
    },
    // 单项删除
    browsingDel (id) {
      this.$Modal.confirm({
        title: '请选择',
        content: '确定删除这一条信息？',
        onOk: () => {
          this.$http.put('/person/personCookies/deleteBySimple', {
            id: id
          }, {
            _this: this
          }, res => {
            this.selectBrowsing()
          }, e => {})
        }
      })
    },
    // 分页查询
    selectBrowsing () {
      this.$http.get('/person/personCookies/page', {
        userId: '8',
        pageNo: this.page,
        pageSize: this.rows
      }, {
        _this: this,
        loading: 'loading'
      }, res => {
        for (let i = 0; i < res.data.list.length; i++) {
          res.data.list[i].checked = false
        }
        this.browseList = res.data.list
        console.log(this.browseList)
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

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  .my-browsing{
  }
  .content .content-detail {
    width: 100%;
    height: calc(100% - 8px);
    overflow-y: auto;
    padding: 0.1rem;
    background-color: white;
    .card{
      width: 100%;
      height: 100px;
      border: 1px solid #EEE;
      margin-bottom: 10px;
      transition: all .2s ease-in-out;
      cursor: pointer;
      padding: 40px;
      background: #FFF;
      overflow: hidden;
      border-radius: 5px;
      .un-select();
      &:hover{
        box-shadow: 0px 1px 13px 0px #DDD;
      }
      &.selected{
        border: 1px solid @baseColor;
      }
      .card-btn{
        margin-right: 2px;
        &:last-child{
          margin-right: 0;
        }
      }
      .ivu-tag-dot{
        height: 24px;
        line-height: 24px;
        border: 1px solid #e8eaec !important;
        color: #515a6e !important;
        background: #fff !important;
        padding: 0 5px;
      }
    }
  }
  .domBtn {border:1px gray solid;background-color:#FFE6B0}
</style>
