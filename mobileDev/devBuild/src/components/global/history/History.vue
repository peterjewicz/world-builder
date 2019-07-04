<template>
  <div v-if="isactive">
    <HistoryAdd @historyItemAdded="historyItemAdded" @closeHistory="closeHistory"  v-bind:active="historyAddActive"/>
    <h2>History</h2>
    <button v-if="!editModeOff" class="primary" v-on:click="toggleHistoryAddActive">Add Event</button>
    <div class="history-wrapper">
      <!-- eslint-disable-next-line -->
      <HistoryItem v-for="(item, index) in historyPoints" v-bind:key="index" v-bind:title="item.title" v-bind:time="item.time" v-bind:desc="item.desc" v-bind:editModeDeactivate="editModeOff" @editHistoryItem="editHistoryItem($event, index)"/>
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
  props: ['active', 'values', 'editModeDeactivate'],
  data () {
    return {
      historyAddActive: false,
      historyPoints: [],
      editModeOff: this.editModeDeactivate
    }
  },
  watch: {
    values: function(newVal, oldVal) {
      newVal.forEach(historyPoint => {
        this.historyItemAdded(historyPoint);
      })
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  created() {
    // this.generateHistoryItems();
  },
  methods: {
    historyItemAdded(item) {
      this.historyPoints.push(item);
      this.histroyPoints = this.sortHistoryItems(this.historyPoints);
      this.historyAddActive = false;

      const data = {
        title: 'history',
        values: this.historyPoints
      }
      this.$emit('valueChanged', data)
    },
    sortHistoryItems(arr) {
      let sortedData = arr.sort((a, b) => {
        return parseInt(a.time) > parseInt(b.time);
      });

      return sortedData;
    },
    toggleHistoryAddActive() {
      this.historyAddActive = true;
    },
    closeHistory() {
      this.historyAddActive = false;
    },
    editHistoryItem(historyData, index) {
      this.historyPoints[index].title = historyData.title;
      this.historyPoints[index].time = historyData.time;
      this.historyPoints[index].desc = historyData.desc;
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
