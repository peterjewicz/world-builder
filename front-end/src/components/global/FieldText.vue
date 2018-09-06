<template>
  <div class="row">
    <div class="field-details">
      <h4>{{this.title}}</h4>
      <p>{{this.description}}</p>
    </div>
    <div class="field-content">
      <input type="text" v-model="fieldValue" v-on:change="handleValueChange" v-on:input="handleInput"/>
      <wysiwyg v-model="fieldValue" />
      <div class="linkedEntity" v-if="showEntityPicker">
        choose
        <ul>
          <li v-on:click="selectEntity" v-for="entity in sortedEntities" :data-value="entity.value.overview.name">
            {{entity.value.overview.name}}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'FieldText',
  props: ['title', 'name', 'description', 'value', 'linkable', 'searchEntities'],
  data () {
    return {
      fieldValue: this.value,
      // fieldValue: "<a href='test.html'>test text</a>",
      atActive: false,
      currentAtPosition: null,
      showEntityPicker: false,
      sortedEntities: []
    }
  },
  watch: {
    value: function(newVal, oldVal) {
      this.fieldValue = this.value
      console.log(this.value)
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
    // TODO we need to account for a space so if the user want's to @ something they haven't made yet
    handleInput: function () {
      // We only care about all this if the field is linkable if not we can just skip it all
      if (this.linkable) {
        if (this.atActive) {
          let searchValue = this.fieldValue.substring(this.currentAtPosition + 1, this.fieldValue.length);

          // This is the instance where they've gone back and ersased the @symbol
          if (this.fieldValue[this.currentAtPosition] !== '@') {
            this.atActive = false;
            this.showEntityPicker = false;
            this.currentAtPosition = null;
          } else {
            let values = [];
            let currentStoreState = this.$store.getters.getValues;
            let entities = this.searchEntities.split(',');
            entities.forEach(entity => {
              currentStoreState[entity].forEach(storeEntity => {
                values.push(storeEntity);
              })
            })

            this.sortedEntities = values.filter(item => {
              let currentValue = item.value.overview
              for (let property in currentValue) {
                if (currentValue[property].includes(searchValue)) {
                  return true;
                }
              }
            })
          }
        } else {
          let atPosition = this.fieldValue[this.fieldValue.length - 1];
          if (atPosition === '@') {
            this.currentAtPosition = this.fieldValue.length - 1;
            this.atActive = true;
            this.showEntityPicker = true;
          }
        }
      }
    },
    selectEntity(e) {
      let currentValue = e.srcElement.attributes[1].nodeValue;
      let preSelectValue = this.fieldValue.substring(this.fieldValue, this.currentAtPosition);
      this.fieldValue = preSelectValue + '@' + currentValue;
      this.atActive = false;
      this.showEntityPicker = false;
      this.currentAtPosition = null;
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
