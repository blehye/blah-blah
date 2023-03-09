<template>
  <div id="app">
    <Header @openLoginModal="openLoginModal"></Header>
    <router-view/>
    <Footer></Footer>
  </div>
</template>
<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  components: {
    Header,
    Footer
  },
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {},
  created() {

  },
  mounted() {},
  unmounted() {},
  methods: {
    openLoginModal() {
      console.log('open login modal')
    }
  }
}
</script>

<style>
@import './assets/styles/reset.css';
@import './assets/styles/variables.css';
@import './assets/styles/common.css';

*,
div,
span {
  font-family: 'Noto Sans KR', sans-serif;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
