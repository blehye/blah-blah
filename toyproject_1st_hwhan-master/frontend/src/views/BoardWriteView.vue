<template>
  <div class="container">
    <div class="write-container">
      <section class="btn-container">
        <div class="flex">
          <h1>신규 게시글 작성</h1>
          <h2 @click="writeJson">파일로 게시글 작성</h2>
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
            @click="writeBoardOne"
          />
        </div>
      </section>
      <section class="title-container">
        <select name="" id="" class="category" v-model="category">
          <option value="">카테고리를 선택하세요</option>
          <option
            :value="category.key"
            :key="category.key"
            v-for="category in categoryList"
          >
            {{ category.name }}
          </option>
        </select>
        <input
          type="text"
          placeholder="제목을 입력하세요"
          class="title"
          v-model="title"
        />
      </section>
      <section class="content-container">
        <textarea
          name=""
          id=""
          cols="30"
          rows="10"
          placeholder="내용을 입력하세요"
          class="content"
          spellcheck="false"
          v-model="content"
        ></textarea>
      </section>
      <section class="file-container">
        <input
          type="file"
          class="file-btn"
          style="margin-bottom: 10px; width: 300px"
          @change="addImage($event)"
          v-for="(file, i) in fileCnt"
          :key="i"
        />
      </section>
      <section class="file-add-btn-container">
        <input
          type="button"
          class="addBtn"
          value="파일 추가하기"
          @click="addFile()"
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
      title: '',
      content: '',
      category: '',
      image: [],
      categoryList: [],
      setting: {},
      event: '',
      fileCnt: 1
    }
  },
  setup() {},
  created() {
    this.fileCnt = 1
    axios
      .post('/api/board/category/get')
      .then((response) => {
        // console.log(response.data)
        this.categoryList = response.data
      })
      .catch((error) => console.log(error))
    axios
      .post('/api/board/setting/get')
      .then((response) => {
        // console.log(response.data)
        this.setting = response.data
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {
    addFile(event) {
      if (this.fileCnt === parseInt(this.setting.maxImg)) {
        alert(`파일은 ${this.setting.maxImg}개까지 첨부 가능합니다`)
        event.preventDefault()
      }
      this.fileCnt += 1
    },
    writeBoardOne() {
      const formData = new FormData()
      formData.append('category', this.category)
      formData.append('title', this.title)
      formData.append('content', this.content)
      for (let i = 0; i < this.image.length; i++) {
        formData.append('image', this.image[i])
      }
      axios({
        method: 'post',
        url: '/api/board/add',
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        data: formData
      })
        .then((response) => {
          console.log(response.data)
          this.setting = response.data
          this.$router.push({ path: '/' })
          alert('게시글 작성이 완료되었습니다')
        })
        .catch((error) => console.log(error))
    },
    addImage(event) {
      console.log(event.target.files[0])
      this.image.push(event.target.files[0])
    },
    cancel() {
      this.$router.go(-1)
    },
    writeJson() {
      this.$router.push('/b/json/write')
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
.category {
  width: 100%;
  height: 50px;
  border: 1px solid var(--border);
  border-radius: 7px;
  outline: none;
  margin-bottom: 10px;
  padding: 0px 17px;
  font-size: 15px;
  color: gray;
}
.title {
  width: 100%;
  height: 50px;
  color: gray;
  border: 1px solid var(--border);
  border-radius: 7px;
  padding: 0px 20px;
  font-size: 15px;
}
.title:focus,
.content:focus,
.category:focus {
  outline: 1px solid rgb(201, 227, 255);
  border: none;
}
.content {
  width: 100%;
  margin: 10px 0px;
  border-radius: 7px;
  border: 1px solid var(--border);
  padding: 20px;
  font-size: 15px;
  resize: none;
  height: 400px;
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
.file-add-btn-container {
  display: flex;
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
