<template>
  <div class="Character">
    <Dropdown :text="dropdownText" :color="dropdownColor" :active="dropdownActive" @hideDropdown="hideDropdown"/>
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
      <div class="media stat-item" v-bind:class="{ active: mediaActive }" v-on:click="changeActiveScreen('mediaActive')">
        <h4>Media</h4>
      </div>
    </div>
    <Overview @valueChanged="valuesChanged" v-bind:values="this.overviewValues" v-bind:active="overviewActive"/>
    <Physical @valueChanged="valuesChanged" v-bind:values="this.physicalValues" v-bind:active="physicalActive"/>
    <Personality @valueChanged="valuesChanged" v-bind:active="personalityActive" />
    <Social v-bind:active="socialActive" />
    <History @valueChanged="valuesChanged" v-bind:values="this.historyValues" v-bind:active="historyActive" />
    <Media @valueChanged="valuesChanged" v-bind:active="mediaActive" />
    <button class="primary large" v-on:click="addCharacter">Save Character!</button>
  </div>
</template>

<script>
import Overview from './overview/Overview';
import Physical from './physical/Physical';
import Personality from './personality/Personality';
import Social from './social/Social';
import History from '../global/history/History';
import Media from '../global/media/Media';
import Dropdown from '../global/Dropdown';

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
    Media,
    Header,
    Dropdown

  },
  data () {
    return {
      // TODO change this back when you're done with history
      overviewActive: true,
      physicalActive: false,
      personalityActive: false,
      socialActive: false,
      historyActive: false,
      mediaActive: false,

      // This set of values is for edit functionality
      // They will hold the current value in the DB
      // and then pass it down to the specific child
      overviewValues: {},
      physicalValues: {},
      historyValues: {},

      completeValues: {
        overview: [],
        physical: [],
        personality: [],
        history: [],
        media: ''
      },

      currentId: '',
      errorMessage: '',
      dropdownActive: false,
      dropdownText: '',
      dropdownColor: ''

    }
  },
  mounted() {
    if (this.$route.params.id) {
      const values = this.$store.getters.getValues;
      const currentChar = values.character.filter((char) => {
        if (char._id === this.$route.params.id) {
          return true;
        }
      });

      this.currentId = currentChar[0]._id;

      this.overviewValues = currentChar[0].value.overview;
      this.completeValues.overview = {...currentChar[0].value.overview};

      this.physicalValues = currentChar[0].value.physical;
      this.completeValues.physical = {...currentChar[0].value.physical};

      this.historyValues = currentChar[0].value.history;
      this.completeValues.history = {...currentChar[0].value.physical};
    }
  },
  methods: {
    changeActiveScreen(val) {
      this.overviewActive = false;
      this.physicalActive = false;
      this.personalityActive = false;
      this.socialActive = false;
      this.historyActive = false;
      this.mediaActive = false;
      this[val] = true;
    },
    valuesChanged(e) {
      this.completeValues[e.title] = e.values;
    },
    addCharacter() {
      // It's possible to save without an image
      if (this.completeValues.media) {
        this.uploadImage(this.completeValues['media']);

        // we only want to save the name of the file being used here. as we know the pattern and location of it
        this.completeValues['media'] = this.completeValues['media'].name;
      }
      const encodedVal = JSON.stringify(this.completeValues);
      const worldId = this.$store.getters.getCurrentWorld;
      axios({
        url: api + '/entity',
        method: 'post',
        data: {
          type: 'character',
          values: encodedVal,
          worldId: worldId,
          currentId: this.currentId},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.dropdownText = 'Your Character Has Been Added!';
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
    uploadImage(selectedFile) {
      const formData = new FormData()

      formData.append('myFile', selectedFile, selectedFile.name)
      formData.append('worldId', this.$store.getters.getCurrentWorld);
      console.log(formData)
      axios.post('http://localhost:3000/api/uploads', formData)
        .then(response => {
          console.log(response)
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
