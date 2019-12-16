<template>
  <div class="MyStories">
    <Dropdown :text="dropdownText" :color="dropdownColor" :active="dropdownActive" @hideDropdown="hideDropdown"/>
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <h1>My Stories</h1>
    <input v-model="storyName" />
    <button v-on:click="addStory" class="button primary">Add Story</button>
    <template v-if="getStories.length > 0">
      <div class="entity" v-for="stories in getStories">
        <h5>Name: <span v-html="stories.name">{{stories.name}}</span></h5>
        <router-link v-bind:to="`/storyBuilder/${stories._id}`"><button class="primary">View</button></router-link>
      </div>
    </template>
  </div>
</template>

<script>
import Header from '../pages/includes/Header';
import Dropdown from '../global/Dropdown';
import store from '../../store/store.js';

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'MyStories',
  components: {
    Header,
    Dropdown
  },
  data () {
    return {
      storyName: '',

      errorMessage: '',
      dropdownActive: false,
      dropdownText: '',
      dropdownColor: ''
    }
  },
  mounted() {

  },
  computed: {
    getStories() {
      const storeSegment = this.$store.getters.getValues.stories;
      return storeSegment;
    }
  },
  methods: {
    addStory() {
      const worldId = this.$store.getters.getCurrentWorld;
      axios({
        url: api + `/create-story/${worldId}`,
        method: 'post',
        data: {
          name: this.storyName},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.dropdownText = 'Your Story Has Been Created!';
        this.dropdownColor = 'green';
        this.dropdownActive = true;

        store.commit('addStory', response.data)
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
<style scoped lang="scss">
</style>
