<template>
    <article class="container">
      <header class="header-container">
        <section class="header-category">
          <div>{{this.board.categoryName}}</div>
        </section>
        <section class="header-title">
          <div>{{this.board.title}}</div>
        </section>
        <section class="board-info">
          <div class="writer">{{this.board.writer.nick}}</div>
          <div class="board-info-detail">
            <ul class="hit-like-cmt">
              <li>
                <i class="fa-solid fa-eye"></i>
                <span>{{ this.board.hit }}</span>
              </li>
              <li>
                <i class="fa-solid fa-heart"></i>
                <span>{{ this.board.likeCnt }}</span>
              </li>
              <li>
                <i class="fa-regular fa-comment"></i>
                <span>{{ this.board.cmtCnt }}</span>
              </li>
              <div v-if="this.$store.state.loginMember.email === this.board.writer.email">
                <li>
                  <input type="button" value="수정" class="edit-btn" @click="editBoard">
                  <input type="button" value="삭제" class="delete-btn" @click="deleteBoard">
                </li>
              </div>
              <div v-else></div>

            </ul>
            <ul class="date-scrap">
              <li>
                <span>{{ this.board.enrollDate | dateFormat }}</span>
              </li>
              <li>
                <i class="fa-regular fa-bookmark"></i>
              </li>
            </ul>
          </div>
      </section>
      </header>
      <section class="content-container">
        <p align="left">
          <img :src="'../../../blahblah/src/main/resources/static/upload/image_2321395129986775.jpg'" alt="게시판이미지" v-for="(img, i) in board.imageInfoList" :key="i">
          <br>
          {{ this.board.content }}
        </p>
      </section>
    </article>
  </template>
<script>
import Swal from 'sweetalert2'
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      id: '',
      board: {},
      category: '',
      categoryName: ''
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
    this.id = this.$route.params.id

    axios
      .post('/api/board/one/get', this.id)
      .then((response) => {
        this.board = response.data
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {
    editBoard() {
      this.$router.push('/b/edit/' + this.id)
    },
    deleteBoard() {
      Swal.fire({
        icon: 'success',
        title: '정말 삭제하시겠습니까?',
        text: '( ㅠ.ㅠ )',
        showCancelButton: true,
        confirmButtonColor: '#007ff7',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'

      }).then((result) => {
        if (result.isConfirmed) {
          axios
            .post('/api/board/one/del', this.id)
            .then((response) => {
              this.$router.push('/')
            })
            .catch((error) => console.log(error))
        }
      }
      )
    }
  }
}
</script>
<style scoped>
  .flex {
    display: flex;
  }
  .container {
    width: 1100px;
    margin: 0 auto;
  }
  .header-container {
    height: 170px;
    margin: 40px 0px;

    font-weight: 600;
    border-bottom: 1px solid rgb(236, 236, 236);
  }
  .header-category {
    display: flex;
    margin-bottom: 20px;
  }
  .header-title {
    display: flex;
    font-size: 25px;
    margin-bottom: 20px;
  }
  .board-info {
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
  .hit-like-cmt li {
    display: flex;
    align-items: center;
    margin-right: 20px;
    font-weight: 400;
    color: gray;
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
    margin-bottom: 20px;
    font-weight: 400;
  }
  .content-container p{
    line-height: 25px;
    padding-bottom: 50px;
    margin-bottom: 150px;
    border-bottom: 1px solid rgb(236, 236, 236);
  }
  .edit-btn, .delete-btn {
    margin-right: 5px;
    border: none;
    background-color: white;
    font-size: 15px;
    cursor: pointer;
  }
</style>
