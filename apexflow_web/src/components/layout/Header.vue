<template>
  <el-header class="github-header">
    <!-- 左侧：面包屑和快速操作 -->
    <div class="header-left">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="item in breadcrumb" :key="item.path">
          {{ item.title }}
        </el-breadcrumb-item>
      </el-breadcrumb>

      <div class="quick-actions">
        <el-button type="primary" size="small" :icon="Plus">
          新建订单
        </el-button>
        <el-button size="small" :icon="Upload">
          导入数据
        </el-button>
        <el-button size="small" :icon="Download">
          导出报表
        </el-button>
      </div>
    </div>

    <!-- 右侧：用户信息和工具 -->
    <div class="header-right">
      <!-- 通知中心 -->
      <el-dropdown trigger="click" @command="handleCommand">
        <div class="header-icon">
          <el-badge :value="8" :max="99">
            <el-icon :size="20"><Bell /></el-icon>
          </el-badge>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item :icon="Bell">系统通知 (3)</el-dropdown-item>
            <el-dropdown-item :icon="ChatDotRound">消息提醒 (5)</el-dropdown-item>
            <el-dropdown-item divided :icon="Setting">通知设置</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>

      <!-- 搜索按钮 -->
      <el-button type="text" :icon="Search" class="search-btn" />

      <!-- 用户信息 -->
      <el-dropdown @command="handleUserCommand">
        <div class="user-info">
          <el-avatar :size="32" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          <div class="user-details">
            <span class="username">管理员</span>
            <span class="user-role">超级管理员</span>
          </div>
          <el-icon><ArrowDown /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item :icon="User">个人中心</el-dropdown-item>
            <el-dropdown-item :icon="Setting">账号设置</el-dropdown-item>
            <el-dropdown-item :icon="Lock">修改密码</el-dropdown-item>
            <el-dropdown-item divided :icon="SwitchButton">切换账号</el-dropdown-item>
            <el-dropdown-item :icon="Close">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import {
  Plus, Upload, Download, Bell, Search,
  User, Setting, Lock, SwitchButton, Close,
  ArrowDown, ChatDotRound
} from '@element-plus/icons-vue'

const route = useRoute()

const breadcrumb = computed(() => {
  const matched = route.matched.filter(item => item.meta?.title)
  return matched.map(item => ({
    path: item.path,
    title: item.meta?.title as string
  }))
})

const handleCommand = (command: string) => {
  console.log('命令:', command)
}

const handleUserCommand = (command: string) => {
  console.log('用户命令:', command)
  if (command === '退出登录') {
    // 处理退出登录逻辑
  }
}
</script>

<style scoped>
.github-header {
  height: 60px;
  background: #ffffff;
  border-bottom: 1px solid #e1e4e8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
}

.header-left {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

:deep(.el-breadcrumb) {
  font-size: 12px;
  color: #586069;
}

:deep(.el-breadcrumb__inner) {
  color: #0366d6;
}

:deep(.el-breadcrumb__item:last-child .el-breadcrumb__inner) {
  color: #24292e;
  font-weight: 500;
}

.quick-actions {
  display: flex;
  gap: 8px;
}

.quick-actions .el-button {
  font-size: 12px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.header-icon {
  padding: 8px;
  border-radius: 6px;
  cursor: pointer;
  color: #586069;
}

.header-icon:hover {
  background: #f6f8fa;
  color: #0366d6;
}

:deep(.el-badge__content) {
  transform: scale(0.8) translate(50%, -50%);
}

.search-btn {
  color: #586069;
}

.search-btn:hover {
  color: #0366d6;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 4px 8px;
  border-radius: 6px;
  cursor: pointer;
}

.user-info:hover {
  background: #f6f8fa;
}

.user-details {
  display: flex;
  flex-direction: column;
  line-height: 1.2;
}

.username {
  font-size: 14px;
  font-weight: 500;
  color: #24292e;
}

.user-role {
  font-size: 12px;
  color: #586069;
}
</style>
