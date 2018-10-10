<template>
  <div class="Home">
    <div class="hero">
      <div class="nav">
        <ul>
          <li><router-link v-bind:to="'login'">Login</router-link></li>
          <li><router-link v-bind:to="'create'">Signup</router-link></li>
          <li>Pricing</li>
        </ul>
      </div>
      <div class="text-content">
        <h1>Never Miss A detail Building Rich Worlds</h1>
        <h3>Keep Every Charcter, City, and Much More In One Place</h3>
        <router-link v-bind:to="'create'"><button class="primary">Sign Up</button></router-link>
      </div>
      <div class="castle">
      </div>
      <div class="grass">
      </div>
      <div class="cloud cloud1">
        <img :src="getImage('cloud')" width="100px"/>
      </div>
    </div>
    <div class="content-row">
      <div class="col-6 content-text">
        <h2 class="purpleText">A New World At Your Fingertips</h2>
        <p>
          Worldbuilder is built to make creating worlds as easy as possible.
          Never forget a fact, never contradict yourself again. With all your facts
          contained in one place it makes building your worlds as easy as possible.
        </p>
        <p>
          Whether you're a writer, dungeon master, or just love building worlds Worldbuilder
          is the perfect tool to help keep you oragnized. Our preloaded entities give you
          thee power to create neatly organized worlds that will make keeping everything
          in sycn.
        </p>
      </div>
      <div class="col-6 dragonbg">
      </div>
      <!-- <img class="circle-image" :src="getImage('dragon')" width="400px" height="400px"/> -->
      <!-- <img class="" :src="getImage('dragon')" width="100%" />  -->
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
          <img :src="getImageJPG('flowitem1')"/>
        </div>
      </div>

      <div class="flow-item">
        <div class="col-6">
          <img :src="getImageJPG('flowitem2')"/>
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
          <img :src="getImageJPG('flowitem3')"/>
        </div>
      </div>
    </div>
    <div class="features row">
      <div class="feature-row">
        <div class="feature">
          <h3><i class="fas fa-pencil-alt"></i> Unlimited Details</h3>
          <p>
            Write as much or as little as you like. There's no limit to how
            many worlds, characters, cities, and more than you can write.
          </p>
        </div>
        <div class="feature">
          <h3><i class="fas fa-map-marked"></i> Maps (Coming Soon)</h3>
          <p>
            Upload and drop pins on maps to keep an eye on all your locations. Pins can be linked
            to other maps making it a seemless experience going from country to region to city maps.
          </p>
        </div>
        <div class="feature">
          <h3><i class="fas fa-link"></i> Link With References</h3>
          <p>
            Our smart linking feature allows you to "@" other entities. Easily
            link up a character with the city he lives in or an important event
            with a point of interest.
          </p>
        </div>
      </div>
      <div class="feature-row">
        <div class="feature">
          <h3><i class="fas fa-server"></i> Backed Up And Safe</h3>
          <p>
            With everything saved to our server you never have to worry about
            losing a notebook with important details in it. Our servers are
            secured with best practices to keep your info safe.
          </p>
        </div>
        <div class="feature">
          <h3><i class="fas fa-images"></i> Add Imagery</h3>
          <p>
            Upload images associated with all your details to keep a visual reference. We
            save and store all your images that you can edit or add to at any time.
          </p>
        </div>
        <div class="feature">
          <h3><i class="fas fa-globe-americas"></i> Multiple Worlds</h3>
          <p>
            For paid members, there's no limit to the amount of worlds you can create.
            For one low payment create as many worlds as you like, they're all backed up
            and safely stored for you to access at any time!
          </p>
        </div>
      </div>
    </div>
    <div class="pricing">
      <h3>Free Forever For Your First World</h3>
      <h4>And Then $7/month for unlimited worlds</h4>
      <router-link v-bind:to="'create'"><button class="primary">Sign Up Free</button></router-link>
    </div>
    <div class="footer">
      <div class="col-6">
        <ul>
          <li><router-link v-bind:to="'login'">Login</router-link></li>
          <li><router-link v-bind:to="'create'">Signup</router-link></li>
          <li><router-link v-bind:to="'create'">Contact</router-link></li>
          <li><router-link v-bind:to="'create'">Terms</router-link></li>
        </ul>
      </div>
      <div class="col-6">
        <input type="text" placeholder="Get Updates" />
        <button class="newsletter-submit primary">Get Updates</button>
      </div>
    </div>
    <div class="copyright">
      Copyright 2018 - World Builder - Built and Owned By <a href="http://www.totalwebconnections.com" target="_blank">
        Total Web Connections</a>
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
    }

    h1,h2,h3 {
      text-transform: uppercase;
    }

    .hero {
      height: 100vh;
      width: 100%;
      background: #9de3fb;
      background-size: cover;
      background-position: bottom;
      position: relative;

      button {
        position: relative;
        top: 15px;
      }

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
          color: white;
          transition: all .25s;
          font-size: 1.15rem;
          a {
            color: white;
            transition: all .25s;

            &:hover {
              color: $darkBlue;
            }
          }
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

    .content-row {
      // padding-bottom: $globalPadding + 190;
      display: flex;
      padding: 0 0;
      text-align: left;
      box-sizing: border-box;

      .col-6 {
        padding: $globalPadding 0;
        padding-bottom: $globalPadding + 190;
      }

      .content-text {
        padding-left: 15px;
        padding-right: 15px;
      }

      .dragonbg {
        background-image: url('../assets/dragon.png');
        background-size: cover;
        margin-left: 30px;
      }

      @media(max-width: 1023px) {
        flex-flow: wrap;

        .col-6 {
          width: 100%;
        }

        .col-6:first-child {
          padding-bottom: $globalPadding / 2;
          padding-top: $globalPadding / 2;
        }

        .dragonbg {
          margin: 0;
        }
      }
    }

    .flow-walkthrough {
      background-color: $purple;
      color: white;
      display: flex;
      position: relative;
      flex-wrap: wrap;
      text-align: left;

      .flow-item {
        display: flex;
        max-width: $maxWidth;
        margin: 0 auto;
        padding: $globalPadding/3 0;

        img {
          width: 100%;
        }

        h2 {
          border-bottom: 2px solid $lightBlue;
          margin-bottom: 65px;
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
      flex-wrap: wrap;

      .feature-row {
        display: flex;
        flex-wrap: wrap;
      }
    }

    .feature {
      width: 33%;
      padding: 0px 10px;
      box-sizing: border-box;

      i {
        color: $lightBlue;
      }
    }

    .pricing {
      flex-flow: column;
      background: $lightBlue;
      color: white;
      padding: $globalPadding/2 0;
      position: relative;

      h3,h4 {
        margin: 0;
      }
      button {
        margin-top: 10px;
      }
    }

    .footer {
      display: flex;
      padding: $globalPadding/3 15px;
      max-width: $maxWidth;
      margin: 0 auto;

      ul {
        list-style: none;
        padding: 0;
        margin: 0;
        text-align: left;
      }

      input {
        margin-right: 0px;
      }

      button {
        float: right;
      }
    }

    .copyright {
      padding: 10px 0;
      font-size: .6rem;
    }

    // Tablet styles
    @media(max-width: 1024px) {
      .content-row {
        .col-6 {
          width: 100%;
          text-align: center;
        }

        img {
          display: none;
        }
      }

      .feature {
        width: 50%;
      }

      .flow-item {
        flex-wrap: wrap;
        .col-6 {
          width: 100%;
        }

        img {
          width: 50%;
        }
      }
    }

    // Mobile Styles
    @media(max-width: 767px) {
      .feature {
        width: 100%;
      }

      .flow-item {
        img {
          width: 100%;
        }
      }

      .footer {
        .col-6 {
          width: 100%;
        }
      }
    }
  }
</style>
