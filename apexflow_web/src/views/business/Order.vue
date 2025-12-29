<template>
  <div class="order-page">
    <h2>📋 订单列表</h2>

    <el-card>
      <template #header>
        <div class="card-header">
          <span>订单管理</span>
          <div>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索订单号/客户"
              style="width: 200px; margin-right: 10px;"
              clearable
            />
            <el-button type="primary" icon="Search">搜索</el-button>
            <el-button type="success" icon="Plus">新建订单</el-button>
          </div>
        </div>
      </template>

      <el-table :data="orderList" stripe style="width: 100%">
        <el-table-column prop="orderNo" label="订单号" width="180" />
        <el-table-column prop="customer" label="客户" width="120" />
        <el-table-column prop="amount" label="金额" width="100">
          <template #default="{ row }">
            <span class="amount">¥{{ row.amount.toFixed(2) }}</span>
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
            <el-button type="warning" link size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :small="true"
          layout="total, sizes, prev, pager, next, jumper"
          :total="100"
        />
      </div>
    </el-card>

    <div class="page-info">
      <p>这是一个订单管理页面的示例，用于演示Vue路由和Element Plus表格的使用。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const searchKeyword = ref('')
const currentPage = ref(1)
const pageSize = ref(10)

const orderList = ref([
  { orderNo: '202312150001', customer: '张三', amount: 299.00, status: '待发货', createTime: '2023-12-15 10:30:00' },
  { orderNo: '202312150002', customer: '李四', amount: 599.00, status: '已发货', createTime: '2023-12-15 11:15:00' },
  { orderNo: '202312150003', customer: '王五', amount: 129.00, status: '待付款', createTime: '2023-12-15 13:45:00' },
  { orderNo: '202312150004', customer: '赵六', amount: 899.00, status: '待发货', createTime: '2023-12-15 14:20:00' },
  { orderNo: '202312150005', customer: '钱七', amount: 459.00, status: '已完成', createTime: '2023-12-15 15:10:00' },
  { orderNo: '202312150006', customer: '孙八', amount: 1599.00, status: '已发货', createTime: '2023-12-15 16:45:00' },
  { orderNo: '202312150007', customer: '周九', amount: 259.00, status: '待付款', createTime: '2023-12-15 17:20:00' },
  { orderNo: '202312150008', customer: '吴十', amount: 789.00, status: '已发货', createTime: '2023-12-15 18:30:00' }
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
.order-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.amount {
  font-weight: 600;
  color: #24292e;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.page-info {
  margin-top: 20px;
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #0366d6;
}

.page-info p {
  color: #586069;
  margin: 0;
}
</style>
