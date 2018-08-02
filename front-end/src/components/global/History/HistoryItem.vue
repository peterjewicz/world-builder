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
    margin: 100px 0;
    position: relative;

    &:not(:last-of-type) {
      &:before {
        content: '';
        background: black;
        position: absolute;
        bottom: -100px;
        left: calc(50% - 2px);
        width: 4px;
        height: 100px;
      }
    }
  }

</style>
