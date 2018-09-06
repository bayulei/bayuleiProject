<!-- 带label的输入框 -->
<!--
  可接收一个width用于控制输入框长度
  可接收一个placeholder用户显示待输入提示
  可接收一个clearable用户是否包含清除按钮
-->
<template>
  <div id="labelInput">
    <label>{{ label }}</label>
    <div class="label-input-content" :style="{ width: width + 'px' }">
      <Input :value="value" @on-change="handleChange"  :placeholder="placeholder" :clearable="clearable"></Input>
    </div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      currentValue: this.value
    }
  },
  methods: {
    handleChange (event) {
      this.$emit('input', event.target.value)
    }
  },
  components: {},
  props: {
    value: String,
    label: String,
    width: {
      type: Number,
      default: 200
    },
    placeholder: String,
    clearable: {
      type: Boolean,
      default: true
    }
  },
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #labelInput{
    vertical-align: top;
    min-width: 250px;
    label{
      display: inline-block;
      border: 1px solid #DDD;
      border-top-left-radius: 4px;
      border-bottom-left-radius: 4px;
      border-right: none;
      height: 32px;
      line-height: 32px;
      background: #F4F8FB;
      min-width: 80px;
      text-align: center;
      vertical-align: middle;
      float: left;
      font-size: 12px;
      color: #515a6e;
      padding: 0;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
    }
    .label-input-content{
      display: inline-block;
      .ivu-input-wrapper{
        display: inline-block;
        width: 100%;
        position: relative;
        vertical-align: middle;
        line-height: normal;
        .ivu-input{
          border-top-left-radius: 0;
          border-bottom-left-radius: 0;
        }
      }
    }
  }
</style>
