<template>
    <div class="container">
      <div class="write-container">
        <section class="title-container">
          <input type="text" placeholder="제목을 입력하세요" class="title">
          <select name="" id="">
            <option value="">카테고리</option>
            <option value="">카테고리</option>
            <option value="">카테고리</option>
          </select>
        </section>
        <section class="content-container">
          <textarea name="" id="" cols="30" rows="10" placeholder="내용을 입력하세요" class="content"></textarea>
        </section>
        <section class="file-container">
          <input type="file" name="" id="">
        </section>
      </div>
    </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      sampleData: ''
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
.container {
  width: 1100px;
  margin: 0 auto;
}
.write-container {
  margin: 50px;
  padding: 40px;
  border: 1px solid var(--border);
}
.title {
  width: 100%;
  height: 50px;
  color: gray;
  border: 1px solid var(--border);
}

.title:focus {
  outline: 1px solid var(--main-color);

}
</style>
