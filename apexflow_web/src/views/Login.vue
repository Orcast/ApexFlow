<template>
  <div class="login">
    <!-- 登录表单 -->
    <div class="login-card">
      <h2>🔐 用户登录</h2>

      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label>用户名：</label>
          <input
            type="text"
            v-model="username"
            placeholder="请输入用户名"
            required
          />
        </div>

        <div class="form-group">
          <label>密码：</label>
          <input
            type="password"
            v-model="password"
            placeholder="请输入密码"
            required
          />
        </div>

        <div class="form-group">
          <label>
            <input type="checkbox" v-model="remember" />
            记住我
          </label>
        </div>

        <button type="submit" :disabled="loading" class="login-btn">
          {{ loading ? '登录中...' : '登录' }}
        </button>
      </form>

      <!-- 登录结果 -->
      <div v-if="loginResult" class="login-result">
        <p :class="loginResult.success ? 'success' : 'error'">
          {{ loginResult.message }}
        </p>
        <p v-if="loginResult.success">
          用户名：{{ loginResult.user.username }}<br>
          登录时间：{{ loginResult.user.loginTime }}
        </p>
      </div>

      <!-- 底部链接 -->
      <div class="login-footer">
        <p>
          还没有账号？
          <a href="#" @click.prevent="showRegisterHint">注册账号</a>
        </p>
        <p>
          <router-link to="/">← 返回首页</router-link>
        </p>
      </div>
    </div>

    <!-- 登录提示 -->
    <div class="login-hint">
      <h3>💡 这个登录页演示了什么？</h3>
      <ul>
        <li>Vue的响应式数据绑定：输入框和v-model</li>
        <li>表单处理：@submit.prevent</li>
        <li>条件渲染：v-if</li>
        <li>事件处理：@click</li>
        <li>路由导航：this.$router.push</li>
        <li>动态样式：:class</li>
      </ul>
      <p>这是一个模拟登录，不会真正发送请求。</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',

  // 定义数据
  data() {
    return {
      username: '',
      password: '',
      remember: false,
      loading: false,
      loginResult: null
    }
  },

  // 定义方法
  methods: {
    // 处理登录
    handleLogin() {
      // 显示加载状态
      this.loading = true
      this.loginResult = null

      console.log('尝试登录：', {
        username: this.username,
        password: '***隐藏***',
        remember: this.remember
      })

      // 模拟API请求（延迟1秒）
      setTimeout(() => {
        this.loading = false

        // 模拟登录验证
        if (this.username && this.password) {
          this.loginResult = {
            success: true,
            message: '登录成功！',
            user: {
              username: this.username,
              loginTime: new Date().toLocaleString()
            }
          }

          // 2秒后跳转到首页
          setTimeout(() => {
            this.$router.push('/')
          }, 2000)
        } else {
          this.loginResult = {
            success: false,
            message: '登录失败：请填写用户名和密码'
          }
        }
      }, 1000)
    },

    // 显示注册提示
    showRegisterHint() {
      alert('注册功能暂未实现，请直接使用任意用户名和密码登录测试')
    }
  },

  // 生命周期钩子
  created() {
    console.log('🔐 登录页组件已创建')
  }
}
</script>

<style scoped>
.login {
  max-width: 600px;
  margin: 0 auto;
}

.login-card {
  background: white;
  border-radius: 10px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}

h2 {
  text-align: center;
  color: #1890ff;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #333;
  font-weight: 500;
}

.form-group input[type="text"],
.form-group input[type="password"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  font-size: 16px;
  transition: border-color 0.3s;
}

.form-group input:focus {
  outline: none;
  border-color: #1890ff;
}

.form-group input[type="checkbox"] {
  margin-right: 8px;
}

.login-btn {
  width: 100%;
  padding: 12px;
  background: #1890ff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s;
}

.login-btn:hover:not(:disabled) {
  background: #40a9ff;
}

.login-btn:disabled {
  background: #8c8c8c;
  cursor: not-allowed;
}

.login-result {
  margin-top: 20px;
  padding: 15px;
  border-radius: 4px;
  text-align: center;
}

.success {
  color: #52c41a;
  font-weight: bold;
}

.error {
  color: #ff4d4f;
  font-weight: bold;
}

.login-footer {
  margin-top: 30px;
  padding-top: 20px;
  border-top: 1px solid #f0f0f0;
  text-align: center;
}

.login-footer a {
  color: #1890ff;
  text-decoration: none;
}

.login-footer a:hover {
  text-decoration: underline;
}

.login-hint {
  background: #f6ffed;
  border: 1px solid #b7eb8f;
  border-radius: 8px;
  padding: 20px;
}

.login-hint h3 {
  color: #389e0d;
  margin-bottom: 15px;
}

.login-hint ul {
  margin-bottom: 15px;
  padding-left: 20px;
}

.login-hint li {
  margin-bottom: 8px;
  color: #666;
}

.login-hint p {
  color: #666;
  font-style: italic;
}
</style>
