<template>
  <div class="home">
    <home-header></home-header>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import HomeHeader from './components/Header'
export default {
  name: 'home',
  data () {
    return {}
  },
  methods: {
    /**
     * @description: 主题设置
     * @author: xx
     * @date: 2018-08-29 14:56:39
     */
    setTheme (theme) {
      document.getElementById('app').className = theme
    }
  },
  components: {
    HomeHeader
  },
  computed: {
    ...mapState([
      'theme'
    ])
  },
  watch: {
    theme (val) {
      // 主题修改
      this.setTheme(val)
    }
  },
  mounted () {
    this.setTheme(this.theme)
  }
}
</script>

<style lang="less" scoped>
  @import '~styles/style';
  .home{
    width: 100%;
    height: 100%;
  }
</style>
