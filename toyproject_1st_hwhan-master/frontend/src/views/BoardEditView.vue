<template>
  <div class="container">
    <div class="write-container">
      <section class="btn-container">
        <h1>게시글 수정</h1>
        <div>
          <input
            type="button"
            value="취소"
            class="cancel-btn"
            @click="cancel"
          />
          <input
            type="button"
            value="수정"
            class="enroll-btn"
            @click="editBoardOne"
          />
        </div>
      </section>
      <section class="title-container">
        <select name="" id="" class="category" v-model="board.category">
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
          v-model="board.title"
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
          v-model="board.content"
        ></textarea>
      </section>
      <section class="file-container">
        <section class="img-container">
          <div
            v-for="item in fileList"
            :key="item.chageName"
            class="img-info"
          >
            <div>{{ item.originName }}</div>
            <div class="delete-btn" @click="deleteFile(item.changeName)"><i class="fa-solid fa-x"></i></div>
          </div>
        </section>
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
      id: '',
      board: {},
      // title: '',
      // content: '',
      // category: '',
      image: [],
      categoryList: [],
      setting: {},
      originFileCnt: null,
      fileCnt: 0,
      fileList: []
    }
  },
  setup() {},
  created() {
    this.fileCnt = 0

    axios
      .post('/api/board/category/get')
      .then((response) => {
        this.categoryList = response.data
      })
      .catch((error) => console.log(error))
    axios
      .post('/api/board/setting/get')
      .then((response) => {
        this.setting = response.data
      })
      .catch((error) => console.log(error))
    this.id = this.$route.params.id
    axios
      .post('/api/board/one/get', this.id)
      .then((response) => {
        console.log(this.id)
        this.board = response.data
        this.originFileCnt = response.data.imageInfoList.length
        this.fileList = response.data.imageInfoList

        console.log(this.fileList.length)
      })
      .catch((error) => console.log(error))
  },
  mounted() {},
  unmounted() {},
  methods: {
    addFile(event) {
      if (this.fileCnt + this.originFileCnt === parseInt(this.setting.maxImg)) {
        alert(`파일은 ${this.setting.maxImg}개까지 첨부 가능합니다`)
        event.preventDefault()
      }
      this.fileCnt += 1
    },
    editBoardOne() {
      const formData = new FormData()
      formData.append('id', this.id)
      formData.append('category', this.board.category)
      formData.append('title', this.board.title)
      formData.append('content', this.board.content)
      formData.append('originFileList', JSON.stringify(this.fileList))
      for (let i = 0; i < this.image.length; i++) {
        formData.append('image', this.image[i])
      }
      axios({
        method: 'post',
        url: '/api/board/set',
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        data: formData
      })
        .then((response) => {
          console.log(response.data)
          this.setting = response.data
          this.$router.go(-1)
          alert('게시글 수정이 완료되었습니다')
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
    deleteFile(changeName) {
      console.log(changeName)
      this.fileList.forEach((item, index) => {
        if (item.changeName === changeName) {
          this.fileList.splice(index, 1)
          this.originFileCnt -= 1
        }
        console.log(this.fileList)
      })
    }
  }
}
</script>
<style scoped>
.container {
  width: 1100px;
  margin: 0 auto;
}
.write-container {
  margin: 50px;
  padding: 60px;
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
.file-container input:not(input[type='button']) {
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
  align-items: center;
  margin-bottom: 15px;
}
.btn-container h1 {
  font-size: 22px;
  font-weight: 500;
  margin-left: 5px;
}
.enroll-btn, .cancel-btn {
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
.img-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 15px;
}
.img-info {
  display: flex;
  align-items: center;
}
.img-info div {
  font-size: 14px;
  margin-left: 10px;
}
.uploaded-img {
  width: 35px;
  height: 35px;
}
.delete-btn {
  width: 18px;
  height: 18px;
  border: none;
  background-color: rgb(193, 193, 193);
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid rgb(189, 189, 189);
  cursor: pointer;
}
.delete-btn i {
  font-size: 13px;
  color: white;
}
</style>
