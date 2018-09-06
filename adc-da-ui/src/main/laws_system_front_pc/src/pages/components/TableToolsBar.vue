<!-- 表格工具栏 -->
<template>
  <div class="table-tools-bar">
 <transition enter-active-class="animated slideInDown" leave-active-class="animated slideOutUp" :duration="1000">
    <div class="advanced-search" v-if="isAdvancedSearch">
      高级搜索
      <Button type="primary" class="toggle-btn" @click="toggleSearch">收起</Button>
    </div>
    </transition>
    <div class="tools-bar-wrapper">
      <slot name="left"></slot>
      <slot name="right"></slot>
    </div>
    <Divider />
  </div>
</template>

<script>
export default {
  name: 'table-tools-bar',
  data () {
    return {
      //
    }
  },
  methods: {
  // 高级搜索关闭
  toggleSearch () {
    this.$emit('toggleSearch')
  }
},
props: {
  // 是否为高级搜索
  isAdvancedSearch: {
    type: Boolean,
    default: false
  }
}
}
</script>

<style lang="less">
  @import 'animate.css';
  .table-tools-bar{
    padding: 0.1rem 0;
    .advanced-search{
      width: 100%;
      height: 200px;
      position: absolute;
      top: 0;
      left: 0;
      background: rgba(255,255,255,.95);
      border-bottom: 1px solid #DDD;
      z-index: 100;
      padding: 0.2rem;
      .toggle-btn{
        position: absolute;
        bottom: 0.2rem;
        right: 0.2rem;
      }
    }
    .tools-bar-wrapper{
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 0.1rem;
      .ivu-input-wrapper{
        width: auto;
        min-width: 150px;
        margin-right: 0.1rem;
      }
    }
    .ivu-form-item{
      margin-bottom: 0;
    }
  }
</style>
