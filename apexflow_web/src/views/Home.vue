<template>
  <div class="home">
    <!-- 页面标题 -->
    <h2>🎉 欢迎使用极简电商系统！</h2>

    <!-- 简介 -->
    <div class="intro">
      <p>这是一个超简化的电商管理系统示例，帮你理解Vue项目的结构和工作流程。</p>
      <p>当前页面：首页（Home.vue）</p>
    </div>

    <!-- 功能卡片 -->
    <div class="features">
      <div class="feature-card" @click="goToLogin">
        <h3>🔑 登录功能</h3>
        <p>点击这里或使用顶部导航栏的"登录"链接，前往登录页面</p>
      </div>

      <div class="feature-card">
        <h3>🛒 订单管理</h3>
        <p>这是一个功能占位，实际项目中这里会显示订单列表</p>
      </div>

      <div class="feature-card">
        <h3>📊 数据统计</h3>
        <p>这是一个功能占位，实际项目中这里会显示数据图表</p>
      </div>
    </div>

    <!-- 状态显示 -->
    <div class="status">
      <h3>当前应用状态：</h3>
      <ul>
        <li>路由正常 ✓</li>
        <li>Vue 3 运行正常 ✓</li>
        <li>Vue Router 运行正常 ✓</li>
        <li>Vite 开发服务器运行正常 ✓</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',

  // 定义方法
  methods: {
    goToLogin() {
      // 使用路由跳转到登录页
      this.$router.push('/login')
    }
  },

  // 生命周期钩子：组件创建时执行
  created() {
    console.log('🏠 首页组件已创建')
  },

  // 生命周期钩子：组件挂载到DOM后执行
  mounted() {
    console.log('🏠 首页组件已挂载到DOM')
  }
}
</script>

<style scoped>
/* scoped表示这些样式只在这个组件生效 */

.home {
  padding: 20px;
}

h2 {
  color: #1890ff;
  margin-bottom: 20px;
  text-align: center;
}

.intro {
  background: #f0f8ff;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 30px;
  border-left: 4px solid #1890ff;
}

.intro p {
  margin-bottom: 10px;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.feature-card {
  background: white;
  border: 1px solid #e8e8e8;
  border-radius: 8px;
  padding: 20px;
  cursor: pointer;
  transition: all 0.3s;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  border-color: #1890ff;
}

.feature-card h3 {
  color: #1890ff;
  margin-bottom: 10px;
}

.feature-card p {
  color: #666;
  font-size: 14px;
}

.status {
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
}

.status h3 {
  margin-bottom: 15px;
  color: #333;
}

.status ul {
  list-style: none;
}

.status li {
  padding: 8px 0;
  color: #52c41a;
  font-weight: bold;
}
</style>
