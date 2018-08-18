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
  props: ['title', 'name', 'description', 'value'],
  data () {
    return {
      fieldValue: this.value
    }
  },
  watch: {
    value: function(newVal, oldVal) {
      this.fieldValue = this.value
    }
  },
  methods: {
    handleValueChange: function () {
      const data = {
        field: this.name,
        value: this.fieldValue
      }
      this.$emit('valueChanged', data)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .row {
    display: flex;
    max-width: 620px;
    margin: 0 auto;
    justify-content: center;

    .field-details {
      text-align: left;
      h4 {margin-bottom: 0px;}
      p {margin-top: 0px;}
      width: 50%;
    }
    .field-content {
      display: flex;
      justify-content: center;
      flex-direction: column;
      width: 50%;

      input {
        height: 15px;
      }
    }
  }
</style>
