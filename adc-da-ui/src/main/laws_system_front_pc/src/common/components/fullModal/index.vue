<!-- 全区域模态框(新增、编辑大量数据使用) -->
<template>
  <div id="fullModal">
    <div class="full-modal-content">
      <span class="modal-close-btn" @click="toggleClose">&times;</span>
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
    let _this = this
    this.$nextTick(() => {
      $('.full-modal-content').stop().animate({ left: '20%' }, 500)
      $('#fullModal').click(function () {
        _this.toggleClose()
      })
      $('.full-modal-content').click(function (e) {
        e.stopPropagation()
      })
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
    background: rgba(255,255,255,.85);
    .full-modal-content{
      width: 80%;
      height: 100%;
      background: rgba(255,255,255,.95);
      position: absolute;
      top: 0;
      left: 100%;
      border-left: 1px solid #DDD;
      padding: 0.5rem 0 0.5rem 0.5rem;
      z-index: 10000;
      box-shadow: -1px -1px 20px 5px #DDD;
      overflow-y: auto;
      .modal-close-btn{
        display: inline-block;
        width: 1rem;
        height: 1rem;
        display: flex;
        justify-content: center;
        align-items: center;
        position: absolute;
        top: 0;
        right: 0;
        font-size: 0.8rem;
        cursor: pointer;
        z-index: 101;
        color: rgba(0,0,0,.55);
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        &:hover{
          color: rgba(0,0,0,85);
        }
      }
      .ivu-form-item{
        display: flex;
        .ivu-form-item-content{
          flex: 1;
          padding-right: 1rem;
          .ivu-date-picker{
            width: 100%;
          }
        }
      }
    }
  }
</style>
