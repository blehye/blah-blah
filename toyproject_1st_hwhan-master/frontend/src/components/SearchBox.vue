<template>
    <div class="container">
        <i class="fa-solid fa-magnifying-glass"></i>
        <input type="search" name="" id="" placeholder="관심있는 내용을 검색해보세요!" class="search-box">
    </div>
</template>
<script>
export default {
  components: {},
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
    height: 60px;
    width: 735px;
    position: relative;
}
.container i{
    position: absolute;
    left: 26px;
    top: 20px;
    font-size: 20px;
}

.search-box {
    width: 100%;
    height: 100%;
    outline: none;
    border: 2px solid black;
    border-radius: 50px;
    font-size: 18px;
    color: black;
    padding: 20px 20px 20px 60px;
}
</style>
