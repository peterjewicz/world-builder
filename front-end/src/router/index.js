import Vue from 'vue';
import store from '../store/store.js';

import Router from 'vue-router';
import Home from '@/components/Home';
import Settings from '@/components/pages/Settings';
import Login from '@/components/Login';
import Create from '@/components/user/Create';
import Dashboard from '@/components/pages/Dashboard';
import ViewAll from '@/components/pages/ViewAll';
// import View from '@/components/pages/View';
import NewWorld from '@/components/NewWorld';

// Import all of your entity components here
import Character from '@/components/character/Character';
import View from '@/components/pages/View';
import Location from '@/components/location/Location';
import Region from '@/components/region/Region';
import City from '@/components/city/City';
import Creature from '@/components/creature/Creature';
import PointOfInterest from '@/components/pointOfInterest/PointOfInterest';
import Religion from '@/components/religion/Religion';
import Language from '@/components/language/Language';
import Spell from '@/components/spell/Spell';
import Item from '@/components/item/Item';
import Planet from '@/components/planet/Planet';
import Technology from '@/components/technology/Technology';

import MyStories from '@/components/StoryBuilder/MyStories'
import StoryBuilder from '@/components/StoryBuilder/StoryBuilder'

import Privacy from '@/components/pages/frontend/Privacy'
import Terms from '@/components/pages/frontend/Terms'

// Bring in all the required liraries and extras
const axios = require('axios');
const api = process.env.API;

Vue.use(Router)

const handleSubpageLogAndData = (to, from, next) => {
  if (localStorage.getItem('token')) {
    console.log(store.getters.getCurrentWorld)
    if (store.getters.getCurrentWorld) {
      next();
    } else {
      next({ path: '/dashboard' })
    }
  } else {
    next({ path: 'login' })
  }
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/privacy',
      name: 'Privacy',
      component: Privacy
    },
    {
      path: '/terms',
      name: 'Terms',
      component: Terms
    },
    {
      path: '/pricing', // still a homepage route
      name: 'Home',
      component: Home
    },
    {
      path: '/contact', // still a homepage route
      name: 'Home',
      component: Home
    },
    {
      path: '/settings',
      name: 'Settings',
      component: Settings
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/create-world',
      name: 'createWorld',
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
      path: '/view/:entity?/:id?',
      name: 'View',
      component: View,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/character/:id?',
      name: 'Create-Character',
      component: Character,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/location/:id?',
      name: 'Create-Location',
      component: Location,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/region/:id?',
      name: 'Create-Region',
      component: Region,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/city/:id?',
      name: 'Create-City',
      component: City,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/pointOfInterest/:id?',
      name: 'Create-PointOfInterst',
      component: PointOfInterest,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/creature/:id?',
      name: 'Create-Creature',
      component: Creature,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/religion/:id?',
      name: 'Create-Religion',
      component: Religion,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/language/:id?',
      name: 'Create-Language',
      component: Language,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/spell/:id?',
      name: 'Create-Spell',
      component: Spell,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/item/:id?',
      name: 'Create-Item',
      component: Item,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/planet/:id?',
      name: 'Create-Planet',
      component: Planet,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/new/technology/:id?',
      name: 'Create-Technology',
      component: Technology,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/storyBuilder/:id?',
      name: 'Story-Builder',
      component: StoryBuilder,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/myStories',
      name: 'My-Stories',
      component: MyStories,
      beforeEnter: handleSubpageLogAndData
    },
    {
      path: '/all/:entity',
      name: 'View-Entity',
      component: ViewAll,
      beforeEnter: handleSubpageLogAndData
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
              next({ path: 'create-world' })
            } else {
              store.commit('saveWorlds', response.data.body)
              next();
            }
          })
          .catch(() => {
            // redirect to login
            next({ path: 'login' })
          })
      }
    }
  ],
  scrollBehavior(to, from) {
    return {x: 0, y: 0}
  }
})
