<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Plant" v-bind:value="nameValue"/>
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Writea Brief Description Of This Planet" v-bind:value="descValue" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Population" name="population" description="What Is The Population Of This Planet?" v-bind:value="populationValue"/>
    <FormText @valueChanged="formValueChanged" title="Size" name="Size" description="How Big Is This Planet" v-bind:value="sizeValue"/>
  </div>
</template>

<script>
import FormText from '../../global/FieldText';

export default {
  name: 'Overview',
  components: {
    FormText
  },
  props: ['active', 'values'],
  data () {
    return {
      nameValue: '',
      descValue: '',
      populationValue: '',
      sizeValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.populationValue = newVal.population;
      this.sizeValue = newVal.size;
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  methods: {
    formValueChanged(e) {
      const title = e.field.toLowerCase();
      this[title + 'Value'] = e.value

      this._emitValues()
    },
    _emitValues() {
      const valuesArray = {
        name: this.nameValue,
        desc: this.descValue,
        population: this.populationValue,
        size: this.sizeValue
      };
      const data = {
        title: 'overview',
        values: valuesArray
      }
      this.$emit('valueChanged', data)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
