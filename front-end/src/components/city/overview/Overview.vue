<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" description="What's The Name Of Your Region?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Nickmane" description="Does This City Have a Nickname?" v-bind:value="nicknameValue" />
    <FormText @valueChanged="formValueChanged" title="Districts" description="Does This City Have Multiple Districs or Other Separation?" v-bind:value="districtValue" />
    <FormText @valueChanged="formValueChanged" title="Language" description="What Language(s) Are Spoken Here?" v-bind:value="languageValue" />
    <FormText @valueChanged="formValueChanged" title="Religion" description="What Religion(s) are Practiced Here" v-bind:value="religionValue" />
    <FormText @valueChanged="formValueChanged" title="Imports" description="What Are The Popular Imports Here" v-bind:value="importValue" />
    <FormText @valueChanged="formValueChanged" title="Exports" description="What Are The Key Exports Of This Region" v-bind:value="exportValue" />
    <FormText @valueChanged="formValueChanged" title="Goverment" description="What Type of Goverment Does This Region Have" v-bind:value="govermentValue" />
    <FormText @valueChanged="formValueChanged" title="Title" description="What Title Does The Ruler Hold? (King, Monarch, ect.)" v-bind:value="titleValue" />
    <FormText @valueChanged="formValueChanged" title="Ruler" description="Who are/is The Current Ruler(s)" v-bind:value="rulerValue" />
    <FormText @valueChanged="formValueChanged" title="Population" description="How Many People Live Here?" v-bind:value="populationValue" />
    <FormText @valueChanged="formValueChanged" title="Reputation" description="What is This Region Know For?" v-bind:value="reputationValue" />
    <FormText @valueChanged="formValueChanged" title="Size" description="How Big Is This Region?" v-bind:value="sizeValue" />

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
      nicknameValue: '',
      districtValue: '',
      languageValue: '',
      religionValue: '',
      importValue: '',
      exportValue: '',
      govermentValue: '',
      titleValue: '',
      rulerValue: '',
      populationValue: '',
      reputationValue: '',
      sizeValue: ''
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
