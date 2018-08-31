<template>
  <div class="home">
    <div class="logo"></div>
    <!-- 搜索框 -->
    <div class="search-box">
      <div class="search-option">主题</div>
      <div class="search-input">
        <Input type="text" v-model="keywords" autofocus id="search-input" :placeholder="searchRecommend" clearable></Input>
      </div>
      <div class="search-btn box-bg">
        <Icon type="ios-search" :size="34" color="rgba(0,0,0,.8)" />
      </div>
      <div class="search-text">{{ searchText }}</div>
    </div>
    <!-- 菜单导航 -->
    <div class="nav-menu">
      <div class="menu-left">
        <div class="menu-box">
          <div class="menu-item-wrapper">
            <!-- 分析工具 -->
            <div class="menu"></div>
            <!-- 数据分析 -->
            <div class="menu"></div>
          </div>
          <div class="menu-item-wrapper">
            <!-- 本地标准法规库 -->
            <div class="menu"></div>
          </div>
        </div>
        <div class="menu-box">
          <div class="menu-item-wrapper">
            <!-- 资料中心 -->
            <div class="menu"></div>
          </div>
          <div class="menu-item-wrapper">
            <!-- 国内动态 -->
            <div class="menu"></div>
          </div>
        </div>
        <div class="menu-box left3">
          <!-- 待办任务 -->
          <div class="menu"></div>
        </div>
        <div class="menu-box left4">
          <!-- 标准团队 -->
          <div class="menu"></div>
          <!-- 云端资源库 -->
          <div class="menu"></div>
          <!-- 流程中心 -->
          <div class="menu"></div>
          <!-- 配置管理 -->
          <div class="menu"></div>
        </div>
      </div>
      <div class="menu-right"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      searchText: '标准高级检索',
      searchRecommend: '中文文献、外文文献 (elsevier、Springer、wiley......)',
      keywords: '' // 搜索关键词
    }
  },
  methods: {
    //
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  .home{
    width: 100%;
    height: 100%;
    background: url("~assets/images/home/bg.png") no-repeat;
    background-position: center center;
    background-size: 100% 100%;
    padding: 1.57% 0 0 1.66%;
    .logo{
      width: 22.55%;
      height: 4.35%;
      background-image: url("~assets/images/home/logo.png");
      background-size: 100%;
      background-repeat: no-repeat;
    }
    .search-box{
      width: 45.4%;
      height: 5.1%;
      margin: 3.6% auto 5.4% auto;
      display: flex;
      .search-option{
        flex: 0 0 13.74%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #757575;
        font-size: 14px;
        border-right: 2px solid #D9E9ED;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        background: rgba(255,255,255,.8);
      }
      .search-input{
        flex: 0 0 55.37%;
        background: rgba(255,255,255,.8);
        .ivu-input-wrapper{
          width: 100%;
          height: 100%;
          border: none;
          display: flex;
          align-items: center;
          .ivu-icon{
            cursor: pointer;
          }
        }
        .ivu-input:hover{
          border: none;
        }
        .ivu-input{
          width: 100%;
          height: 100%;
          padding: 0 0.4rem;
          color: #757575;
          font-size: 14px;
          background: transparent;
          box-shadow: none;
          border: none;
          &:focus{
            box-shadow: none;
          }
        }
      }
      .search-btn{
        flex: 0 0 13.69%;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        opacity: .9;
        &:hover{
          opacity: 1;
        }
      }
      .search-text{
        flex: 0 0 2.26rem;
        margin-left: 0.5rem;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #FFF;
        letter-spacing: 1px;
        font-size: 14px;
      }
    }
    .nav-menu{
      width: 54.16%;
      height: 57.9%;
      margin: 0 auto;
      display: flex;
      .menu-left{
        flex: 0 0 61%;
        height: 100%;
        display: flex;
        flex-flow: row wrap;
        align-content: flex-start;
        .menu-box{
          flex: 0 0 48.5%;
          height: 48.75%;
          margin-right: 3%;
          &:nth-child(1),
          &:nth-child(2){
            margin-bottom: 2.5%;
          }
          &:nth-child(even){
            margin-right: 0;
          }
          .menu-item-wrapper{
            width: 100%;
            height: 48.4%;
            display: flex;
            margin-bottom: 3.2%;
            &:last-child{
              margin-bottom: 0;
            }
            .menu{
              flex: 1;
              background: #DDD;
              margin-right: 3.2%;
              &:last-child{
                margin-right: 0;
              }
            }
          }
        }
        .left3{
          .menu{
            width: 100%;
            height: 100%;
            background: #DDD;
          }
        }
        .left4{
          display: flex;
          flex-flow: row wrap;
          align-content: flex-start;
          .menu{
            flex: 0 0 48.5%;
            height: 48%;
            background: #DDD;
            margin-right: 3%;
            margin-bottom: 3%;
            &:last-child{
              margin-bottom: 0;
            }
            &:nth-child(even){
              margin-right: 0;
            }
          }
        }
      }
      .menu-right{
        flex: 0 0 29.6%;
        margin-left: 9.6%;
        heighgt: 100%;
        border: 1px solid #DDD;
      }
    }
  }
</style>
