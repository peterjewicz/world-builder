<template>
  <div v-if="isactive">
    <HistoryAdd v-bind:active="historyAddActive"/>
    <h2>History</h2>
    <button class="primary" v-on:click="toggleHistoryAddActive">Add Event</button>
    <div class="history-wrapper">
      <!-- eslint-disable-next-line -->
      <HistoryItem v-for="item in data" v-bind:key="index" v-bind:title="item.title" v-bind:time="item.time" v-bind:desc="item.desc"/>
    </div>
  </div>
</template>

<script>
import HistoryItem from './HistoryItem';
import HistoryAdd from './HistoryAdd';

export default {
  name: 'History',
  components: {
    HistoryItem,
    HistoryAdd
  },
  props: ['active'],
  data () {
    return {
      data: [],
      historyAddActive: false
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
      const historyPoints = [
        {
          title: 'another',
          time: 300,
          desc: 'suff'
        },
        {
          title: 'test',
          time: 100,
          desc: 'test text here for stuff test text here for stuff test text here for stuff test text here for stuff test text here for stuff test text here for stuff test text here for stuff test text here for stuff'
        },
        {
          title: 'test2',
          time: 50,
          desc: 'test text here for stuffssss'
        }];

      // We created a new local var as we still need to add the arrows and stuff
      let sortedData = historyPoints.sort((a, b) => {
        return a.time > b.time;
      });

        // TODO we need to add the checks to put the arrows here on the sortedData array

      this.data = sortedData;
    },
    toggleHistoryAddActive() {
      this.historyAddActive = true;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .history-wrapper {
    display: block;
    max-width: 320px;
    margin: 0 auto;
  }

</style>
