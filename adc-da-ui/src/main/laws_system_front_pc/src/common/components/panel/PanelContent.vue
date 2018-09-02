<template>
  <div class="panel-content">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'panel-content'
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .panel-content{
    height: calc(~'100% - 0.9rem');
    overflow: hidden;
    position: relative;
    padding: 0.56rem 0.8rem;
    background: @subContainerContentColor;
  }
</style>
