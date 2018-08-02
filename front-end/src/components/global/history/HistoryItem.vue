<template>
  <div class="historyItem">
    <h2>{{title}}</h2>
    <p>{{time}}</p>
    <p>{{desc}}</p>
  </div>
</template>

<script>

export default {
  name: 'HistoryItem',
  props: ['title', 'time', 'desc', 'arrowUp', 'arrowDown'],
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

  .historyItem {
    width: 100%;
    padding: 10px;
    text-align: center;
    background: #dad95f;
    margin: 200px 0;
    position: relative;
    right: 320px;
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

    // &:not(:last-of-type) {
    //   &:before {
    //     content: '';
    //     background: black;
    //     position: absolute;
    //     to: -100px;
    //     left: calc(50% - 2px);
    //     width: 4px;
    //     height: 100px;
    //   }
    // }
  }

</style>
