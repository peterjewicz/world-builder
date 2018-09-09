<template>
  <div class="row area-field">
    <div class="field-details">
      <h4>{{this.title}}</h4>
      <p>{{this.description}}</p>
    </div>
    <div class="field-content">
      <!-- <textarea v-model="fieldValue" v-on:change="handleValueChange"/> -->
      <wysiwyg v-model="fieldValue" v-on:change="handleValueChange" @change="handleInput" v-on:input="handleInput"/>
      <div class="linkedEntity" v-if="showEntityPicker">
        choose
        <ul>
          <li v-on:click="selectEntity" v-for="entity in sortedEntities"
              :data-value="entity.value.overview.name"
              :data-id="entity._id"
              :data-type="entity.type">
            {{entity.value.overview.name}}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'TextField',
  props: ['title', 'name', 'description', 'value', 'linkable', 'searchEntities'],
  data () {
    return {
      fieldValue: this.value,
      atActive: false,
      currentAtPosition: null,
      showEntityPicker: false,
      sortedEntities: []
    }
  },
  watch: {
    value: function(newVal, oldVal) {
      this.fieldValue = this.value
    }
  },
  methods: {
    handleValueChange: function () {
      const data = {
        field: this.name,
        value: this.fieldValue
      }
      this.$emit('valueChanged', data)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .row {
    display: flex;
    max-width: 620px;
    margin: 0 auto;
    justify-content: center;

    .field-details {
      text-align: left;
      h4 {margin-bottom: 0px;}
      p {margin-top: 0px;}
      width: 50%;
    }
    .field-content {
      display: flex;
      justify-content: center;
      flex-direction: column;
      width: 50%;
    }
  }
</style>
