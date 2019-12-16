import Vuex from 'vuex'
import Vue from 'vue';

Vue.use(Vuex)

const state = {
  worlds: [],
  currentWorld: '',
  values: ''
}

const getters = {
  getWorlds: state => state.worlds,
  getCurrentWorld: state => state.currentWorld,
  getValues: state => state.values
};

const mutations = {
  /**
   * Updates the store worlds with the current list - overrides all
   * @param {Object} state - the current state
   * @param {Array} payload - an array of world objects
   */
  saveWorlds: (state, payload) => {
    state.worlds = payload;
  },
  /**
   * Updates the current world
   * @param {Object} state - the current state
   * @param {String} payload - a string id of the current world
   */
  saveCurrentWorld: (state, payload) => {
    state.currentWorld = payload;
  },
  /**
   * Updates the values which holds the entites for the current world
   * This makes everything faster and requires less API calls
   * @param {Object} state - the current state
   * @param {Object} payload - a object representation of this worlds entities
   */
  saveValue: (state, payload) => {
    state.values = payload;
  },
  /**
   * Adds a new story to the store
   * @param {Object} story - a story object
   * @param {Object} payload - object of current state
   */
  addStory: (state, payload) => {
    state.values.stories.push(payload)
  }
};

export default new Vuex.Store({
  state: state,
  getters: getters,
  mutations
})
