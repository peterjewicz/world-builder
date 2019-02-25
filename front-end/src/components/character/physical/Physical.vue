<template>
  <div v-if="isactive" class="Physical">
    <h2>Physical</h2>
    <FormText @valueChanged="formValueChanged" title="Race" name="race" description="What's The Race or Species of This Character?" v-bind:value="raceValue" searchEntities="creature"/>
    <FormText @valueChanged="formValueChanged" title="Weight" name="weight" description="How Much Does This Character Weight?" v-bind:value="weightValue" />
    <FormText @valueChanged="formValueChanged" title="Height" name="height" description="How Tall Is This Character?" v-bind:value="heightValue" />
    <FormText @valueChanged="formValueChanged" title="Eye Color" name="eye" description="What's This Characers Eye Color?" v-bind:value="eyeValue" />
    <FormText @valueChanged="formValueChanged" title="Hair Color" name="hair" description="What's This Characers Hair Color?" v-bind:value="hairValue" />
    <FormText @valueChanged="formValueChanged" title="Facial Hair" name="facialhair" description="Describe any Facial Hair." v-bind:value="facialValue" />
    <FormText @valueChanged="formValueChanged" title="Tatoos and Piecings" name="tatoo" description="Does This Character Have any Tatoos?" v-bind:value="tatooValue" />
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
