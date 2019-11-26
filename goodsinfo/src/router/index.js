import Vue from 'vue'
import Router from 'vue-router'
import main from '@/components/main'
import detail from '@/components/detail'
import login from '@/components/login'
import loginin from '@/components/loginin'
import cart from '@/components/cart'
import makeorder from '@/components/makeorder'
import order from '@/components/order'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'main',
      component: main
    },
     {
      path: '/detail',
      name: 'detail',
      component: detail
    },
    {
      path: '/login',
      name: 'login',
      component:login
    },
    {
      path: '/loginin',
      name: 'loginin',
      component:loginin
    },
    {
      path: '/cart',
      name: 'cart',
      component:cart
    },
    {
      path: '/makeorder',
      name: 'makeorder',
      component:makeorder
    },
    {
      path: '/order',
      name: 'order',
      component:order
    },
  ]
})