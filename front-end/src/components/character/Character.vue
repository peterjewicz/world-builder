<template>
  <div class="Character">
    <h2>Character</h2>
    <p>Create a new character here. Fill in as much or as little data a you want!</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="background stat-item" v-on:click="changeActiveScreen('backgroundActive')">
        <h4>Background</h4>
      </div>
      <div class="personaility stat-item" v-on:click="changeActiveScreen('personalityActive')">
        <h4>Personaility</h4>
      </div>
      <div class="social stat-item" v-on:click="changeActiveScreen('socialActive')">
        <h4>Social</h4>
      </div>
      <div class="history stat-item">
        <h4>History</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <Background  v-bind:active="backgroundActive"/>
    <Personality @valueChanged="valuesChanged" v-bind:active="personalityActive" />
    <Social v-bind:active="socialActive" />
    <button v-on:click="addCharacter">Add!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview'
import Background from './background/Background'
import Personality from './personality/Personality'
import Social from './social/Social'
const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Character',
  components: {
    Overview,
    Background,
    Personality,
    Social

  },
  data () {
    return {
      overviewActive: true,
      backgroundActive: false,
      personalityActive: false,
      socialActive: false,

      overviewValues: [],

      completeValues: {
        overview: [],
        background: [],
        personality: [],
        social: []
      }

    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.backgroundActive = false;
      this.physicalActive = false;
      this[val] = true;
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
      console.log(this.completeValues)
    },
    addCharacter() {
      const encodedVal = JSON.stringify(this.completeValues);
      console.log(encodedVal);
      return;
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
