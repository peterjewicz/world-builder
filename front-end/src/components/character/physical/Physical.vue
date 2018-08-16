<template>
  <div v-if="isactive" class="Physical">
    <h2>Physical</h2>
    <FormText @valueChanged="formValueChanged" title="Race" description="What's The Race or Species of This Character?" v-bind:value="raceValue" />
    <FormText @valueChanged="formValueChanged" title="Weight" description="How Much Does This Character Weight?" v-bind:value="weightValue" />
    <FormText @valueChanged="formValueChanged" title="Height" description="How Tall Is This Character?" v-bind:value="heightValue" />
    <FormText @valueChanged="formValueChanged" title="Eye" description="What's This Characers Eye Color?" v-bind:value="eyeValue" />
    <FormText @valueChanged="formValueChanged" title="HairColor" description="What's This Characers Hair Color?" v-bind:value="hairValue" />
    <FormText @valueChanged="formValueChanged" title="FacialHair" description="Describe any Facial Hair." v-bind:value="facialValue" />
    <FormText @valueChanged="formValueChanged" title="TatoosPiecing" description="Does This Character Have any Tatoos?" v-bind:value="tatooValue" />
  </div>
</template>

<script>
import FormText from '../../global/FieldText';

export default {
  name: 'Physical',
  components: {
    FormText
  },
  props: ['active', 'values'],
  data () {
    return {
      raceValue: '',
      weightValue: '',
      heightValue: '',
      eyeValue: '',
      hairValue: '',
      facialValue: '',
      tatooValue: ''
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.raceValue = newVal.race;
      this.weightValue = newVal.weight;
      this.heightValue = newVal.height;
      this.eyeValue = newVal.eye;
      this.hairValue = newVal.hair;
      this.facialValue = newVal.facial;
      this.tatooValue = newVal.tatoo;
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
        race: this.raceValue,
        weight: this.weightValue,
        height: this.heightValue,
        eye: this.eyeValue,
        hair: this.hairValue,
        facial: this.facialValue,
        tatoo: this.tatooValue
      };

      const data = {
        title: 'physical',
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
