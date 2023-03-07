<template>
    <div class="container">
        <header>
            <div class="header-title">
                <i class="fa-regular fa-face-smile"></i>
                <span>블라블라</span>
            </div>
            <div class="more">
                <router-link to="/topic">더보기</router-link>
                <i class="fa-solid fa-chevron-right"></i>
            </div>
        </header>
        <article class="grid-box">
            <div>헬스장 위치 회사 근처? 집근처?</div>
            <div>
                <i class="fa-solid fa-eye"></i>
                <span>100</span>
            </div>
            <div>헬스장 위치 회사 근처? 집근처?</div>
            <div>
                <i class="fa-solid fa-eye"></i>
                <span>100</span>
            </div>
            <div>헬스장 위치 회사 근처? 집근처?</div>
            <div>
                <i class="fa-solid fa-eye"></i>
                <span>100</span>
            </div>
            <div>헬스장 위치 회사 근처? 집근처?</div>
            <div>
                <i class="fa-solid fa-eye"></i>
                <span>100</span>
            </div>
            <div>헬스장 위치 회사 근처? 집근처?</div>
            <div>
                <i class="fa-solid fa-eye"></i>
                <span>100</span>
            </div>
        </article>
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
    width: 320px;

}
.container > header{
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    margin: 0px 5px;
    border-bottom: 2px solid #eee;
}

.header-title {
    font-size: 20px;
    font-weight: 600;
}

.header-title i {
    margin-right: 5px;
}

.grid-box {
    display: grid;
    grid-template-columns: 3fr 1fr;
    margin-top: 15px;
}

.grid-box div {
    height: 30px;
    font-size: 15px;
}
</style>
