<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" name="name" description="What's The Name Of This Item?" v-bind:value="nameValue" />
    <FormText @valueChanged="formValueChanged" title="Descrition" name="desc" description="What Is This Item?" v-bind:value="descValue" />
    <FormText @valueChanged="formValueChanged" title="Weight" name="weight" description="How Much Does It Weigh?" v-bind:value="weightValue" />
    <FormText @valueChanged="formValueChanged" title="Rarity" name="rarity" description="How Rare Is This Item?" v-bind:value="rarityValue" />
    <FormText @valueChanged="formValueChanged" title="Significane" name="significance" description="Why Is This Item Important?" v-bind:value="significanceValue" />
    <FormText @valueChanged="formValueChanged" title="Owner(s)" name="owners" description="Who Owns This Item?" v-bind:value="ownersValue" />
    <FormText @valueChanged="formValueChanged" title="Location" name="location" description="Where Is This Item Currently Located?" v-bind:value="locationValue" />
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
      weightValue: '',
      rarityValue: '',
      significaneValue: '',
      ownersValue: '',
      locationValue: ''
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      this.nameValue = newVal.name;
      this.descValue = newVal.desc;
      this.weightValue = newVal.weight;
      this.rarityValue = newVal.rarity;
      this.significanceValue = newVal.significance;
      this.ownersValue = newVal.owners;
      this.locationValue = newVal.location
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
        weight: this.weightValue,
        rarity: this.rarityValue,
        significance: this.significanceValue,
        owners: this.ownersValue,
        location: this.locationVale
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
