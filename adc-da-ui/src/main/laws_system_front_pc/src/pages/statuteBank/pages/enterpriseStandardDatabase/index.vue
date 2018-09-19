<!-- 企业标准库公共页面 -->
<template>
  <div id="enterpriseStandardDatabase">
    <panel-header :tabs="tabs" :active="active" @activated="showComponent"></panel-header>
    <panel-content>
      <components :is="active"></components>
    </panel-content>
  </div>
</template>

<script>
import EnterpriseStandardDatabase from './components/EnterpriseStandardDatabase'
import EnterpriseStandardLedger from './components/EnterpriseStandardLedger'
import EnterpriseStandardAnnualPlan from './components/EnterpriseStandardAnnualPlan'
export default {
  name: 'enterpriseStandardDatabase-common',
  data () {
    return {
      tabs: [
        {
          title: '企业标准库',
          name: 'EnterpriseStandardDatabase'
        },
        {
          title: '企业标准台账',
          name: 'EnterpriseStandardLedger'
        },
        {
          title: '企业标准年度计划',
          name: 'EnterpriseStandardAnnualPlan'
        }
      ],
      active: 'EnterpriseStandardDatabase'
    }
  },
  methods: {
    showComponent (name) {
      this.active = name
    }
  },
  components: {
    EnterpriseStandardDatabase,
    EnterpriseStandardLedger,
    EnterpriseStandardAnnualPlan
  },
  props: {},
  computed: {},
  watch: {},
  mounted () {}
}
</script>

<style lang="less">
  #enterpriseStandardDatabase{

  }
</style>
