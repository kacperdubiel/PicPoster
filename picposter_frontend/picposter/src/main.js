import Vue from 'vue'
// import StartView from './StartView.vue'
import PostView from './PostView.vue'
import BootstrapVue from 'bootstrap-vue'

Vue.config.productionTip = false;
Vue.use(BootstrapVue)

new Vue({
  render: h => h(PostView),
}).$mount('#app')
