<template>
  <div class="historyItem">
    <div class="editHistoryItemWrapper" v-if="this.editActive">
      <span v-on:click="editActive = false" class="editTrigger">Close <i class="fas fa-times"></i></span>
      <input v-model="itemTitle" v-on:change="editHistoryItem" type="text" name="title" />
      <input v-model="itemDate"  v-on:change="editHistoryItem" type="text" name="date" />
      <textarea v-model="itemDesc"  v-on:change="editHistoryItem" type="text" name="description" />
    </div>
    <!-- TODO put icon here -->
    <span v-on:click="editActive = true" class="editTrigger">Edit <i class="fas fa-edit"></i></span>
    <p class="date">{{time}}</p>
    <h4>{{title}}</h4>
    <p>{{desc}}</p>
  </div>
</template>

<script>

export default {
  name: 'HistoryItem',
  props: ['title', 'time', 'desc'],
  data () {
    return {
      editActive: false,
      itemTitle: this.title,
      itemDate: this.time,
      itemDesc: this.desc
    }
  },
  methods: {
    editHistoryItem() {
      let historyData = {
        title: this.itemTitle,
        time: this.itemDate,
        desc: this.desc
      }

      this.$emit('editHistoryItem', historyData);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  @import '../../../styles/main';

  .historyItem {
    width: 100%;
    padding: 10px;
    text-align: center;
    background: #dad95f;
    margin: 200px 0;
    position: relative;
    right: 320px;

    .editTrigger {
      font-size: .75rem;
      position: absolute;
      cursor: pointer;
      transition: all .25s;
      top: 5px;
      right: 5px;
      margin: 0;
      i {
        font-size: 1rem;
      }

      &:hover {
        color: $lightBlue;
      }
    }

    .editHistoryItemWrapper {
      position: absolute;
      padding-top: 25px;
      width: 100%;
      height: 100%;
      background: white;
      overflow: scroll;
      left: 0;
      top: 0;
      z-index: 2;
      border: 1px solid black;
    }

    .date {
      position: absolute;
      top: 5px;
      left: 5px;
      margin: 0;
    }

    h4 {
      margin: 0;
    }

    &:before {
      content: '';
      background: black;
      position: absolute;
      top: -100px;
      right: calc(-2px - 140px);
      width: 4px;
      height: 400px;
    }
    &:after {
      content: '';
      background: black;
      position: absolute;
      top: 50%;
      right: calc(0px - 140px);
      width: 140px;
      height: 4px;
    }

    &:nth-child(even) {
      left: 320px;
      &:before {
        content: '';
        background: black;
        position: absolute;
        top: -100px;
        left: calc(-2px - 160px);
        width: 4px;
        height: 400px;
      }
      &:after {
        content: '';
        background: black;
        position: absolute;
        top: 50%;
        left: calc(0px - 160px);
        width: 160px;
        height: 4px;
      }
    }
  }

  @media(max-width: 1024px) {
    .historyItem {
      right: 160px;

      &:nth-child(even) {
        left: 160px;
      }
    }
  }

  @media(max-width: 768px) {
    .historyItem {
      width: 100%;
      right: 0px;
      left: 0px !important;
      &:before {
        top: 100%;
        right: 50%;
        height: 200px;
      }
      &:after {
        display: none;
      }
      &:nth-child(even) {
        &:before {
          top: 100%;
          left: 50%;
          height: 200px;
        }
      }
    }
  }

</style>
