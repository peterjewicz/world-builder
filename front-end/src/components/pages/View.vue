<template>
  <div class="View">
    <Header @worldUpdated="worldUpdated" homeActive="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <div class="View-body">
      <div class="View-body-flexWrapper">
        <div class="View-body-flex-content">
          <h2>Bio</h2>
          <p>{{modifiedEntity.bio}}</p>
        </div>
        <div class="View-body-preview">
          <img  v-bind:src="`${this.currentEntity.value.media}`" width="100%" />
          <div class="View-body-preview-item" v-for="(properties, property) in modifiedEntity.preview">
            <h3>{{property}} :</h3> <p>{{properties}}</p>
          </div>
        </div>
      </div>
      <div class="View-body-content">
        <div class="View-body-item" v-for="(properties, property) in modifiedEntity.full">
          <h2>{{property}}</h2>
          <div class="View-body-property" v-for="(atrValue, attribute) in modifiedEntity.full[property]">
            <h4>{{attribute}}:</h4> <p>{{atrValue}}</p>
          </div>
        </div>
        <History v-bind:values="modifiedEntity.history" v-bind:active="true" v-bind:editModeDeactivate="true"/>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
import store from '../../store/store.js';
import History from '../global/history/History';
import {reload} from '../../scripts/reload';
import {settings} from '../../scripts/viewSettings';

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'ViewAll',
  components: {
    Header,
    History
  },
  data () {
    return {
      currentEntity: {},
      modifiedEntity: {}
    }
  },
  mounted() {
    reload(this);
    const values = this.$store.getters.getValues;
    const group = values[this.$route.params.entity]
    const entitySettings = settings[this.$route.params.entity]
    const currentEntity = group.filter(char => {
      if (char._id === this.$route.params.id) {
        return true;
      }
    });
    this.modifiedEntity = this.generateEntityView(currentEntity[0], entitySettings);
    this.currentEntity = currentEntity[0]

    console.log(this.currentEntity)
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
    },
    generateEntityView(entity, settings) {
      let returnObj = {full: {}, preview: {}}
      returnObj.full = {};
      returnObj.preview = {};
      returnObj.bio = '';
      returnObj.history = {};

      for (let property in entity.value) {
        if (property !== 'media' && property !== 'history') {
          returnObj.full[property] = {}
        }
        for (let subProp in entity.value[property]) {
          if (settings.includes(subProp)) {
            returnObj.preview[subProp] = entity.value[property][subProp];
          } else if (subProp === 'bio') {
            returnObj.bio = entity.value[property][subProp];
          } else if (property !== 'media' && property !== 'history') {
            returnObj.full[property][subProp] = entity.value[property][subProp];
          } else if (property === 'history') {
            returnObj.history = entity.value.history;
          }
        }
      }
      console.log(returnObj);
      return returnObj;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .View-body {
    text-align: left;

    &-flexWrapper {
      display: flex;

      .View-body-flex-content {
        width: 70%;
        padding: 15px;
        margin: 0 15px;
        box-sizing: border-box;
        border-bottom: 2px solid #4f5f6f;
      }

      .View-body-preview {
        width: 30%;
        padding: 15px;
        border-left: 2px solid #4f5f6f;
        border-bottom: 2px solid #4f5f6f;
        box-sizing: border-box;
      }
    }

    &-content {
      width: 75%;
      box-sizing: border-box;
      padding: 15px;
      margin: 0 15px;

      h2 {
        text-transform: uppercase;
      }

      .View-body-item {
        border-bottom: 2px solid #4f5f6f;
      }

      .View-body-property {
        display: flex;

        h4 {
          margin: 5px 10px;
          text-transform: capitalize;
        }
      }
    }

  }

</style>
