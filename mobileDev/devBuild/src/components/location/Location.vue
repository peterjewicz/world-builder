<template>
  <div class="Location">
    <h2>Location</h2>
    <p>Create a new Location here. Fill in as much or as little data a you want!</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="history stat-item" v-on:click="changeActiveScreen('historyActive')">
        <h4>History</h4>
      </div>
      <div class="media stat-item" v-on:click="changeActiveScreen('mediaActive')">
        <h4>Media</h4>
      </div>
    </div>

    <button class="primary large saveButton" v-on:click="addLocation">Save Location!</button>
  </div>
</template>

<script>
import History from '../global/history/History';
import Media from '../global/media/Media';

const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Location',
  components: {
    History,
    Media
  },
  data () {
    return {

      // TODO Change this back once you're done with the History Studd
      overviewActive: false,
      historyActive: true,
      mediaActive: false,

      overviewValues: {},
      historyValues: {},
      mediaValue: '',

      completeValues: {
        overview: [],
        physical: [],
        personality: [],
        social: [],
        history: [],
        media: ''
      }

    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.physicalActive = false;
      this.personalityActive = false;
      this.socialActive = false;
      this.historyActive = false;
      this[val] = true;
      console.log(this.historyActive);
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
    },
    addLocation() {
      const encodedVal = JSON.stringify(this.completeValues);

      axios.post(api + '/entity', {
        type: 'character',
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
