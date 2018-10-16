<template>
  <div class="Planet">
    <Dropdown :text="dropdownText" :color="dropdownColor" :active="dropdownActive" @hideDropdown="hideDropdown"/>
    <Header homeActive="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <h2>Planet</h2>
    <p>Use this to create a planet for your universe. Fill out as much or as little as you like.</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-bind:class="{ active: overviewActive }" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="map stat-item" v-bind:class="{ active: historyActive }" v-on:click="changeActiveScreen('historyActive')">
        <h4>History</h4>
      </div>
      <div class="media stat-item" v-bind:class="{ active: mediaActive }" v-on:click="changeActiveScreen('mediaActive')">
        <h4>Media</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <History @valueChanged="valuesChanged" v-bind:values="this.historyValues" v-bind:active="historyActive" />
    <Media @valueChanged="valuesChanged" v-bind:values="this.mediaValue" v-bind:active="mediaActive"/>
    <button class="primary large" v-on:click="addEntity">Save Spell!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview';
import Dropdown from '../global/Dropdown';
import Header from '../pages/includes/Header';
import History from '../global/history/History';
import Media from '../global/media/Media';
import {reloadToDashboard} from '../../scripts/reload';

const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Planet',
  components: {
    Overview,
    Dropdown,
    Header,
    History,
    Media
  },
  data () {
    return {

      // TODO Change this back once you're done with the History Studd
      overviewActive: true,
      mediaActive: false,
      historyActive: false,

      overviewValues: {},
      historyValues: {},
      mediaValue: '',

      completeValues: {
        overview: [],
        history: [],
        media: ''
      },

      currentId: '',
      dropdownActive: false,
      dropdownText: '',
      dropdownColor: ''

    }
  },
  mounted() {
    reloadToDashboard(this)
    
    if (this.$route.params.id) {
      const values = this.$store.getters.getValues;
      const currentPlanet = values.planet.filter((planet) => {
        if (planet._id === this.$route.params.id) {
          return true;
        }
      });

      this.currentId = currentPlanet[0]._id;

      this.overviewValues = currentPlanet[0].value.overview;
      this.completeValues.overview = {...currentPlanet[0].value.overview};

      this.historyValues = currentPlanet[0].value.history;
      this.completeValues.history = {...currentPlanet[0].value.physical};

      this.mediaValue = currentPlanet[0].value.media;
      this.completeValues.media = this.mediaValue;
    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.historyActive = false;
      this.mediaActive = false;
      this[val] = true;
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
    },
    addEntity() {
      const encodedVal = JSON.stringify(this.completeValues);
      const worldId = this.$store.getters.getCurrentWorld;
      axios({
        url: api + `/entity/${worldId}`,
        method: 'post',
        data: {
          type: 'planet',
          values: encodedVal,
          currentId: this.currentId},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.dropdownText = 'Your Planet Has Been Added!';
        this.dropdownColor = 'green';
        this.dropdownActive = true;

        if (response.data !== 'Entity Updated') {
          this.currentId = response.data._id;
        }
      }).catch(error => {
        if (error.response.status === 401) {
          this.dropdownText = 'Your login is invalid, please login to continue';
        } else {
          this.dropdownText = 'An unknown error has occured. Please try again or contact support.'
        }
        this.dropdownColor = 'red';
        this.dropdownActive = true;
      })
    },
    hideDropdown() {
      this.dropdownActive = false;
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
