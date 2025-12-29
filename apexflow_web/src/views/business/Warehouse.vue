<template>
  <div class="warehouse-page">
    <h2>📦 库存管理</h2>

    <el-row :gutter="16" class="warehouse-stats">
      <el-col :span="8">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #1890ff;">
              <el-icon size="24"><Box /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ totalProducts }}</div>
              <div class="stat-label">商品总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #52c41a;">
              <el-icon size="24"><Goods /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ totalStock }}</div>
              <div class="stat-label">库存总量</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div class="stat-item">
            <div class="stat-icon" style="color: #faad14;">
              <el-icon size="24"><Warning /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ lowStockCount }}</div>
              <div class="stat-label">低库存商品</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-card class="inventory-table">
      <template #header>
        <div class="card-header">
          <span>库存列表</span>
          <div class="header-actions">
            <el-button type="primary" icon="Plus" size="small">入库</el-button>
            <el-button type="success" icon="Minus" size="small">出库</el-button>
            <el-button type="warning" icon="Edit" size="small">盘点</el-button>
          </div>
        </div>
      </template>

      <el-table :data="inventoryList" stripe style="width: 100%">
        <el-table-column prop="sku" label="SKU" width="150" />
        <el-table-column prop="productName" label="商品名称" width="200" />
        <el-table-column prop="category" label="分类" width="120">
          <template #default="{ row }">
            <el-tag size="small">{{ row.category }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="currentStock" label="当前库存" width="120">
          <template #default="{ row }">
            <span :class="row.currentStock <= row.minStock ? 'low-stock' : ''">
              {{ row.currentStock }}
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="minStock" label="最低库存" width="120" />
        <el-table-column prop="maxStock" label="最高库存" width="120" />
        <el-table-column prop="warehouse" label="仓库位置" width="150" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag
              :type="row.currentStock <= row.minStock ? 'danger' : 'success'"
              size="small"
            >
              {{ row.currentStock <= row.minStock ? '需补货' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" size="small" link @click="adjustStock(row)">调拨</el-button>
            <el-button type="warning" size="small" link @click="showDetail(row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <div class="page-info">
      <p>这是一个库存管理页面，用于管理商品库存信息。</p>
      <p>演示了库存监控、预警和基本操作功能。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { Box, Goods, Warning, Plus, Minus, Edit } from '@element-plus/icons-vue'

const inventoryList = ref([
  { sku: 'SKU001', productName: 'iPhone 15 Pro', category: '手机', currentStock: 45, minStock: 10, maxStock: 100, warehouse: 'A区-1排-3架' },
  { sku: 'SKU002', productName: '小米智能手表', category: '数码', currentStock: 82, minStock: 20, maxStock: 150, warehouse: 'B区-2排-1架' },
  { sku: 'SKU003', productName: '戴森吹风机', category: '家电', currentStock: 8, minStock: 5, maxStock: 50, warehouse: 'C区-1排-4架' },
  { sku: 'SKU004', productName: '华为MateBook', category: '电脑', currentStock: 23, minStock: 15, maxStock: 80, warehouse: 'A区-3排-2架' },
  { sku: 'SKU005', productName: '索尼耳机', category: '数码', currentStock: 56, minStock: 10, maxStock: 100, warehouse: 'B区-1排-5架' },
  { sku: 'SKU006', productName: '格力空调', category: '家电', currentStock: 12, minStock: 5, maxStock: 30, warehouse: 'D区-2排-3架' },
  { sku: 'SKU007', productName: '联想平板', category: '数码', currentStock: 34, minStock: 20, maxStock: 120, warehouse: 'C区-2排-1架' },
  { sku: 'SKU008', productName: '佳能相机', category: '数码', currentStock: 67, minStock: 15, maxStock: 100, warehouse: 'A区-2排-4架' }
])

const totalProducts = computed(() => inventoryList.value.length)
const totalStock = computed(() => {
  return inventoryList.value.reduce((sum, item) => sum + item.currentStock, 0)
})
const lowStockCount = computed(() => {
  return inventoryList.value.filter(item => item.currentStock <= item.minStock).length
})

const adjustStock = (row: any) => {
  console.log('调拨商品:', row.productName)
}

const showDetail = (row: any) => {
  console.log('查看商品详情:', row.productName)
}
</script>

<style scoped>
.warehouse-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.warehouse-stats {
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

.inventory-table {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-actions {
  display: flex;
  gap: 10px;
}

.low-stock {
  color: #ff4d4f;
  font-weight: 600;
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
