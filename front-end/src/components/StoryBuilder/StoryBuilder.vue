<template>
  <div class="StoryBuilder">
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link v-bind:to="`/myStories`"><a><i class="fas fa-arrow-left"></i>Back To Stories</a></router-link>
      </div>
    </div>
    <div class="Controls">
      <button class="button primary" v-on:click="addCard">Add Card</button>
    </div>
    <div class="Canvas">
    </div>
    <div class="StoryBuilder__container">
      <draggable v-model="cards" group="people" @start="drag=true" @end="reOrderCards">
          <div class="card" v-for="card in cards" :key="card.position">
            <i class="fas fa-grip-vertical dragHandler"></i>
            <input v-on:change="saveCards" type="text" v-model="card.title"/>
            <textarea v-on:change="saveCards" v-model="card.text"></textarea>
            <div v-on:click="deleteCard(card.position)" class="card__delete">
              <p>x</p>
            </div>
          </div>
      </draggable>
    </div>
  </div>
</template>

<script>
import Header from '../pages/includes/Header';
import draggable from 'vuedraggable'

const axios = require('axios');
const api = process.env.API;

const cardSkeleton = { // default state for a card
  position: 0,
  title: 'Title',
  text: 'Desc'
}

export default {
  name: 'StoryBuilder',
  components: {
    Header,
    draggable
  },
  data () {
    return {
      cards: [{ // TODO conditionnal on not having any other cards
        position: 0,
        title: 'First Card',
        text: 'Some Text'
      }],
      currentStory: ''
    }
  },
  mounted() {
    this.currentStory = this.$route.params.id
    const currentStoryVals = this.$store.getters.getValues.stories.filter(story => story._id === this.$route.params.id)[0]
    this.cards = JSON.parse(currentStoryVals.stories)
  },
  methods: {
    addCard() {
      let cardToAdd = JSON.parse(JSON.stringify(cardSkeleton)) // I think we can do that shallow copy thing here
      cardToAdd.position = this.cards.length
      this.cards.push(cardToAdd)
      this.saveCards()
    },
    deleteCard(position) {
      this.cards = this.cards.filter(card => card.position !== position)
      this.saveCards()
    },
    reOrderCards() {
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
        // TODO may need to handle it here
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
<style scoped lang="scss">
  .StoryBuilder {
    .Controls {
      margin-top: 20px;
    }
    &__container {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;

      div:first-child {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
      }

      .dragHandler {
        // position: absolute;
        // top: -5px;
      }

      .card {
        border: 1px solid black;
        width: 200px;
        height: 160px;
        padding: 10px;
        margin: 20px;
        position: relative;
        display: block !important;
        text-align: left;

        &__delete {
          position: absolute;
          right: -10px;
          top: -10px;
          width: 20px;
          height: 20px;
          background: red;
          border-radius: 50%;
          transition: all .25s;
          text-align: center;

          p {
            margin: 0;
            line-height: 20px;
            color: white;
          }

          &:hover {
            cursor: pointer;
            background: darken(red, 10%);
          }
        }

        input, textarea {
          width: 100%;
          box-sizing: border-box;
        }
      }
    }
  }
</style>
