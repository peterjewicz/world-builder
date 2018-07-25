<template>
  <div class="row">
    <div class="field-details">
      <h4>{{this.title}}</h4>
      <p>{{this.description}}</p>
    </div>
    <div class="field-content">
      <input type="text" v-model="fieldValue" v-on:change="handleValueChange"/>
    </div>
  </div>
</template>

<script>

export default {
  name: 'FieldText',
  props: ['title', 'description', 'value'],
  data () {
    return {
      fieldValue: this.value
    }
  },
  methods: {
    handleValueChange: function () {
      console.log(this.fieldValue)
      this.$emit('value', this.fieldValue)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
