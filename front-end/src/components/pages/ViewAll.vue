<template>
  <div class="ViewAll">
    <Header @worldUpdated="worldUpdated"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <h2>{{this.$route.params.entity}}s</h2>
    <div class="entityWrapper">
      <template v-if="getEntities.length > 0">
        <div class="entity" v-for="entity in getEntities">
          <img v-if="entity.value.media" v-bind:src="`https://s3.amazonaws.com/worldbuilder-twc/5b6ae767f0512f0c5d8ef4dd/${entity.value.media}`" width="100%" />
          <h5>Name: <span v-html="entity.value.overview.name">{{entity.value.overview.name}}</span></h5>
          <router-link v-bind:to="`/new/${entityType}/${entity._id}`"><button class="primary">View/Edit</button></router-link>
        </div>
      </template>
      <h3 v-else >No Entities Found For This Type</h3>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
import store from '../../store/store.js';

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'ViewAll',
  components: {
    Header
  },
  data () {
    return {
      entities: [],
      entityType: this.$route.params.entity
    }
  },
  computed: {
    getEntities() {
      const currentEntity = this.$route.params.entity;
      const storeSegment = this.$store.getters.getValues[currentEntity];
      console.log(storeSegment)
      return storeSegment;
    }
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

  h2 {
    text-transform: capitalize;
  }

  .entityWrapper {
    display: flex;
    flex-wrap: wrap;

    .entity {
      flex-basis: 30%;
      box-sizing: border-box;
      text-align: left;
      padding: 10px;
      margin: 10px auto;
      border: 1px solid black;
    }
  }

  @media (max-width: 767px) {
    .entityWrapper {
      .entity{
        flex-basis: 90%;
      }
    }
  }
</style>
