<template>
  <router-view />
</template>

<script setup lang="ts">

</script>

<style>
/* 全局覆盖一些Element Plus样式以获得更好的GitHub风格 */
:root {
  --el-color-primary: #0366d6;
  --el-border-color: #e1e4e8;
  --el-border-radius-base: 6px;
}

body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: #ffffff;
}

/* 自定义滚动条样式 */
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 4px;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

</style>
