<!-- 全区域模态框(新增、编辑大量数据使用) -->
<template>
  <div id="fullModal">
    <div class="full-modal-content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'full-modal',
  data () {
    return {}
  },
  methods: {
    toggleClose () {
      let _this = this
      $('.full-modal-content').stop().animate({ left: '100%' }, 500, function () {
        _this.$emit('toggleClose', false)
      })
    }
  },
  components: {},
  props: {
    fullModalShow: Boolean
  },
  model: {
    prop: 'fullModalShow',
    event: 'toggleClose'
  },
  computed: {},
  watch: {},
  mounted () {
    this.$nextTick(() => {
      $('.full-modal-content').stop().animate({ left: '20%' }, 500)
    })
  }
}
</script>

<style lang="less">
  #fullModal{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 9999;
    background: rgba(0,0,0,.05);
    .full-modal-content{
      width: 80%;
      height: 100%;
      background: rgba(255,255,255,.85);
      position: absolute;
      top: 0;
      left: 100%;
      border-left: 1px solid #DDD;
      padding: 0.2rem;
      z-index: 10000;
      box-shadow: -1px -1px 20px 5px #DDD;
    }
  }
</style>
