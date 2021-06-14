import Vue from 'vue'
import VueRouter from 'vue-router'
import StartView from '../views/StartView.vue'
import ProfileView from '../views/ProfileView.vue'
import WallView from '../views/WallView.vue'
import PostView from '../views/PostView.vue'
import AddPostView from '../views/AddPostView.vue'
import AddProfileImageView from '../views/AddProfileImageView.vue'
import NotFoundView from '../views/NotFoundView.vue'
import SearchView from '../views/SearchView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'StartView',
    component: StartView
  },
  {
    path: '/wall',
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
    path: '/search/:prefix',
    name: 'SearchView',
    component: SearchView
  },
  {
    path: '/addpost',
    name: 'AddPostView',
    component: AddPostView
  },
  {
    path: '/addprofileimage',
    name: 'AddProfileImageView',
    component: AddProfileImageView
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
  routes,
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve(savedPosition)
        }, 450)
      })
    } else {
      return { x: 0, y: 0 }
    }
  }
})

export default router
