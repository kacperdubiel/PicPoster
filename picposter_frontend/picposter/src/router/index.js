import Vue from 'vue'
import VueRouter from 'vue-router'
import StartView from '../views/StartView.vue'
import ProfileView from '../views/ProfileView.vue'
import WallView from '../views/WallView.vue'
import PostView from '../views/PostView.vue'
import NotFoundView from '../views/NotFoundView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'StartView',
    component: StartView
  },
  {
    path: '/wall/:userId',
    name: 'WallView',
    component: WallView
  },
  {
    path: '/profile/:userId',
    name: 'ProfileView',
    component: ProfileView
  },
  {
    path: '/post/:postId',
    name: 'PostView',
    component: PostView
  },
  {
    path: '/:catchAll(.*)',
    name: 'NotFoundView',
    component: NotFoundView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
