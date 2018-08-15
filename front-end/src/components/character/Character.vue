<template>
  <div class="Character">
    <Header />
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <h2>Character</h2>
    <p>Create a new character here. Fill in as much or as little data a you want!</p>
    <p class="error">{{errorMessage}}</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-bind:class="{ active: overviewActive }" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="Physical stat-item" v-bind:class="{ active: physicalActive }"  v-on:click="changeActiveScreen('physicalActive')">
        <h4>Physical</h4>
      </div>
      <div class="personaility stat-item" v-bind:class="{ active: personalityActive }" v-on:click="changeActiveScreen('personalityActive')">
        <h4>Personaility</h4>
      </div>
      <div class="social stat-item" v-bind:class="{ active: socialActive }" v-on:click="changeActiveScreen('socialActive')">
        <h4>Social</h4>
      </div>
      <div class="history stat-item" v-bind:class="{ active: historyActive }" v-on:click="changeActiveScreen('historyActive')">
        <h4>History</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <Physical @valueChanged="valuesChanged" v-bind:active="physicalActive"/>
    <Personality @valueChanged="valuesChanged" v-bind:active="personalityActive" />
    <Social v-bind:active="socialActive" />
    <History @valueChanged="valuesChanged" v-bind:active="historyActive" />
    <button class="primary large" v-on:click="addCharacter">Save Character!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview'
import Physical from './physical/Physical'
import Personality from './personality/Personality'
import Social from './social/Social'
import History from '../global/history/History'

import Header from '../pages/includes/Header';
const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Character',
  components: {
    Overview,
    Physical,
    Personality,
    Social,
    History,
    Header

  },
  data () {
    return {
      // TODO change this back when you're done with history
      overviewActive: true,
      physicalActive: false,
      personalityActive: false,
      socialActive: false,
      historyActive: false,

      overviewValues: [],

      completeValues: {
        overview: [],
        physical: [],
        personality: [],
        history: []
      },

      errorMessage: ''

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
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
    },
    addCharacter() {
      const encodedVal = JSON.stringify(this.completeValues);
      const worldId = this.$store.getters.getCurrentWorld
      axios({
        url: api + '/entity',
        method: 'post',
        data: {
          type: 'character',
          values: encodedVal,
          worldId: worldId},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        // TODO we need to handle the response here
        console.log(response)
      }).catch(error => {
        if (error.response.status === 401) {
          this.errorMessage = 'Your login is invalid, please login to continue';
        } else {
          this.errorMessage = 'An unknown error has occured. Please try again or contact support.'
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  @import '../../styles/main';
  .statsWrapper {
    display: flex;
    text-align: center;
    flex-direction: row;

    div {
      flex-grow: 1;
    }

    .stat-item {
      &.active {
        border-bottom: 3px solid $lightBlue
      }
    }
  }
</style>
