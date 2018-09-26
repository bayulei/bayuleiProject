<!-- 个人板块管理 -->
<template>
  <div class="individual-plate">
    <div class="container">
      <Transfer
        :data="data"
        :target-keys="targetKeys"
        :list-style="listStyle"
        :titles="['可选模块','个人模块']"
        :render-format="renderPersonal "
        @on-change="handleChange"
        @on-selected-change="selectedChange"
        filterable>
      </Transfer>
      <div class="btn-group">
      <Button type="primary" @click="reloadMockData()">上移</Button>
      <Button type="primary" @click="reloadMockData" style="margin-left: 0.5rem;">下移</Button>
      </div>
      <Button type="primary" class="saveBtn" @click="savePersonal">保存修改</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'individual-plate',
  data () {
    return {
      curData: [],
      // data:数据源，其中的数据将会被渲染到左边一栏中，targetKeys 中指定的除外。
      data: [
        { 'key': '0', 'label': '个人信息', 'disabled': false },
        { 'key': '1', 'label': '个人板块', 'disabled': true },
        { 'key': '2', 'label': '我的动态', 'disabled': false },
        { 'key': '3', 'label': '我的收藏', 'disabled': false },
        { 'key': '4', 'label': '我的推送', 'disabled': false },
        { 'key': '5', 'label': '我的浏览', 'disabled': false }
      ],
      // 显示在右侧框数据的key集合
      targetKeys: [],
      // list-style两个穿梭框的自定义样式
      listStyle: {
        width: '6rem',
        height: '11rem'
      }
    }
  },
  methods: {
    handleChange (newTargetKeys, targetKeys, direction, moveKeys) {
      this.targetKeys = newTargetKeys
    },
    selectedChange (sourceSelectedKeys, targetSelectedKeys) {
      this.numData = targetSelectedKeys
    },
    renderPersonal (item) {
      return item.label
    },
    reloadMockData () {
      for (var i = 0; i < this.numData.length; i++) {
        // console.log(this.numData[])
      }
    },
    savePersonal () {
    }
  },
  watch: {
    targetKeys (val) {
      console.log($('.ivu-transfer').find('.ivu-transfer-list:nth-child(3)').find('.ivu-transfer-list-content'))
    }
  }
}
</script>

<style lang="less" scoped>
  .individual-plate{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .container{
      margin: 1rem 7rem;
      .btn-group{
        float: right;
        margin:1rem 5rem;
      }
      .btn-group Button{
         width: 2.5rem;
      }
      .saveBtn{
        margin: 0.1rem 4rem;
        width: 4rem;
      }
    }
  }
</style>
