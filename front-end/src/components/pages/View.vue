<template>
  <div class="View">
    <Header @worldUpdated="worldUpdated" homeActive="true"/>
    <div class="subHeader">
      <div class="maxWidthWrap">
        <!-- <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link> -->
        <a class="linkStyle" @click="$router.go(-1)"><i class="fas fa-arrow-left"></i> Back</a>
      </div>
    </div>
    <div class="View-body">
      <div class="View-body-flexWrapper">
        <div class="View-body-flex-content">
          <h2>Bio</h2>
          <p @click="handleLinkClick" v-html="modifiedEntity.bio"></p>
          <div class="View-body-item" v-for="(properties, property) in modifiedEntity.full">
            <h3>{{property}}</h3>
            <div class="View-body-property" v-for="(attrValue, attribute) in modifiedEntity.full[property]" v-if="attrValue">
              <h4>{{attribute}}:</h4> <p>{{attrValue}}</p>
            </div>
          </div>
        </div>
        <div class="View-body-preview">
          <img v-if="modifiedEntity.media"  v-bind:src="modifiedEntity.media" width="100%" />
          <img v-else :src="getPic('generic')" width="100%" />
          <div class="View-body-preview-item" v-if="properties" v-for="(properties, property) in modifiedEntity.preview">
            <h5>{{property}} :</h5> <p @click="handleLinkClick">{{properties}}</p>
          </div>
        </div>
      </div>
      <div class="View-body-history">
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
    this.onMount()
  },
  computed: {
    // getEntities() {
    //   const currentEntity = this.$route.params.entity;
    //   const storeSegment = this.$store.getters.getValues[currentEntity];
    //   return storeSegment;
    // }
  },
  watch: {
    '$route' (to, from) {
      if (to.params.id !== from.params.id) {
        this.onMount()
      }
    }
  },
  methods: {
    onMount() {
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
    },
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

        // TODO this is a bit messy
        for (let subProp in entity.value[property]) {
          if (settings.includes(subProp)) {
            returnObj.preview[subProp] = entity.value[property][subProp];
          } else if (subProp === 'bio' || subProp === 'desc') {
            returnObj.bio = entity.value[property][subProp].replace('@', ''); // Remove the @ for linking
          } else if (property !== 'media' && property !== 'history') {
            returnObj.full[property][subProp] = entity.value[property][subProp];
          } else if (property === 'history') {
            returnObj.history = entity.value.history;
          } else if (property === 'media') {
            returnObj.media = entity.value.media
          }
        }
      }
      return returnObj;
    },
    getPic(image) {
      return require('../../assets/' + image + '.png');
    },
    handleLinkClick(event) {
      event.preventDefault();
      let { target } = event;
      if (target && target.tagName === 'A') {
        const url = new URL(target.href);
        const to = url.hash;

        this.$router.push(to.replace('#', ''));
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .linkStyle {
    cursor: pointer;
  }

  .View-body {
    text-align: left;

    img {
      min-height: 200px;
    }

    h5, h3 {
      text-transform: capitalize;
    }

    &-flexWrapper {
      display: flex;

      .View-body-flex-content {
        width: 70%;
        padding: 15px;
        margin: 0 15px;
        box-sizing: border-box;
        border-bottom: 2px solid #4f5f6f;

        .View-body-item {
          border-bottom: 2px solid #4f5f6f;
          padding: 10px 0;

          &:last-of-type {
            border-bottom: none;
          }
        }

        h2 {
          text-transform: uppercase;
        }

        .View-body-property {
          display: flex;

          p {
            line-height: 2rem;
          }

          h4 {
            margin: 5px 10px;
            padding-top: 6px;
            text-transform: capitalize;
          }
          h5, h3 {
            text-transform: capitalize;
          }
        }
      }

      .View-body-preview {
        width: 30%;
        // padding: 15px;
        border-left: 2px solid #4f5f6f;
        border-bottom: 2px solid #4f5f6f;
        box-sizing: border-box;

        &-item {
          display: flex;
          padding: 15px;

          p {
            line-height: 2rem;
            margin: 0;
          }

          h5 {
            margin: 0;
            text-transform: capitalize;
            padding-right: 10px;
          }
        }
      }
    }

    &-content {
      width: 75%;
      box-sizing: border-box;
      padding: 15px;
      margin: 0 15px;

    }

    .View-body-history {
      padding: 15px;
    }

    @media(max-width: 767px) {
      .View-body {
        &-flexWrapper {
          flex-wrap: wrap;
          flex-direction: column-reverse;

          .View-body-preview {
            width: 100%;
            border-left: none;
          }
        }
      }
    }

  }

</style>
