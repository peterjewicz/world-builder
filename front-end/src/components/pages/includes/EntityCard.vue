<template>
  <div class="entity">
    <div class="entity-inner">
      <h3>{{title}}</h3>
    </div>
    <div class="entity-inner">
      <h4><a v-bind:href="newurl">New</a></h4>
      <h4><a v-bind:href="allurl">View All</a></h4>
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
    width: 300px;
    min-width: 300px;
    height: 150px;
    border: 2px solid $darkBlue;
    display: flex;
    flex-direction: column;

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
