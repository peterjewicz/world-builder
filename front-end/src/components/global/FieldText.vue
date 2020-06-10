<template>
  <div class="row text-field ">
    <div class="field-details">
      <h4>{{this.title}}</h4>
      <p>{{this.description}}</p>
      <p class="linkableText" v-if="this.linkable">Type '@' To Link To Other Entities!</p>
    </div>
    <div class="field-content">
      <!-- <input type="text" v-model="fieldValue" v-on:change="handleValueChange" v-on:input="handleInput"/> -->
      <div v-bind:class="{ large: this.largeField }">
        <wysiwyg v-model="fieldValue" v-on:change="handleValueChange" @change="handleInput" v-on:input="handleInput"/>
      </div>
      <div class="linkedEntity" v-if="showEntityPicker">
        <span class="linkedTitle">Choose an Item To Link</span>
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
  name: 'FieldText',
  props: ['title', 'name', 'description', 'value', 'linkable', 'searchEntities', 'largeField'],
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
      // console.log(this.value)
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

          // check for ; as its the last of the nbsp; we use in the html
          if (searchValue[searchValue.length - 1] === ';') {
            this.atActive = false;
            this.showEntityPicker = false;
            this.currentAtPosition = null;
          }
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
                // assign the type here so we can build the link
                storeEntity.type = entity;
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
      // [1] is name
      // [2] is id
      // [3] is type
      let currentName = e.srcElement.attributes[1].nodeValue;
      let currentId = e.srcElement.attributes[2].nodeValue
      let currentType = e.srcElement.attributes[3].nodeValue

      let currentValue = `<a contentEditable="false" target="_blank" href="#/view/${currentType}/${currentId}">${currentName}</a>`;
      let preSelectValue = this.fieldValue.substring(this.fieldValue, this.currentAtPosition);
      this.fieldValue = preSelectValue + '@' + currentValue;
      this.atActive = false;
      this.showEntityPicker = false;
      this.currentAtPosition = null;
      this.handleValueChange()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../../styles/main';
  .row {
    display: flex;
    max-width: 620px;
    margin: 0 auto;
    justify-content: center;

    .linkableText {
      font-size: .6rem;
      margin: 0;
      padding: 0;
      position: relative; // bit of a hack to get this close to main text and maintain bottom margin on mobile
      bottom: 10px;
    }

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
        border: 2px solid #e9edf2;
        position: absolute;
        top: 70px;
        width: 300px;
        max-height: 100px;
        overflow: scroll;
        padding: 5px;
        background: white;
        z-index: 5;

        .linkedTitle {
          border-bottom: 1px solid #e9edf2;
        }

        ul {
          margin: 0;
          list-style: none;
          text-align: left;

          li{
            transition: all .25s;
            cursor: pointer;

            &:hover{
              color: $lightBlue;
            }

          }
        }
      }
    }
  }
  @media(max-width: 768px) {
    .row {
      flex-wrap: wrap;

      .field-details, .field-content {
        width: 100%;
      }

      .field-details {
        text-align: center;
      }
    }
  }
</style>
