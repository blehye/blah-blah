<template>
  <div class="container">
    <Category @filterCategory="filter"></Category>
    <section class="board-section">
      <BoardOne
        :cate="category"
        v-for="(board, i) in boardList"
        :key="i"
        :board="board"
      ></BoardOne>
    </section>
  </div>
</template>
<script>
import axios from 'axios'
import Category from '../components/Category.vue'
import BoardOne from '../components/BoardOne.vue'
export default {
  components: { Category, BoardOne },
  data() {
    return {
      categoryList: [],
      category: '',
      boardList: []
    }
  },
  setup() {},
  created() {
    const category = this.$route.params.category
    this.category = category
    console.log(this.category)
    axios
      .post('/api/board/list/get', this.category)
      .then((response) => {
        console.log(response.data)
        this.boardList = response.data
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {
    filter(categoryKey) {
      this.category = categoryKey
      axios
        .post('/api/board/list/get', this.category)
        .then((response) => {
          this.boardList = response.data
        })
        .catch((error) => console.log(error))
    }
  }
}
</script>
<style scoped>
.board-section {
  margin-bottom: 150px;
}
</style>
