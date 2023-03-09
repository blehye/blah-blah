<template>
  <div class="container">
    <div class="write-container">
      <section class="title-container">
        <select name="" id="" class="category">
          <option value="">카테고리를 선택하세요</option>
          <option value="">카테고리1</option>
          <option value="">카테고리2</option>
          <option value="">카테고리3</option>
        </select>
        <input type="text" placeholder="제목을 입력하세요" class="title" />
      </section>
      <section class="content-container">
        <textarea
          name=""
          id=""
          cols="30"
          rows="10"
          placeholder="내용을 입력하세요"
          class="content"
        ></textarea>
      </section>
      <section class="file-container" v-html="fileTag"></section>
      <section class="file-add-btn-container">
        <input
          type="button"
          class="addBtn"
          value="파일 추가하기"
          @click="addFile(event)"
        />
      </section>
      <section class="enroll-btn-container">
        <input type="button" value="등록" class="enroll-btn" />
      </section>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      fileCnt: 1,
      fileTag:
        '<input type="file" name="" id="" class="file-btn" style="margin-bottom: 10px; width:300px;">'
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    addFile(event) {
      if (this.fileCnt === 5) {
        alert('파일은 5개까지 첨부 가능합니다')
        event.preventDefault()
      }
      this.fileTag +=
        '<input type="file" name="" id="" class="file-btn" style="margin-bottom: 10px; width:300px;">'
      this.fileCnt += 1
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
.file-container input {
  width: 300px;
}
.file-btn {
  margin-bottom: 10px;
}

.file-add-btn-container {
  display: flex;
}
.enroll-btn-container {
  display: flex;
  justify-content: flex-end;
}
.enroll-btn {
  width: 60px;
  height: 30px;
  background-color: var(--main-color);
  color: white;
  border: none;
  font-size: 15px;
  cursor: pointer;
}
.enroll-btn:hover {
  background-color: rgb(17, 84, 192);
}
</style>
