<template>
  <div class="StoryBuilder">
    <Header titleText="Story Builder" homeActive="true" hideWorlds="true"/>
    <div class="Controls">
      <button class="button primary" v-on:click="addCard">Add Card</button>
    </div>
    <div class="Canvas">
    </div>
    <div class="StoryBuilder__container">
      <div class="card" v-for="card in cards">
        <input type="text" v-model="card.title"/>
        <textarea v-model="card.text"></textarea>
        <div v-on:click="deleteCard(card.position)" class="card__delete">
          <p>x</p>
        </div>
      </div>
    </div>
    <button v-on:click="showCards">Show</button>
  </div>
</template>

<script>
import Header from '../pages/includes/Header';

const cardSkeleton = { // default state for a card
  position: 0,
  title: 'Title',
  text: 'Desc'
}

export default {
  name: 'StoryBuilder',
  components: {
    Header
  },
  data () {
    return {
      cards: [{ // TODO conditionnal on not having any other cards
        position: 0,
        title: 'First Card',
        text: 'Some Text'
      }]
    }
  },
  mounted() {

  },
  methods: {
    addCard() {
      let cardToAdd = JSON.parse(JSON.stringify(cardSkeleton)) // I think we can do that shallow copy thing here
      cardToAdd.position = this.cards.length
      this.cards.push(cardToAdd)
    },
    deleteCard(position) {
      this.cards = this.cards.filter(card => card.position !== position)
    },
    reOrderCards() {

    },
    saveCards() {
      // called after every card state change
      // needs to be debounced in the edit so we don't get a million updates
    },
    showCards() {
      // for testing only
      console.log(this.cards)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .StoryBuilder {
    &__container {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;

      .card {
        border: 1px solid black;
        width: 200px;
        height: 150px;
        padding: 10px;
        margin: 20px;
        position: relative;

        &__delete {
          position: absolute;
          right: -10px;
          top: -10px;
          width: 20px;
          height: 20px;
          background: red;
          border-radius: 50%;
          transition: all .25s;

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
