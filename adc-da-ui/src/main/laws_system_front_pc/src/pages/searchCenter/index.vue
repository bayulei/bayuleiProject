<!-- 法规库 -->
<template>
 <div class="search-center">
   <com-header></com-header>
   <section class="home-section">
     <!-- 搜索框 -->
     <div class="search-box">
       <div class="search-option">
         <Select v-model="selectKey">
           <Option value="standNumber">标准编号</Option>
           <Option value="standName">标准名称</Option>
           <Option value="standState">标准状态</Option>
           <Option value="standFile">标准正文内容</Option>
           <Option value="applyArctic">适用车型</Option>
           <Option value="issueTime">发布日期</Option>
           <Option value="putTime">实施日期</Option>
           <Option value="draftUser">起草人</Option>
           <Option value="draftingUnit">起草部门</Option>
           <Option value="replaceStandNum">代替标准</Option>
           <Option value="replacedStandNum">被代替标准</Option>
           <Option value="lawsName">文件名称</Option>
           <Option value="2">动态标题</Option>
         </Select>
       </div>
       <div class="search-input">
         <Input type="text" v-model="selectValue" autofocus id="search-input" placeholder="请输入查询信息" clearable></Input>
       </div>
       <div class="search-btn">
         <Icon type="ios-search" :size="34" color="rgba(0,0,0,.8)" @click.native="searchCenteric"/>
       </div>
       <div class="search-text" >标准高级搜索</div>
     </div>
     <nav class="section-nav">
       <div class="nav-content">
         <div class="nav-content-left">
           <!-- 国内外标准法规库 -->
           <div class="searchbox" id="basic_searchbox" style="height: auto;">
             <div class="searchbox-item" data-id="0" id="searchitem_G_STD_DOMAIN">
               <div class="l" id="searchitem_G_STD_DOMAIN_l">标准类型<i></i></div>
               <div class="c" id="searchitem_G_STD_DOMAIN_c">
                 <div class="control-type">(多选)</div>
                 <div class="filter_option" style="padding-right:20px;">
                 <span title="全部" class="option_all">全部</span>
                 <span title="国家标准计划" data-id="0" data-value="国家标准计划" class="selected">国家标准计划</span>
                 <span title="国家标准" data-id="1" data-value="国家标准" class="selected">国家标准</span>
                 <span title="行业标准" data-id="2" data-value="行业标准" class="selected">行业标准</span>
                 <span title="地方标准" data-id="3" data-value="地方标准" class="">地方标准</span>
                 </div>
               </div>
             </div><div class="searchbox-item" data-id="1" id="searchitem_G_STD_NATURE"><div class="l" id="searchitem_G_STD_NATURE_l">标准性质<i></i></div><div class="c" id="searchitem_G_STD_NATURE_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="强制性" data-id="0" data-value="强制性">强制性</span><span title="推荐性" data-id="1" data-value="推荐性">推荐性</span>
           </div></div></div><div class="searchbox-item" data-id="2" id="searchitem_CURRENT_LINK" style="display: block;"><div class="l" id="searchitem_CURRENT_LINK_l">计划状态<i></i></div><div class="c" id="searchitem_CURRENT_LINK_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="正在起草" data-id="0" data-value="正在起草">正在起草</span><span title="正在审查" data-id="1" data-value="正在审查">正在审查</span><span title="正在批准" data-id="2" data-value="正在批准">正在批准</span><span title="已终止" data-id="3" data-value="已终止">已终止</span></div></div></div><div class="searchbox-item" data-id="3" id="searchitem_G_STATE" style="display: block;"><div class="l" id="searchitem_G_STATE_l">标准状态<i></i></div><div class="c" id="searchitem_G_STATE_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="现行" data-id="0" data-value="现行">现行</span><span title="废止" data-id="1" data-value="废止">废止</span></div></div></div><div class="searchbox-item" data-id="4" id="searchitem_G_TRADE_DEPT_FULL" style="display: block;"><div class="l" id="searchitem_G_TRADE_DEPT_FULL_l">行业分类<i></i></div>
             <div class="c" id="searchitem_G_TRADE_DEPT_FULL_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="WS 卫生" data-id="0" data-value="WS 卫生">WS 卫生</span><span title="MH 民用航空" data-id="1" data-value="MH 民用航空">MH 民用航空</span><span title="SN 出入境检验检疫" data-id="2" data-value="SN 出入境检验检疫">SN 出入境检验检疫</span><span title="CJ 城镇建设" data-id="3" data-value="CJ 城镇建设">CJ 城镇建设</span><span title="YC 烟草" data-id="4" data-value="YC 烟草">YC 烟草</span><span title="TB 铁路运输" data-id="5" data-value="TB 铁路运输">TB 铁路运输</span></div></div><a href="javascript:;" class="r" id="searchitem_G_TRADE_DEPT_FULL_r"><span class="text">展开</span></a></div><div class="searchbox-item" data-id="5" id="searchitem_G_PROVINCE" style="display: none;"><div class="l" id="searchitem_G_PROVINCE_l">区域/地方<i></i></div><div class="c" id="searchitem_G_PROVINCE_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="海南" data-id="0" data-value="海南">海南</span><span title="上海" data-id="1" data-value="上海">上海</span><span title="吉林" data-id="2" data-value="吉林">吉林</span><span title="辽宁" data-id="3" data-value="辽宁">辽宁</span><span title="河南" data-id="4" data-value="河南">河南</span><span title="湖南" data-id="5" data-value="湖南">湖南</span><span title="山东" data-id="6" data-value="山东">山东</span><span title="江苏" data-id="7" data-value="江苏">江苏</span><span title="安徽" data-id="8" data-value="安徽">安徽</span><span title="新疆" data-id="9" data-value="新疆">新疆</span></div></div></div><div class="searchbox-item" style="border: 0" data-id="6" id="searchitem_G_ICS_NAME"><div class="l" id="searchitem_G_ICS_NAME_l">ICS分类<i></i></div><div class="c" id="searchitem_G_ICS_NAME_c"><div class="control-type">(多选)</div><div class="filter_option" style="padding-right:20px;"><span title="全部" class="option_all selected">全部</span><span title="01_综合、术语学、标准化、文献" data-id="0" data-value="01_综合、术语学、标准化、文献">01_综合、术语学、标准化、文献</span><span title="07_数学、自然科学" data-id="1" data-value="07_数学、自然科学">07_数学、自然科学</span><span title="11_医药卫生技术" data-id="2" data-value="11_医药卫生技术">11_医药卫生技术</span><span title="13_环保、保健和安全" data-id="3" data-value="13_环保、保健和安全">13_环保、保健和安全</span><span title="65_农业" data-id="4" data-value="65_农业">65_农业</span><span title="67_食品技术" data-id="5" data-value="67_食品技术">67_食品技术</span><span title="71_化工技术" data-id="6" data-value="71_化工技术">71_化工技术</span><span title="83_橡胶和塑料工业" data-id="7" data-value="83_橡胶和塑料工业">83_橡胶和塑料工业</span><span title="85_造纸技术" data-id="8" data-value="85_造纸技术">85_造纸技术</span><span title="87_涂料和颜料工业" data-id="9" data-value="87_涂料和颜料工业">87_涂料和颜料工业</span><span title="91_建筑材料和建筑物" data-id="10" data-value="91_建筑材料和建筑物">91_建筑材料和建筑物</span><span title="97_家用和商用设备、文娱、体育" data-id="11" data-value="97_家用和商用设备、文娱、体育">97_家用和商用设备、文娱、体育</span></div></div><a href="javascript:;" class="r" id="searchitem_G_ICS_NAME_r"><span class="text">展开</span></a></div></div>
           <!-- 待办任务 -->
           <div class="nav-left-bottom" id="pendingTask">
             <h5>查询结果</h5>
             <div class="box-line"></div>
             <!--<ul>
               <li v-for="needTodo in todoList" :key="needTodo.id">{{ needTodo.content }}</li>
             </ul>-->
             <div class="content-detail" v-if="selectinfoList.length > 0">
               <div class="card domBtn" v-for="(item, index) in selectinfoList" :key="index" :class="{ 'selected': item.checked }">
                 <!--  标准   -->
                 <Row v-if="item.type == 'stand'">
                   <Col span="2">{{ (searchInfoEO.page-1)*searchInfoEO.pageSize + index+1}} <Tag checkable color="warning">标签四</Tag></Col>
                   <Col span="5" size="large">标准编号: {{ item.stand_sort }} {{ item.stand_number }} - {{ item.stand_year }}</Col>
                   <Col span="5" >
                   <b>《{{ item.stand_name }}》</b>
                   </Col>
                   <Col span="5">
                   <Tag type="dot" :color="item.standStateShow === 0 ? '' : (item.standStateShow === 1 ? 'warning' : (item.standStateShow === 2 ? 'success' : 'error'))">
                     {{ item.stand_state}}
                   </Tag>
                   </Col>
                   <Col span="5" >
                   <Button @click="goProcess(item)" class="card-btn">流程</Button>
                   <Button @click="selectStandardPro(item,'show')" class="card-btn">查看</Button>
                   <Icon type="md-star" size="26" style="margin-right:5px;cursor: pointer"></Icon>
                   <Icon type="ios-redo" size="26"  style="cursor: pointer"></Icon>
                   </Col>
                 </Row>
                 <!--  法规   -->
                 <Row v-if="item.type == 'laws'">
                   <Col span="2">{{ (searchInfoEO.page-1)*searchInfoEO.pageSize + index+1}}</Col>
                   <Col span="5" size="large">法规编号:{{ item.laws_number }}</Col>
                   <Col span="5" >
                   <b>《{{ item.laws_name }}》</b>
                   </Col>
                   <Col span="5">
                   <Tag type="dot" :color="item.standStateShow === 0 ? '' : (item.standStateShow === 1 ? 'warning' : (item.standStateShow === 2 ? 'success' : 'error'))">
                     {{ item.laws_status }}
                   </Tag>
                   </Col>
                   <Col span="5" >
                   <Button @click="goProcess(item)" class="card-btn">流程</Button>
                   <Button @click="selectStandardPro(item,'show')" class="card-btn">查看</Button>
                   <Icon type="md-star" size="26" style="margin-right:5px;cursor: pointer"></Icon>
                   <Icon type="ios-redo" size="26"  style="cursor: pointer"></Icon>
                   </Col>
                 </Row>
                 <!--  企业标准   -->
                 <Row v-if="item.type == 'bussstand'">
                   <Col span="2">{{ (searchInfoEO.page-1)*searchInfoEO.pageSize + index+1}}</Col>
                   <Col span="5" size="large">标准编号: {{ item.stand_code }}</Col>
                   <Col span="5" >
                   <b>《{{ item.stand_name }}》</b>
                   </Col>
                   <Col span="5">
                   <Tag type="dot" :color="item.standStateShow === 0 ? '' : (item.standStateShow === 1 ? 'warning' : (item.standStateShow === 2 ? 'success' : 'error'))">
                     {{ item.stand_status }}
                   </Tag>
                   </Col>
                   <Col span="5" >
                   <Button @click="goProcess(item)" class="card-btn">流程</Button>
                   <Button @click="selectStandardPro(item,'show')" class="card-btn">查看</Button>
                   <Icon type="md-star" size="26" style="margin-right:5px;cursor: pointer"></Icon>
                   <Icon type="ios-redo" size="26"  style="cursor: pointer"></Icon>
                   </Col>
                 </Row>
               </div>
             </div>
             <has-no-data pClass="content-detail" v-else></has-no-data>
             <loading :loading="loading">数据获取中</loading>
           </div>
           <pagination :total="total" @pageChange="pageChange" @pageSizeChange="pageSizeChange"></pagination>
         </div>
         <div class="nav-content-right">
           <!-- 动态信息 -->
           <div class="nav-right-top" id="dynamicEarlyWarning">
             <h5>为我推荐</h5>
             <span class="more">更多</span>
             <div class="box-line"></div>
             <!--<ul>
               <li v-for="item in domesticDynamics" :key="item.id">
                 <i v-if="item.id === '1001'">new</i>
                 <a>{{ item.content }}</a>
                 <span>{{ item.time }}</span>
               </li>
             </ul>-->
           </div>
           <!-- 资料中心 -->
           <div class="nav-right-bottom" id="informationCenter">
             <h5>检索历史</h5>
             <span class="more">更多</span>
             <div class="box-line"></div>
             <ul>
               <!--<li v-for="item in informationData" :key="item.id">
                 <span class="type">{{ item.type === 1 ? '新闻' : (item.type === 2 ? '通知' : (item.type === 3 ? '月报' : '资料')) }}</span>
                 <a class="content">{{ item.content }}</a>
                 <span class="time">{{ item.time }}</span>
               </li>-->
             </ul>
           </div>
         </div>
       </div>
     </nav>
   </section>
   <footer class="home-footer">
     <!-- 友情链接 -->
     <div class="friendshipLink">
     </div>
     <!-- 页尾 -->
     <div class="footer">
     </div>
   </footer>
 </div>
</template>

<script src="./js/searchCenter.js"></script>

<style lang="less">
  @import '~styles/style';
  @import '~styles/mixins';
  .search-center{
    .home-section{
      height: 77.2%;
      background: @homeSectionBgColor;
      padding-top: 1.6%;
      .search-box{
        width: 57.95%;
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
          font-size: 16px;
          background: rgba(255,255,255,.8);
          border: 1px solid #B3C3C7;
          .flex-center;
          .ivu-select{
            height: 100%;
            background: transparent;
            border: none;
            .ivu-select-selection{
              height: 100%;
              border: none;
              border-radius: 0;
              box-shadow: none;
              & > div{
                height: 100%;
                .flex();
                align-items: center;
                justify-content: center;
              }
            }
          }
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
              padding: 0 20px;
              font-size: 16px;
            }
          }
        }
        .search-btn{
          flex: 0 0 13.63%;
          background: @homeHeaderBgColor;
          cursor: pointer;
          &:hover{
            opacity: .8;
          }
          .flex-center;
          .ivu-icon{
            color: #FFF !important;
          }
        }
        .search-text{
          .flex-center;
          color: #2A639B;
          font-weight: bold;
          font-size: 16px;
          flex: 0 0 calc(~'100% - 82.26%');
          position: relative;
          top: 5px;
        }
      }
      .section-nav{
        width: 90%;
        height: 87%;
        margin: 0 auto;
        max-width: 1600px;
        .nav-content{
          width: 100%;
          height: 100%;
          position: relative;
          .nav-content-left {
            width: 70.14%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            font-size: 17px;
            .nav-left-top{
              width: 100%;
              height: 30%;
            }
            .nav-left-bottom{
              width: 100%;
              height: 70%;
              position: absolute;
              bottom: 0;
              left: 0;
              color: #666;
              h5{
                font-size: 18px;
                line-height: 30px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                font-weight: 600;
              }
              ul{
                margin-top: 5px;
                height: 85%;
                overflow-y: auto;
                li{
                  padding: 5px;
                  .ellipsis;
                  font-size: 14px;
                }
              }
              .card {
                width: 100%;
                height: 100px;
                border: 1px solid #EEE;
                margin-bottom: 10px;
                -webkit-transition: all 0.2s ease-in-out;
                transition: all 0.2s ease-in-out;
                cursor: pointer;
                padding: 5px 15px;
                background: #FFF;
                overflow: hidden;
                border-radius: 5px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
              }
            }
          }
          .nav-content-right{
            width: 29.46%;
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
              h5{
                font-size: 18px;
                line-height: 30px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                font-weight: 600;
              }
              ul{
                height: 85%;
                overflow-y: auto;
                li{
                  padding: 0 5px;
                  margin-bottom: 5px;
                  position: relative;
                  cursor: pointer;
                  display: flex;
                  display: -ms-flex;
                  align-items: center;
                  font-size: 14px;
                  &:last-child{
                    margin-bottom: 0;
                  }
                  a{
                    display: inline-block;
                    max-width: 80%;
                    color: #333;
                    .ellipsis;
                  }
                  &:hover{
                    a{
                      color: #12508E;
                    }
                    span{
                      color: #12508E;
                    }
                  }
                  i{
                    font-style: italic;
                    color: #E4393C;
                    font-weight: bold;
                    margin-right: 5px;
                  }
                  span{
                    position: absolute;
                    top: 0;
                    right: 7.5px;
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
              h5{
                font-size: 18px;
                line-height: 30px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                font-weight: 600;
              }
              ul{
                width: 100%;
                height: 85%;
                overflow-y: auto;
                li{
                  padding: 0 0.1rem;
                  margin-bottom: 0.15rem;
                  position: relative;
                  cursor: pointer;
                  display: flex;
                  display: -ms-flex;
                  align-items: center;
                  font-size: 14px;
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
                      background: #AAA;
                    }
                  }
                  .time{
                    position: absolute;
                    top: 0;
                    right: 0.1rem;
                  }
                  a{
                    padding-left: 0.1rem;
                    color: #333;
                    display: inline-block;
                    max-width: 80%;
                    .ellipsis;
                  }
                  &:hover{
                    a{
                      color: #12508E;
                    }
                    .time{
                      color: #12508E;
                    }
                  }
                }
              }
            }
            .more{
              cursor: pointer;
              color: #12508E;
              position: absolute;
              top: 0;
              right: 0;
              z-index: 100;
              padding: 5px 10px;
              &:hover{
                color: #FFF;
                background: #12508E;
                transition: all .2s linear;
              }
            }
          }
          #standardRegulationLibrary{
            background: @homeHeaderBgColor;
            color: #FFF;
          }
          #enterpriseStandardLaw{
            background: @boxBgColor;
            color: #FFF;
          }
          #processCenter{
            background: @boxBgColor;
            color: #FFF;
          }
          #standardTeam{
            background: @homeHeaderBgColor;
            color: #FFF;
          }
          #pendingTask{color: #333;}
          #dynamicEarlyWarning{color: #333;}
          #informationCenter{color: #333;}
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
          color: #333;
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
              color: #333;
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
        color: #333;
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
              background: #333;
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
    .searchbox {
      border: 1px solid #e3e3e3;
      padding-left: 10px;
      padding-right: 10px;
      overflow: hidden;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
    }
    .searchbox-item {
      border-bottom: 1px dashed #e3e3e3;
      padding-bottom: 8px;
      overflow: hidden;
      position: relative;
      color: black;
    }
    .searchbox .l {
      box-sizing: content-box;
      padding: 0;
      border-radius: 3px;
      text-align: center;
      background: none;
      color: #333;
      font-weight: bold;
      font-size: 14px;
      position: absolute;
      left: 0;
      top: 8px;
      width: 72px;
      line-height: 22px;
    }
    .searchbox .c {
      width: auto !important;
      padding-left: 90px;
      padding-right: 70px;
      height: 30px;
      overflow: hidden;
    }
    #basic_searchbox .control-type {
      display: none;
    }
    .searchbox .c .control-type {
      color: #5CB85C;
      width: 44px;
      height: 30px;
      padding-top: 10px;
      float: left;
    }
    .filter_option .selected {
      background: #18a689;
      color: #fff;
    }
    .filter_option span {
      display: inline-block;
      line-height: 22px;
      border-radius: 3px;
      padding-left: 6px;
      padding-right: 6px;
      cursor: pointer;
      margin-right: 17px;
      margin-top: 8px;
    }
    .searchbox .r {
      line-height: 22px;
      position: absolute;
      right: 0;
      top: 8px;
    }
  }
</style>
