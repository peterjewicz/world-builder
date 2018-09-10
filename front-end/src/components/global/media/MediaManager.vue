<template>
  <div class="MediaManager">
    <h2>Media Manager</h2>
    <div class="media-wrapper">
      <div class="currentMedia">
        <div v-for="image in userImages">
          <img v-bind:src="`https://s3.amazonaws.com/worldbuilder-twc/${image.key}`" width="100px"/>
        </div>
      </div>
      <div class="newMedia">
        <input type="file" @change="onFileChanged">
      </div>
    </div>
  </div>
</template>

<script>
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Media',
  components: {
  },
  props: ['active'],
  data () {
    return {
      selectedFile: null,
      userImages: []
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  created() {

  },
  mounted() {
    let currentWorld = this.$store.getters.getCurrentWorld;
    axios({
      url: api + '/worlds/' + currentWorld + '/images',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      console.log(response.data.body['object-summaries']);
      this.userImages = response.data.body['object-summaries']
    })
  },
  methods: {
    onFileChanged (event) {
      this.selectedFile = event.target.files[0];
      this._emitValues();
    },
    _emitValues() {
      const data = {
        title: 'media',
        values: this.selectedFile
      }
      this.$emit('valueChanged', data)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .MediaManager {
    position: absolute;
    top: 0;
    left: 0;
    background: white;
    width: 75%;
    margin: 0 auto;
  }
</style>
