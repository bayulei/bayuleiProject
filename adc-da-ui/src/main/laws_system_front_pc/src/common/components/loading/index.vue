<!-- 局部loading -->
<template>
  <Spin fix v-if="loading">
    <Icon custom="i-icon icon-icon-loading" size=18 class="spin-loading"></Icon>
    <div class="loading-tips">
      <slot></slot>
    </div>
  </Spin>
</template>

<script>
export default {
  name: 'local-loading',
  props: {
    loading: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style lang="less"></style>
