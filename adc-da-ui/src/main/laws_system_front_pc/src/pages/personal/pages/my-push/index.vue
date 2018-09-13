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
  </div>
</template>

<script>
export default {
  name: 'my-push',
  data () {
    return {
      search: {
        pushName: ''
      }
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
