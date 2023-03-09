import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
export default new Vuex.Store({
  plugins: [
    createPersistedState()
  ],
  state: {
    loginMember: {
      email: '',
      pwd: '',
      nick: '',
      phone: '',
      enrollDate: ''
    }

  },
  getters: {
  },
  mutations: {
    login(state, payload) {
      state.loginMember.email = payload.email
      state.loginMember.pwd = payload.pwd
      state.loginMember.phone = payload.phone
      state.loginMember.nick = payload.nick
      state.loginMember.enrollDate = payload.enrollDate
    },
    logout(state) {
      state.loginMember.email = ''
      state.loginMember.pwd = ''
      state.loginMember.phone = ''
      state.loginMember.nick = ''
      state.loginMember.enrollDate = ''
    }
  },
  actions: {
  },
  modules: {
  }
})
