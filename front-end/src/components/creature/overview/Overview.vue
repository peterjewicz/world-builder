<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" description="What's The Name Of the Creature?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Location" description="What's Does This Creature Live?" v-bind:value="locationValue" />
    <FormText @valueChanged="formValueChanged" title="Population" description="How Many Are Left In The World?" v-bind:value="populationValue" />
    <FormText @valueChanged="formValueChanged" title="diet" description="What Does This Creatue Eat?" v-bind:value="dietValue" />
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
      locationValue: '',
      populationValue: '',
      dietValue: ''
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
        nickname: this.nicknameValue,
        district: this.districtValue,
        language: this.languageValue,
        religion: this.religionValue,
        import: this.importValue,
        export: this.exportValue,
        goverment: this.govermentValue,
        title: this.titleValue,
        ruler: this.rulerValue,
        population: this.populationValue,
        reputation: this.reputationValue,
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
