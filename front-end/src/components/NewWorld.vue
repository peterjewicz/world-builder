<template>
  <div class="NewWorld">
    <Header />
      <h2>Start By Creating a New World</h2>
      <p>
        A world is where all of your entites and ideas live. Any characters, locations, or anything
        else that you create lives inside of a world which helps keep you organized!
       </p>
       <input type="text" v-model="worldName" placeholder="Your World's Name!" name="name" />
       <button class="primary large" v-on:click="createWorld" type="submit">Create</button>
  </div>
</template>

<script>

import Header from './global/HeaderFrontend.vue';
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'NewWorld',
  components: {
    Header
  },
  data () {
    return {
      worldName: ''
    }
  },
  methods: {
    createWorld() {
      axios.post(api + '/worlds', {
        name: this.worldName,
        headers: {'token': 'foobar'}
        // send header here with localstorage.getItem('token');
      })
        .then((response) => {
          alert('World Successfully Created!');
        })
        .catch((error) => {
          console.log(error)
          alert('There was an error');
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../styles/main';

</style>
