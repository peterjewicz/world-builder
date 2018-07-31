<template>
  <div class="header">
    <div>
      Hamburger
    </div>
    <div>
      Title
    </div>
    <div>
      World Switcher
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
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .header {
    display: flex;
    justify-content: space-between
  }

</style>
