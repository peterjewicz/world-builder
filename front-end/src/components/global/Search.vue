<template>
  <div class="Search">
    <input type="text" placeholder="search" v-model="searchVal" @input="handleSearch"/>
    <div class="searchResults" v-if="showResults">
      <span class="resultsTitle">Related Items</span>
      <ul>
        <li v-on:click="selectEntity" v-for="entity in searchResults"
            :data-value="entity.value.overview.name"
            :data-id="entity._id"
            :data-type="entity.type">
          {{entity.type}}: {{entity.value.overview.name}}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import store from '../../store/store.js';

export default {
  name: 'Search',
  data () {
    return {
      searchVal: '',
      showResults: false,
      searchResults: []
    }
  },
  computed: {
  },
  mounted() {

  },
  methods: {
    handleSearch() {
      let currentSearchVal = this.searchVal.trim()
      if (currentSearchVal.length > 3) {
        this.searchResults = []
        let currentStoreState = this.$store.getters.getValues;

        // Itterate through all properties of all items to see if there's a match
        // TODO we might want to see about limiting which fields it goes through.
        for (let property in currentStoreState) {
          currentStoreState[property].forEach(item => {
            for (let itemProp in item.value.overview) {
              if (item.value.overview[itemProp].includes(currentSearchVal)) {
                let itemCopy = item;
                itemCopy.type = property;
                this.searchResults.push(itemCopy);
              }
            }
          })
        }
        this.showResults = true
      } else {
        this.showResults = false
      }
    },

    selectEntity(e) {
      let currentId = e.srcElement.attributes[1].nodeValue
      let currentType = e.srcElement.attributes[2].nodeValue

      let entityLink = `new/${currentType}/${currentId}`

      this.$router.push(entityLink)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" >
  @import '../../styles/main';

  .Search {
    position: relative;

    .searchResults {
      border: 2px solid #e9edf2;
      position: absolute;
      top: 31px;
      width: 300px;
      max-height: 100px;
      overflow: scroll;
      padding: 5px;
      background: white;
      z-index: 5;

      .resultsTitle {
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

</style>
