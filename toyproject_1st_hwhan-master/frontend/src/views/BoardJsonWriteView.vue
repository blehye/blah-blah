<template>
  <div class="container">
    <div class="write-container">
      <section class="btn-container">
        <div class="flex">
          <h1>파일로 게시글 작성</h1>
          <h2 @click="writeBoard">신규 게시글 작성</h2>
        </div>
        <div>
          <input
            type="button"
            value="취소"
            class="cancel-btn"
            @click="cancel"
          />
          <input
            type="button"
            value="등록"
            class="enroll-btn"
            @click="writeBoardOneByJson"
          />
        </div>
      </section>
      <section class="file-container">
        <input
          type="file"
          class="file-btn"
          style="margin-bottom: 10px; width: 300px"
          @change="addFile($event)"
        />
      </section>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      file: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    writeBoardOneByJson() {
      axios({
        method: 'post',
        url: '/api/board/json/add',
        headers: {
          'Content-Type': 'application/json'
        },
        data: this.file
      })
        .then((response) => {
          console.log(response.data)
          this.setting = response.data
          this.$router.push({ path: '/' })
          alert('게시글 작성이 완료되었습니다')
        })
        .catch((error) => console.log(error))
    },
    addFile(event) {
      console.log(event.target.files[0])
      this.file = event.target.files[0]
    },
    cancel() {
      this.$router.go(-1)
    },
    writeBoard() {
      this.$router.push('/b/write')
    }
  }
}
</script>
<style scoped>
.flex {
  display: flex;
  align-items: flex-start;
}
.container {
  width: 1100px;
  margin: 0 auto;
}
.write-container {
  margin: 50px;
  padding: 40px 60px 60px 60px;
  border: 1px solid rgb(234, 234, 234);
  border-radius: 20px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}
.file-container {
  display: flex;
  flex-direction: column;
}
.file-container input {
  width: 300px;
}
.file-btn {
  margin-bottom: 10px;
}

.btn-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
}
.btn-container h1 {
  font-size: 22px;
  font-weight: 500;
  margin-left: 5px;
  margin-right: 10px;
}
.btn-container h2 {
  font-size: 14px;
  font-weight: 400;
  border: 1px solid var(--border);
  color: gray;
  padding: 5px;
  cursor: pointer;
}
.enroll-btn,
.cancel-btn {
  width: 65px;
  height: 35px;
  color: white;
  border: none;
  font-size: 15px;
  cursor: pointer;
  margin-left: 5px;
}
.enroll-btn {
  background-color: var(--main-color);
}
.cancel-btn {
  background-color: rgb(165, 165, 165);
}
.enroll-btn:hover {
  background-color: var(--main-color-hover);
}
</style>
