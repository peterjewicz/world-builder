<template>
  <div class="Hamburger" v-bind:class="{ active: hamburgerActive }">
    <h3>{{getSelectedWorldName(currentWorld, worlds)}}</h3>
    <p class="HamburgerClose" v-on:click="_emitValues">X</p>
    <ul>
      <li v-for="world in worlds" v-on:click="setWorld(world)">
        <span class="selectedWorld" v-if="world._id === currentWorld">{{world.name}}</span>
        <span v-else>{{world.name}}</span>
      </li>
    </ul>
    <router-link v-bind:to="'create-world'"><span class="addWorld hover-darkblue">Add World +</span></router-link>
    <div class="hamburgerSearch">
      <Search />
    </div>
  </div>
</template>

<script>
import store from '../../../store/store.js';
import Search from '../../global/Search';

export default {
  name: 'Hamburger',
  components: {
    Search
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
    },
    _emitValues() {
      this.$emit('hamburgerClosed', false)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
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

    .HamburgerClose {
      position: absolute;
      top: 10px;
      left: 10px;
      color: white;
      transition: .25s;
      cursor: pointer;

      &:hover {
        color: $darkBlue;
      }
    }

    ul {
      list-style: none;
      margin: 0;
      padding: 0;

      li {
        padding: 5px 0;
        color: white;
        cursor: pointer;

        .selectedWorld {
          color: #2c3e50;
          cursor: default;
        }
      }
    }
  }

</style>
