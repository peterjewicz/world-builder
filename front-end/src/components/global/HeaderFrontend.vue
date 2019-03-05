<template>
  <div class="header-frontEnd">
    <div v-if="showHome" class="homeButton">
      <router-link v-bind:to="'/dashboard'"><i class="fas fa-home"></i></router-link>
    </div>
    <h4>Worlds Crafter</h4>
  </div>
</template>

<script>

export default {
  name: 'Header',
  props: ['showHome'],
  data () {
    return {
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../styles/main';

  // TODO we might want to fix this to no be absolute, probably need to re-work the whole page
  // Might not matter through since this is really only used for login and create atm
  .header-frontEnd {
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    background: $darkBlue;
    color: white;
    padding: 5px;
    box-sizing: border-box;

    .homeButton {
      float: left;
      a {
        color: white;
        position: relative;
        top: 5px;

        &:hover {
          color: $lightBlue;
        }
      }
    }

    h4 {
      margin: 0;
      font-size: 1.25rem;
    }
  }

</style>
