<template>
  <div class="container">
    <!-- <div>인증완</div>
    <div>{{ userEmail }}</div> -->

  </div>
</template>
<script>
import axios from 'axios'
import Swal from 'sweetalert2'
export default {
  components: {},
  data() {
    return {
      userEmail: ''
    }
  },
  setup() {},
  created() {
    const email = this.$route.params.email
    const random = this.$route.params.random
    console.log(random)
    this.userEmail = email

    axios.post('/api/member/emailAuth', this.userEmail)
      .then(response => {
        const result = response.data.result
        if (result === 'SUCCESS') {
          console.log(response)
        } else {
          alert('회원가입 이메일 인증 실패')
        }
      })
      .catch((error) => console.log(error))

    Swal.fire({
      icon: 'success',
      title: '이메일 인증이 완료 되었습니다!',
      text: '로그인 후 다양한 회원들과 소통하세요!',
      confirmButtonColor: '#007ff7'

    }).then((result) => {
      if (result.isConfirmed) {
        this.$router.push('/')
      }
    }
    )
  },
  mounted() {

  },
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
.container {
  height: 1000px;
}
</style>
