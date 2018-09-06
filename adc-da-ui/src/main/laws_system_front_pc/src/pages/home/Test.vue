<template>
  <div class="test">
    <label-input v-model="text" class="lable-input" label="用户名"></label-input>
    <span>{{ text }}</span>
  </div>
</template>
<script>
export default {
  data () {
    return {
      text: ''
    }
  },
  methods: {},
  mounted () {}
}
</script>
<style lang="less">
  .test{
    padding: 15px;
  }
</style>
