<template>
  <div class="finance-page">
    <h2>💰 收入统计</h2>

    <el-row :gutter="16" class="finance-stats">
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #52c41a;">
              <el-icon size="24"><Money /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">¥{{ formatNumber(dailyIncome) }}</div>
              <div class="stat-label">今日收入</div>
              <div class="stat-trend" style="color: #52c41a;">+12.5%</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #1890ff;">
              <el-icon size="24"><PieChart /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">¥{{ formatNumber(weeklyIncome) }}</div>
              <div class="stat-label">本周收入</div>
              <div class="stat-trend" style="color: #52c41a;">+8.3%</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #722ed1;">
              <el-icon size="24"><Histogram /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">¥{{ formatNumber(monthlyIncome) }}</div>
              <div class="stat-label">本月收入</div>
              <div class="stat-trend" style="color: #ff4d4f;">-2.1%</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #faad14;">
              <el-icon size="24"><DataAnalysis /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">¥{{ formatNumber(yearlyIncome) }}</div>
              <div class="stat-label">今年收入</div>
              <div class="stat-trend" style="color: #52c41a;">+15.7%</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="finance-charts">
      <el-col :span="16">
        <el-card>
          <template #header>
            <div class="chart-header">
              <span>收入趋势图</span>
              <el-select v-model="incomeTimeRange" size="small" style="width: 120px;">
                <el-option label="按月" value="monthly" />
                <el-option label="按周" value="weekly" />
                <el-option label="按日" value="daily" />
              </el-select>
            </div>
          </template>

          <div class="chart-placeholder">
            <el-icon :size="48" color="#e1e4e8"><TrendCharts /></el-icon>
            <p>收入趋势图表</p>
            <p class="placeholder-hint">接入ECharts后可显示详细的收入趋势分析</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <template #header>
            <div class="chart-header">
              <span>收入来源分布</span>
            </div>
          </template>

          <div class="income-distribution">
            <div v-for="item in incomeDistribution" :key="item.source" class="distribution-item">
              <div class="distribution-info">
                <span class="source-dot" :style="{ background: item.color }"></span>
                <span class="source-name">{{ item.source }}</span>
              </div>
              <div class="distribution-value">
                <span class="amount">¥{{ formatNumber(item.amount) }}</span>
                <span class="percentage">({{ item.percentage }}%)</span>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-card class="recent-transactions">
      <template #header>
        <div class="card-header">
          <span>最近交易记录</span>
          <el-button type="primary" link size="small">查看全部</el-button>
        </div>
      </template>

      <el-table :data="recentTransactions" stripe style="width: 100%" size="small">
        <el-table-column prop="transactionId" label="交易ID" width="180" />
        <el-table-column prop="orderNo" label="订单号" width="150" />
        <el-table-column prop="customer" label="客户" width="120" />
        <el-table-column prop="amount" label="金额" width="120">
          <template #default="{ row }">
            <span class="amount">¥{{ formatNumber(row.amount) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="paymentMethod" label="支付方式" width="120">
          <template #default="{ row }">
            <el-tag size="small">{{ row.paymentMethod }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === '成功' ? 'success' : 'danger'" size="small">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="时间" width="180" />
      </el-table>
    </el-card>

    <div class="page-info">
      <p>这是一个收入统计页面，用于展示财务数据和收入分析。</p>
      <p>演示了统计卡片、图表和交易记录表格的布局。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Money, PieChart, Histogram, DataAnalysis, TrendCharts } from '@element-plus/icons-vue'

const incomeTimeRange = ref('monthly')

// 模拟数据
const dailyIncome = 85600
const weeklyIncome = 523800
const monthlyIncome = 2456700
const yearlyIncome = 28945600

const incomeDistribution = ref([
  { source: '在线支付', amount: 156800, percentage: 65, color: '#1890ff' },
  { source: '货到付款', amount: 45600, percentage: 19, color: '#52c41a' },
  { source: '银行转账', amount: 23800, percentage: 10, color: '#722ed1' },
  { source: '其他', amount: 19500, percentage: 8, color: '#faad14' }
])

const recentTransactions = ref([
  { transactionId: 'TX20231215001', orderNo: '202312150001', customer: '张三', amount: 299.00, paymentMethod: '微信支付', status: '成功', time: '2023-12-15 10:30:00' },
  { transactionId: 'TX20231215002', orderNo: '202312150002', customer: '李四', amount: 599.00, paymentMethod: '支付宝', status: '成功', time: '2023-12-15 11:15:00' },
  { transactionId: 'TX20231215003', orderNo: '202312150003', customer: '王五', amount: 129.00, paymentMethod: '货到付款', status: '待收款', time: '2023-12-15 13:45:00' },
  { transactionId: 'TX20231215004', orderNo: '202312150004', customer: '赵六', amount: 899.00, paymentMethod: '微信支付', status: '成功', time: '2023-12-15 14:20:00' },
  { transactionId: 'TX20231215005', orderNo: '202312150005', customer: '钱七', amount: 459.00, paymentMethod: '支付宝', status: '成功', time: '2023-12-15 15:10:00' }
])

const formatNumber = (num: number) => {
  return num.toLocaleString('zh-CN')
}
</script>

<style scoped>
.finance-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.finance-stats {
  margin-bottom: 20px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 15px;
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.02);
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 20px;
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
  font-size: 12px;
  margin-top: 2px;
}

.finance-charts {
  margin-bottom: 20px;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-placeholder {
  height: 250px;
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

.income-distribution {
  padding: 10px 0;
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

.distribution-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.source-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
}

.source-name {
  color: #24292e;
}

.distribution-value {
  text-align: right;
}

.amount {
  font-weight: 600;
  color: #24292e;
}

.percentage {
  font-size: 12px;
  color: #586069;
  margin-left: 4px;
}

.recent-transactions .amount {
  color: #52c41a;
  font-weight: 600;
}

.page-info {
  margin-top: 20px;
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #722ed1;
}

.page-info p {
  color: #586069;
  margin: 5px 0;
}
</style>
