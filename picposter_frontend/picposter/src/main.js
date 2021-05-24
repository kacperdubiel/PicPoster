import Vue from 'vue'
import StartView from './StartView.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(StartView),
}).$mount('#app')
