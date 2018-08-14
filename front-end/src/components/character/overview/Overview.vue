<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText @valueChanged="formValueChanged" title="Name" description="What's The Name Of Your Character?" v-bind:value="nameValue" />
    <TextField @valueChanged="formValueChanged" title="Bio" description="Write a Short Bio About This Character?" v-bind:value="bioValue" />
    <FormText @valueChanged="formValueChanged" title="Role" description="What's This Characters Role?" v-bind:value="roleValue" />
    <FormText @valueChanged="formValueChanged" title="Nickname" description="Does the character go by any nicknames?" v-bind:value="nicknameValue" />
    <FormText @valueChanged="formValueChanged" title="Gender" description="What gender is this character?" v-bind:value="genderValue" />
    <FormText @valueChanged="formValueChanged" title="Age" description="How Old Is This Character" v-bind:value="ageValue" />
  </div>
</template>

<script>
import FormText from '../../global/FieldText';
import TextField from '../../global/TextField';

export default {
  name: 'Overview',
  components: {
    FormText,
    TextField
  },
  props: ['active', 'values'],
  data () {
    return {
      nameValue: '',
      bioValue: '',
      roleValue: '',
      nicknameValue: '',
      genderValue: '',
      ageValue: ''
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
        bio: this.bioValue,
        role: this.roleValue,
        nickname: this.nicknameValue,
        gender: this.genderValue,
        age: this.ageValue
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
