<template>
  <div id="join-main">
    <form>
      <div id="join-area">
        <div id="join-title">
          <div class="title">회원가입</div>
          <div class="content">모두가 생각을 나눌수 있도록</div>
        </div>

        <div id="id">
          <div class="text">이메일</div>
          <div class="relative">
            <input type="text" name="id" v-model="id" required @keyup="checkIdValidity" :readonly="validity.checkIdDuplicate == true"/>
            <input type="button" :value="idDupBtnStr" class="duplicate-btn" @click="checkIdDup" :class="{idDupBtnActive : idDupBtnActive}" :disabled="validity.checkIdDuplicate == true">
          </div>

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
            @keyup="checkPwdValidity"
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
            @keyup="checkPwd2Validity"
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
          <div class="relative">
            <input
              type="text"
              name="nick"
              placeholder="영문,숫자,한글로 2자 이상 8자 이하"
              v-model="nick"
              required
              @keyup="checkNickValidity"
              :readonly="validity.checkNickDuplicate == true"
            />
            <input type="button" :value="nickDupBtnStr" class="duplicate-btn" @click="checkNickDup" :class="{nickDupBtnActive: nickDupBtnActive}" :disabled="validity.checkNickDuplicate == true">
          </div>
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
            @keyup="checkPhoneValidity"
          />
          <div id="phoneResult" class="result">{{ phoneResult }}</div>
        </div>

        <input @click="join(event)" type="button" id="join-btn" value="회원가입" :class="{'join-btn-active': joinActive}"/>
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
      phoneResult: '',
      joinActive: false,

      validity: {
        id: false,
        pwd: false,
        pwd2: false,
        nick: false,
        phone: false,
        checkIdDuplicate: false,
        checkNickDuplicate: false
      },
      idDupBtnStr: '중복체크',
      nickDupBtnStr: '중복체크',
      idDupBtnActive: false,
      nickDupBtnActive: false

    }
  },
  setup() {},
  created() {
    console.log(this.$store.state.loginMember)
  },
  mounted() {},
  unmounted() {},
  methods: {
    checkIdValidity() {
      this.checkId()
      this.checkTotalJoinValidity()
    },
    checkPwdValidity() {
      this.checkPwd()
      this.checkTotalJoinValidity()
    },
    checkPwd2Validity() {
      this.checkPwd2()
      this.checkTotalJoinValidity()
    },
    checkNickValidity() {
      this.checkNick()
      this.checkTotalJoinValidity()
    },
    checkPhoneValidity() {
      this.checkPhone()
      this.checkTotalJoinValidity()
    },
    checkId() {
      // 이메일 형식이 (알파벳,숫자,-,_,.)@lutes.co.kr
      const idCheck =
        // eslint-disable-next-line
        /^([0-9a-zA-Z_\.-]+)@lutes.co.kr$/
      // 공백 체크
      const spaceCheck = /\s/

      this.validity.id = false
      if (this.id === '') {
        this.idResult = '이메일을 입력해주세요'
      } else if (this.id.search(spaceCheck) !== -1) {
        this.idResult = '공백없이 작성해주세요'
      } else if (!idCheck.test(this.id)) {
        this.idResult = '이메일 형식이 올바르지 않습니다'
      } else {
        this.idResult = ''
        this.validity.id = true
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
      this.validity.pwd = false
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
        this.validity.pwd = true
      }
    },
    checkPwd2() {
      this.validity.pwd2 = false
      if (this.pwd !== this.pwd2) {
        this.pwdResult2 = '비밀번호가 동일하지 않습니다'
      } else {
        this.pwdResult2 = ''
        this.validity.pwd2 = true
      }
    },
    checkNick() {
      // 닉네임 : 영문, 숫자, 한글로 2자 ~ 8자
      const nickCheck = /^[a-zA-Z0-9가-힣]{2,8}$/
      // 공백 체크
      const spaceCheck = /\s/

      this.validity.nick = false
      if (this.nick === '') {
        this.nickResult = '닉네임을 입력해주세요'
      } else if (this.nick.search(spaceCheck) !== -1) {
        this.nickResult = '공백없이 작성해주세요'
      } else if (!nickCheck.test(this.nick)) {
        this.nickResult = '닉네임을 다시 확인해주세요'
      } else {
        this.nickResult = ''
        this.validity.nick = true
      }
    },
    checkPhone() {
      // 전화번호 : 11자리 숫자만
      const phoneCheck = /[0-9]{11}/g
      // 공백 체크
      const spaceCheck = /\s/

      this.validity.phone = false
      if (this.phone === '') {
        this.phoneResult = '전화번호를 입력해주세요'
      } else if (this.phone.search(spaceCheck) !== -1) {
        this.phoneCheck = '공백없이 작성해주세요'
      } else if (!phoneCheck.test(this.phone)) {
        this.phoneResult = '전화번호를 다시 확인해주세요'
      } else {
        this.phoneResult = ''
        this.validity.phone = true
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
    checkTotalJoinValidity() {
      if (this.validity.id && this.validity.pwd && this.validity.pwd2 && this.validity.nick && this.validity.phone && this.validity.checkIdDuplicate && this.validity.checkNickDuplicate) {
        this.joinActive = true
      } else {
        this.joinActive = false
      }
    },
    join(event) {
      if (!(this.validity.id && this.validity.pwd && this.validity.pwd2 && this.validity.nick && this.validity.phone)) {
        alert('항목을 입력해주세요')
        event.preventDefault()
      }
      if (this.validity.checkIdDuplicate === false) {
        alert('아이디 중복검사를 진행해주세요')
        event.preventDefault()
      }
      if (this.validity.checkNickDuplicate === false) {
        alert('닉네임 중복검사를 진행해주세요')
        event.preventDefault()
      }
      if (!(this.validity.id && this.validity.pwd && this.validity.pwd2 && this.validity.nick && this.validity.phone && this.validity.checkIdDuplicate && this.validity.checkNickDuplicate)) {
        event.preventDefault()
      }
      console.log('제출')
      this.joinActive = true
      const joinData = {}
      joinData.email = this.id
      joinData.pwd = this.pwd
      joinData.pwd2 = this.pwd2
      joinData.phone = this.phone
      joinData.nick = this.nick
      axios
        .post('/api/member/join', joinData)
        .then((response) => {
          console.log(response.data.result)
          this.$router.push({ path: '/joinAuth' })
        })
        .catch((error) => console.log(error))
    },
    checkIdDup() {
      if (this.validity.id === false) {
        alert('아이디를 다시 확인해주세요!')
      } else {
        const data = { email: this.id }
        axios
          .post('/api/member/checkIdDup', data)
          .then((response) => {
            console.log(response.data.result)
            const result = response.data.result
            if (result === 'SUCCESS') {
              this.validity.checkIdDuplicate = true
              this.idDupBtnStr = '확인완료'
              this.idDupBtnActive = true
              this.checkTotalJoinValidity()
            } else {
              this.validity.checkIdDuplicate = false
              this.checkTotalJoinValidity()
              alert('이미 사용중인 아이디입니다')
            }
          })
          .catch((error) => console.log(error))
      }
    },
    checkNickDup() {
      if (this.validity.nick === false) {
        alert('닉네임을 다시 확인해주세요!')
      } else {
        const data = { nick: this.nick }
        axios
          .post('/api/member/checkNickDup', data)
          .then((response) => {
            console.log(response.data.result)
            const result = response.data.result
            if (result === 'SUCCESS') {
              this.validity.checkNickDuplicate = true
              this.nickDupBtnStr = '확인완료'
              this.nickDupBtnActive = true
              this.checkTotalJoinValidity()
            } else {
              this.validity.checkNickDuplicate = false
              this.checkTotalJoinValidity()
              alert('이미 사용중인 닉네임입니다')
            }
          })
          .catch((error) => console.log(error))
      }
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
  height: 48px;
  border-radius: 4px;
  background-color: rgb(138, 138, 138);
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
.join-btn-active {
  background-color: var(--main-color) !important;
  cursor: pointer;
}
.duplicate-btn {
  width: 60px;
  height: 26px;
  background-color: white;
  border: 1px solid var(--btn);
  position: absolute;
  right: 7px;
  top: 7px;
  font-size: 13px;
  color: var(--btn);
  cursor: pointer;
}

.idDupBtnActive {
  color: gray !important;
  border: 1px solid rgb(172, 172, 172);
  cursor: default;
}

.nickDupBtnActive {
  color: gray !important;
  border: 1px solid rgb(182, 182, 182);
  cursor: default;
}
</style>
