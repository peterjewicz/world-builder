<template>
  <div v-if="isactive" class="Personality">
    <h2>Personality</h2>
    <div class="row">
      <div class="field-details">
        <h4>Personality</h4>
        <p>Write a General Description About How This Character Acts</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Motivations/Goals</h4>
        <p>What Drives This Character To Action</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Flaws</h4>
        <p>What Flaws Does This Character Have?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Quirks</h4>
        <p>Does This Character Have any Odd Quirks or Habits?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Talents</h4>
        <p>Is This Character Unusally Talented In any Way?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Hobbies</h4>
        <p>What Hobbies Or Downtime Activities Does This Character Enjoy</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Personality',
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
