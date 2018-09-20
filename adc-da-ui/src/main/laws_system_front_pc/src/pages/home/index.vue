<!-- 首页 -->
<template>
  <div id="home">
    <header class="home-header">
      <div class="logo"></div>
      <ul class="home-nav">
        <router-link tag="li" v-for="topNav in topNavList" :key="topNav.name" :to="topNav.path">{{ topNav.title }}</router-link>
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
    <section class="home-section">
      <!-- 搜索框 -->
      <div class="search-box">
        <div class="search-option">主题</div>
        <div class="search-input">
          <Input type="text" v-model="keywords" autofocus id="search-input" :placeholder="searchRecommend" clearable></Input>
        </div>
        <div class="search-btn">
          <Icon type="ios-search" :size="34" color="rgba(0,0,0,.8)" />
        </div>
        <div class="search-text">{{ searchText }}</div>
      </div>
      <nav class="section-nav">
        <div class="nav-content">
          <div class="nav-content-left">
            <!-- 国内外标准法规库 -->
            <router-link tag="div" class="nav-left-top nav-item" id="standardRegulationLibrary" to="/statuteBank">
              <div class="nav-icon iconfont">&#xe656;</div>
              <h5>国内外标准法规库</h5>
            </router-link>
            <!-- 企业标准库 -->
            <div class="nav-left-top nav-item" id="enterpriseStandardLaw">
              <div class="nav-icon iconfont">&#xe6ee;</div>
              <h5>企业标准库</h5>
            </div>
            <!-- 流程中心 -->
            <router-link tag="div" class="nav-left-top nav-item" id="processCenter" to="/processCenter">
              <div class="nav-icon iconfont">&#xe634;</div>
              <h5>流程中心</h5>
            </router-link>
            <!-- 标准团队 -->
            <div class="nav-left-top nav-item" id="standardTeam">
              <div class="nav-icon iconfont">&#xe600;</div>
              <h5>标准团队</h5>
            </div>
            <!-- 待办任务 -->
            <div class="nav-left-bottom" id="pendingTask">
              <h5>待办任务</h5>
              <ul>
                <li v-for="needTodo in todoList" :key="needTodo.id">{{ needTodo.content }}</li>
              </ul>
            </div>
          </div>
          <div class="nav-content-right">
            <!-- 动态信息 -->
            <div class="nav-right-top" id="dynamicEarlyWarning">
              <h5>动态信息</h5>
              <span class="more">更多</span>
              <ul>
                <li v-for="item in domesticDynamics" :key="item.id">
                  <i v-if="item.id === '1001'">new</i>
                  <a>{{ item.content }}</a>
                  <span>{{ item.time }}</span>
                </li>
              </ul>
            </div>
            <!-- 资料中心 -->
            <div class="nav-right-bottom" id="informationCenter">
              <h5>资料中心信息</h5>
              <span class="more">更多</span>
              <ul>
                <li v-for="item in informationData" :key="item.id">
                  <span class="type">{{ item.type === 1 ? '新闻' : (item.type === 2 ? '通知' : (item.type === 3 ? '月报' : '资料')) }}</span>
                  <a class="content">{{ item.content }}</a>
                  <span class="time">{{ item.time }}</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </section>
    <footer class="home-footer">
      <!-- 友情链接 -->
      <div class="friendshipLink">
        <span>友情链接:</span>
        <ul>
          <li v-for="(link, index) in link" :key="index"><a :href="link.url">{{ link.title }}</a></li>
        </ul>
      </div>
      <!-- 页尾 -->
      <div class="footer">
        <Row>
          <Col span="3" push="1"><span class="footer-border-right">同时在线人数</span> <a>{{ currentInline }}</a></Col>
          <Col span="3" push="1"><span class="footer-border-right total-inline">累计登录人数</span> <a>{{ totalInline }}</a></Col>
          <Col span="4" push="4"><img :src="logoSm" alt="logo"></Col>
          <Col span="4" push="4"><span class="contact-number">联系电话: {{ contactNumber }}</span></Col>
          <Col span="6" push="4"><span class="contact-address">地址: {{ contactAddress }}</span></Col>
        </Row>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      currentTime: '', // 当前时间
      userAvator: require('assets/images/user-avator.png'), // 用户头像
      keywords: '',
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
      ],
      searchText: '标准高级检索',
      searchRecommend: '中文文献、外文文献 (elsevier、Springer、wiley......)',
      todoList: [
        {
          id: '1001',
          content: '10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训10点半组织新员工培训'
        },
        {
          id: '1002',
          content: '12点公司楼下咖啡厅约见客户王经理'
        },
        {
          id: '1003',
          content: '14点税务局提交本月单据'
        },
        {
          id: '1004',
          content: '15点组织召开本周周会'
        },
        {
          id: '1005',
          content: '16点总结周会内容，发邮件给部门经理'
        },
        {
          id: '1006',
          content: '15点组织召开本周周会'
        },
        {
          id: '1007',
          content: '16点总结周会内容，发邮件给部门经理'
        },
        {
          id: '1008',
          content: '15点组织召开本周周会'
        },
        {
          id: '1009',
          content: '16点总结周会内容，发邮件给部门经理'
        }
      ], // 待办事项
      dynamicEarlyWarningTab: '', // 动态预警(tab值)
      domesticDynamics: [
        {
          id: '1001',
          content: '汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001汽车法规国内动态0001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          content: '汽车法规国内动态0002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          content: '汽车法规国内动态0003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          content: '汽车法规国内动态0004',
          time: '08/28 12:00'
        }
      ],
      informationData: [
        {
          id: '1001',
          type: 1,
          content: '资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001',
          time: '08/28 12:00'
        },
        {
          id: '1002',
          type: 2,
          content: '资料信息中心新闻政策1002',
          time: '08/28 12:00'
        },
        {
          id: '1003',
          type: 3,
          content: '资料信息中心新闻政策1003',
          time: '08/28 12:00'
        },
        {
          id: '1004',
          type: 4,
          content: '资料信息中心新闻政策1004',
          time: '08/28 12:00'
        },
        {
          id: '1005',
          type: 1,
          content: '资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001资料信息中心新闻政策1001',
          time: '08/28 12:00'
        },
        {
          id: '1006',
          type: 2,
          content: '资料信息中心新闻政策1002',
          time: '08/28 12:00'
        },
        {
          id: '1007',
          type: 3,
          content: '资料信息中心新闻政策1003',
          time: '08/28 12:00'
        },
        {
          id: '1008',
          type: 4,
          content: '资料信息中心新闻政策1004',
          time: '08/28 12:00'
        }
      ], // 资料中心信息
      link: [
        {
          title: '企业OA系统',
          url: '#'
        },
        {
          title: '邮箱系统',
          url: '#'
        },
        {
          title: '知识工程系统',
          url: '#'
        },
        {
          title: '国标委官网',
          url: '#'
        },
        {
          title: '工信部官网',
          url: '#'
        },
        {
          title: '全标委官网',
          url: '#'
        },
        {
          title: 'ECE官网',
          url: '#'
        }
      ],
      currentInline: 45, // 同时在线人数
      totalInline: 300, // 累计登录总人数
      contactNumber: '022-23652365', // 联系电话
      contactAddress: '天津市西青区西青道999号', // 联系地址
      logoSm: require('assets/images/home/logo_sm.png')
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
    }
  },
  components: {},
  props: {},
  computed: {},
  watch: {},
  mounted () {
    this.getTime()
  }
}
</script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  #home{
    .home-header{
      height: 7.8%;
      background: @homeHeaderBgColor;
      padding: 0 0.95%;
      .un-select();
      .flex();
      align-items: flex-end;
      color: #FFF;
      font-size: 15px;
      font-weight: bold;
      .logo{
        height: 86.6%;
        flex: 0 0 23.71%;
        background: #DDE;
        background: url("~assets/images/home/logo.png") no-repeat center;
        background-size: contain;
      }
      .home-nav{
        flex: 1;
        .flex();
        padding: 0 0.5rem;
        height: 86.6%;
        li{
          color: #FFF;
          font-size: 16px;
          width: 25%;
          text-align: center;
          position: relative;
          transition: transform 0.3s linear;
          .un-select();
          .flex();
          justify-content: center;
          align-items: center;
          &:hover{
            cursor:pointer;
            color: #52b8db;
          }
          /*&::before{*/
            /*content: '';*/
            /*width: 0;*/
            /*height: 2px;*/
            /*background: #FFF;*/
            /*position: absolute;*/
            /*bottom: -0.15rem;*/
            /*left: 50%;*/
            /*transform: translate(-50%);*/
            /*transition: all .3s linear;*/
          /*}*/
          /*&:hover{*/
            /*cursor: pointer;*/
            /*transform: translateY(-5px);*/
            /*&::before{*/
              /*width: 50%;*/
              /*bottom: -0.25rem;*/
            /*}*/
          /*}*/
        }
      }
      @media screen and (max-width: 1366px) {
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
          img{
            width: 40px;
            margin-right: 4.5%;
          }
        }
      }
    }
    .home-section{
      height: 77.2%;
      background: @homeSectionBgColor;
      padding-top: 1.6%;
      .search-box{
        width: 62.95%;
        height: 7.9%;
        margin: 0 auto;
        display: flex;
        margin-bottom: 1.65%;
        &>div{
          height: 100%;
        }
        .search-option{
          flex: 0 0 13.63%;
          color: #515151;
          font-size: .32rem;
          background: rgba(255,255,255,.8);
          border: 1px solid #B3C3C7;
          .flex-center;
        }
        .search-input{
          flex: 0 0 55%;
          background: rgba(255,255,255,.8);
          border: 1px solid #B3C3C7;
          border-left: transparent;
          .ivu-input-wrapper{
            height: 100%;
            display: flex;
            align-items: center;
            .ivu-input{
              height: 100%;
              background: transparent;
              border: none !important;
              box-shadow: none;
              padding: 0 .4rem;
              font-size: .32rem;
            }
          }
        }
        .search-btn{
          flex: 0 0 13.63%;
          background: @bgColor;
          cursor: pointer;
          &:hover{
            opacity: .8;
          }
          .flex-center;
        }
        .search-text{
          .flex-center;
          color: #2A639B;
          font-weight: bold;
          font-size: .32rem;
          flex: 0 0 calc(~'100% - 82.26%');
          position: relative;
          top: 5px;
        }
      }
      .section-nav{
          width: 90%;
          height: 87%;
          margin: 0 auto;
          .nav-content{
            width: 100%;
            height: 100%;
            color: #FFF;
            position: relative;
            .nav-content-left {
              width: 36.14%;
              height: 100%;
              position: absolute;
              top: 0;
              left: 0;
              .nav-left-top{
                &.nav-item{
                  width: 48.9%;
                  height: 23%;
                  position: absolute;
                  padding-top: 3.5%;
                  &:nth-child(1){
                    top: 0;
                    left: 0;
                  }
                  &:nth-child(2){
                    top: 0;
                    right: 0;
                  }
                  &:nth-child(3){
                    left: 0;
                    top: 24.9%;
                  }
                  &:nth-child(4){
                    right: 0;
                    top: 24.9%;
                  }
                  h5{
                    font-size: 17px;
                    text-align: center;
                  }
                }
              }
              .nav-left-bottom{
                width: 100%;
                height: 49%;
                position: absolute;
                bottom: 0;
                left: 0;
                color: #666;
                padding: 0.25rem;
                h5{
                  font-size: 18px;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  font-weight: 600;
                }
                ul{
                  margin-top: 0.1rem;
                  height: 85%;
                  overflow-y: auto;
                  li{
                    padding: .1rem;
                    .ellipsis;
                  }
                }
              }
              .nav-icon{
                font-size: 40px;
                text-align: center;
              }
            }
            .nav-content-right{
              width: 60.46%;
              height: 100%;
              position: absolute;
              top: 0;
              right: 0;
              .nav-right-top{
                position: relative;
                width: 100%;
                height: 47.9%;
                position: absolute;
                top: 0;
                left: 0;
                padding: 0.25rem;
                .more{
                  cursor: pointer;
                  color: #FFF;
                  position: absolute;
                  top: 2px;
                  right: 2px;
                  z-index: 100;
                  padding: 0.1rem 0.2rem;
                  &:hover{
                    color: #12508E;
                    background: #FFF;
                    transition: all .2s linear;
                  }
                }
                .ivu-tabs{
                  height: 100%;
                  .ivu-tabs-nav .ivu-tabs-tab-active{ color: #FFF; }
                  .ivu-tabs-mini .ivu-tabs-tab{
                    color: #CCC;
                    &:hover{
                      color: #FFF;
                    }
                  }
                  .ivu-tabs-bar{
                    margin-bottom: 0;
                  }
                  .ivu-tabs-content{
                    height: calc(~'100% - 0.66rem');
                    ul{
                      width: 100%;
                      height: 100%;
                      padding: 0.15rem;
                      overflow-y: auto;
                      li{
                        padding: 0 0.1rem;
                        margin-bottom: 0.1rem;
                        position: relative;
                        cursor: pointer;
                        display: flex;
                        display: -ms-flex;
                        align-items: center;
                        &:last-child{
                          margin-bottom: 0;
                        }
                        a{
                          display: inline-block;
                          max-width: 70%;
                          .ellipsis;
                        }
                        &:hover{
                          a{
                            color: @baseColor;
                          }
                          span{
                            color: @baseColor;
                          }
                        }
                        i{
                          font-style: italic;
                          color: #E4393C;
                          font-weight: bold;
                          margin-right: 0.1rem;
                        }
                        a{
                          color: #FFF;
                        }
                        span{
                          position: absolute;
                          top: 0;
                          right: 0.15rem;
                        }
                      }
                    }
                  }
                }
                h5{
                  font-size: 18px;
                  height: 0.8rem;
                  line-height: 0.8rem;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  margin-bottom: 5px;
                  font-weight: 600;
                }
                ul{
                  height: 85%;
                  overflow-y: auto;
                  li{
                    padding: 0 0.1rem;
                    margin-bottom: 0.1rem;
                    position: relative;
                    cursor: pointer;
                    display: flex;
                    display: -ms-flex;
                    align-items: center;
                    &:last-child{
                      margin-bottom: 0;
                    }
                    a{
                      display: inline-block;
                      max-width: 80%;
                      .ellipsis;
                    }
                    &:hover{
                      a{
                        color: #FFF;
                      }
                      span{
                        color: #FFF;
                      }
                    }
                    i{
                      font-style: italic;
                      color: #E4393C;
                      font-weight: bold;
                      margin-right: 0.1rem;
                    }
                    a{
                      color: rgba(255,255,255,.9);
                    }
                    span{
                      position: absolute;
                      top: 0;
                      right: 0.15rem;
                    }
                  }
                }
              }
              .nav-right-bottom{
                width: 100%;
                height: 49%;
                position: absolute;
                bottom: 0;
                left: 0;
                padding: 0 0.2rem;
                .more{
                  cursor: pointer;
                  color: #FFF;
                  position: absolute;
                  top: 2px;
                  right: 2px;
                  z-index: 100;
                  padding: 0.1rem 0.2rem;
                  &:hover{
                    color: #12508E;
                    background: #FFF;
                    transition: all .2s linear;
                  }
                }
                h5{
                  font-size: 18px;
                  height: 0.8rem;
                  line-height: 0.8rem;
                  -webkit-user-select: none;
                  -moz-user-select: none;
                  -ms-user-select: none;
                  user-select: none;
                  margin-bottom: 10px;
                  font-weight: 600;
                }
                ul{
                  width: 100%;
                  height: calc(~'100% - 1rem - 10px');
                  overflow-y: auto;
                  li{
                    padding: 0 0.1rem;
                    margin-bottom: 0.15rem;
                    position: relative;
                    cursor: pointer;
                    display: flex;
                    display: -ms-flex;
                    align-items: center;
                    &:last-child{
                      margin-bottom: 0;
                    }
                    .type{
                      position: relative;
                      display: inline-block;
                      padding-right: 0.15rem;
                      &::before{
                        content: '';
                        display: inline-block;
                        width: 2px;
                        height: 80%;
                        position: absolute;
                        top: 7.5%;
                        right: 0;
                        background: #FFF;
                      }
                    }
                    .time{
                      position: absolute;
                      top: 0;
                      right: 0.1rem;
                    }
                    a{
                      padding-left: 0.1rem;
                      color: rgba(255,255,255,.9);
                      display: inline-block;
                      max-width: 80%;
                      .ellipsis;
                    }
                    &:hover{
                      a{
                        color: #FFF;
                      }
                      .time{
                        color: #FFF;
                      }
                    }
                  }
                }
              }
            }
            #standardRegulationLibrary{
              background: @boxBgColor;
              color: #FFF;
              &:hover{
                color: #12508E;
              }
            }
            #enterpriseStandardLaw{
              background: @boxBgColor;
              color: #FFF;
              &:hover{
                color: #12508E;
              }
            }
            #processCenter{
              background: @boxBgColor;
              color: #FFF;
              &:hover{
                color: #12508E;
              }
            }
            #standardTeam{
              background: @boxBgColor;
              color: #FFF;
              &:hover{
                color: #12508E;
              }
            }
            #pendingTask{background: @boxBgColor; color: #FFF;}
            #dynamicEarlyWarning{background: @boxBgColor;}
            #informationCenter{background: @boxBgColor;}
          }
          .nav-item{
            cursor: pointer;
          }
        }
    }
    .home-footer{
      background: @homeSectionBgColor;
      height: calc(~'100% - 7.8% - 77.2%');
      .flex();
      flex-wrap: wrap;
      .friendshipLink{
        width: 68%;
        margin: 0 auto;
        display: flex;
        display: -ms-flex;
        position: relative;
        top: 10px;
        span{
          color: #888;
          display: inline-flex;
          display: -ms-inline-flexbox;
          align-items: center;
          flex: 0 0 2rem;
          text-align: center;
          font-size: 0.28rem;
          font-weight: bold;
        }
        ul{
          display: flex;
          display: -ms-flex;
          flex: 1;
          li{
            float: left;
            flex: 1;
            display: flex;
            display: -ms-flex;
            align-items: center;
            a{
              color: #888;
              font-size: 14px;
              &:hover{
                color: @baseColor;
                text-decoration: underline;
              }
            }
          }
        }
      }
      .footer{
        width: 90%;
        margin: 0.3rem auto 0 auto;
        color: #888;
        font-size: 14px;
        font-weight: bold;
        span{
          position: relative;
          display: inline-block;
          &.footer-border-right{
            padding: 0 0.1rem;
            &::before{
              content: '';
              display: block;
              width: 1px;
              height: 75%;
              position: absolute;
              top: 12.5%;
              right: 0;
              background: #888;
            }
          }
        }
        .ivu-col{
          text-align: center;
        }
        a{
          color: @baseColor;
          cursor: default;
        }
        img{
          width: 2.44rem;
        }
        .contact-number,
        .contact-address{
          padding: 0 0.2rem;
        }
      }
    }
  }
</style>
