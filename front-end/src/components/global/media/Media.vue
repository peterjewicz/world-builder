<template>
  <div v-if="isactive">
    <h2>Media</h2>
    <div class="media-wrapper">
      <input type="file" @change="onFileChanged">
    </div>
  </div>
</template>

<script>
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
