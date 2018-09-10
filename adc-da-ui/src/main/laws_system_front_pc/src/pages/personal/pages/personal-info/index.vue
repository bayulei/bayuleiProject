<!-- 个人信息 -->
<template>
  <div class="personal-info">
    <panel-header :tabs="tabsList" @activated="showComponent" :active="active"></panel-header>
    <panel-content>
      <components :is="active"></components>
    </panel-content>
  </div>
</template>

<script>
import PersonalData from './components/PersonalData'
import AccountSecurity from './components/AccountSecurity'
export default {
  name: 'personal-info',
  data () {
    return {
      active: 'PersonalData',
      tabsList: [{
        title: '编辑个人资料',
        name: 'PersonalData'
      }, {
        title: '账号安全设置',
        name: 'AccountSecurity'
      }]
    }
  },
  methods: {
    /**
     * @description: 切换组件side-bar-left
     * @author: xx
     * @date: 2018-08-30 14:00:36
     */
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    PersonalData,
    AccountSecurity
  }
}
</script>
<style lang="less" scoped>
  .personal-info{
    height: 100%;
  }
</style>
