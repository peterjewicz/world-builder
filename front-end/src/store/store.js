import Vuex from 'vuex'
import Vue from 'vue';

Vue.use(Vuex)

const state = {
  worlds: []
}

const getters = {
  getWorlds: state => state.worlds
};

const mutations = {
  /**
   * Updates the store worlds with the current list - overrides all
   * @param {Object} state - the current state
   * @param {Array} payload - an array of world objects
   */
  saveWorlds: (state, payload) => {
    state.worlds = payload;
  }
};

export default new Vuex.Store({
  state: state,
  getters: getters,
  mutations
})
