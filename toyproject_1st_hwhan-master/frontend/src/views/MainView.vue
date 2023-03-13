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
      <div class="hot-topic">
        <header>실시간 검색어</header>
        <ul class="hot-topic-list">
          <li>
            <span class="rank">1</span>
            <span class="hot-topic-name">한혜원</span>
          </li>
          <li>
            <span class="rank">2</span>
            <span class="hot-topic-name">혜원이</span>
          </li>
          <li>
            <span class="rank">3</span>
            <span class="hot-topic-name">Han Hye Won</span>
          </li>
          <li>
            <span class="rank">4</span>
            <span class="hot-topic-name">봉달희</span>
          </li>
          <li>
            <span class="rank">5</span>
            <span class="hot-topic-name">검정봉다리</span>
          </li>
          <li>
            <span class="rank">6</span>
            <span class="hot-topic-name">여행가고싶당</span>
          </li>
          <li>
            <span class="rank">7</span>
            <span class="hot-topic-name">검색 중</span>
          </li>
          <li>
            <span class="rank">8</span>
            <span class="hot-topic-name">얌얌</span>
          </li>
          <li>
            <span class="rank">9</span>
            <span class="hot-topic-name">냐미냐미</span>
          </li>
          <li>
            <span class="rank">10</span>
            <span class="hot-topic-name">소금빵</span>
          </li>
        </ul>
      </div>
      <div class="main-ad1">
        <img src="@/assets/upload/ad1.png" alt="" />
      </div>
      <div class="main-ad2">
        <img src="@/assets/upload/ad2.gif" alt="" />
      </div>
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
.main-ad1,
.main-ad2 {
  width: 300px;
  margin-bottom: 20px;
}
.hot-topic {
  width: 300px;
  height: 378px;
  background-color: #f8f8f8;
  padding: 30px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
}
.hot-topic header {
  font-size: 17px;
  font-weight: 600;
  margin-bottom: 10px;
}
.hot-topic-list li {
  margin: 15px 0px;
  font-size: 14px;
  display: flex;
}
.rank {
  color: gray;
  margin-right: 16px;
  font-weight: 600;
  width: 16px;
}
article {
  display: grid;
  grid-template-columns: 1fr 1fr;
  row-gap: 40px;
  margin: 40px 0px;
}
</style>
