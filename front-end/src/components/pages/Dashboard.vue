<template>
  <div class="dashboard">
    <Header />
    <h2>What Would You Like To Do</h2>
    <div class="dashboard-body">
      <EntityCard title="Characters" newurl="/new/character" allurl="/all/character"/>
      <EntityCard title="Places"/>
      <EntityCard title="Regions"/>
      <EntityCard title="Cities"/>
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

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>
