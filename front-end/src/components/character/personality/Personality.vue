<template>
  <div v-if="isactive" class="Personality">
    <h2>Personality</h2>
    <FormText @valueChanged="formValueChanged" title="Personality" name="personality" description="Write a General Description About How This Character Acts" v-bind:value="personalityValue" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology" />
    <FormText @valueChanged="formValueChanged" title="Motivation" name="motivation" description="What Drives This Character To Action" v-bind:value="motivationValue" searchEntities="character,creature,region,city,pointofinterest,religion,language,spell,item,planet,technology"/>
    <FormText @valueChanged="formValueChanged" title="Flaws" name="flaws" description="What Flaws Does This Character Have?" v-bind:value="flawsValue" />
    <FormText @valueChanged="formValueChanged" title="Quirks" name="quirks" description="Does This Character Have any Odd Quirks or Habits?" v-bind:value="quirksValue" />
    <FormText @valueChanged="formValueChanged" title="Talents" name="talents" description="Is This Character Unusally Talented In any Way?" v-bind:value="talentsValue" />
    <FormText @valueChanged="formValueChanged" title="hobbies" name="hobbies" description="What Hobbies Or Downtime Activities Does This Character Enjoy" v-bind:value="hobbiesValue" />
  </div>
</template>

<script>
import FormText from '../../global/FieldText';
export default {
  name: 'Personality',
  components: {
    FormText
  },
  props: ['active'],
  data () {
    return {
      personalityValue: '',
      motivationValue: '',
      flawsValue: '',
      quirksValue: '',
      talentsValue: '',
      hobbiesValue: ''
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
        personality: this.personalityValue,
        motivation: this.motivationValue,
        flaws: this.flawsValue,
        quirks: this.quirksValue,
        talents: this.talentsValue,
        hobbies: this.hobbiesValue
      };

      const data = {
        title: 'personality',
        values: valuesArray
      }
      this.$emit('valueChanged', data)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
