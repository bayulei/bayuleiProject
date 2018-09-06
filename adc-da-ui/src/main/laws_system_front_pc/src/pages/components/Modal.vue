<!-- 自定义弹窗 -->
<template>
   <Modal
     v-model="showModal"
     :title="title"
     @on-visible-change="visibleChange">
     <slot></slot>
   </Modal>
</template>

<script>
export default {
  name: 'modal',
  data () {
    return {
      showModal: false
    }
  },
  methods: {
    visibleChange (show) {
      if (show === false) {
        this.show = false
        this.$emit('cancel')
      }
    }
  },
  props: {
    show: {
      type: Boolean
    },
    title: {
      type: String,
      default: 'header'
    }
  },
  computed: {},
  watch: {
    show (val) {
      this.showModal = val
    }
  }
}
</script>

<style lang="less"></style>
