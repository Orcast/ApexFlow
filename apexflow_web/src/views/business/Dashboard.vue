<template>
  <div class="dashboard-page">
    <!-- 统计卡片 -->
    <el-row :gutter="16" class="stats-row">
      <el-col :span="6">
        <el-card class="stat-card" shadow="never">
          <div class="stat-content">
            <div class="stat-icon" style="background: #e8f4ff; color: #0366d6;">
              <el-icon><ShoppingCart /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">1,256</div>
              <div class="stat-label">今日订单</div>
            </div>
            <div class="stat-trend" style="color: #52c41a;">
              +12.5%
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="never">
          <div class="stat-content">
            <div class="stat-icon" style="background: #f0f9eb; color: #67c23a;">
              <el-icon><Van /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">89%</div>
              <div class="stat-label">发货率</div>
            </div>
            <div class="stat-trend" style="color: #67c23a;">
              +3.2%
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="never">
          <div class="stat-content">
            <div class="stat-icon" style="background: #fef0f0; color: #f56c6c;">
              <el-icon><Refresh /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">24</div>
              <div class="stat-label">售后申请</div>
            </div>
            <div class="stat-trend" style="color: #f56c6c;">
              -5.1%
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card" shadow="never">
          <div class="stat-content">
            <div class="stat-icon" style="background: #f4f4f5; color: #909399;">
              <el-icon><Money /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">¥85,600</div>
              <div class="stat-label">今日销售额</div>
            </div>
            <div class="stat-trend" style="color: #67c23a;">
              +18.3%
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 主要图表区域 -->
    <el-row :gutter="16" class="charts-row">
      <el-col :span="16">
        <el-card class="chart-card" shadow="never">
          <template #header>
            <div class="card-header">
              <span>销售趋势</span>
              <el-select size="small" v-model="timeRange" style="width: 120px;">
                <el-option label="最近7天" value="7d" />
                <el-option label="最近30天" value="30d" />
                <el-option label="最近90天" value="90d" />
              </el-select>
            </div>
          </template>
          <div class="chart-placeholder">
            <div class="placeholder-content">
              <el-icon :size="48" color="#e1e4e8"><TrendCharts /></el-icon>
              <p>这里是销售趋势图表</p>
              <p class="placeholder-hint">接入ECharts或Chart.js后可显示真实数据</p>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="chart-card" shadow="never">
          <template #header>
            <div class="card-header">
              <span>订单状态分布</span>
            </div>
          </template>
          <div class="distribution-chart">
            <div class="distribution-item" v-for="item in orderDistribution" :key="item.status">
              <div class="distribution-label">
                <span class="status-dot" :style="{background: item.color}"></span>
                {{ item.status }}
              </div>
              <div class="distribution-value">{{ item.value }}%</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 最近订单表格 -->
    <el-card class="recent-orders" shadow="never">
      <template #header>
        <div class="card-header">
          <span>最近订单</span>
          <el-button type="primary" link>查看全部</el-button>
        </div>
      </template>
      <el-table :data="recentOrders" style="width: 100%" size="small">
        <el-table-column prop="orderNo" label="订单号" width="180">
          <template #default="{ row }">
            <span class="order-link">{{ row.orderNo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="customer" label="客户" width="120" />
        <el-table-column prop="amount" label="金额" width="100">
          <template #default="{ row }">
            <span class="amount">¥{{ row.amount.toLocaleString() }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="getStatusType(row.status)" size="small">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="120">
          <template #default>
            <el-button type="primary" link size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import {
  ShoppingCart, Van, Refresh, Money,
  TrendCharts
} from '@element-plus/icons-vue'

const timeRange = ref('7d')

const orderDistribution = ref([
  { status: '待付款', value: 15, color: '#ff9900' },
  { status: '待发货', value: 45, color: '#1890ff' },
  { status: '已发货', value: 30, color: '#52c41a' },
  { status: '已完成', value: 8, color: '#909399' },
  { status: '已取消', value: 2, color: '#ff4d4f' }
])

const recentOrders = ref([
  { orderNo: '202312150001', customer: '张三', amount: 299.00, status: '待发货', createTime: '2023-12-15 10:30:00' },
  { orderNo: '202312150002', customer: '李四', amount: 599.00, status: '已发货', createTime: '2023-12-15 11:15:00' },
  { orderNo: '202312150003', customer: '王五', amount: 129.00, status: '待付款', createTime: '2023-12-15 13:45:00' },
  { orderNo: '202312150004', customer: '赵六', amount: 899.00, status: '待发货', createTime: '2023-12-15 14:20:00' },
  { orderNo: '202312150005', customer: '钱七', amount: 459.00, status: '已完成', createTime: '2023-12-15 15:10:00' }
])

const getStatusType = (status: string) => {
  const typeMap: Record<string, string> = {
    '待付款': 'warning',
    '待发货': 'primary',
    '已发货': 'success',
    '已完成': 'info',
    '已取消': 'danger'
  }
  return typeMap[status] || ''
}
</script>

<style scoped>
.dashboard-page {
  padding: 0;
}

.stats-row {
  margin-bottom: 16px;
}

.stat-card {
  border: 1px solid #e1e4e8;
}

.stat-content {
  display: flex;
  align-items: center;
  gap: 16px;
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 24px;
  font-weight: 600;
  color: #24292e;
  line-height: 1.2;
}

.stat-label {
  font-size: 14px;
  color: #586069;
  margin-top: 4px;
}

.stat-trend {
  font-size: 14px;
  font-weight: 500;
}

.charts-row {
  margin-bottom: 16px;
}

.chart-card {
  border: 1px solid #e1e4e8;
  height: 320px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
}

.chart-placeholder {
  height: 240px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.placeholder-content {
  text-align: center;
  color: #586069;
}

.placeholder-content p {
  margin: 8px 0 0;
}

.placeholder-hint {
  font-size: 12px;
  color: #a0a0a0;
}

.distribution-chart {
  padding: 20px;
}

.distribution-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
}

.distribution-item:last-child {
  border-bottom: none;
}

.distribution-label {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #24292e;
}

.status-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
}

.distribution-value {
  font-weight: 600;
  color: #24292e;
}

.recent-orders {
  border: 1px solid #e1e4e8;
}

.order-link {
  color: #0366d6;
  cursor: pointer;
  text-decoration: none;
}

.order-link:hover {
  text-decoration: underline;
}

.amount {
  font-weight: 600;
  color: #24292e;
}
</style>
