<template>
  <div class="order-analysis">
    <h2>📊 订单分析</h2>

    <el-row :gutter="16" class="analysis-cards">
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="analysis-card">
            <div class="card-icon" style="color: #1890ff;">
              <el-icon size="24"><DataAnalysis /></el-icon>
            </div>
            <div class="card-content">
              <div class="card-value">1,256</div>
              <div class="card-label">今日订单数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="analysis-card">
            <div class="card-icon" style="color: #52c41a;">
              <el-icon size="24"><TrendCharts /></el-icon>
            </div>
            <div class="card-content">
              <div class="card-value">¥85,600</div>
              <div class="card-label">今日销售额</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="analysis-card">
            <div class="card-icon" style="color: #faad14;">
              <el-icon size="24"><ShoppingBag /></el-icon>
            </div>
            <div class="card-content">
              <div class="card-value">89%</div>
              <div class="card-label">平均转化率</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="analysis-card">
            <div class="card-icon" style="color: #722ed1;">
              <el-icon size="24"><User /></el-icon>
            </div>
            <div class="card-content">
              <div class="card-value">156</div>
              <div class="card-label">新增客户数</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-card class="chart-container">
      <template #header>
        <div class="chart-header">
          <span>订单趋势分析</span>
          <el-select v-model="chartTimeRange" size="small" style="width: 120px;">
            <el-option label="最近7天" value="7d" />
            <el-option label="最近30天" value="30d" />
            <el-option label="最近90天" value="90d" />
          </el-select>
        </div>
      </template>

      <div class="chart-placeholder">
        <el-icon :size="48" color="#e1e4e8"><TrendCharts /></el-icon>
        <p>订单分析图表区域</p>
        <p class="placeholder-hint">接入ECharts后可显示详细数据分析</p>
      </div>
    </el-card>

    <div class="page-info">
      <p>这是一个订单分析页面，用于展示订单数据统计和分析图表。</p>
      <p>实际项目中这里会集成ECharts、Chart.js等图表库进行数据可视化。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { DataAnalysis, TrendCharts, ShoppingBag, User } from '@element-plus/icons-vue'

const chartTimeRange = ref('7d')
</script>

<style scoped>
.order-analysis {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.analysis-cards {
  margin-bottom: 20px;
}

.analysis-card {
  display: flex;
  align-items: center;
  gap: 15px;
}

.card-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.02);
}

.card-content {
  flex: 1;
}

.card-value {
  font-size: 24px;
  font-weight: 600;
  color: #24292e;
  line-height: 1.2;
}

.card-label {
  font-size: 14px;
  color: #586069;
  margin-top: 4px;
}

.chart-container {
  margin-bottom: 20px;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-placeholder {
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #586069;
}

.chart-placeholder p {
  margin-top: 10px;
}

.placeholder-hint {
  font-size: 12px;
  color: #a0a0a0;
  margin-top: 5px;
}

.page-info {
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #52c41a;
}

.page-info p {
  color: #586069;
  margin: 5px 0;
}
</style>
