<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Spell?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Description" name="desc" description="What Does This Spell Do and How Is It Cast?" v-bind:value="descValue" />
    <FormText @valueChanged="formValueChanged" title="Components" name="components" description="Are There Special Materials Or Components Needed To Cast The Spell?" v-bind:value="componentsValue" />
    <FormText @valueChanged="formValueChanged" title="Caster" name="caster" description="Is There Only a Specific Person(s) That Can Cast This Spell?" v-bind:value="casterValue" />
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
      componentsValue: '',
      casterValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.componentsValue = newVal.components;
      this.casterValue = newVal.caster;
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
        components: this.componentsValue,
        caster: this.casterValue
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
