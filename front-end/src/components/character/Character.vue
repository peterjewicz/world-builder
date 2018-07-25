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
    <Overview v-bind:active="overviewActive"/>
    <Background v-bind:active="backgroundActive"/>
    <Personality v-bind:active="personalityActive" />
    <Social v-bind:active="socialActive" />
  </div>
</template>

<script>

import Overview from './overview/Overview'
import Background from './background/Background'
import Personality from './personality/Personality'
import Social from './social/Social'

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
      socialActive: false

    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.backgroundActive = false;
      this.physicalActive = false;
      this[val] = true;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">

  .Character {
    .row {
      display: flex;
      max-width: 620px;
      margin: 0 auto;
      justify-content: center;

      .field-details {
        text-align: left;
        h4 {margin-bottom: 0px;}
        p {margin-top: 0px;}
        width: 50%;
      }
      .field-content {
        display: flex;
        justify-content: center;
        flex-direction: column;
        width: 50%;

        input {
          height: 15px;
        }
      }
    }
  }
  .statsWrapper {
    display: flex;
    text-align: center;
    flex-direction: row;

    div {
      flex-grow: 1;
    }
  }
</style>
