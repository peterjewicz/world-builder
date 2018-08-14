<template>
  <div class="HistoryAdd" v-if="isactive">
    <h1>Add shit here</h1>
  </div>
</template>

<script>

export default {
  name: 'HistoryAdd',
  props: ['active'],
  data () {
    return {

    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .HistoryAdd {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(255,255,255,.85);
    z-index: 10;
  }
</style>
