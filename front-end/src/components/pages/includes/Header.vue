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
          <select v-model="currentWorld" v-on:change="changeWorld">
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
      currentWorld: '',
      worlds: this.$store.getters.getWorlds
    }
  },
  methods: {
    changeWorld() {
      store.commit('saveCurrentWorld', this.currentWorld)
      this.$emit('worldUpdated', this.currentWorld)
      // this.$router.push(`/dashboard?world=${this.currentWorld}`);
      //
      // // TODO we probably just want to bubble this up to the dashboard instead of reloading the page
      // // We'll leave it now cause I'm lazy
      // location.reload()
    }
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
