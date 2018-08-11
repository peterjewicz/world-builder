import Vue from 'vue';
import store from '../store/store.js';

import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld';
import Login from '@/components/Login';
import Create from '@/components/user/Create';
import Dashboard from '@/components/pages/Dashboard';
import NewWorld from '@/components/NewWorld';

// Import all of your entity components here
import Character from '@/components/character/Character';
import Location from '@/components/location/Location';
import Region from '@/components/region/Region';
import City from '@/components/city/City';
import Creature from '@/components/creature/Creature';
import PointOfInterest from '@/components/pointOfInterest/PointOfInterest';
import Religion from '@/components/religion/Religion';
import Language from '@/components/language/Language';
import Spell from '@/components/spell/Spell';
import Item from '@/components/item/Item';

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
      path: '/new-world',
      name: 'NewWorld',
      component: NewWorld,
      beforeEnter: (to, from, next) => {
        if (localStorage.getItem('token')) {
          next();
        } else {
          next({ path: 'login' })
        }
      }
    },
    {
      path: '/create',
      name: 'Create',
      component: Create
    },
    {
      path: '/new/character',
      name: 'Create-Character',
      component: Character
    },
    {
      path: '/new/location',
      name: 'Create-Location',
      component: Location
    },
    {
      path: '/new/region',
      name: 'Create-Region',
      component: Region
    },
    {
      path: '/new/city',
      name: 'Create-City',
      component: City
    },
    {
      path: '/new/pointOfInterest',
      name: 'Create-PointOfInterst',
      component: PointOfInterest
    },
    {
      path: '/new/creature',
      name: 'Create-Creature',
      component: Creature
    },
    {
      path: '/new/religion',
      name: 'Create-Religion',
      component: Religion
    },
    {
      path: '/new/language',
      name: 'Create-Language',
      component: Language
    },
    {
      path: '/new/spell',
      name: 'Create-Spell',
      component: Spell
    },
    {
      path: '/new/item',
      name: 'Create-item',
      component: Item
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      beforeEnter: (to, from, next) => {
        const id = localStorage.getItem('token');
        axios({
          url: api + '/' + id + '/worlds/',
          method: 'get',
          headers: {'token': localStorage.getItem('token')}
        })
          .then((response) => {
            if (response.data.body === null) {
              next({ path: 'new-world' })
            } else {
              store.commit('saveWorlds', response.data.body)
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
