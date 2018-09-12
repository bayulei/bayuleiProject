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
  </div>
</template>

<script>
export default {
  name: 'my-dynamics',
  data () {
    return {
      search: {
        dynamicsName: ''
      }
    }
  },
  methods: {
    // 查找
    dynamicsSelect () {
    }
  }
}
</script>

<style lang="less" scoped>
  .my-dynamics{
    background: #FFF;
    padding: 0.2rem 0.3rem;
  }
</style>
