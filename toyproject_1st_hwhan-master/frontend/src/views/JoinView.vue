<template>
  <div id="join-main">
    <form @submit.prevent="join">
      <div id="join-area">
        <div id="join-title">
          <div class="title">회원가입</div>
          <div class="content">모두가 생각을 나눌수 있도록</div>
        </div>

        <div id="id">
          <div class="text">이메일</div>
          <input
            type="text"
            name="email"
            v-model="email"
            required
            @blur="checkEmail"
          />
          <div id="idResult" class="result"></div>
        </div>

        <div id="pwd">
          <div class="text">비밀번호</div>
          <input
            :type="pwdType"
            name="pwd"
            autocomplete="off"
            v-model="pwd"
            required
          />
          <span
            class="material-symbols-outlined"
            :class="{ pwdActive: pwdActive }"
            @click="togglePwd"
            >{{ pwdActiveIcon }}</span
          >
          <div id="pwdResult" class="result">
            영문,숫자,특수문자 2가지 이상 포함, 8자 이상 32자 이하, 연속 3자
            이상 동일한 숫자,문자 제외
          </div>
        </div>

        <div id="pwd">
          <div class="text">비밀번호 확인</div>
          <input
            :type="pwdType2"
            name="pwd2"
            autocomplete="off"
            placeholder="입력했던 비밀번호를 다시 입력해주세요"
            v-model="pwd2"
            required
          />
          <span
            class="material-symbols-outlined"
            :class="{ pwdActive2: pwdActive2 }"
            @click="togglePwd2"
            >{{ pwdActiveIcon2 }}</span
          >
          <div id="pwdCheck" class="result"></div>
        </div>

        <div id="nick">
          <div class="text">닉네임</div>
          <input
            type="text"
            name="nick"
            placeholder="영문,숫자,한글로 2자 이상 8자 이하"
            v-model="nick"
            required
          />
          <div id="nickResult" class="result"></div>
        </div>

        <div id="phone">
          <div class="text">전화번호</div>
          <input
            type="text"
            name="phone"
            placeholder="- 제외한 숫자만"
            v-model="phone"
            required
          />
          <div id="phoneResult" class="result"></div>
        </div>

        <input type="submit" id="join-btn" value="회원가입" />
      </div>
    </form>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      email: '',
      pwd: '',
      pwd2: '',
      phone: '',
      nick: '',
      pwdType: 'password',
      pwdActive: false,
      pwdActiveIcon: 'visibility_off',
      pwdType2: 'password',
      pwdActive2: false,
      pwdActiveIcon2: 'visibility_off'
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    checkEmail() {},
    togglePwd() {
      this.pwdActive = !this.pwdActive
      if (this.pwdActive) {
        this.pwdType = 'text'
        this.pwdActiveIcon = 'visibility'
      } else {
        this.pwdType = 'password'
        this.pwdActiveIcon = 'visibility_off'
      }
    },
    togglePwd2() {
      this.pwdActive2 = !this.pwdActive2
      if (this.pwdActive2) {
        this.pwdType2 = 'text'
        this.pwdActiveIcon2 = 'visibility'
      } else {
        this.pwdType2 = 'password'
        this.pwdActiveIcon2 = 'visibility_off'
      }
    },
    join() {
      console.log('제출')
      const joinData = {}
      joinData.email = this.email
      joinData.pwd = this.pwd
      joinData.pwd2 = this.pwd2
      joinData.phone = this.phone
      joinData.nick = this.nick
      axios
        .post('/api/member/join', joinData)
        .then((response) => console.log(response.data.result))
        .catch((error) => console.log(error))
    }
  }
}
</script>
<style scoped>
#join-main {
  width: 444px;
  margin: auto;
}
form {
  margin: auto;
}
#join-area {
  height: 900px;
  padding: 50px;
  display: grid;
  grid-template-rows: repeat(9, 1fr);
  align-items: center;
  justify-content: center;
}
#join-title {
  display: grid;
  grid-template-rows: 2fr 1fr;
  justify-items: center;
  align-items: center;
  margin-bottom: 10px;
}
.title {
  font-size: 24px;
  font-weight: 700;
  color: #0e386e;
  margin-bottom: 10px;
}
.text {
  display: flex;
  width: 100%;
  height: 100%;
  color: #535353;
  font-size: 14px;
  margin-top: 20px;
  margin-bottom: 3px;
}
input[type='text']:not(.choices__input),
input[type='password'] {
  width: 100%;
  height: 40px;
  padding: 14px 45px 14px 14px;
  border-radius: 4px;
  border: 1px solid #d9d9d9;
}
#pwd {
  position: relative;
}
#pwd div + input + span {
  position: absolute;
  left: 90%;
  top: 45px;
  color: #d9d9d9;
}
#pwdResult {
  margin-top: 10px;
}
#join-area #join-btn {
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  cursor: pointer;
  height: 48px;
  border-radius: 4px;
  background-color: #225ba7;
  color: #ffffff;
  margin-top: 20px;
}
.joinAgree {
  display: flex;
  justify-content: center;
  font-size: 12px;
  color: #929292;
  text-align: center;
}
#joinAgree-check {
  width: 12px;
  display: flex;
  justify-content: center;
}
input[id='joinAgree-check'] {
  position: relative;
  top: 9.5px;
  left: 15px;
}
label[for='joinAgree-check'] {
  position: relative;
  top: -6px;
  left: 5px;
}
#joinApi {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
}
#joinApi div {
  width: 35px;
  height: 35px;
  background-color: #535353;
  margin: 2px;
}
.result {
  line-height: 16px;
  font-size: 13px;
  color: #929292;
}
</style>
