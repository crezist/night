import Vue from 'vue'
import Router from 'vue-router'
import main from '@/components/main'
import detail from '@/components/detail'
import login from '@/components/login'
import loginin from '@/components/loginin'
import cart from '@/components/cart'
import makeorder from '@/components/makeorder'
import order from '@/components/order'
<<<<<<< HEAD
import myshop from '@/components/myshop'
import toinsertstoreuser from '@/components/toinsertstoreuser'
=======
import goodscollection from '@/components/goodscollection'


>>>>>>> 928cfc6090bd0e3f3b402294d4bd27b5b0111eee
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
    {
<<<<<<< HEAD
       path: '/toinsertstoreuser',
       name: 'toinsertstoreuser',
       component: toinsertstoreuser
     },
     {
        path: '/myshop',
        name: 'myshop',
        component: myshop
      },
=======
      path: '/goodscollection',
      name: 'goodscollection',
      component:goodscollection
    },
>>>>>>> 928cfc6090bd0e3f3b402294d4bd27b5b0111eee
  ]
})
