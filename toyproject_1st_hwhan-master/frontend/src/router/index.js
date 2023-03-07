import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'
import JoinView from '../views/JoinView.vue'
import TopicView from '../views/TopicView.vue'

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
    path: '/topic',
    name: 'TopicView',
    component: TopicView
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
