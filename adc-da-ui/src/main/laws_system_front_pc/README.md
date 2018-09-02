# laws_system_front_pc
## 项目基本机构

```
├─assets			—————————————————————————————————————————————————— 静态资源
│  ├─images
│  │  ├─home
│  │  └─login
│  ├─my-theme
│  └─styles			—————————————————————————————————————————————————— 样式文件
├─common			—————————————————————————————————————————————————— 全局组件与插件
│  ├─components		—————————————————————————————————————————————————————— 全局组件
│  │  └─panel
│  └─plugins		—————————————————————————————————————————————————————— 插件(一些公共的方法)
│      └─date
├─pages
│  ├─components		—————————————————————————————————————————————————————— 公共组件(在页面里引入的那种)
│  ├─config			—————————————————————————————————————————————————————— 项目一级菜单
│  │  └─pages		—————————————————————————— 二级菜单
│  │      ├─dynamicInformationManage
│  │      │  └─components	———————————————————————— 三级菜单
│  │      ├─informationCenterConfig
│  │      │  └─components
│  │      ├─mechanismManage
│  │      │  └─components
│  │      ├─regulationsManage
│  │      │  └─components
│  │      ├─roleManage
│  │      │  └─components
│  │      ├─userManage
│  │      │  └─components
│  │      └─warningTimeSetting
│  │          └─components
│  ├─home			—————————————————————————————————————————————————————— 项目一级菜单
│  ├─login			—————————————————————————————————————————————————————— 项目一级菜单
│  └─personal		—————————————————————————————————————————————————————— 项目一级菜单
│      ├─components	
│      └─pages
│          ├─individual-plate
│          │  └─components
│          ├─my-browsing
│          │  └─components
│          ├─my-collection
│          │  └─components
│          ├─my-dynamics
│          │  └─components
│          ├─my-push
│          │  └─components
│          └─personal-info
│              └─components
├─router		—————————————————————————————————————————————————————— 路由配置文件
└─store			—————————————————————————————————————————————————————— vuex状态管理器
    ├─actions
    ├─getters
    ├─mutations
    └─state
```