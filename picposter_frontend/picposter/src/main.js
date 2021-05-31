import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//import axios from 'axios'
//axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')


Vue.config.productionTip = false;
Vue.use(router)
Vue.use(store)

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')
