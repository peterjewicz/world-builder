<template>
  <div class="Hamburger" v-bind:class="{ active: hamburgerActive }">
    <h3>{{getSelectedWorldName(currentWorld, worlds)}}</h3>
    <ul>
      <li v-for="world in worlds" v-on:click="setWorld(world)">{{world.name}}</li>
    </ul>
    <router-link v-bind:to="'create-world'"><span class="addWorld hover-darkblue">Add World +</span></router-link>
  </div>
</template>

<script>
import store from '../../../store/store.js';

export default {
  name: 'Hamburger',
  components: {

  },
  props: ['hamburgerActive'],
  data () {
    return {
      worlds: this.$store.getters.getWorlds,
      selectedWorld: this.currentWorld
    }
  },
  computed: {
    currentWorld: {
      get () {
        return store.getters.getCurrentWorld;
      },
      set (value) {
        store.commit('saveCurrentWorld', value);
        this.$emit('worldUpdated', value)
      }
    }
  },
  mounted() {
  },
  methods: {
    getSelectedWorldName(current, worlds) {
      if (current) {
        return worlds.filter(world => world._id === current)[0].name
      }
    },
    setWorld(world) {
      store.commit('saveCurrentWorld', world._id);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../../styles/main';

  .Hamburger {
    position: fixed;
    right: -250px;
    top: 0;
    background: $purple;
    width: 200px;
    height: 100vh;
    z-index: 200;
    border-left: 2px solid darken($purple, 50%);
    transition: all .25s;

    &.active {
      right: 0px;
    }
  }

</style>
