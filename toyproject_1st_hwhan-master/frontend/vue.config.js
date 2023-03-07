const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
  ],
  // npm run build 타겟 디렉토리
  outputDir: '../blahblah/src/main/resources/static',

  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        ws: false
      },
      '/api': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        ws: false
      }
    },
    historyApiFallback: true
  }
})
