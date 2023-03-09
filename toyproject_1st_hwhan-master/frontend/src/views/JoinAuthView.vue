<template>
  <div id="certification-area">
    <div id="certification">
      <div id="certification-title">
        <div id="title-big"><span>인증메일</span>을 보냈어요</div>
        <div id="title-small">메일을 확인해주세요✉️</div>
      </div>

      <div id="certification-content">
        아직 한단계가 더 남았어요!<br />
        <span>가입하신 이메일을 인증</span>해주시면,<br />
        블라블라의 모든 서비스를 이용하실 수 있어요!
      </div>

      <div id="certification-btn">
        <div>🥰 가입해주셔서 진심으로 감사합니다 🥰</div>
        <button id="login-btn2" @click="openLoginModal">로그인</button>
        <button id="main-btn" @click="goHome">메인으로</button>
      </div>
    </div>
    <LoginModal :isActive="isActive" @closeModal="closeModal"></LoginModal>
  </div>
</template>
<script>
import LoginModal from '../components/LoginModal.vue'
export default {
  components: { LoginModal },
  data() {
    return {
      isActive: false
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    openLoginModal() {
      this.isActive = true
    },
    closeModal() {
      this.isActive = false
    },
    goHome() {
      this.$router.push({ path: '/' })
    }
  }
}
</script>
<style scoped>
#certification-area {
  width: 100%;
  height: 100%;
  font-family: 'Noto Sans KR', sans-serif;
}
#certification {
  width: 500px;
  padding: 50px;
  padding-bottom: 330px;
  margin: auto;
  display: grid;
  grid-template-rows: 120px 150px 200px;
  text-align: center;
  align-items: center;
}
#title-big {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 15px;
}
#title-small,
#certification-content,
#certification-btn div {
  font-size: 16px;
}
#certification-content {
  line-height: 170%;
}
#certification-btn button {
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  cursor: pointer;
  width: 320px;
  height: 48px;
  border-radius: 4px;
  margin-top: 20px;
}
#certification-btn #login-btn2 {
  background-color: var(--main-color);
  color: #ffffff;
}
#certification-btn #main-btn {
  border: 1px solid var(--dark-blue);
  color: var(--dark-blue);
}
#title-big span {
  color: var(--dark-blue);
}
#certification-content span {
  font-weight: 700;
}
</style>
