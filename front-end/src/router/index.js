import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Create from '@/components/user/Create'
import Dashboard from '@/components/pages/Dashboard'

// Import all of your create components here
import Character from '@/components/character/Character'

// Bring in all the required liraries and extras
const axios = require('axios');
const api = process.env.API;

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/create',
      name: 'Create',
      component: Create
    },
    {
      path: '/create-character',
      name: 'Create-Character',
      component: Character
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      beforeEnter: (to, from, next) => {
        const id = '10';
        axios.get(api + '/' + id + '/worlds/')
          .then((response) => {
            if (response.data === '') {
              next({ path: 'login' })
            } else {
              next();
            }
          })
          .catch((error) => {
            // redirect to login
            console.log(error)
          })
      }
    }
  ]
})
