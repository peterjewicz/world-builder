<template>
   <div class="dashboard-sidebar hide-on-mobile">
     <h5>Your Worlds</h5>
     <ul class="sidebar-worldList">
       <li v-for="world in this.$store.getters.getWorlds">
         <span class="currentWorld fullwidth" v-if="world._id === selectedWorld">
           {{world.name}} <span class="activeText">(active)</span>
         </span>
         <span class="fullwidth" v-else v-on:click="changeWorld(world._id)">
           {{world.name}}
         </span>
       </li>
       <li><router-link v-bind:to="'create-world'"><span class="addWorld hover-darkblue">Add World +</span></router-link></li>
     </ul>
   </div>
 </template>

<script>
import store from '../../../store/store.js';
import { mapGetters } from 'vuex'

export default {
  name: 'Sidebar',
  data () {
    return {
      worlds: this.$store.getters.getWorlds
    }
  },
  computed: {
    ...mapGetters({
      selectedWorld: 'getCurrentWorld'
    })
  },
  methods: {
    changeWorld(id) {
      store.commit('saveCurrentWorld', id)
      this.$emit('worldUpdated', id)
    }
  }
}
</script>

 <style lang="scss" scoped>
   .dashboard-sidebar {
     text-align: left;

     h5 {
       background: #b169e9;
       color: white;
       text-align: center;
       padding: 15px;
       margin-top: 0px;
     }

     ul {
       list-style: none;
       margin: 0;
       padding: 0;
       text-align: left;
       width: 100%;

       li{
         width: 100%;
         padding: 5px 0;
         cursor: pointer;
       }

       span.fullwidth {
         padding-left: 10px;
         padding-top: 2px;
         padding-bottom: 2px;
         box-sizing: border-box;
         width: 100%;
         display: block;

         transition: all .5s;

         &:not(.currentWorld):hover {
           background: #dbe7db;
         }
       }

       span.activeText {
         font-size: 10px;
       }

       .currentWorld {
         background: #60d25d;
         color: white;
       }
     }
   }
 </style>
