<template>
  <div class="Home">
    <div class="hero">
      <div class="nav" v-bind:class="{ scrolled: scrolled }">
        <ul class="hide-on-mobile">
          <li><router-link v-bind:to="'login'">Login</router-link></li>
          <li><router-link v-bind:to="'create'">Signup</router-link></li>
          <li>Pricing</li>
          <li>Contact</li>
        </ul>
        <div class="hamburger mobile-only">
          <i class="fas fa-bars"></i>
        </div>
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

      <div class="cloud cloud2">
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
    </div>
    <div class="pricing">
      <div class="pricing-background"></div>
      <div class="pricing-content">
        <h2>All Your Details In One Place.</h2>
      </div>
    </div>
    <div class="flow-walkthrough">
      <div class="flow-item swap-row">
        <div class="col-6">
          <h2>Create Worlds</h2>
          <p>
            Separate your ideas by building worlds. All your characters, locations, and other ideas
            live in your world, and can be linked and referenced together. This let's you easily focus on multiple
            ideas. Free users are limited to a single world, while paid members are allowed an unlimited
            number of worlds. In either case, there is no limit to how many entities you can add to a world. Create
            an unlimited number of characters and other types for all of your worlds.
          </p>
        </div>
        <div class="col-6">
          <img :src="getImageJPG('flowitem1-2')"/>
        </div>
      </div>

      <div class="flow-item">
        <div class="col-6">
          <img :src="getImageJPG('flowitem2-2')"/>
        </div>
        <div class="col-6">
          <h2>Add Details</h2>
          <p>
            For each of your worlds add as much or little details as you want. The olny limit is your own
            imagination. WorldsCrafter comes packed with a bunch of pre-made templates with common fields to help
            get you started qucikly. Don't see what you need? Let us know! We're always looking to improve the software
            to help make it more diverse and powerful for all our users.
          </p>
        </div>
      </div>

      <div class="flow-item swap-row">
        <div class="col-6">
          <h2>Build Endlessly</h2>
          <p>
            Build to your hearts content with unlimited of any type of entity. Both free and paid users enjoy
            unlimited entities per world, and can create as many characters, locations, spells, cities (and more) that
            they want. Paid users also get access to unlimited worlds and 10x as much file storage for all the images,
            maps, and other visual content to accompany each piece of your world.
          </p>
        </div>
        <div class="col-6">
          <img :src="getImageJPG('flowitem3-2')"/>
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
    <div class="contact row">
      <div class="formWrapper" v-bind:class="{ confirmed: messageSubmitted }">
        <form>
          <h2>Question? Comment? Concern?</h2>
          <h3>Get In Touch!</h3>
          <input v-model="name" type="text" name="name" placeholder="Name" />
          <input v-model="email" type="text" name="email" placeholder="email" />
          <textarea v-model="message" name="message" placeholder="message"></textarea>
          <button @click="submitForm" class="primary form-trigger">SEND MESSAGE</button>
        </form>
        <div class="form-confirmation">
          <h2>Thank You For Your Message! We Will Respond Shortly!</h2>
        </div>
      </div>
    </div>
    <div class="pricing">
      <div class="pricing-background second"></div>
      <div class="pricing-content">
        <h3>Free Forever For Your First World</h3>
        <h4>And Then $7/month for unlimited worlds</h4>
        <router-link v-bind:to="'create'"><button class="primary">Sign Up Free</button></router-link>
      </div>
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
        <div class="newsletter" v-bind:class="{ confirmed: newsletterConfirmed }">
          <div class="newsletter-wrapper">
            <input type="text" v-model="newsletterEmail" placeholder="Get Updates" />
            <button @click="newsletterSignup" class="newsletter-submit primary">Get Updates</button>
          </div>
          <div class="newsletter-confirmation">
            <p>Thanks For Signing Up!</p>
          </div>
        </div>
      </div>
    </div>
    <div class="copyright">
      <router-link v-bind:to="'privacy'">Privacy Policy</router-link> |
      <router-link v-bind:to="'terms'">Terms Of Use</router-link>
      <br>
      Copyright 2018 - Worlds Crafter - Built and Owned By <a href="http://www.totalwebconnections.com" target="_blank">
        Total Web Connections</a>
    </div>
  </div>
</template>

<script>
const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Home',
  data () {
    return {
      scrolled: false,
      name: '',
      email: '',
      message: '',
      newsletterEmail: '',
      newsletterConfirmed: false,
      messageSubmitted: false
    }
  },
  created () {
    window.addEventListener('scroll', this.handleScroll);
  },
  destroyed () {
    window.removeEventListener('scroll', this.handleScroll);
  },
  methods: {
    getImage(image) {
      return require('../assets/' + image + '.png');
    },
    getImageJPG(image) {
      return require('../assets/' + image + '.jpg');
    },
    handleScroll(e) {
      let scrollPos = window.scrollY || window.scrollTop || document.getElementsByTagName('html')[0].scrollTop;

      if (scrollPos >= 100) {
        this.scrolled = true;
      } else {
        this.scrolled = false;
      }
    },
    submitForm(e) {
      // handle axios call to email server here
      event.preventDefault();

      // Super simple validation for now
      if (!this.name || !this.email || !this.message) {
        alert("Please Fill Out All Fields");
        return;
      }

      axios({
        url: api + '/homeEmail',
        method: 'post',
        data: {
          name: this.name,
          email: this.email,
          message: this.message
        }
      }).then(response => {
        // hide form and show confirmation
        this.messageSubmitted = true;
      })
    },
    newsletterSignup() {
      axios({
        url: api + '/newsletterSignup',
        method: 'post',
        data: {
          email: this.newsletterEmail
        }
      }).then(response => {
        this.newsletterConfirmed = true;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import '../styles/main';
  .Home {

    overflow-x: hidden;

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
        position: fixed;
        top: 0px;
        padding: 15px;
        width: 100%;
        left: 0px;
        z-index: 50;
        text-align: left;

        &.scrolled {
          background: $lightBlue;
          border-bottom: 1px solid $purple;
        }

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

      h1,h2,h3 {
        margin: 0;
      }

      .cloud1 {
        animation: cloud1 8s linear infinite;
      }

      .cloud2 {
        animation: cloud2 8s linear infinite;
        top: 200px;
        left: -900px
      }

      @keyframes cloud1 {
          0%   {right: 30px;}
          50% {right: 70px;}
          100% {right: 30px;}
      }

      @keyframes cloud2 {
          0%   {left: -900px;}
          50% {left: -850px;}
          100% {left: -900px;}
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

        @media(max-width: 1024px) {
          & {
            position: relative;
            top: 50px;
          }
        }

        @media(max-width: 768px) {
          & {
            top: 20px;
          }
        }
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
      // background-color: $purple;
      color: black;
      display: flex;
      position: relative;
      flex-wrap: wrap;
      text-align: left;

      .flow-item {
        display: flex;
        max-width: $maxWidth;
        margin: 0 auto;
        padding: $globalPadding/3 0;

        img{ width: 100%;
          margin: 0 auto;
          display: table;
        }

        @media(max-width: 1024px) {
          img { width: 50%}
          &.swap-row {
            .col-6 {
              position: relative;
              bottom: 50%;
            }
            .col-6:first-child {
              top: 50%;
            }
          }
        }

        @media(max-width: 768px) {
          img{ width: 100%;}
        }

        h2 {
          border-bottom: 2px solid $lightBlue;
          margin-bottom: 65px;
        }
      }

      // &:before {
      //   width: 100%;
      //   height: 190px;
      //   content: '';
      //   position: absolute;
      //   top: -190px;
      //   left: 0;
      //   background: url('../assets/curve.png') no-repeat center bottom;
      //   background-size: auto 180px;
      // }
    }

    .features {
      display: flex;
      flex-wrap: wrap;
      border-top: 2px solid $purple;

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

    .contact {
      display: flex;
      flex-direction: column;
      text-align: center;
      border-top: 2px solid $purple;

      h3,h2{
        margin-top: 0px;
      }
      h2{margin-bottom: 0px;}

      .formWrapper {
        width: 100%;
        max-width: 610px;
        margin: 0 auto;

        form {
          display: flex;
          flex-flow: wrap;
          flex-direction: row;
          justify-content: space-between;

          h2,h3 {
            width: 100%;
          }
        }
        input {
          width: 45%;
          max-width: 300px;
          margin-left: 0;
          margin-right: 0px;
        }

        textarea {
          width: 100%;
          max-width: 610px;
        }

        .form-confirmation {
          display: none;
        }

        &.confirmed {
          form {
            display: none;
          }

          .form-confirmation {
            display: block;
          }
        }
      }



      button {
        margin: 0 auto;
        display: table;
        width: 175px;
      }
    }

    .pricing {
      flex-flow: column;
      color: white;
      padding: $globalPadding/2 0;
      position: relative;

      .pricing-background {
        background: $purple;
        position: absolute;
        top: 0;
        left: 50%;
        width: 120%;
        height: 100%;
        z-index: 1;
        transform: translate(-50%,0) rotate(-5deg);

        &.second {
          transform: translate(-50%,0) rotate(5deg);
        }
      }

      .pricing-content {
        position: relative;
        z-index: 2;
      }
      h3,h4 {
        margin: 0;
      }
      button {
        margin-top: 10px;
      }
    }

    .newsletter {
      position: relative;

      .newsletter-confirmation {
        position: absolute;
        top: 0;
        left: 25%;
        display: none;
      }
    }

    .newsletter.confirmed{
      .newsletter-wrapper {
        display: none;
      }
      .newsletter-confirmation {
        display: block;
      }
    }

    .footer {
      display: flex;
      flex-wrap: wrap;
      padding: $globalPadding/2 15px;
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

      .pricing-content h2 {
        font-size: 1.75rem;
      }

      .footer {
        .col-6 {
          width: 100%;
        }

        .newsletter {
          margin-top: 24px;
        }
      }
    }

    @media(max-width: 413px) {
      .castle {
        height: 38vh !important;
      }
    }
  }
</style>
