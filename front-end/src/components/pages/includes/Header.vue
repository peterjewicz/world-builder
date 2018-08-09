<template>
  <div class="header">
    <div>
      Hamburger
    </div>
    <div>
      Title
    </div>
    <div>
      <select>
        <option v-for="world in worlds">{{ world.name }}</option>
      </select>
    </div>
    <div>
      Profile
    </div>
  </div>
</template>

<script>

export default {
  name: 'Header',
  data () {
    return {
      worlds: this.$store.getters.getWorlds
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../../styles/main';

  .header {
    display: flex;
    justify-content: space-between;
    background: $lightBlue;
  }

</style>
