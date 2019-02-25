<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of the Creature?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Write a Short Description About This Creature." v-bind:value="descValue" largeField="true" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Location" name="location" description="Where Does This Creature Live?" v-bind:value="locationValue" searchEntities="region,city,pointofinterest"/>
    <FormText @valueChanged="formValueChanged" title="Population" name="population" description="How Many Are Left In The World?" v-bind:value="populationValue" />
    <FormText @valueChanged="formValueChanged" title="diet" name="diet" description="What Does This Creatue Eat?" v-bind:value="dietValue" />
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
      locationValue: '',
      populationValue: '',
      dietValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.locationValue = newVal.location;
      this.populationValue = newVal.population;
      this.dietValue = newVal.diet
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
        location: this.locationValue,
        population: this.populationValue,
        diet: this.dietValue
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
