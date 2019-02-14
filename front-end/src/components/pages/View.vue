<template>
  <div class="View">
    <Header @worldUpdated="worldUpdated" homeActive="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <div class="View-body" v-for="(properties, property) in currentEntity.value">
      <h1>{{property}}</h1>
      <div class="View-body-property" v-for="(atrValue, attribute) in currentEntity.value[property]">
        <h2>{{attribute}}: {{atrValue}}</h2>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
import store from '../../store/store.js';
import {reload} from '../../scripts/reload';

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'ViewAll',
  components: {
    Header
  },
  data () {
    return {
      currentEntity: {}
    }
  },
  mounted() {
    reload(this);

    const values = this.$store.getters.getValues;
    const group = values[this.$route.params.entity]
    const currentEntity = group.filter(char => {
      if (char._id === this.$route.params.id) {
        return true;
      }
    });

    console.log(currentEntity[0].value)
    this.currentEntity = currentEntity[0]
  },
  computed: {
    // getEntities() {
    //   const currentEntity = this.$route.params.entity;
    //   const storeSegment = this.$store.getters.getValues[currentEntity];
    //   return storeSegment;
    // }
  },
  methods: {
    worldUpdated(currentWorld) {
      axios({
        url: api + '/worlds/' + currentWorld + '/entities',
        method: 'get',
        headers: {'token': localStorage.getItem('token')}
      }).then(response => {
        store.commit('saveValue', response.data)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>
