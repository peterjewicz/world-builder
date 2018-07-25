<template>
  <div v-if="isactive" class="Overview">
    <h2>Overview</h2>
    <FormText title="Name" description="What's The Name Of Your Character?"/>
    <FormText title="Role" description="What's This Characters Role?"/>
    <FormText title="Nickname(s)" description="Does the character go by any nicknames?"/>
    <FormText title="Gender" description="What gender is this character?"/>
    <FormText title="Age" description="How Old Is This Character"/>
  </div>
</template>

<script>
import FormText from '../../global/FieldText';

export default {
  name: 'Overview',
  components: {
    FormText
  },
  props: ['active'],
  data () {
    return {

    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
