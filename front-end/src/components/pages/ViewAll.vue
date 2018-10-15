<template>
  <div class="ViewAll">
    <Header @worldUpdated="worldUpdated" homeActive="true"/>
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
      <h3 style="margin: 0 auto;" v-else >No Entities Found For This Type</h3>
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
  beforeRouteEnter (to, from, next) {
    // const currentEntity = this.$route.params.entity;
    // const storeSegment = this.$store.getters.getValues[currentEntity];
    // console.log(storeSegment)
    // return;
    // const id = localStorage.getItem('token');
    //
    // if (!id) {
    //   next({ path: 'login' })
    // }
    // axios({
    //   url: api + '/' + id + '/worlds/',
    //   method: 'get',
    //   headers: {'token': localStorage.getItem('token')}
    // })
    // .then((response) => {
    //   if (response.data.body === null) {
    //     next({ path: 'new-world' })
    //   } else {
    //     store.commit('saveWorlds', response.data.body)
    //     const worlds = this.$store.getters.getWorlds;
    //     let currentWorld = this.$store.getters.getCurrentWorld;
    //
    //     axios({
    //       url: api + '/worlds/' + currentWorld + '/entities',
    //       method: 'get',
    //       headers: {'token': localStorage.getItem('token')}
    //     }).then(response => {
    //       store.commit('saveValue', response.data)
    //       next();
    //     })
    //   }
    // })
    // .catch((e) => {
    //   // redirect to login
    //   console.log(e)
    //   return;
    //   next({ path: 'login' })
    // })
    next()
  },
  components: {
    Header
  },
  data () {
    return {
      entities: [],
      entityType: this.$route.params.entity
    }
  },
  mounted() {
    const currentEntity = this.$route.params.entity;
    const storeSegment = this.$store.getters.getValues[currentEntity];
    if (!storeSegment) {
      // let currentWorld = this.$store.getters.getCurrentWorld;

      const id = localStorage.getItem('token');
      axios({
        url: api + '/' + id + '/worlds/',
        method: 'get',
        headers: {'token': localStorage.getItem('token')}
      })
        .then((response) => {
          if (response.data.body === null) {
            // next({ path: 'new-world' })
          } else {
            store.commit('saveWorlds', response.data.body)
            let currentWorld = this.$store.getters.getWorlds[0];
            console.log(currentWorld)
            currentWorld = currentWorld._id
            console.log(currentWorld)

            axios({
              url: api + '/worlds/' + currentWorld + '/entities',
              method: 'get',
              headers: {'token': localStorage.getItem('token')}
            }).then(response => {
              store.commit('saveValue', response.data)
            }).catch(() => {
              console.log("You don't have permission to access that world")
            })
            // next();
          }
        })
    }
  },
  computed: {
    getEntities() {
      const currentEntity = this.$route.params.entity;
      const storeSegment = this.$store.getters.getValues[currentEntity];
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
