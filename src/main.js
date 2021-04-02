import Vue from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/ko'
import 'element-ui/lib/theme-chalk/index.css';
import VueTableDynamic from 'vue-table-dynamic';
import axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import "./resource/table.css"


Vue.use(BootstrapVue)
Vue.use(ElementUI, {locale});
Vue.use(VueTableDynamic);


Vue.config.productionTip = false
Vue.use(axios)
Vue.prototype.$http = axios
// Vue.prototype.$url = 'http://localhost:8080/alienServer'
Vue.prototype.$url = 'http://localhost:8154'


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
