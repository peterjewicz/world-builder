<template>
  <div v-if="isactive">
    <h2>Media</h2>
    <div class="media-wrapper">
      <input type="file" @change="onFileChanged">
      <button @click="onUpload">Upload!</button>
    </div>
  </div>
</template>

<script>
const axios = require('axios');
export default {
  name: 'Media',
  components: {
  },
  props: ['active'],
  data () {
    return {
      selectedFile: null
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
  methods: {
    onFileChanged (event) {
      this.selectedFile = event.target.files[0];
      this._emitValues();
    },
    onUpload() {
      const formData = new FormData()
      console.log(this.selectedFile)
      formData.append('myFile', this.selectedFile, this.selectedFile.name)
      formData.append('worldId', this.$store.getters.getCurrentWorld);
      console.log(formData)
      axios.post('http://localhost:3000/api/uploads', formData)
      .then(response => {
        console.log(response)
      })
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


</style>
