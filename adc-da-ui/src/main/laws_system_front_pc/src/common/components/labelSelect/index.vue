<!-- 带label的下拉框 -->
<!--
  可接收一个width用于控制输入框长度
  可接收一个placeholder用户显示待输入提示
  可接收一个clearable用户是否包含清除按钮
  可接收一个options数据显示选项(必传,值为{ label: '', value: '' })
-->
<template>
  <div id="labelSelect">
    <label>{{ label }}</label>
    <div class="label-select-content" :style="{ width: width + 'px' }">
      <Select :value="value" @on-change="handleChange" :placeholder="placeholder" :clearable="clearable" v-if="type === 'select'">
        <Option v-for="opt in options" :value="opt.value" :key="opt.value">{{ opt.label }}</Option>
      </Select>
      <DatePicker type="date" :value="value" :placeholder="placeholder" v-else-if="type === 'datePicker'" @on-change="handleChange"></DatePicker>
    </div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {}
  },
  methods: {
    handleChange (event) {
      this.$emit('input', event)
    }
  },
  components: {},
  props: {
    value: String,
    label: String,
    options: {
      type: Array,
      required: true
    },
    width: {
      type: Number,
      default: 200
    },
    placeholder: {
      type: String,
      default: this.type === 'select' ? '请选择' : '请选择日期'

    },
    clearable: {
      type: Boolean,
      default: true
    },
    // 内容类型(select: 下拉选择框 datePicker: 日期选择框)
    type: {
      type: String,
      default: 'select'
    }
  },
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #labelSelect{
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
    .ivu-select-selection{
      border-top-left-radius: 0;
      border-bottom-left-radius: 0;
    }
    .label-select-content{
      display: inline-block;
    }
    .ivu-date-picker{
      .ivu-input{
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
      }
    }
  }
</style>
