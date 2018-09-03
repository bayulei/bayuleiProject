<template>
  <div id="test">
    <h5 class="theme">{{ theme }}</h5>
    <Select v-model="theme" style="width:200px" @on-change="themeChange">
      <Option v-for="item in themeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
    </Select>
    <div class="box">
      标题
    </div>
  </div>
</template>

<script>
export default {
  name: 'test',
  data () {
    return {
      theme: 'theme-one',
      themeList: [{
        label: '海之蓝',
        value: 'theme-one'
      }, {
        label: '樱桃红',
        value: 'theme-two'
      }]
    }
  },
  methods: {
    themeChange (theme) {
      document.getElementById('app').className = theme
    }
  },
  mounted () {
    this.themeChange(this.theme)
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  #test{
    background: #DDD;
    padding: 20px;
    .full-screen;
    .theme{
      margin-bottom: 20px;
    }
    .box{
      .flex-center;
      width: 100px;
      height: 100px;
      border: 2px solid @baseColor;
      margin-top: 20px;
    }
  }
</style>
