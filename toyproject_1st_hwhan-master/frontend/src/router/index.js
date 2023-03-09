import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'
import JoinView from '../views/JoinView.vue'
import JoinAuthView from '../views/JoinAuthView.vue'
import EmailAuthView from '../views/EmailAuthView.vue'
import BoardView from '../views/BoardView.vue'
import BoardWriteView from '../views/BoardWriteView.vue'

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
    path: '/board',
    name: 'BoardView',
    component: BoardView
  },
  {
    path: '/board/write',
    name: 'BoardWriteView',
    component: BoardWriteView
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
