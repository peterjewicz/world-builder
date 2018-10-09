<template>
  <div class="header">
    <div class="maxWidthWrap">
      <!-- TODO redo this as the header relies on having this wrapper -->
      <div class="search-wrapper">
        <div v-if="searchActive">
          <Search />
        </div>
      </div>
      <div>
        <h4>{{titleText}}</h4>
      </div>
      <div class="header-left-content">
        <div class="worlds-wrapper" v-if="!hideWorlds">
          Worlds:
          <select v-model="currentWorld">
            <option v-for="world in worlds" :value="world._id">{{ world.name }}</option>
          </select>
          <router-link v-bind:to="'create-world'"><span class="addWorld hover-darkblue">Add World +</span></router-link>
        </div>
        <div class="settings-link">
          <router-link v-bind:to="'settings'"><i class="fas fa-user"></i></router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from '../../../store/store.js';
import Search from '../../global/Search';

export default {
  name: 'Header',
  components: {
    Search
  },
  props: ['hideWorlds', 'titleText', 'searchActive'],
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
    // console.log(this.$store.getters.getCurrentWorld)
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../../styles/main';

  .header {
    background: $lightBlue;
    padding: 7px 5px;

    h4 {
      margin: 0;
      font-size: 1.25rem;
    }

    .hamburger {
      text-align: left;
    }

    .maxWidthWrap {
      display: flex;
      div {
        flex-grow: 1;
        flex-basis: 0;
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
      div {
        flex-grow: 1;
        flex-basis: 0;
      }
      .worlds-wrapper {
        flex-grow: 2;
        position: relative;
        top: 6px;
      }
    }

    .settings-link {
      a {
        color: white;
        &:hover {
          color: $darkBlue;
        }
      }

    }
  }

</style>
