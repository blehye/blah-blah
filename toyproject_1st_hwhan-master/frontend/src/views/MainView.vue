<template>
  <div class="wrapper">
    <div class="wrapper-left">
      <search-box></search-box>
      <article>
        <BoardPreview
          v-for="category in categoryList"
          :key="category.key"
          :category="category"
        ></BoardPreview>
      </article>
    </div>
    <div class="wrapper-right">
      <div class="hot-topic"></div>
      <div class="main-ad1"></div>
      <div class="main-ad2"></div>
    </div>
  </div>
</template>
<script>
import SearchBox from '../components/SearchBox.vue'
import BoardPreview from '../components/BoardPreview.vue'
import axios from 'axios'
export default {
  components: {
    SearchBox,
    BoardPreview
  },
  data() {
    return {
      categoryList: []
    }
  },
  setup() {},
  created() {
    axios
      .post('/api/board/category/get')
      .then((response) => {
        console.log(response.data)
        this.categoryList = response.data
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
.wrapper {
  width: 1100px;
  margin: 0 auto;
  margin-top: 40px;
  display: flex;
}

.wrapper-left {
  width: 735px;
}
.wrapper-right {
  width: 365px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.wrapper-right div {
  width: 300px;
  height: 300px;
  background-color: rgb(210, 229, 236);
  margin-bottom: 40px;
}

article {
  display: grid;
  grid-template-columns: 1fr 1fr;
  row-gap: 40px;
  margin: 40px 0px;
}
</style>
