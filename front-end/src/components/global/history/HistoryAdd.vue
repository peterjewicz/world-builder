<template>
  <div class="HistoryAdd" v-if="isactive">
    <span class="closeHistory hover-lightblue" v-on:click="closeHistory">Close <i class="fas fa-times"></i></span>
    <h2>Add a New Historical Event</h2>
    <label for="title">Title</label>
    <input v-model="itemTitle" type="text" name="title" />

    <label for="date">Date</label>
    <input v-model="itemDate" placeholder="Enter Numbers Only" type="text" name="date" />

    <label for="title">Description</label>
    <textarea v-model="itemDesc"  type="text" name="description" />

      <button class="primary" v-on:click="addHistoryItem">Add Event</button>
  </div>
</template>

<script>
export default {
  name: 'HistoryAdd',
  props: ['active'],
  data () {
    return {
      itemTitle: '',
      itemDate: '',
      itemDesc: ''
    }
  },
  computed: {
    isactive: function () {
      // `this` points to the vm instance
      return this.active;
    }
  },
  methods: {
    addHistoryItem() {
      const tempObj = {
        title: this.itemTitle,
        time: this.itemDate,
        desc: this.itemDesc
      }
      this.itemTitle = '';
      this.itemDate = '';
      this.itemDesc = '';
      this.$emit('historyItemAdded', tempObj)
    },
    closeHistory() {
      this.$emit('closeHistory', true);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

  .HistoryAdd {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(255,255,255,.95);
    z-index: 10;

    .closeHistory {
      position: absolute;
      top: 15px;
      right: 15px;
      cursor: pointer;
    }
  }
</style>
