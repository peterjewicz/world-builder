<template>
  <div class="Media" v-if="isactive">
    <MediaManager v-bind:active="mediaManagerActive" @closeMediaManager="closeMediaManager" @imageSelected="imageSelected"/>
    <h2>Media</h2>
    <div class="media-wrapper">
      <input v-on:click="openMediaManager" type="text" v-model="selectedFile" placeholder="Click To Open Media Manager!">
    </div>
  </div>
</template>

<script>
import MediaManager from './MediaManager';

export default {
  name: 'Media',
  components: {
    MediaManager
  },
  props: ['active', 'values'],
  data () {
    return {
      selectedFile: null,
      mediaManagerActive: false
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.selectedFile = newVal;
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
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this._emitValues();
    },
    imageSelected(e) {
      this.mediaManagerActive = false;
      this.selectedFile = e;
      this._emitValues();
    },
    openMediaManager() {
      this.mediaManagerActive = true;
    },
    closeMediaManager() {
      this.mediaManagerActive = false;
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
  .Media {
    position: relative;
  }
</style>
