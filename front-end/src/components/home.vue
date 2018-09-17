<template>
  <div class="Home">
    <div class="hero">
      <div class="nav">
        <ul>
          <li>Login</li>
          <li>Signup</li>
          <li>Pricing</li>
        </ul>
      </div>
      <div class="text-content">
        <h1>Never Miss A detail Building Rich Worlds</h1>
        <h3>Keep Every Charcter, City, and Much More In One Place</h3>
        <button class="primary">Sign Up</button>
      </div>
      <div class="castle">
      </div>
      <div class="grass">
      </div>
      <div class="cloud cloud1">
        <img :src="getImage('cloud')" width="100px"/>
      </div>
    </div>
    <div class="row">
      <div class="col-6">
        <h2 class="purpleText">A New World At Your Fingertips</h2>
        <p>
          Worldbuilder is built to make creating worlds as easy as possible.
          Never forget a fact, never contradict yourself again.
        </p>
      </div>
      <img class="circle-image" :src="getImage('dragon')" width="400px" height="400px"/>
    </div>
    <div class="flow-walkthrough">
      <div class="flow-item">
        <div class="col-6">
          <h2>Create Worlds</h2>
          <p>
            Separate your ideas by building worlds. All your characters, locations, and other ideas
            live in your world, and can be linked a referenced together. This let's you focus on multiple
            ideas. Free users are limited to a single world, while paid members are allowed an unlimited
            number of worlds. In either case, there is no limit to how many entities you can add to a world. Create
            an unlimited number of characters and other types for all of your worlds.
          </p>
        </div>
        <div class="col-6">
          <img :src="getImageJPG('flowitem1')" width="100%"/>
        </div>
      </div>

      <div class="flow-item">
        <div class="col-6">
          <img :src="getImageJPG('flowitem2')" width="100%"/>
        </div>
        <div class="col-6">
          <h2>Add Details</h2>
          <p>
            For each of your worlds add as much or little details as you want. The olny limit is your own
            imagination. Worldbuilder comes packed with a bunch of pre-made templates with common fields to help
            get you started qucikly. Don't see what you need? Let us know! We're always looking to improve the software
            to help make it more diverse and powerful for all our users.
          </p>
        </div>
      </div>

      <div class="flow-item">
        <div class="col-6">
          <h2>Build Endlessly</h2>
          <p>
            Build to your hearts content with unlimited of any type of entity. Both free and paid users enjoy
            unlimited entites per world, and can create as many characters, locations, spells, cities (and more) that
            they want. Paid users also get access to unlimited worlds and 10x as much file storage for all the images,
            maps, and other visual content to accompony each piece of your world.
          </p>
        </div>
        <div class="col-6">
          <img :src="getImageJPG('flowitem3')" width="100%"/>
        </div>
      </div>
    </div>
    <div class="features row">
      <div class="feature">
        <h3>Feature Title</h3>
        <p>Feature text can go here
          to talk about the feature in question
        </p>
      </div>
      <div class="feature">
        <h3>Feature Title</h3>
        <p>Feature text can go here
          to talk about the feature in question
        </p>
      </div>
      <div class="feature">
        <h3>Feature Title</h3>
        <p>Feature text can go here
          to talk about the feature in question
        </p>
      </div>
    </div>
    <div class="pricing row">
      Pricing details can go here
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
    }
  },
  methods: {
    getImage(image) {
      return require('../assets/' + image + '.png');
    },
    getImageJPG(image) {
      return require('../assets/' + image + '.jpg');
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import '../styles/main';
  .Home {

    p {
      line-height: 25px;
      font-size: 18px;
    }

    .hero {
      height: 100vh;
      width: 100%;
      background: #9de3fb;
      background-size: cover;
      background-position: bottom;
      position: relative;


      .nav {
        position: absolute;
        top: 15px;
        left: 15px;

        ul {
          margin: 0;
          padding: 0;
          list-style: none;
        }

        li {
          display: inline-block;
          padding: 0 10px;
        }
      }

      .text-content {
        height: 50vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        text-align: center;
        padding-top: 5vh;

        button {
          margin: 0 auto;
        }
      }

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

      .cloud {
        position: absolute;
        top: 45px;
        right: 30px;
      }

      .cloud1 {
        animation: cloud1 8s linear infinite;
      }

      h1,h2,h3 {
        margin: 0;
      }

      @keyframes cloud1 {
          0%   {right: 30px;}
          50% {right: 70px;}
          100% {right: 30px;}
      }
    }

    .circle-image{
      border-radius: 50%;
      margin: 0 auto;
    }

    .row {
      text-align: left;
      padding: $globalPadding 0;
      max-width: $maxWidth;
      margin: 0 auto;
    }

    .col-6 {
      width: 50%;
      padding: 0 10px;
      box-sizing: border-box;
    }

    .flow-walkthrough {
      background-color: $purple;
      color: white;
      display: flex;
      position: relative;
      flex-wrap: wrap;
      text-align: left;
      // padding-bottom: $globalPadding;

      .flow-item {
        display: flex;
        max-width: $maxWidth;
        margin: 0 auto;
        padding: $globalPadding/3 0;

        h2 {
          border-bottom: 2px solid $lightBlue;
        }
      }

      &:before {
        width: 100%;
        height: 190px;
        content: '';
        position: absolute;
        top: -190px;
        left: 0;
        background: url('../assets/curve.png') no-repeat center bottom;
        background-size: auto 180px;
      }
    }

    .features {
      display: flex;
    }

    .feature {
      width: 33%;
      padding: 0px 10px;
    }
  }
</style>
