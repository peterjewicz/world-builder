<template>
  <div class="MyStories">
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <h1>My Stories</h1>
    <input v-model="storyName" />
    <button v-on:click="addStory" class="button primary">Add Story</button>
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
    Header
  },
  data () {
    return {
      storyName: ""
    }
  },
  mounted() {

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

        console.log(response.data)
      }).catch(error => {
        if (error.response.status === 401) {
          this.dropdownText = 'Your login is invalid, please login to continue';
        } else {
          this.dropdownText = 'An unknown error has occured. Please try again or contact support.'
        }
        this.dropdownColor = 'red';
        this.dropdownActive = true;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">


</style>
