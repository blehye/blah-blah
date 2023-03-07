<template>
  <div class="container">
    <div class="header-top">
      <div class="introduce">
        <i class="fa-regular fa-face-smile"></i>
        <a>홈페이지 소개</a>
      </div>
    </div>
    <header class="header">
      <div class="wrapper">
        <h1 class="main-logo">
          <router-link to="/"> Lutes </router-link>
        </h1>
        <div class="user-area">
          <!-- <input type="button" value="글쓰기"> -->
          <router-link to="/join">
            <input type="button" value="JOIN" class="enrollBtn" />
          </router-link>
          <input
            type="button"
            value="LOGIN"
            class="enrollBtn"
            @click="showModal"
          />
        </div>
      </div>
    </header>
    <div class="blackBG" :class="{ show: isActive }">
      <div id="modal-whiteBG">
        <button type="button" id="modal-closed" @click="closeModal">
          <span class="material-symbols-outlined">close</span>
        </button>

        <div id="logo">
          <a href=""><img src="" alt="루테스로고" /></a>
        </div>

        <form action="" method="POST" onsubmit="return check2();">
          <div>
            <input
              class="inputbox"
              type="text"
              name="id"
              placeholder="이메일"
            />
          </div>

          <div>
            <input
              class="inputbox"
              type="password"
              name="pwd"
              autocomplete="off"
              placeholder="비밀번호"
            />
          </div>

          <button type="submit" class="modal-login-btn">로그인</button>
        </form>

        <div id="account_find">
          <a href="">아이디(이메일) 찾기</a>
          <span>|</span>
          <a href="">비밀번호 찾기</a>
          <span>|</span>
          <a href="">회원가입</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
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
    showModal() {
      this.isActive = true
    },
    closeModal() {
      this.isActive = false
    }
  }
}
</script>
<style scoped>
.header-top {
  height: 40px;
  background-color: rgb(1, 2, 80);
  color: white;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.introduce {
  margin-left: 20px;
  display: flex;
  align-items: center;
}

.introduce a {
  font-size: 14px;
  margin-left: 5px;
}
.header {
  height: 80px;
  border-bottom: 1px solid #d1d1d1;
}

.header > div {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.wrapper {
  width: 1100px;
  height: 100%;
  margin: 0 auto;
}

.main-logo {
  font-size: 40px;
  font-weight: 700;
}

.enrollBtn {
  border: none;
  font-size: 20px;
  background-color: white;
}

/* 모달 시작 */
.blackBG {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  display: none;
  --opacity: 0;
  z-index: 10000;
}
.hidden {
  display: none;
}
.show {
  display: block;
  padding: 10px;
}

#modal-whiteBG {
  text-align: center;
  width: 383px;
  height: 450px;
  position: absolute;
  top: 28vh;
  left: 40vw;
  background: white;
  border-radius: 8px;
  padding: 30px;
  display: grid;
  grid-template-rows: 30px 80px 220px 75px;
  z-index: 10001;
}
#modal-whiteBG form {
  display: flex;
  flex-direction: column;
}
#modal-whiteBG form * {
  margin-bottom: 15px;
}
#login-btn,
#modal-closed,
.modal-login-btn,
#alarm-btn {
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  cursor: pointer;
}

.inputbox {
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  outline: none;
}
.material-symbols-outlined {
  display: flex;
  justify-content: flex-end;
  font-variation-settings: 'FILL' 0, 'wght' 200, 'GRAD' 0, 'opsz' 48;
}
.inputbox {
  width: 100%;
  height: 50px;
  padding: 14px;
  border-radius: 4px;
  border: 1px solid #d9d9d9;
}
.modal-login-btn {
  width: 320px;
  height: 48px;
  border-radius: 4px;
  background-color: var(--main-color);
  color: #ffffff;
}
#line {
  display: flex;
  flex-basis: 100%;
  align-items: center;
  color: rgba(0, 0, 0, 0.35);
  font-size: 12px;
  margin: 8px 0px;
}
#line::before,
#line::after {
  content: '';
  flex-grow: 1;
  background: rgba(0, 0, 0, 0.35);
  height: 1px;
  font-size: 0px;
  line-height: 0px;
  margin: 0px 16px;
}
#account_find {
  display: flex;
  justify-content: center;
  align-items: center;
}
#account_find * {
  margin: 5px;
  font-size: 12px;
  color: #535353;
}
#loginApi {
  display: flex;
  justify-content: center;
  align-items: center;
}
#loginApi div {
  width: 35px;
  height: 35px;
  background-color: #535353;
  margin: 2px;
}
/* 모달 끗 */
</style>
