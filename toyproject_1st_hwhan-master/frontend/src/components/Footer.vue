<template>
  <footer>
    <div class="wrapper">
      <div class="footer-left">
        <ul class="footer-link-list">
          <li>서비스 소개</li>
          |
          <li>이용약관</li>
          |
          <li>디렉토리</li>
          |
          <li>개인정보 처리방침</li>
          |
          <li>BlahBlah 기업서비스</li>
          |
          <li>신고가이드</li>
        </ul>
        <div>© 2023 BlahBlah. Inc</div>
      </div>
      <div>
        <div></div>
        <div></div>
      </div>
    </div>
  </footer>
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
.wrapper {
  width: 1100px;
  margin: 0 auto;
  padding: 30px 0px;
}
footer {
  width: 100%;
  height: 125px;
  border-top: 1px solid #d1d1d1;
}
.footer-left {
  width: 700px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  font-size: 14px;
  color: rgb(160, 160, 160);
}
.footer-link-list {
  display: flex;
  margin-bottom: 25px;
}
.footer-link-list li {
  padding: 0px 7px;
}
</style>
