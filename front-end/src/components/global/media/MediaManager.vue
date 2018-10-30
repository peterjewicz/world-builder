<template>
  <div v-if="isactive" class="MediaManager">
    <div v-on:click="handleCloseMediaManager" class="closeMediaManager">Close <i class="fas fa-times"></i></div>
    <div class="media-wrapper">
      <div class="currentMedia">
        <h3>Media Manager</h3>
        <p>Select an image to use or upload a new one!</p>
        <div class="image-wrapper" v-for="(image, index) in userImages">
          <img v-bind:key="index" v-on:click="selectImage(`https://s3.amazonaws.com/worldbuilder-twc/${image.key}`)" v-bind:src="`https://s3.amazonaws.com/worldbuilder-twc/${image.key}`" width="100px"/>
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
      selectedImage: null
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
    axios({
      url: api + '/worlds/' + currentWorld + '/images',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      response.data.body['object-summaries'].forEach(item => {
        this.userImages.push(item);
      })
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
      if (this.secltedImage) {
        this.$emit('imageSelected', this.selectedImage);
      } else {
        alert('Please Select an Image First!');
      }
    },
    uploadImage() {
      const formData = new FormData();
      const worldId = this.$store.getters.getCurrentWorld;
      formData.append('myFile', this.selectedFile, this.selectedFile.name)

      console.log(localStorage.getItem('token'))
      axios({
        url: `http://localhost:3000/api/worlds/${worldId}/upload`,
        method: 'post',
        data: formData,
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        this.userImages.push(`https://s3.amazonaws.com/worldbuilder-twc/${this.selectedFile.name}`)
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
      img{
        min-width: 100%;
        max-width: none;
        width: auto;
        min-height: 100%;
        max-height: 100%;
      }
    }

    .currentMedia {
      display: flex;
      flex-direction: row;
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
