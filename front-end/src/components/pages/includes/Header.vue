<template>
  <div class="header">
    <div class="maxWidthWrap">
      <div class="hamburger">
        <i class="fas fa-bars"></i>
      </div>
      <div>
        Dashboard
      </div>
      <div class="header-left-content">
        <div class="worlds-wrapper">
          Worlds:
          <select v-model="currentWorld">
            <option v-for="world in worlds" :value="world._id">{{ world.name }}</option>
          </select>
        </div>
        <div>
          <i class="fas fa-user"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from '../../../store/store.js';

export default {
  name: 'Header',
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
    padding: 5px;

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

    .header-left-content {
      display: flex;
      text-align: right;
      div {
        flex-grow: 1;
        flex-basis: 0;
      }
      .worlds-wrapper {
        flex-grow: 2;
      }
    }
  }

</style>
