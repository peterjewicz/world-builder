<template>
  <div class="HistoryAdd" v-if="isactive">
    <span v-on:click="closeHistory">X</span>
    <h2>Add a New Historical Event</h2>
    <label for="title">Title</label>
    <input v-model="itemTitle" type="text" name="title" />

    <label for="date">Date</label>
    <input v-model="itemDate"  type="text" name="date" />

    <label for="title">Description</label>
    <textarea v-model="itemDesc"  type="text" name="description" />

      <button class="primary" v-on:click="addHistoryItem">Add Event</button>
  </div>
</template>

<script>

// TODO
// 1. We need to push all added events to some type of que system so we can save them
// Unless we want to just auto save them?
// 2. Same Goes For edits/deletes - Need to keep a que of the changes and push that with the
// entire entity? Then we need to remove it from the object before save. Seems messy.
// Best way might just be to blow it away eahc time - yea that'll work

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
    background: rgba(255,255,255,.85);
    z-index: 10;
  }
</style>
