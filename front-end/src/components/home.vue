<template>
  <div class="Home">
    <div class="hero">
      <h2>Hero Image</h2>
      <div class="castle">
      </div>
      <div class="grass">
      </div>
    </div>
    <div class="row">
      left text about prodct right is image
    </div>
    <div class="features row">
      <h2>3 key features</h2>
    </div>
    <div>
      we need some more features of stuff Here
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .Home {
    .hero {
      height: 100vh;
      width: 100%;
      background: #9de3fb;
      // background-image: url('../assets/bg.png');
      background-size: cover;
      background-position: bottom;
      position: relative;

      .castle {
        background-image: url('../assets/castle.png');
        background-size: contain;
        background-repeat: no-repeat;
        background-position: center center;
        width: 100%;
        height: 50vh;
        position: absolute;
        bottom: 0;
        left: 0;
        z-index: 2;
      }

      .grass {
        background-image: url('../assets/grass.png');
        background-size: contain;
        background-repeat: repeat-x;
        background-position: bottom center;
        width: 100%;
        height: 40vh;
        position: absolute;
        bottom: 0;
        left: 0;
        z-index: 1;
      }

      h1,h2,h3 {
        margin: 0;
      }
    }
  }
</style>
