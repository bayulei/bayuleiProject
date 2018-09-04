<template>
  <div class="panel-header">
    <ul class="tabs">
      <li v-for="(item, index) in tabs"
          :key="index"
          :class="{ 'active border-right': active === item.name }"
          @click="activated(item.name)">{{ item.title }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'panel-header',
  methods: {
    // 组件切换
    activated (comp) {
      this.$emit('activated', comp)
    }
  },
  props: {
    tabs: {
      type: Array,
      required: true
    },
    active: String
  }
}
</script>

<style lang="less" scoped>
  .panel-header{
    height: 0.9rem;
    padding: 0.1rem 0 0 0.8rem;
    box-sizing: border-box;
    .tabs{
      li{
        float: left;
        height: 0.8rem;
        line-height: 0.8rem;
        text-align: center;
        padding: 0 0.9rem;
        cursor: pointer;
        user-select: none;
        &.active{
          background: #FFF;
          border-top: 1px solid #CCCCCC;
          border-left: 1px solid #CFCFCF;
          box-sizing: border-box;
        }
        &::before{
          height: calc(~'100% + 1px');
          top: -1px;
        }
      }
    }
  }
</style>
