// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store.js';

import wysiwyg from 'vue-wysiwyg';
Vue.use(wysiwyg, {hideModules:
  { 'bold': true,
    'italic': true,
    'underline': true,
    'justifyLeft': true,
    'justifyCenter': true,
    'justifyRight': true,
    'headings': true,
    'link': true,
    'code': true,
    'orderedList': true,
    'unorderedList': true,
    'image': true,
    'table': true,
    'removeFormat': true,
    'separator': true }
});

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  store: store
})
