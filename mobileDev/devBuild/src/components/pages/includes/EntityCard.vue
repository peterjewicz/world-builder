<template>
  <div class="entity">
    <div class="entity-inner">
      <h4>{{title}}</h4>
    </div>
    <img :src="getPic(img)" width="40px" height="40px" />
    <div class="entity-inner">
      <h5><router-link v-bind:to="newurl">New</router-link></h5>
      <h5><router-link v-bind:to="allurl">View All</router-link></h5>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EntityCard',

  props: ['title', 'newurl', 'allurl', 'img'],
  data () {
    return {
    }
  },
  methods: {
    getPic(image) {
      return require('../../../assets/icons/' + image + '.png');
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../../../styles/main';

  .entity {
    border-radius: 2px;
    width: 215px;
    min-width: 215px;
    height: 125px;
    margin: 20px;
    border: 2px solid #555;
    display: flex;
    flex-direction: column;
    color: white;
    // background: #555; old
    background: #4f5f6f;
    box-shadow: 0px 3px 15px rgba(0,0,0,0.4);

    img {
      margin: 0 auto;
    }

    a {
      color: white;

      &:hover {
        color: $lightBlue;
      }
    }

    .entity-inner {
      display: flex;
      flex-direction: row;
      text-align: center;
      justify-content: space-between;
      height: 75px;

      h3,h4,h5{
        margin: 0;
        display: block;
        flex-grow: 1;
        flex-basis: 0;
      }
    }
  }

  @media(max-width: 767px) {
    .entity {
      margin: 20px auto;
    }
  }

</style>
