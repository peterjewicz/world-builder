<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Religion?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="Give a General Overview of This Religion" v-bind:value="descValue" largeField="true" linkable="true" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Type" name="type" description="Where Type Of Religion Is This (Monotheistic)?" v-bind:value="typeValue" />
    <FormText @valueChanged="formValueChanged" title="Motto/Creed" name="motto" description="What's The Motto or Creed of This Religion" v-bind:value="mottoValue" />
    <FormText @valueChanged="formValueChanged" title="Organization" name="org" description="What is the Organization Structure" v-bind:value="orgValue" />
    <FormText @valueChanged="formValueChanged" title="Leader" name="leader" description="Who Is The Leader?" v-bind:value="leaderValue" linkable="true" searchEntities="character"/>
    <FormText @valueChanged="formValueChanged" title="Location" name="location" description="Where is it Practiced?" v-bind:value="locationValue" linkable="true" searchEntities="region,city,pointofinterest"/>
    <FormText @valueChanged="formValueChanged" title="Assets" name="assets" description="Do They Own Any Significant Assets?" v-bind:value="assetsValue" />
    <FormText @valueChanged="formValueChanged" title="Public Opinion" name="opinion" description="How Do Non-members Feel About Members?" v-bind:value="opinionValue" />
    <FormText @valueChanged="formValueChanged" title="Restrictions" name="restrictions" description="Is Anyone/thing Not Allowed To Practice?" v-bind:value="restrictionsValue" />
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
      typeValue: '',
      mottoValue: '',
      orgValue: '',
      leaderValue: '',
      locationValue: '',
      assetsValue: '',
      opinionValue: '',
      restrictionsValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.typeValue = newVal.type;
      this.mottoValue = newVal.motto;
      this.orgValue = newVal.org;
      this.leaderValue = newVal.leader;
      this.locationValue = newVal.location;
      this.assetsValue = newVal.assets;
      this.opinionValue = newVal.opinion;
      this.restrictionsValue = newVal.restrictions;
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
        type: this.typeValue,
        motto: this.mottoValue,
        org: this.orgValue,
        leader: this.leaderValue,
        location: this.locationValue,
        assets: this.assetsValue,
        opinion: this.opinionValue,
        restrictions: this.restrictionsValue
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
