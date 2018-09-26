<!-- 公共头 -->
<template>
  <header class="home-header">
    <router-link tag="div" class="logo" to="/" title="回到首页"></router-link>
    <Icon @click.native="collapsedSider" class="menu-icon" :class="rotateIcon" :style="{marginLeft: '20px',cursor: 'pointer'}" type="md-menu" size="24"></Icon>
    <ul class="home-nav">
      <router-link tag="li" v-for="topNav in topNavList" :key="topNav.name" :to="topNav.path">
        <span>{{ topNav.title }}</span>
      </router-link>
    </ul>
    <div class="header-right">
      <div class="time-box">{{ currentTime }}</div>
      <div class="user-info">
        <img :src="userAvator" alt="avator">
        <Dropdown trigger="click" @on-click="userInfoOpen">
          <span>欢迎您，管理员 <Icon type="ios-arrow-down"></Icon></span>
          <DropdownMenu slot="list">
            <DropdownItem name="personal">个人中心</DropdownItem>
            <DropdownItem name="logout">退出</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'home-header',
  data () {
    return {
      currentTime: '', // 当前时间
      username: '张三',
      userAvator: require('assets/images/user-avator.png'), // 用户头像
      topNavList: [
        {
          title: '首页',
          path: '/',
          name: 'home'
        },
        // {
        //   title: '云端资源库',
        //   path: '/yunduanziyuanku',
        //   name: 'yunduanziyuanku'
        // },
        {
          title: '本地资源库',
          path: '/bendiziyuanku',
          name: 'bendiziyuanku'
        },
        {
          title: '数据报表',
          path: '/shujubaobiao',
          name: 'shujubaobiao'
        },
        {
          title: '分析工具',
          path: '/fenxigongju',
          name: 'fenxigongju'
        },
        {
          title: '配置管理',
          path: '/config',
          name: 'Config'
        }
      ]
    }
  },
  methods: {
    /**
     * @description: 获取当前时间
     * @author: chenxiaoxi
     * @date: 2018-09-02 10:59:50
     */
    getTime () {
      setInterval(() => {
        this.currentTime = this.$dateFormat(new Date(), 'yyyy-MM-dd hh:mm:ss')
        // this.currentTime = this.$dateFormat(new Date(), 'yyyy-MM-dd hh:mm:ss') + ' 星期' + '日一二三四五六'.charAt(new Date().getDay())
      }, 1000)
    },
    /**
     * @description: 用户菜单栏点击
     * @author: chenxiaoxi
     * @date: 2018-09-13 10:03:52
     */
    userInfoOpen (name) {
      switch (name) {
        case 'personal':
          this.$router.push('/personal')
          break
        case 'logout':
          this.$confirm({
            title: '登出',
            tips: '您确认要退出吗？',
            confirm: () => {
              this.$store.commit('logout')
              this.$Modal.remove()
              this.$router.push('/sign_in')
            }
          })
          break
      }
    },
    collapsedSider () {
      this.$emit('collapsedSider')
    }
  },
  props: {
    isCollapsed: Boolean
  },
  computed: {
    rotateIcon () {
      return [
        'menu-icon',
        this.isCollapsed ? 'rotate-icon' : ''
      ]
    }
  },
  mounted () {
    this.getTime()
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  .home-header{
    height: 7.8%;
    background: @homeHeaderBgColor;
    padding: 0 0.95%;
    .un-select();
    .flex();
    align-items: center;
    color: #FFF;
    font-size: 15px;
    font-weight: bold;
    .logo{
      height: 86.6%;
      flex: 0 0 20%;
      background: #DDE;
      background: url("~assets/images/home/logo.png") no-repeat left center;
      background-size: 100%;
      cursor: pointer;
    }
    .menu-icon{
      margin-left: 20px;
      transition: all .3s linear;
    }
    .home-nav{
      flex: 1;
      .flex();
      padding: 0 5% 0 7%;
      height: 86.6%;
      li{
        color: #FFF;
        font-size: 16px;
        margin-right: 80px;
        text-align: center;
        transition: transform 0.3s linear;
        font-weight: normal;
        .un-select();
        .flex();
        justify-content: center;
        align-items: center;
        span{
          position: relative;
          transition: linear all .2s;
          &::before{
            position: absolute;
            content: '';
            display: block;
            width: 0;
            height: 2px;
            background: #FFF;
            bottom: -2px;
            left: 50%;
            transform: translateX(-50%);
            transition: linear all .2s;
          }
          &:hover{
            cursor: pointer;
            &::before{
              width: 100%;
            }
          }
        }
      }
    }
    @media screen and (max-width: 1366px) {
      .home-nav{
        padding: 0 2.5% 0 6% !important;
        li{
          margin-right: 45px !important;
        }
      }
      .header-right{
        flex: 0 0 27.5% !important;
      }
    }
    .header-right{
      height: 86.6%;
      flex: 0 0 20%;
      .flex();
      .time-box{
        flex: 1;
        .flex();
        align-items: center;
        justify-content: flex-end;
      }
      .user-info{
        flex: 0 0 55%;
        .flex();
        align-items: center;
        justify-content: flex-end;
        cursor: pointer;
        font-weight: normal;
        img{
          width: 40px;
          margin-right: 4.5%;
        }
      }
    }
  }
</style>
