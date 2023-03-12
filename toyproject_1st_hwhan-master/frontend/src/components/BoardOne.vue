<template>
  <div class="container">
    <header>{{ board.title }}</header>
    <div class="content">
      {{ board.content }}
    </div>
    <div class="board-info">
      <div class="writer">{{ board.writer }}</div>
      <div class="board-info-detail">
        <ul class="hit-like-cmt">
          <li>
            <i class="fa-solid fa-eye"></i>
            <span>{{ board.hit }}</span>
          </li>
          <li>
            <i class="fa-solid fa-heart"></i>
            <span>{{ board.likeCnt }}</span>
          </li>
          <li>
            <i class="fa-regular fa-comment"></i>
            <span>{{ board.cmtCnt }}</span>
          </li>
        </ul>
        <ul class="date-scrap">
          <li>
            <span>2023-03-06</span>
          </li>
          <li>
            <i class="fa-regular fa-bookmark"></i>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    cate: String,
    board: Object
  },
  components: {},
  data() {
    return {
      category: ''
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
.flex {
  display: flex;
}
.container {
  width: 1100px;
  margin: 0 auto;
  border-bottom: 1px solid var(--border);
}

.container > header {
  height: 70px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0px 30px;
  font-weight: 600;
}

.content {
  height: 32px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 0px 30px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  overflow: hidden;
}

.board-info {
  padding: 22px 30px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  font-size: 14px;
}

.board-info-detail {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.hit-like-cmt {
  display: flex;
  width: 110px;
  justify-content: space-between;
}
.hit-like-cmt li i {
  margin-right: 3px;
}
.date-scrap {
  display: flex;
}
.date-scrap li {
  margin-left: 15px;
}
.writer {
  margin-bottom: 15px;
}
</style>
