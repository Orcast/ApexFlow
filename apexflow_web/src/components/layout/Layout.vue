<template>
  <el-container class="github-layout">
    <!-- 侧边栏 -->
    <Sidebar :collapsed="isCollapsed" @toggle-collapse="toggleSidebar" />

    <!-- 主内容区 -->
    <el-container class="main-container">
      <!-- 顶部工具栏 -->
      <Header />

      <!-- 页面内容 -->
      <el-main class="main-content">
        <router-view v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>

      <!-- 页脚 -->
      <el-footer height="48px" class="layout-footer">
        <div class="footer-content">
          <span>© 2023 ApexFlow · 电商管理系统</span>
          <div class="footer-links">
            <a href="#">帮助文档</a>
            <a href="#">隐私政策</a>
            <a href="#">服务条款</a>
            <span>版本 v1.0.0</span>
          </div>
        </div>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Sidebar from './Sidebar.vue'
import Header from './Header.vue'

const isCollapsed = ref(false)

const toggleSidebar = () => {
  isCollapsed.value = !isCollapsed.value
}
</script>

<style scoped>
/* Layout.vue */
.github-layout {
  height: 100vh;
  display: flex;
  background: #ffffff;
}

.main-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden; /* 外层容器控制滚动 */
}

.main-content {
  flex: 1;
  padding: 24px;
  background: #fafbfc;
  overflow-y: auto; /* 这里设置滚动 */
}

.layout-footer {
  background: #f6f8fa;
  border-top: 1px solid #e1e4e8;
  display: flex;
  align-items: center;
  padding: 0 24px;
}

.footer-content {
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 12px;
  color: #586069;
}

.footer-links {
  display: flex;
  gap: 16px;
  align-items: center;
}

.footer-links a {
  color: #0366d6;
  text-decoration: none;
}

.footer-links a:hover {
  text-decoration: underline;
}

/* 页面切换动画 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
