<template>
   <div class="dashboard-sidebar hide-on-mobile">
     <h4>Your Worlds</h4>
     <ul class="sidebar-worldList">
       <li v-for="world in this.$store.getters.getWorlds">
         <span class="currentWorld" v-if="world._id === selectedWorld">
           {{world.name}}
         </span>
         <span v-else>
           {{world.name}}
         </span>
       </li>
     </ul>
   </div>
 </template>

 <script>
 export default {
   name: 'Sidebar',
   data () {
     return {
       worlds: this.$store.getters.getWorlds,
       selectedWorld: this.$store.getters.getCurrentWorld
     }
   }
 }
 </script>

 <style lang="scss" scoped>
   .dashboard-sidebar {
     ul {
       list-style: none;
       margin: 0;
       padding: 0 10px;
       text-align: left;
     }
   }
 </style>
