<template>
  <div class="header">
    <Hamburger :hamburgerActive="hamburgerActive" @hamburgerClosed="toggleHamburger"/>
    <div class="maxWidthWrap">
      <!-- TODO redo this as the header relies on having this wrapper -->
      <div class="search-wrapper header-section">
        <div v-if="searchActive">
          <Search />
        </div>
        <div v-if="homeActive" class="home">
          <router-link v-bind:to="'/dashboard'"><i class="fas fa-home"></i></router-link>
        </div>
      </div>
      <div class="header-section">
        <h4>{{titleText}}</h4>
      </div>
      <div class="header-left-content header-section">
        <div class="worlds-wrapper hide-on-mobile " v-if="!hideWorlds">
          Worlds:
          <select class="header-select" v-model="currentWorld">
            <option v-for="world in worlds" :value="world._id">{{ world.name }}</option>
          </select>
          <!-- <router-link v-bind:to="'create-world'"><span class="addWorld hover-darkblue">Add World +</span></router-link> -->
        </div>
        <div class="settings-link">
          <router-link v-bind:to="'/settings'"><i class="fas fa-user"></i></router-link>
        </div>
        <div class="hamburger-control mobile-only">
          <i v-on:click="toggleHamburger" class="fas fa-bars"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from '../../../store/store.js';
import Search from '../../global/Search';
import Hamburger from './Hamburger'

export default {
  name: 'Header',
  components: {
    Search,
    Hamburger
  },
  props: ['hideWorlds', 'titleText', 'searchActive', 'homeActive'],
  data () {
    return {
      worlds: this.$store.getters.getWorlds,
      selectedWorld: this.currentWorld,
      hamburgerActive: false
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
    toggleHamburger() {
      this.hamburgerActive = !this.hamburgerActive
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../../styles/main';

  .header {
    background: $lightBlue;
    padding: 7px 5px;

    .header-select {

    }

    h4 {
      margin: 0;
    }

    .hamburger {
      text-align: left;
    }

    .maxWidthWrap {
      display: flex;

      .header-section {
        width: 33%;
      }
    }

    .home {
      a {
        color: white;
        position: relative;
        top: 5px;

        &:hover {
          color: $darkBlue;
        }
      }
    }

    select {
      min-width: 100px;
    }

    .addWorld {
      color: white;
      font-size: .5rem;
    }

    .header-left-content {
      display: flex;
      text-align: right;
      justify-content: right;
      div {
        // flex-grow: 1;
        // flex-basis: 0;
      }
      .worlds-wrapper {
        flex-grow: 2;
        position: relative;
        top: 6px;
      }
    }

    .settings-link {
      padding-right: 10px;
      padding-left: 35px;
      padding-top: 10px;
      a {
        color: white;
        &:hover {
          color: $darkBlue;
        }
      }

    }

    .hamburger-control  {
      color: white;
      transition: .25s;
      padding-right: 10px;
      padding-left: 10px;
      padding-top: 10px;
      cursor: pointer;
      &:hover {
        color: $darkBlue;
      }
    }

    @media (max-width: 766px) {
      padding-bottom: 15px;

      input {
        margin: 0px;
      }
    }
  }

</style>
