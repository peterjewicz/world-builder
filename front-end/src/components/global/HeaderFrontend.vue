<template>
  <div class="header-frontEnd">
    <h4>World Building</h4>
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
<style lang="scss" >
  @import '../../styles/main';

  // TODO we might want to fix this to no be absolute, probably need to re-work the whole page
  // Might not matter through since this is really only used for login and create atm
  .header-frontEnd {
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    background: $darkBlue;
    color: white;
    padding: 5px;
    box-sizing: border-box;

    h4 {
      margin: 0;
      font-size: 1.25rem;
    }
  }

</style>
