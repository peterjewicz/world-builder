<template>
  <div class="MyStories">
    <Dropdown :text="dropdownText" :color="dropdownColor" :active="dropdownActive" @hideDropdown="hideDropdown"/>
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <h1>My Stories</h1>
    <input placeholder="Story Name" v-model="storyName" />
    <button v-on:click="addStory" class="button primary">Add Story</button>
    <div class="entityWrapper">
      <template v-if="getStories.length > 0">
        <div class="entity" v-for="stories in getStories">
          <h5>Name: <span v-html="stories.name">{{stories.name}}</span></h5>
          <router-link v-bind:to="`/storyBuilder/${stories._id}`"><button class="primary">View</button></router-link>
        </div>
      </template>
    </div>
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

  .MyStories {

    .entityWrapper {
      display: flex;
      flex-wrap: wrap;
    }
    .entity {
      flex-basis: 30%;
      box-sizing: border-box;
      text-align: left;
      padding: 10px;
      margin: 10px auto;
      border: 1px solid black;
      border: 1px solid #dedede;
      padding-top: 0;
      margin: 40px auto;
      background-color: white;
      border-radius: 3px;
      position: relative;
      -webkit-box-shadow: 2px 2px 1px #d6dee3;
      box-shadow: 2px 2px 1px #d6dee3;
    }
  }
</style>
