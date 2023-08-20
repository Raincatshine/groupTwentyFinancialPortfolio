import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import ElementUI from 'element-ui'
import * as echarts from 'echarts'
import { Message, MessageBox } from 'element-ui'  //导入element-ui的消息ui
import request from './network/request'


Vue.config.productionTip = false
Vue.prototype.$echarts =echarts //挂载在原型链上
Vue.prototype.$message =Message
Vue.prototype.$comfirm =MessageBox.confirm
Vue.prototype.$request =request //axios实例挂载

Vue.use(ElementUI)

// 全局css样式
import 'normalize.css'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'


new Vue({
  render: h => h(App),
  router,
  store

}).$mount('#app')
