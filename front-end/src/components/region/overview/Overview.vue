<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of Your Region?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Write a Short Description About This City." v-bind:value="descValue" largeField="true" linkable="true" searchEntities="character,city,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Capitol" name="capitol" description="Does Region Have a Capitol or Place Looked to For Leadership?" v-bind:value="capitolValue" />
    <FormText @valueChanged="formValueChanged" title="Language" name="language" description="What Language(s) Are Spoken Here?" v-bind:value="languageValue" />
    <FormText @valueChanged="formValueChanged" title="Religion" name="religion" description="What Religion(s) are Practiced Here" v-bind:value="religionValue" />
    <FormText @valueChanged="formValueChanged" title="Imports" name="imports" description="What Are The Popular Imports Here" v-bind:value="importsValue" />
    <FormText @valueChanged="formValueChanged" title="Exports" name="exports" description="What Are The Key Exports Of This Region" v-bind:value="exportsValue" />
    <FormText @valueChanged="formValueChanged" title="Goverment" name="goverment" description="What Type of Goverment Does This Region Have" v-bind:value="govermentValue" />
    <FormText @valueChanged="formValueChanged" title="Title" name="title" description="What Title Does The Ruler Hold? (King, Monarch, ect.)" v-bind:value="titleValue" />
    <FormText @valueChanged="formValueChanged" title="Ruler" name="ruler" description="Who are/is The Current Ruler(s)" v-bind:value="rulerValue" />
    <FormText @valueChanged="formValueChanged" title="Population" name="population" description="How Many People Live Here?" v-bind:value="populationValue" />
    <FormText @valueChanged="formValueChanged" title="Reputation" name="reputation" description="What is This Region Know For?" v-bind:value="reputationValue" />
    <FormText @valueChanged="formValueChanged" title="Size" name="size" description="How Big Is This Region?" v-bind:value="sizeValue" />
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
      capitolValue: '',
      languageValue: '',
      religionValue: '',
      importsValue: '',
      exportsValue: '',
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
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.capitolValue = newVal.capitol;
      this.languageValue = newVal.language;
      this.religionValue = newVal.religion;
      this.importsValue = newVal.imports;
      this.exportsValue = newVal.exports;
      this.govermentValue = newVal.goverment;
      this.titleValue = newVal.title;
      this.rulerValue = newVal.ruler;
      this.populationValue = newVal.population;
      this.reputationValue = newVal.reputation;
      this.sizeValue = newVal.size;
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
        desc: this.desc,
        capitol: this.capitolValue,
        language: this.languageValue,
        religion: this.religionValue,
        imports: this.importsValue,
        exports: this.exportsValue,
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
