<template>
  <div id="app">
    <!-- <transition name="slide-fade"> -->
      <router-view></router-view>
    <!-- </transition> -->
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss">
@import 'styles/main';

body, html, #app {
  margin: 0;
  height: 100%;
  font-size: 16px;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.row {
  display: flex;
}

</style>
