<template>
  <div class="entity">
    <div class="entity-inner">
      <h3>{{title}}</h3>
    </div>
    <div class="entity-inner">
      <h4><router-link v-bind:to="newurl">New</router-link></h4>
      <h4><router-link v-bind:to="allurl">View All</router-link></h4>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EntityCard',

  props: ['title', 'newurl', 'allurl'],
  data () {
    return {
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../../../styles/main';

  .entity {
    border-radius: 2px;
    width: 250px;
    min-width: 250px;
    height: 125px;
    border: 2px solid $darkBlue;
    display: flex;
    flex-direction: column;
    color: white;
    background: #444;

    .entity-inner {
      display: flex;
      flex-direction: row;
      text-align: center;
      justify-content: space-between;
      height: 75px;

      h3,h4{
        margin: 0;
        display: block;
        flex-grow: 1;
        flex-basis: 0;
      }
    }
  }

</style>
