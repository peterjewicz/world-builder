<template>
  <div v-if="isactive" class="Background">
    <h2>Background</h2>
    <div class="row">
      <div class="field-details">
        <h4>Race/Species</h4>
        <p>What's The Race or Species of This Character?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Weight</h4>
        <p>How Much Does This Character Weight?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Height</h4>
        <p>How Tall Is This Character?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Eye Color</h4>
        <p>What's This Characers Eye Color?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Hair Color</h4>
        <p>What's This Characers Hair Color?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Facial Hair</h4>
        <p>Describe any Facial Hair</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
    <div class="row">
      <div class="field-details">
        <h4>Tatoos</h4>
        <p>Does This Character Have any Tatoos?</p>
      </div>
      <div class="field-content">
        <input type="text" />
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Background',
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
