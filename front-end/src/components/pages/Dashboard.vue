<template>
  <div class="dashboard">
    <Header />
    <h2>What Would You Like To Do</h2>
    <p>Here you can add details to your world. To switch worlds check the drop down in the upper-right.</p>
    <div class="dashboard-body">
      <h3>Characters & Races </h3>
      <EntityCard title="Characters" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Races" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Creatures" newurl="/new/character" allurl="/all/character"/>
    </div>
    <div class="dashboard-body">
      <h3>Locations</h3>
      <EntityCard title="Regions/Countries" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Cities" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Point Of Interest" newurl="/new/character" allurl="/all/character"/>
    </div>
    <div class="dashboard-body">
      <h3>Social</h3>
      <EntityCard title="Religions" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Languages" newurl="/new/character" allurl="/all/character"/>
    </div>
    <div class="dashboard-body">
      <h3>Fantasy</h3>
      <EntityCard title="Spells" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Items" newurl="/new/character" allurl="/all/character"/>
    </div>
  </div>
</template>

<script>
import Header from './includes/Header';
import EntityCard from './includes/EntityCard';

export default {
  name: 'Dashboard',
  components: {
    Header,
    EntityCard
  },
  data () {
    return {

    }
  },
  methods: {

  },
  mounted() {
    // console.log(this.$route.query.world)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .dashboard-body {
    clear: both;
    text-align: left;
    h3 { margin: 20px; }
    .entity {
      float: left;
      margin: 20px;
    }
  }

</style>
