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
          <router-link to="/"> LUTES </router-link>
        </h1>
        <div class="user-area">
          <!-- <input type="button" value="글쓰기"> -->
          <div v-if="this.$store.state.loginMember.email == ''">
            <router-link to="/join">
              <input type="button" value="JOIN" class="enrollBtn" />
            </router-link>
            <input
              type="button"
              value="LOGIN"
              class="enrollBtn"
              @click="openLoginModal"
            />
          </div>
          <div v-else>
            <div class="login-state-btns">
              <router-link to="/board/write">
                <input type="button" value="글쓰기" class="writeBtn" />
              </router-link>
              <router-link to="/mypage">
                <div class="mypage-btn"><i class="fa-solid fa-user"></i></div>
              </router-link>
              <div class="logout-btn" @click="logout">
                <i class="fa-solid fa-arrow-right-from-bracket"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
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
    logout() {
      this.$store.commit('logout')
      this.$router.push({ path: '/' })
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
  cursor: pointer;
}
.writeBtn {
  border: none;
  width: 74px;
  height: 35px;
  font-size: 20px;
  background-color: var(--main-color);
  color: white;
  font-size: 15px;
  cursor: pointer;
}
.login-state-btns {
  display: flex;
  align-items: center;
}
.mypage-btn {
  width: 35px;
  height: 35px;
  border-radius: 50%;
  background-color: rgb(192, 192, 192);
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0px 15px;
}
.logout-btn {
  color: gray;
  font-size: 20px;
  cursor: pointer;
}
</style>
