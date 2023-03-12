import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'
import JoinView from '../views/JoinView.vue'
import JoinAuthView from '../views/JoinAuthView.vue'
import EmailAuthView from '../views/EmailAuthView.vue'
import BoardView from '../views/BoardView.vue'
import BoardDetailView from '../views/BoardDetailView.vue'
import BoardWriteView from '../views/BoardWriteView.vue'
import TestView from '../views/TestView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainView',
    component: MainView
  },
  {
    path: '/join',
    name: 'JoinView',
    component: JoinView
  },
  {
    path: '/joinAuth',
    name: 'JoinAuthView',
    component: JoinAuthView
  },
  {
    path: '/emailAuth/:random/:email',
    name: 'EmailAuthView',
    component: EmailAuthView
  },
  {
    path: '/board/:category',
    name: 'BoardView',
    component: BoardView
  },
  {
    path: '/board/detail/:category',
    name: 'BoardDetailView',
    component: BoardDetailView
  },
  {
    path: '/b/write',
    name: 'BoardWriteView',
    component: BoardWriteView
  },
  {
    path: '/test/:category',
    name: 'TestView',
    component: TestView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
