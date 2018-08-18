<template>
  <div v-if="isactive" class="Social">
    <h2>Social</h2>
    <FormText @valueChanged="formValueChanged" title="Family" name="family" description="Who's Related To This Character?" v-bind:value="familyValue" />
    <FormText @valueChanged="formValueChanged" title="Friends" name="friends" description="Who's Friends With This Character?" v-bind:value="friendsValue" />
    <FormText @valueChanged="formValueChanged" title="Enemies" name="enemies" description="Who's Enemies With This Character?" v-bind:value="enemiesValue" />
    <FormText @valueChanged="formValueChanged" title="Religion" name="religion" description="Does This Character Follow Any Religions?" v-bind:value="religionValue" />
    <FormText @valueChanged="formValueChanged" title="Politics" name="politics" description="Is This Character Involved or Following any Politics?" v-bind:value="politicsValue" />
    <FormText @valueChanged="formValueChanged" title="Occupation" name="occupation" description="What's This Characters Job?" v-bind:value="occupationValue" />
    <FormText @valueChanged="formValueChanged" title="Likes" name="likes" description="What Does This Character Like?" v-bind:value="likesValue" />
    <FormText @valueChanged="formValueChanged" title="Dislikes" name="dislikes" description="What Does This Character Dislike?" v-bind:value="dislikesValue" />

  </div>
</template>

<script>
import FormText from '../../global/FieldText';

export default {
  name: 'Social',
  components: {
    FormText
  },
  props: ['active'],
  data () {
    return {
      familyValue: '',
      friendsValue: '',
      enemiesValue: '',
      religionValue: '',
      politicsValue: '',
      occupationValue: '',
      likesValue: '',
      dislikesValue: ''
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
        family: this.familyValue,
        friends: this.friendsValue,
        enemies: this.enemiesValue,
        religion: this.religionValue,
        politics: this.politicsValue,
        occupation: this.occupationValue,
        likes: this.likesValue,
        dislikes: this.dislikesValue
      };

      const data = {
        title: 'social',
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
