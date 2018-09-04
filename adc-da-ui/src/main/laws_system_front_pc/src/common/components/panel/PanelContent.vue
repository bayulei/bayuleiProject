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

<style lang="less">
  @import '~styles/style';
  .panel-content{
    width: 100%;
    height: calc(~'100% - 0.9rem');
    position: relative;
    background: @subContainerContentColor;
    overflow: hidden;
  }
</style>
