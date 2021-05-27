import Vue from 'vue'
import ProfileView from './ProfileView'
import StartView from './StartView'
import WallView from './WallView'
import PostView from './PostView'
//import StartView from './StartView.vue'
//import BootstrapVue from 'bootstrap-vue'

const routes = {
  '/': StartView,
  '/profile': ProfileView,
  '/wall' : WallView,
  '/post' : PostView
}

Vue.config.productionTip = false;
//Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  data: {
    currentRoute: window.location.pathname
  },
  computed: {
    ViewComponent () {
      return routes[this.currentRoute]
    }
  },
  render (h) { return h(this.ViewComponent) }
}).$mount('#app')
