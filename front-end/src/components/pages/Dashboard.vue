<template>
  <div class="dashboard">
    <Header @worldUpdated="worldUpdated"/>
    <div class="dashboard-inner">
      <Sidebar @worldUpdated="worldUpdated"/>
      <div class="dashboard-content">
        <h2>What Would You Like To Do</h2>
        <p>Here you can add details to your world. To switch worlds check the drop down in the upper-right.</p>
        <div class="dashboard-body">
          <h3>Characters & Races </h3>
          <EntityCard title="Characters" img="Characters" newurl="/new/character" allurl="/all/character"/>
          <EntityCard title="Creatures" img="Creatures" newurl="/new/creature" allurl="/all/creature"/>
        </div>
        <div class="dashboard-body">
          <h3>Locations</h3>
          <EntityCard title="Regions/Countries" img="Countries" newurl="/new/region" allurl="/all/region"/>
          <EntityCard title="Cities" img="Cities" newurl="/new/city" allurl="/all/city"/>
          <EntityCard title="Point Of Interest"  img="Interest" newurl="/new/pointOfInterest" allurl="/all/pointofinterest"/>
        </div>
        <div class="dashboard-body">
          <h3>Social</h3>
          <EntityCard title="Religions" img="Religions" newurl="/new/religion" allurl="/all/religion"/>
          <EntityCard title="Languages" img="Languages" newurl="/new/language" allurl="/all/language"/>
        </div>
        <div class="dashboard-body">
          <h3>Fantasy</h3>
          <EntityCard title="Spells" img="Spells" newurl="/new/spell" allurl="/all/spell"/>
          <EntityCard title="Items" img="Items" newurl="/new/item" allurl="/all/item"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
import Sidebar from './includes/Sidebar';
import EntityCard from './includes/EntityCard';
import store from '../../store/store.js';

const axios = require('axios');
const api = process.env.API;

export default {
  name: 'Dashboard',
  components: {
    Header,
    EntityCard,
    Sidebar
  },
  data () {
    return {
      currentWorld: ''
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
        console.log(response)
      })
    }
  },
  mounted() {
    console.log(this.$store.getters.getCurrentWorld)
    const worlds = this.$store.getters.getWorlds;
    let currentWorld = this.$store.getters.getCurrentWorld;
    this.currentWorld = currentWorld;
    // We either take the first world in the list or
    // we have to go through and find which one the user is
    // currently looking at.
    if (currentWorld) {
    } else {
      store.commit('saveCurrentWorld', worlds[0]._id)
      currentWorld = worlds[0]._id
    }

    // Here we need to grab all of the entities associated with a world and
    // store them in state. This cuts down on API calls and makes it
    // easier to search as it's all in memory
    axios({
      url: api + '/worlds/' + currentWorld + '/entities',
      method: 'get',
      headers: {'token': localStorage.getItem('token')}
    }).then(response => {
      store.commit('saveValue', response.data)
    })
    // console.log(this.$route.query.world)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../../styles/main';

  .dashboard-inner {
    display: flex;
    flex-flow: wrap;

    .dashboard-content {
      width: 85%;
    }

    .dashboard-sidebar {
      width: 14%;
      border-right: 1px solid $darkBlue;
    }
  }

  .dashboard-body {
    clear: both;
    text-align: left;
    padding-bottom: 35px;
    border-bottom: 1px solid black;
    display: flex;
    flex-flow: wrap;
    h3 {
      margin: 20px;
      width: 100%;
      flex-basis: 100%;
    }
    .entity {
      // float: left;
      margin: 20px;
    }
  }

  @media(max-width: 767px) {
    .dashboard-inner {
      .dashboard-content {
        width: 100%;
      }
    }
  }

</style>
