<template>
  <div class="ViewAll">
    <Header />
    <div class="subHeader">
      <div class="maxWidthWrap">
        <router-link to="/dashboard"><i class="fas fa-arrow-left"></i>Back To World</router-link>
      </div>
    </div>
    <h2>{{this.$route.params.entity}}s</h2>
    <div class="entityWrapper">
      <div class="entity" v-for="entity in entities">
        {{entity._id}}
        <div v-for="entityValues in entity.value">
          <div v-for="(value, key) in entityValues">
            {{key}}: {{value}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';

export default {
  name: 'ViewAll',
  components: {
    Header
  },
  data () {
    return {
      entities: []
    }
  },
  methods: {

  },
  mounted() {
    const currentEntity = this.$route.params.entity;
    const storeSegment = this.$store.getters.getValues[currentEntity];
    this.entities = storeSegment;
    console.log(this.entities)
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

    .entity {
      flex-basis: 33%;
      text-align: left;
      padding: 10px;
      margin: 0 10px;
      border: 1px solid black;
    }
  }
</style>
