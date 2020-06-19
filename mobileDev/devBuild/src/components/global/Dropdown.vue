<template>
  <div :class="['Dropdown', isactive ? 'active': '']"  :style="{backgroundColor: this.color}">
    {{this.text}}
  </div>
</template>

<script>

export default {
  name: 'Dropdown',
  props: ['text', 'color', 'active'],
  data () {
    return {

    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance

      // sends request after 3 seconds to the parent to close the dropdown
      // eslint-disable-next-line
      setTimeout(() => {
        if (this.active === true) {
          this.$emit('hideDropdown', false)
        }
      }, 3000)
      return this.active;
    }
  },
  mounted() {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .Dropdown {
    position: fixed;
    top: -100px;
    left: 0;
    width: 100%;
    padding: 10px;
    text-transform: uppercase;
    background: red;
    transition: all .5s;
    color:white;
    z-index: 50;

    &.active {
      top: 0px;
    }
  }
</style>
