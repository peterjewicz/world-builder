<template>
  <div class="Creature">
    <Dropdown :text="dropdownText" :color="dropdownColor" :active="dropdownActive" @hideDropdown="hideDropdown"/>
    <Header />
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <h2>Item</h2>
    <p>Use this to create a new item. Fill out as much or as little as you like.</p>
    <div class="statsWrapper">
      <div class="overview stat-item" v-on:click="changeActiveScreen('overviewActive')">
        <h4>Overview</h4>
      </div>
      <div class="map stat-item" v-on:click="changeActiveScreen('mapActive')">
        <h4>History</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <button class="primary large" v-on:click="addEntity">Save Item!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview';
import Dropdown from '../global/Dropdown';
import Header from '../pages/includes/Header';
// import History from '../global/history/History'
const axios = require('axios');
const api = process.env.API;

export default {

  name: 'Item',
  components: {
    Overview,
    Dropdown,
    Header
  },
  data () {
    return {

      // TODO Change this back once you're done with the History Studd
      overviewActive: true,
      mapActive: false,

      overviewValues: [],

      completeValues: {
        overview: []
      },

      currentId: '',
      dropdownActive: false,
      dropdownText: '',
      dropdownColor: ''

    }
  },
  mounted() {
    if (this.$route.params.id) {
      const values = this.$store.getters.getValues;
      const currentItem = values.item.filter((city) => {
        if (city._id === this.$route.params.id) {
          return true;
        }
      });

      this.currentId = currentItem[0]._id;

      this.overviewValues = currentItem[0].value.overview;
      this.completeValues.overview = {...currentItem[0].value.overview};
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
      const worldId = this.$store.getters.getCurrentWorld;
      axios({
        url: api + '/entity',
        method: 'post',
        data: {
          type: 'item',
          values: encodedVal,
          worldId: worldId,
          currentId: this.currentId},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.dropdownText = 'Your Item Has Been Added!';
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
