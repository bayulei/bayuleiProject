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
        width: '5rem',
        height: '8rem'
      }
    }
  },
  methods: {
    handleChange (newTargetKeys, targetKeys, direction, moveKeys) {
      this.targetKeys = newTargetKeys
      console.log('targetKeys是：' + this.targetKeys)
    },
    selectedChange (sourceSelectedKeys, targetSelectedKeys) {
      console.log('sourceSelectedKeys是：' + sourceSelectedKeys)
      console.log('targetSelectedKeys是：' + targetSelectedKeys)
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
  }
}
</script>

<style lang="less">
  .individual-plate{
    background: #FFF;
    padding: 0.2rem 0.3rem;
    .container{
      margin: 0.4rem 3rem;
      }
  }
    .btn-group{
      width: 100%;
      /*float: right;*/
      /*position: absolute;*/
      margin:1rem 6.5rem;
      /*left: 15rem;*/
    }
    .btn-group Button{
      width: 2rem;
      }
    .saveBtn{
      margin: 0 4rem;
      width: 4rem;
    }

</style>
