// 1. 从vue包中导入 createApp 函数
import { createApp } from 'vue'

// 2. 导入我们的根组件
import App from './App.vue'

// 3. 导入路由配置
import router from './router'
import 'element-plus/dist/index.css'  // 确保这行存在

// 4. 创建Vue应用实例，并传入根组件
const app = createApp(App)

// 或者单独配置 ElMessage
import { ElMessage } from 'element-plus'
ElMessage.install = (app) => {
  // 确保 ElMessage 使用 document.body 作为容器
  app.config.globalProperties.$message = ElMessage
}

// 5. 使用路由
app.use(router)

// 6. 把应用挂载到 #app 元素上
app.mount('#app')

console.log('✅ Vue应用启动成功！访问 http://localhost:3000')
