import store from '../store/store.js';

const axios = require('axios');
const api = process.env.API;

// Handles the reload of the page, should be placed on pages that need to handle reloads correctly
export const reload = (ref) => {
  const currentEntity = ref.$route.params.entity;
  const storeSegment = ref.$store.getters.getValues[currentEntity];
  if (!storeSegment) {
    const id = localStorage.getItem('token');
    axios({
      url: api + '/' + id + '/worlds/',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    })
      .then((response) => {
        if (response.data.body === null) {
        } else {
          store.commit('saveWorlds', response.data.body)
          let currentWorld = ref.$store.getters.getWorlds[0];
          currentWorld = currentWorld._id;

          axios({
            url: api + '/worlds/' + currentWorld + '/entities',
            method: 'get',
            headers: {'token': localStorage.getItem('token')}
          }).then(response => {
            store.commit('saveValue', response.data)
          }).catch(() => {
            console.log("You don't have permission to access that world")
          })
        }
      })
  }
}

// Sends user to the dashboard on the reload
export const reloadToDashboard = (ref) => {
  const values = ref.$store.getters.getValues;
  if (values === '') {
    ref.$router.push('/dashboard');
  }
}
