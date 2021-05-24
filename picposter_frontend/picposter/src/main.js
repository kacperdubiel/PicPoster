import Vue from 'vue'
import ProfileView from './ProfileView'
//import StartView from './StartView.vue'
//import BootstrapVue from 'bootstrap-vue'

Vue.config.productionTip = false;
//Vue.use(BootstrapVue)

new Vue({
  render: h => h(ProfileView),
}).$mount('#app')
