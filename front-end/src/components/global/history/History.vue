<template>
  <div v-if="isactive">
    <h2>History</h2>
    <div class="history-wrapper">
      <div v-for="item in data">
        <HistoryItem v-bind:title="item.title" v-bind:time="item.time" v-bind:desc="item.desc"/>
      </div>
    </div>
  </div>
</template>

<script>
import HistoryItem from './HistoryItem';

export default {
  name: 'History',
  components: {
    HistoryItem
  },
  props: ['active'],
  data () {
    return {
      data: [],
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  created() {
    this.generateHistoryItems();
  },
  methods: {
    generateHistoryItems() {
      // TODO Pull this from somewhere else
      const historyPoints = [{
        title: 'test',
        time: 100,
        desc: 'test text here for stuff'
      },
      {
        title: 'test2',
        time: 150,
        desc: 'test text here for stuffssss'
      }];

      // TODO we need to first sort the historyPoints array based on time


      this.data = historyPoints;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .history-wrapper {
    display: block;
    max-width: 420px;
    margin: 0 auto;
  }

</style>
