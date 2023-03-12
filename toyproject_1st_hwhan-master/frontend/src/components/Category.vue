<template>
  <section class="container">
    <nav class="wrapper category-nav">
      <router-link
        :to="{ path: '/board/' + category.key }"
        v-for="category in categoryList"
        :key="category.key"
        @click.native="filter(category.key)"
        >{{ category.name }}</router-link
      >
    </nav>
  </section>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
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
        this.categoryList = response.data
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {
    filter(key) {
      this.$emit('filterCategory', key)
    }
  }
}
</script>
<style scoped>
.container {
  border-bottom: 1px solid var(--border);
}
.wrapper {
  width: 1100px;
  margin: 0 auto;
}
.category-nav {
  display: flex;
  justify-content: space-between;
}
</style>
