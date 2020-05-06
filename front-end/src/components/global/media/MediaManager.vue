<template>
  <div v-if="isactive" class="MediaManager" v-bind:class="{ uploadInProgress: uploadInProgress }">
    <div class="loading-overlay">
      <h3>Upload in Progress...</h3>
    </div>
    <div v-on:click="handleCloseMediaManager" class="closeMediaManager">Close <i class="fas fa-times"></i></div>
    <div class="media-wrapper">
      <div class="currentMedia" v-bind:class="{ loadingCurrentMedia: loadingCurrentMedia }">
        <div class="currentMedia-loading">
          <h3>Loading Your Media, Please Wait...</h3>
        </div>
        <h3>Media Manager</h3>
        <p>Select an image to use or upload a new one!</p>
        <div class="image-wrapper" v-for="(image, index) in userImages">
          <img v-bind:key="index" v-on:click="selectImage(`https://s3.amazonaws.com/worldbuilder-twc/${image.key}`)" v-bind:src="`https://s3.amazonaws.com/worldbuilder-twc/${image.key}`" width="100px"/>
          <p v-on:click="deleteImage(image.key)">X</p>
        </div>
      </div>
      <div class="newMedia">
        <div class="currentSelection">
          <p>Currently Selected</p>
          <span class="currentSelectedImage" v-if="!selectedImage">No Image Selected</span>
          <img :src="selectedImage" v-if="selectedImage" width="100%"/>
          <button v-on:click="handleChooseImage" class="primary">Use</button>
        </div>
        <div class="newMediaBottom">
          <input type="file" @change="onFileChanged">
          <button v-on:click="uploadImage" class="primary">Upload</button>
        </div>
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
      userImages: [],
      selectedImage: null,
      uploadInProgress: false,
      loadingCurrentMedia: true
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
  beforeCreate() {
    let currentWorld = this.$store.getters.getCurrentWorld;

    // This request pulls back all of the users current images and pushes them to screen
    axios({
      url: api + '/worlds/' + currentWorld + '/images',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      response.data.body['object-summaries'].forEach(item => {
        this.userImages.push(item);
      })
      this.loadingCurrentMedia = false;
    })
  },
  methods: {
    onFileChanged (event) {
      this.selectedFile = event.target.files[0];
      this._emitValues();
    },
    selectImage(imgUrl) {
      this.selectedImage = imgUrl;
    },
    handleCloseMediaManager() {
      this.$emit('closeMediaManager', true);
    },
    handleChooseImage() {
      if (this.selectedImage) {
        this.$emit('imageSelected', this.selectedImage);
      } else {
        alert('Please Select an Image First!');
      }
    },
    uploadImage() {
      const formData = new FormData();
      const worldId = this.$store.getters.getCurrentWorld;
      formData.append('myFile', this.selectedFile, this.selectedFile.name);
      this.uploadInProgress = true;
      axios({
        url: api + `/worlds/${worldId}/upload`,
        method: 'post',
        data: formData,
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.userImages.push({key: `${worldId}/${this.selectedFile.name}`});
        this.uploadInProgress = false;
      }).catch(e => {
        alert("You've Reach The Max Image Upload Amount")
        this.uploadInProgress = false;
      })
    },
    deleteImage(key) {
      const worldId = this.$store.getters.getCurrentWorld;
      axios({
        url: `http://localhost:3000/api/worlds/${worldId}/image/delete`,
        method: 'post',
        data: {imageId: key}, // sent as data as the id is bucket/id which ends up hitting the wrong route
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        let newArray = this.userImages.filter((image) => {
          return image.key !== key
        })
        this.userImages = newArray
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
  @import '../../../styles/main';

  .MediaManager {
    position: absolute;
    top: -100px;
    left: calc(50% - 360px);
    background: white;
    width: 720px;
    margin: 0 auto;
    border: 1px solid $lightBlue;
    min-height: 240px;
    flex-flow: wrap;

    button {
      width: 70px;
      height: 30px
    }

    .closeMediaManager {
      position: absolute;
      top: 5px;
      right: 5px;
      font-size: .5rem;
      cursor: pointer;
    }

    h3 {
      margin: 10px 0;
      width: 100%;
    }
    p {
      margin-bottom: 5px;
      width: 100%;
    }

    .loading-overlay {
      position: absolute;
      top: 0;
      left: 0;
      background-color: rgba(255,255,255, .95);
      z-index: 2;
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      flex-direction: column;
      display: none;
    }

    .currentMedia-loading {
      position: absolute;
      top: 0;
      left: 0;
      background-color: rgba(255,255,255, .95);
      z-index: 2;
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      flex-direction: column;
      display: none;
    }

    .media-wrapper {
      display: flex;
      flex-flow: wrap;

      .currentSelectedImage {
        font-weight: 300;
        font-size: .75rem;
        padding-bottom: 5px;
      }

      .currentMedia {
        width: 540px;
        flex-flow: wrap;
        overflow-y: scroll;
        max-height:480px;

        &.loadingCurrentMedia {
          display: flex;
        }
      }

      .newMedia{
        width: 180px;
        padding: 5px;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;

        .newMediaBottom {
          align-self: flex-end;
          padding-top: 25px;
        }

        input {
          width: 90%;
        }
      }
    }

    .image-wrapper {
      width: 260px;
      height: 200px;
      overflow: hidden;
      position: relative;

      img{
        min-width: 100%;
        max-width: none;
        width: auto;
        min-height: 100%;
        max-height: 100%;
      }

      p {
        position: absolute;
        top: 0px;
        left: 0px;
        background: red;
        width: 25px;
        height: 25px;
        line-height: 25px;
        color: white;
        margin: 0;
        transition: all .25s;
        cursor: pointer;

        &:hover {
          filter: brightness(85%);
        }
      }
    }

    .currentMedia {
      display: flex;
      flex-direction: row;
    }

    &.uploadInProgress {
      .loading-overlay {
        display: flex;
      }
    }

    @media(max-width: 767px) {

      width: 100%;
      left: 0;
      top: -100px;

      .currentMedia, .newMedia {
        width: 100% !important;

        .newMediaBottom {
          align-self: auto !important;
        }
      }

      .image-wrapper {
        width: 50%;
      }
    }
  }
</style>
