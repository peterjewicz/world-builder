<template>
  <div class="Creature">
    <h2>Language</h2>
    <p>Use this to create a new language. Fill out as much or as little as you like.</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="map stat-item" v-on:click="changeActiveScreen('mapActive')">
        <h4>History</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <button class="primary large" v-on:click="addEntity">Save Language!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview'
// import History from '../global/history/History'
const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Language',
  components: {
    Overview
  },
  data () {
    return {

      // TODO Change this back once you're done with the History Studd
      overviewActive: true,
      mapActive: false,

      overviewValues: [],

      completeValues: {
        overview: []
      }

    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.mapActive = false;
      this[val] = true;
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
    },
    addEntity() {
      const encodedVal = JSON.stringify(this.completeValues);
      // TODO remove this
      axios.post(api + '/entity', {
        type: 'language',
        values: encodedVal
      })
        .then((response) => {
          // Holds the token for future logins
          console.log(response)
        })
        .catch((response) => {
          console.log(response);
          this.loginError = true;
        })
        .then(function () {
          // always executed
        });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  .statsWrapper {
    display: flex;
    text-align: center;
    flex-direction: row;

    div {
      flex-grow: 1;
    }
  }
</style>
