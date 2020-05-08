<template>
  <div class="StoryBuilder">
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link v-bind:to="`/myStories`"><a><i class="fas fa-arrow-left"></i>Back To Stories</a></router-link>
      </div>
    </div>
    <div class="Controls">
      <button class="button primary">Add Card</button>
    </div>
    <div class="canvasParent">
      <div id="canvas">
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../pages/includes/Header';
import panzoom from 'panzoom'
// const axios = require('axios');
// const api = process.env.API;

export default {
  name: 'StoryBuilder',
  components: {
    Header
  },
  // data () {
  // },
  mounted() {
    const element = document.querySelector('#canvas')
    panzoom(element, {boundsPadding: 1, bounds: true})
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .StoryBuilder {

    .canvasParent {
      width: 100%;
      height: 400px;
      overflow: hidden;
    }
    #canvas {
      width: 3000px;
      height: 3000px;
      background: #1e2121;

      &:after {
        content: "";
        background-image: url('../../assets/grid.png');
        background-size: cover;
        opacity: 1;
        background-position: -10px -10px;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        position: absolute;
        z-index: -1;
        background-size: 200px 200px;
        background-repeat: repeat;
      }
    }
  }
</style>
