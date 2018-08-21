<template>
  <div class="row">
    <div class="field-details">
      <h4>{{this.title}}</h4>
      <p>{{this.description}}</p>
    </div>
    <div class="field-content">
      <input type="text" v-model="fieldValue" v-on:change="handleValueChange" v-on:input="handleInput"/>
      <div class="linkedEntity" v-if="showEntityPicker">
        choose
        <ul>
          <li v-for="entity in sortedEntities">{{entity.value.overview.name}}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'FieldText',
  props: ['title', 'name', 'description', 'value'],
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
    },
    handleInput: function () {
      if (this.atActive) {
        let searchValue = this.fieldValue.substring(this.currentAtPosition + 1, this.fieldValue.length);
        let values = this.$store.getters.getValues['city'];
        // for (let x = 0; x < values.length; x++) {
        //   let overview = values[x].value['overview'];
        //   console.log(overview)
        // }
        this.sortedEntities = values.filter(item => {
          let currentValue = item.value.overview
          if (currentValue.name.includes(searchValue) || currentValue.ruler.includes(searchValue)) {
            return true;
          }
        })
        console.log(this.sortedEntities)
      } else {
        let atPosition = this.fieldValue[this.fieldValue.length - 1];
        if (atPosition === '@') {
          this.currentAtPosition = this.fieldValue.length - 1;
          this.atActive = true;
          this.showEntityPicker = true;
        }
      }
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
      position: relative;

      input {
        height: 15px;
      }

      .linkedEntity {
        border: 1px solid #e9edf2;
        position: absolute;
        top: 70px;
        width: 300px;
        height: 50px;
      }
    }
  }
</style>
