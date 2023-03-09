<template>
<div class="blackBG" :class="{ show: isActive }" ref="apple">
        <div id="modal-whiteBG">
          <button type="button" id="modal-closed" @click="closeModal">
            <span class="material-symbols-outlined">close</span>
          </button>

          <div id="logo">
            <a href=""><img src="" alt="루테스로고" /></a>
          </div>

          <form>
            <div>
              <input
                class="inputbox"
                type="text"
                name="id"
                placeholder="이메일"
                v-model="id"
              />
            </div>

            <div>
              <input
                class="inputbox"
                type="password"
                name="pwd"
                autocomplete="off"
                placeholder="비밀번호"
                v-model="pwd"
              />
            </div>

            <input @click="login(event)" type="button" class="modal-login-btn" value="로그인" />
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
</template>
<script>
import axios from 'axios'

export default {
  props: {
    isActive: {
      type: Boolean,
      default: false
    }
  },
  components: {},
  data() {
    return {
      isActive2: this.isActive,
      id: '',
      pwd: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    showModal() {
      this.isActive2 = true
    },
    closeModal() {
      this.$emit('closeModal')
    },
    login(event) {
      if (this.id === '') {
        alert('아이디를 입력해주세요')
        event.preventDefault()
      }
      if (this.pwd === '') {
        alert('패스워드를 입력해주세요')
        event.preventDefault()
      }
      const loginData = {}
      loginData.email = this.id
      loginData.pwd = this.pwd

      axios.post('/api/member/login', loginData)
        .then(response => {
          const result = response.data.result
          if (result === 'SUCCESS') {
            this.closeModal()
            console.log(response.data)
            this.$store.commit('login', response.data.loginMember)
            this.$router.push({ path: '/' })
          } else {
            alert('로그인 실패')
          }
        })
        .catch((error) => console.log(error))
    }
  }
}
</script>
<style scoped>
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
    left: calc(50% - 191px);
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
  /* 모달 끝 */
</style>
