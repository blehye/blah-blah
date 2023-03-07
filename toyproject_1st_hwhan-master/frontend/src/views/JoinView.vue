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
          <input type="text" name="id" v-model="id" required @blur="checkId" />
          <div id="idResult" class="result">{{ idResult }}</div>
        </div>

        <div id="pwd">
          <div class="text">비밀번호</div>
          <input
            :type="pwdType"
            name="pwd"
            autocomplete="off"
            v-model="pwd"
            required
            @blur="checkPwd"
          />
          <span
            class="material-symbols-outlined"
            :class="{ pwdActive: pwdActive }"
            @click="togglePwd"
            >{{ pwdActiveIcon }}</span
          >
          <div class="result">{{ pwdResult }}</div>
          <div id="pwdResult" class="result gray">
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
            @blur="checkPwd2"
          />
          <span
            class="material-symbols-outlined"
            :class="{ pwdActive2: pwdActive2 }"
            @click="togglePwd2"
            >{{ pwdActiveIcon2 }}</span
          >
          <div class="result">{{ pwdResult2 }}</div>
        </div>

        <div id="nick">
          <div class="text">닉네임</div>
          <input
            type="text"
            name="nick"
            placeholder="영문,숫자,한글로 2자 이상 8자 이하"
            v-model="nick"
            required
            @blur="checkNick"
          />
          <div id="nickResult" class="result">{{ nickResult }}</div>
        </div>

        <div id="phone">
          <div class="text">전화번호</div>
          <input
            type="text"
            name="phone"
            placeholder="- 제외한 숫자만"
            v-model="phone"
            required
            @blur="checkPhone"
          />
          <div id="phoneResult" class="result">{{ phoneResult }}</div>
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
      id: '',
      pwd: '',
      pwd2: '',
      phone: '',
      nick: '',
      pwdType: 'password',
      pwdActive: false,
      pwdActiveIcon: 'visibility_off',
      pwdType2: 'password',
      pwdActive2: false,
      pwdActiveIcon2: 'visibility_off',
      idResult: '',
      pwdResult: '',
      pwdResult2: '',
      nickResult: '',
      phoneResult: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    checkId() {
      // 이메일 형식이 (알파벳,숫자,-,_,.)@lutes.co.kr
      const idCheck =
        // eslint-disable-next-line
        /^([0-9a-zA-Z_\.-]+)@lutes.co.kr$/

      // 공백 체크
      const spaceCheck = /\s/

      if (this.id === '') {
        this.idResult = '이메일을 입력해주세요'
      } else if (this.id.search(spaceCheck) !== -1) {
        this.idResult = '공백없이 작성해주세요'
      } else if (!idCheck.test(this.id)) {
        this.idResult = '이메일 형식이 올바르지 않습니다'
      } else {
        this.idResult = ''
      }
    },
    checkPwd() {
      // 영문/숫자/특수문자 2가지 이상 포함, 8자 이상 32자 이하
      const pwdCheck =
        /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,32}$/
      // 같은 문자 3번 이상 X
      const pwd3WordCheck = /(\w)\1\1/
      // 공백 체크
      const spaceCheck = /\s/

      if (this.pwd === '') {
        this.pwdResult = '비밀번호를 입력해주세요'
      } else if (this.pwd.search(spaceCheck) !== -1) {
        this.pwdResult = '공백없이 작성해주세요'
      } else if (!pwdCheck.test(this.pwd)) {
        this.pwdResult = '비밀번호를 다시 확인해주세요'
      } else if (pwd3WordCheck.test(this.pwd)) {
        this.pwdResult = '같은 문자를 3번 이상 사용할 수 없습니다'
      } else {
        this.pwdResult = ''
      }
    },
    checkPwd2() {
      if (this.pwd !== this.pwd2) {
        this.pwdResult2 = '비밀번호가 동일하지 않습니다'
      } else {
        this.pwdResult2 = ''
      }
    },
    checkNick() {
      // 닉네임 : 영문, 숫자, 한글로 2자 ~ 8자
      const nickCheck = /^[a-zA-Z0-9가-힣]{2,8}$/
      // 공백 체크
      const spaceCheck = /\s/

      if (this.nick === '') {
        this.nickResult = '닉네임을 입력해주세요'
      } else if (this.nick.search(spaceCheck) !== -1) {
        this.nickResult = '공백없이 작성해주세요'
      } else if (!nickCheck.test(this.nick)) {
        this.nickResult = '닉네임을 다시 확인해주세요'
      } else {
        this.nickResult = ''
      }
    },
    checkPhone() {
      // 전화번호 : 11자리 숫자만
      const phoneCheck = /[0-9]{11}/g
      // 공백 체크
      const spaceCheck = /\s/

      if (this.phone === '') {
        this.phoneResult = '전화번호를 입력해주세요'
      } else if (this.phone.search(spaceCheck) !== -1) {
        this.phoneCheck = '공백없이 작성해주세요'
      } else if (!phoneCheck.test(this.phone)) {
        this.phoneResult = '전화번호를 다시 확인해주세요'
      } else {
        this.phoneResult = ''
      }
    },
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
      joinData.email = this.id
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
  background-color: var(--main-color);
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
  color: #e02525;
  margin-top: 5px;
}
.gray {
  color: #929292;
}
</style>
