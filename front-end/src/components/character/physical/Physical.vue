<template>
  <div v-if="isactive" class="Physical">
    <h2>Physical</h2>
  </div>
</template>

<script>

export default {
  name: 'Physical',
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
<style scoped>

</style>
