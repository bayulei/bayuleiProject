# laws_system_front_pc
## 项目基本机构

```
├─assets			—————————————————————————————————————————————————— 静态资源
│  ├─images
│  │  ├─home
│  │  └─login
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

## 注意:
+ 1 项目引用组件时，会自动识别文件夹下的index.js / index.vue 例如引入src/pages/home组件 import Home from '@/pages/home' 即可引入, home下的index.vue则为home组件
+ 2 如果没有index.vue 则需要具体的组件名， 比如组件名是Home.vue 则 import Home from '@/pages/home/Home' .vue/.js 可省略
+ 3 '@' 符号代表src，代表src的别名，别名（alias）可在webpack.base.config.js中配置


### vue快速使用

#### .vue文件结构

```
<template>
 <div class="example"> // -----> template外层需要有一个div包裹元素
	<input type="text" v-model="username">
	
	<input type="checkbox" v-model="checkedName" value="tom">tom</checkbox>
	<input type="checkbox" v-model="checkedName" value="jerry">jerry</checkbox>
	<input type="checkbox" v-model="checkedName" value="john">john</checkbox>
	
	<span>{{ checkedName }}</span> 此时选中tom jerrry checkName就是 ['tom','jerry']
	
	<select v-model="sex">
	<option value="1">男</option>
	<option value="2">女</option>
	</select>
	
	// 子组件解释看下面
	<com-header :giveChild="username" @fromChild="process"> 我是com-header组件插槽的内容(vue子组件，可以直接使用，也可以在中间插入东西,具体解释看下面子组件的介绍) </com-header> // 在components里写ComHeader的行为叫作注册组件 注册后才能使用 否则控制台会警告 did you register component <com-header> .... ?
 </div>
<template>  
<script>
import ComHeader from '@/pages/components/Header' // --------------> 引入组件(引入要在script顶部,import引入的模块不能用h5标签，如header,footer,可使用别名代替)
export default {
	name: 'example',
	data () {
		return {
			username: '',
			sex: '1',
			checkedName:''
		}
	},
	methods: {
		fn () {
			console.log('方法被执行')
		}
	},
	components: {
		// 引入的组件 import 后的模块名是啥 这里就写啥
		ComHeader,
		xxx,
		xxx
	},
	props: {
		// 解释在下面
	},
	computed: {
		// 解释在下面
	},
	watch: {
		// 解释在下面
	},
	// --------------------> vue 生命周期函数(组件被挂载，初始化方法什么的可以在这个方法里调用)
	mounted () {
		this.fn() //--------------> 调用方法或者data中的属性 可以通过 this调用 this指向vue实例
		let _this = this // ---------------> jquery里面调用this this会发生改变 在外层用一个变量去作为vue this的别名
		$('select').change(function(){
			console.log(this.username) // --------------> 注意: 这里的this指向的是.change前的对象 也就是$('select')
			console.log(_this.user) // ------------> username的内容
		})
	}
}
</script>
```

> #### v-model(双向数据绑定): 
> 在这里的输入框内容改变时data里的username会同时更改,同理通过js给修改data里的username，输入框内的数据也会改变
> 
> select也可以使用v-model进行数据绑定,选男后data里的sex就是1,选女后sex就是2
> 

> #### @click(点击事件)
```
  <input type="button" value="按钮" @click="fn" /> // ----> 即可调用methods里的fn方法 @click="fn(123)"
  methods: {
	  fn (value) {
		  console.log(value)
	  }
  }
```

> #### props(父组件向子组件通信)
> #### $emit(子组件向父组件通信)
>
```
	例: Header.vue组件
	<template>
		<div class="header">
			{{ giveChild }} ---> 输出父组件的username
			
			<input type="button" @click="send" value="子组件向父组件通信" />
		</div>
	</template>
	<script>
		export default {
			name: 'template',
			data () {
				return {
					
				}
			},
			methods: {
				send () {
					this.$emit('fromChild', '任意数据或不传')
				}
			},
			components: {},
			props: {
				giveChild: String ---> 这里可以定义一个类型(规范)
			},
			mounted: {
				
			}
		}
	</script>
	
	父组件里:
	<com-header :giveChild="username" @fromChild="process"></com-header> ---> giveChild是随便起的一个名字 username是父组件data中的属性
	methods: {
		process (childData) {
			这里的childData就是子组件传来的东西， 也可以不传 不写childData
			console.log('子组件发来消息啦')
		}
	}
``
> #### computed(计算属性)
``` 
	data () {
		return {
			a: 1,
			b: 2
		}
	}
	computed: {
		c () {
			return a + b // ----> computed里的值作为一个方法 需要return 返回
		}
	}
```

> #### watch(监视者)
```
	data () {
		return {
			a: 1,
			b: 2
		}
	},
	computed: {
		c () {
			return a + b
		}
	},
	watch: {
		// 方法1
		c (newVal,oldVal) {
			console.log('c变化了') // watch 不会立刻触发，只有c的值改变了，才会触发 比如 this.a = 5 这时候computed会重新计算 c的值发生改变 才会触发watch
		}
		// 方法2 (深度监听:用于监听一个对象，例如data里 user: { name: 'tom' }),改变user.name 上面那样写监听不会触发
		c: {
			deep: true,
			handler (newVal,oldVal) {}
		}	
	}
```


> #### :class(动态绑定class)
```
	<template>
		<div class="template">
			<ul>
				<li v-for="(item,index) in navList" 
				:key="index"
				@click="open(item.name)"
				:class="{ '你要加的class(例如给当前激活的导航加一个class)': active === item.name   }">{{ item.name }}</li> // :后面是一个true或者false的判断
			</ul>
		</div>
	</template>
	
	data () {
		return {
			active: 'UserCenter'
			navList: [{
				name: 'UserCenter',
				roleId: 100
			}, {
				name: 'SystemConfig',
				roleId: '101'
			}]
		}
	},
	methods: {
		open (routerName) {
			this.$router.push({
				name: routerName
			})
		}
	}
``

> #### v-if v-else-if v-else 和 java的 c:if 差不多
> #### v-if可以触发数据的重新渲染， 比如某个数组有数据后再加载 v-if不成立的时候 元素不会出现在dom结构中
```
	<template>
		<div class="template">
			<ul>
				<li v-for="(item, index) in navList" :key="index">{{ item.name }}</li>
			</ul>
			
			<div v-if="navList"> // navList为空 则v-if返回false 这个div不显示
				当前的路由name是 {{ active }}
			</div>
		</div>
	</template>
	
	data () {
		return {
			active: 'UserCenter'
			navList: []
		}
	},
	methods: {
		open (routerName) {
			this.$router.push({
				name: routerName
			})
		}
	}
``

> #### v-show (和v-if显示效果相同 只是v-show不成立的时候 元素会出现在dom结构中 display:none)


> #### router(路由配置)
> ##### 每新建一个页面 都需要对router下的index.js进行配置

import Home from '@/pages/home'
const routes = [
	{
		path: '/路径', -------------------> 访问该组件使用的url
		name: '路由的名称', --------------> 作为路由唯一的标识 这里最好用英文
		// component: Home, ----------------> 路由的普通使用,需要在上面引入
		component: resolve => require(['@/pages/home'], resolve), ----------> 路由的懒加载模式，可以大大提升渲染效率 推荐使用
		meta: {
			title: '名称', -----------> 组件的中文名称，可以不填，用于做页面的当前位置标识 例如 首页 --- 用户管理 --- 新增用户
			requireAuth: true -------> 结合vuex判断用户是否登录 做路由拦截 如果没有登录直接访问这个地址 则拒绝
		},
		children: [{
			// 和上面一样，为子路由，使用场景：左侧是导航 右侧是一个视图窗口 父组件是Home组件 只切换右侧的视图窗口
		}]
	}
]

> 路由跳转 <router-link to="路由的地址">跳转</router-link> 动态绑定的都用 :to :title 和 a href差不多
> <router-link tag="div"></router-link> router-link默认渲染为a标签 tag可以改变渲染
> js路由跳转 
> this.$router.push(路由的path/ { path: '路由的path', params: { name: 'tom' }}) 
> this.$router.push({ name:'路由的名称',query:{ name:'tom' } })
> 
> 路由传参的params需要在routes中配置
{
	path: '/user/:name' query不用配置
}
在页面里使用路由传来的参数
	this.$route.params
	this.$route.query
 