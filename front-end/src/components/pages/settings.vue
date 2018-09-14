<template>
  <div class="Settings">
    <Header titleText="Settings"/>
    <h2>User Settings</h2>
    <p>Email: test@test.com</p>

    <h2>Billing</h2>
  </div>
</template>

<script>
import Header from './includes/Header';

export default {
  name: 'Settings',
  components: {
    Header
  },
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .Settings {

  }
</style>
