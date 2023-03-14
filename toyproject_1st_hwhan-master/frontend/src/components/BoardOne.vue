<template>
    <div class="container">
      <header>
        <router-link :to="{ path: '/b/detail/' + board.no}">
          {{ board.title }}
        </router-link>
      </header>
      <div class="content" align="left">
        <router-link :to="{ path: '/b/detail/' + board.no}">
          {{ board.content }}
        </router-link>
      </div>
      <div class="board-info">
        <div class="writer">{{ board.writer.nick }}</div>
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
              <span>{{board.enrollDate | dateFormat}}</span>
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
  filters: {
    dateFormat(value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value === '') return ''

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      const date = new Date(value)

      // 연도, 월, 일 추출
      const year = date.getFullYear()
      let month = date.getMonth() + 1
      let day = date.getDate()

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = '0' + month
      }

      if (day < 10) {
        day = '0' + day
      }

      // 최종 포맷 (ex - '2021-10-08')
      return year + '-' + month + '-' + day
    }
  },
  setup() {},
  created() {
  },
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
