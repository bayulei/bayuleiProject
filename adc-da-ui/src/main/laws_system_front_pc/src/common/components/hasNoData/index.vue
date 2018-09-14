<!-- 暂无数据 -->
<template>
  <div class="hasNoData iconfont" :class="pClass">
    <div class="no-data-icon">
      &#xe6ef;
      <span>{{ tips }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {}
  },
  methods: {},
  components: {},
  props: {
    pClass: String,
    tips: {
      type: String,
      default: '暂无数据'
    }
  },
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  .hasNoData {
    display: flex;
    display: -ms-flex;
    justify-content: center;
    align-items: center;
    background: rgba(100, 100, 100, .05);
    .no-data-icon {
      width: 80px;
      height: 80px;
      display: flex;
      display: -ms-flex;
      justify-content: center;
      align-items: center;
      border-radius: 50%;
      font-size: 50px;
      flex-flow: column;
      color: #AAA;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
      span {
        font-size: 14px;
        font-weight: normal;
        margin-top: -10px;
        color: #AAA;
      }
    }
  }
</style>
