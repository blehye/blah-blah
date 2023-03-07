<template>
    <div class="container">
      <!-- <nav>
        <router-link to="/topic/blahblah"></router-link>
        <router-link to="/topic/hobby"></router-link>
        <router-link to="/topic/trip"></router-link>
      </nav>
      <router-view/> -->
      <BoardOne></BoardOne>
      <BoardOne></BoardOne>
      <BoardOne></BoardOne>
      <BoardOne></BoardOne>
    </div>
</template>
<script>
import BoardOne from '../components/BoardOne.vue'
export default {
  components: {
    BoardOne
  },
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
</style>
