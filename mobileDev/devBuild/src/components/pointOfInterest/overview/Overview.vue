<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Location?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Write a Short Description About This City." v-bind:value="descValue" largeField="true" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Location" name="location" description="Where is It Located?" v-bind:value="locationValue" linkable="true" searchEntities="region,city"/>
    <FormText @valueChanged="formValueChanged" title="Significance" name="significance" description="Why Is The Location Significant?" v-bind:value="significanceValue" />
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
      significanceValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.locationValue = newVal.location;
      this.significanceValue = newVal.significance;
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
        significance: this.significanceValue

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
