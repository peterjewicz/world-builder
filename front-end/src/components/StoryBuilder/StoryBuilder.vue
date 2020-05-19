<template>
  <div class="StoryBuilder">
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link v-bind:to="`/myStories`"><a><i class="fas fa-arrow-left"></i>Back To Stories</a></router-link>
      </div>
    </div>
    <div class="canvasParent">
      <div class="Controls">
        <button v-on:click="onAddCard" class="button">+ Card</button>
      </div>
      <div id="canvas">
        <div v-for="card in cards" class="card" :id="card.id" :style="{top: card.top, left: card.left}">
          <div class="cardHeader">
            <div v-on:click="onDeleteCard(card.id)" class="cardDelete">X</div>
            <input class="cardHeaderInput" v-model="card.name"/>
          </div>
          <div class="cardBody">
            <FormText @valueChanged="formValueChanged($event, card.id)" v-bind:value="card.content" largeField="false" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../pages/includes/Header';
import panzoom from 'panzoom';
import displace from 'displacejs';
import { v1 as uuid } from 'uuid';
import store from '../../store/store.js';
import FormText from '../global/FieldText';

const axios = require('axios');
const api = process.env.API;

const cardDefault = {
  name: 'Click To Edit',
  left: '0px',
  top: '0px',
  content: 'contents'
}

const onMoveEventStart = (handler) => {
  handler.pause()
}

export default {
  name: 'StoryBuilder',
  components: {
    Header,
    FormText
  },
  data () {
    return {
      panHandler: null,
      cards: [],
      currentStory: ''
    }
  },
  mounted() {
    this.currentStory = this.$route.params.id
    const element = document.querySelector('#canvas')
    this.panHandler = panzoom(element, {boundsPadding: 1, bounds: true})

    const currentStoryVals = this.$store.getters.getValues.stories.filter(story => story._id === this.$route.params.id)[0]
    this.cards = JSON.parse(currentStoryVals.stories)

    const elements = document.getElementsByClassName('card')
    setTimeout(() => {
      for (let element of elements) {
        displace(element, {
          constrain: true,
          onMouseDown: () => onMoveEventStart(this.panHandler),
          onMouseUp: (elem) => this.onMoveEventEnd(elem),
          ignoreFn: (evt) => event.target.closest('.field-content') != null

        })
      }
    }, 300);
  },
  methods: {
    onMoveEventEnd (elem) {
      this.panHandler.resume()
      this.cards = this.cards.map(card => {
        return card.id === elem.id ? {...card, top: elem.style.top, left: elem.style.left} : card
      })
      this.saveCards()
    },
    formValueChanged(e, id) {
      this.cards = this.cards.map(card => {
        return card.id === id ? {...card, content: e.value} : card
      })
      this.saveCards()
    },
    onAddCard() {
      this.cards.push({...cardDefault, id: uuid()})
      const elements = document.getElementsByClassName('card')

      // should probably do something better here
      setTimeout(() => {
        this.saveCards()
        for (let element of elements) {
          displace(element, {
            constrain: true,
            onMouseDown: () => onMoveEventStart(this.panHandler),
            onMouseUp: (elem) => this.onMoveEventEnd(elem)
          })
        }
      }, 300);
    },
    onDeleteCard(id) {
      this.cards = this.cards.filter(card => card.id !== id)
      this.saveCards()
    },
    saveCards() {
      // called after every card state change
      // needs to be debounced in the edit so we don't get a million updates
      axios({
        url: api + `/story/editCards/${this.currentStory}`,
        method: 'post',
        data: {
          values: JSON.stringify(this.cards)},
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        // we don't need the response as on success we have the current value
        store.commit('updateCurrentStory', {storyId: this.currentStory, cards: JSON.stringify(this.cards)})
      }).catch(error => {
        if (error.response.status === 401) {
          this.dropdownText = 'Your login is invalid, please login to continue';
        } else {
          this.dropdownText = 'An unknown error has occured. Please try again or contact support.'
        }
        this.dropdownColor = 'red';
        this.dropdownActive = true;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  .StoryBuilder {

    .canvasParent {
      width: 100%;
      height: calc(100vh - 79px);
      overflow: hidden;
      position: relative;

      .Controls {
        position: absolute;
        left: 0;
        top: 0;
        z-index: 200;

        button {
          border: 1px solid white;
          text-align: center;
          background: transparent;
          color: white;
          transition: all .5s;
          font-size: 1.25rem;

          &:hover {
            background: white;
            color: black;
            cursor: pointer;
          }
        }
      }
    }
    #canvas {
      width: 3000px;
      height: 3000px;
      background: #1e2121;
      position: relative;

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

    .card {
      position: absolute;
      width: 200px;
      height: 200px;
      background: white;

      .cardHeader {
        position: relative;

        .cardDelete {
          position: absolute;
          top: -5px;
          right: -5px;
          background: red;
          width: 20px;
          height: 20px;
          border-radius: 50%;
          color: white;
          transition: all .5s;

          &:hover {
            filter: brightness(85%);
            cursor: pointer;
          }
        }
      }

      .cardHeaderInput {
        border: none;
        text-align: center;
      }

      input {
        width: 90%;
        margin: 0 auto;
      }
    }
    .editr {
      width: 100% !important;
      &--content {
        box-sizing: border-box !important;
        width: 100% !important;
        border-radius: 0px !important;
        height: 150px !important;
        max-height: 150px !important;
      }
    }

    .row .field-content[data-v-bd22776a] {
      width: 100% !important;
    }

    .field-details {
      display: none !important;
    }
  }
</style>
