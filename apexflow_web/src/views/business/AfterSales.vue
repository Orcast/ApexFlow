<template>
  <div class="aftersales-page">
    <h2>🔄 售后管理</h2>

    <el-tabs v-model="activeTab" type="card">
      <el-tab-pane label="待处理" name="pending">
        <el-table :data="pendingList" stripe>
          <el-table-column prop="refundNo" label="售后单号" width="180" />
          <el-table-column prop="orderNo" label="关联订单" width="150" />
          <el-table-column prop="customer" label="客户" width="120" />
          <el-table-column prop="type" label="售后类型" width="100">
            <template #default="{ row }">
              <el-tag :type="row.type === '退款' ? 'danger' : 'warning'" size="small">
                {{ row.type }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="amount" label="金额" width="100">
            <template #default="{ row }">
              ¥{{ row.amount.toFixed(2) }}
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="申请时间" width="180" />
          <el-table-column label="操作" width="150">
            <template #default>
              <el-button type="primary" size="small" link>处理</el-button>
              <el-button type="info" size="small" link>查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="处理中" name="processing">
        <el-table :data="processingList" stripe>
          <el-table-column prop="refundNo" label="售后单号" width="180" />
          <el-table-column prop="orderNo" label="关联订单" width="150" />
          <el-table-column prop="customer" label="客户" width="120" />
          <el-table-column prop="type" label="售后类型" width="100" />
          <el-table-column prop="processor" label="处理人" width="120" />
          <el-table-column prop="amount" label="金额" width="100" />
          <el-table-column label="操作" width="120">
            <template #default>
              <el-button type="primary" size="small" link>继续处理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="已完成" name="completed">
        <el-table :data="completedList" stripe>
          <el-table-column prop="refundNo" label="售后单号" width="180" />
          <el-table-column prop="orderNo" label="关联订单" width="150" />
          <el-table-column prop="customer" label="客户" width="120" />
          <el-table-column prop="type" label="售后类型" width="100" />
          <el-table-column prop="result" label="处理结果" width="100">
            <template #default="{ row }">
              <el-tag :type="row.result === '同意' ? 'success' : 'danger'" size="small">
                {{ row.result }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="completeTime" label="完成时间" width="180" />
          <el-table-column label="操作" width="100">
            <template #default>
              <el-button type="info" size="small" link>查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>

    <div class="statistics">
      <el-row :gutter="16">
        <el-col :span="8">
          <el-statistic title="今日售后申请" :value="12" />
        </el-col>
        <el-col :span="8">
          <el-statistic title="待处理数量" :value="8" />
        </el-col>
        <el-col :span="8">
          <el-statistic title="平均处理时间" :value="2.5">
            <template #suffix>
              <span style="font-size: 12px;">小时</span>
            </template>
          </el-statistic>
        </el-col>
      </el-row>
    </div>

    <div class="page-info">
      <p>这是一个售后管理页面，用于处理退货、退款等售后申请。</p>
      <p>页面展示了如何使用选项卡（Tabs）和统计组件（Statistic）。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const activeTab = ref('pending')

const pendingList = ref([
  { refundNo: 'AF20231215001', orderNo: '202312150001', customer: '张三', type: '退款', amount: 299.00, createTime: '2023-12-15 09:30:00' },
  { refundNo: 'AF20231215002', orderNo: '202312150003', customer: '李四', type: '换货', amount: 129.00, createTime: '2023-12-15 11:15:00' },
  { refundNo: 'AF20231215003', orderNo: '202312150004', customer: '王五', type: '退款', amount: 899.00, createTime: '2023-12-15 14:20:00' }
])

const processingList = ref([
  { refundNo: 'AF20231214001', orderNo: '202312140012', customer: '赵六', type: '退款', processor: '客服001', amount: 450.00 }
])

const completedList = ref([
  { refundNo: 'AF20231213001', orderNo: '202312130015', customer: '钱七', type: '换货', result: '同意', completeTime: '2023-12-14 16:30:00' },
  { refundNo: 'AF20231213002', orderNo: '202312130018', customer: '孙八', type: '退款', result: '拒绝', completeTime: '2023-12-14 17:45:00' }
])
</script>

<style scoped>
.aftersales-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

:deep(.el-tabs__header) {
  margin-bottom: 20px;
}

.statistics {
  margin-top: 30px;
  padding: 20px;
  background: #f6f8fa;
  border-radius: 8px;
}

:deep(.el-statistic) {
  text-align: center;
}

:deep(.el-statistic__number) {
  font-size: 32px;
  color: #0366d6;
}

.page-info {
  margin-top: 20px;
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #faad14;
}

.page-info p {
  color: #586069;
  margin: 5px 0;
}
</style>
