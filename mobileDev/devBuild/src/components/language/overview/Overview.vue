<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Language?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Write a Short Description About This City." v-bind:value="descValue" largeField="true" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Spoken By" name="by" description="Who Speaks This Language?" v-bind:value="byValue" linkable="true" searchEntities="character,creature"/>
    <FormText @valueChanged="formValueChanged" title="Location" name="location" description="Where is it Spoken If Only Specific Regions?" v-bind:value="locationValue" linkable="true" searchEntities="region,city,pointofinterest"/>
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
      byValue: '',
      locationValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.byValue = newVal.by;
      this.locationValue = newVal.location;
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
        by: this.byValue,
        location: this.locationValue
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
