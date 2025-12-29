<template>
  <div class="evaluation-page">
    <h2>⭐ 评价管理</h2>

    <el-card>
      <template #header>
        <div class="card-header">
          <span>商品评价</span>
          <div class="filter-controls">
            <el-select v-model="filterRating" placeholder="评分筛选" size="small" style="width: 100px;">
              <el-option label="全部" value="all" />
              <el-option label="5星" value="5" />
              <el-option label="4星" value="4" />
              <el-option label="3星" value="3" />
              <el-option label="2星" value="2" />
              <el-option label="1星" value="1" />
            </el-select>
            <el-select v-model="filterStatus" placeholder="状态筛选" size="small" style="width: 100px; margin-left: 10px;">
              <el-option label="全部" value="all" />
              <el-option label="已展示" value="shown" />
              <el-option label="已隐藏" value="hidden" />
            </el-select>
            <el-button type="primary" size="small" style="margin-left: 10px;">筛选</el-button>
          </div>
        </div>
      </template>

      <div class="evaluation-list">
        <div v-for="item in evaluationList" :key="item.id" class="evaluation-item">
          <div class="evaluation-header">
            <div class="evaluation-info">
              <span class="customer-name">{{ item.customer }}</span>
              <el-rate
                v-model="item.rating"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value} 分"
              />
              <span class="product-name">商品: {{ item.productName }}</span>
            </div>
            <div class="evaluation-time">{{ item.createTime }}</div>
          </div>

          <div class="evaluation-content">
            {{ item.content }}
          </div>

          <div class="evaluation-actions">
            <el-tag size="small" :type="item.status === '已展示' ? 'success' : 'info'">
              {{ item.status }}
            </el-tag>
            <div class="action-buttons">
              <el-button
                v-if="item.status === '已展示'"
                type="warning"
                size="small"
                @click="hideEvaluation(item.id)"
              >
                隐藏
              </el-button>
              <el-button
                v-if="item.status === '已隐藏'"
                type="success"
                size="small"
                @click="showEvaluation(item.id)"
              >
                展示
              </el-button>
              <el-button type="info" size="small" @click="replyToEvaluation(item.id)">回复</el-button>
              <el-button type="danger" size="small" @click="deleteEvaluation(item.id)">删除</el-button>
            </div>
          </div>
        </div>
      </div>
    </el-card>

    <div class="statistics">
      <el-row :gutter="16">
        <el-col :span="6">
          <div class="stat-card">
            <div class="stat-value">{{ totalEvaluations }}</div>
            <div class="stat-label">总评价数</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-card">
            <div class="stat-value">{{ averageRating.toFixed(1) }}</div>
            <div class="stat-label">平均评分</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-card">
            <div class="stat-value">{{ recentEvaluations }}</div>
            <div class="stat-label">近7天评价</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="stat-card">
            <div class="stat-value">{{ replyRate }}%</div>
            <div class="stat-label">回复率</div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="page-info">
      <p>这是一个评价管理页面，用于管理用户对商品的评价。</p>
      <p>演示了评分组件（Rate）、标签（Tag）和筛选功能的使用。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const filterRating = ref('all')
const filterStatus = ref('all')

const evaluationList = ref([
  {
    id: 1,
    customer: '张三',
    rating: 5,
    productName: 'iPhone 15 Pro',
    content: '手机非常棒，运行流畅，拍照效果出色，续航也很给力！',
    createTime: '2023-12-15 10:30:00',
    status: '已展示'
  },
  {
    id: 2,
    customer: '李四',
    rating: 4,
    productName: '小米智能手表',
    content: '功能齐全，外观漂亮，就是续航稍微有点短。',
    createTime: '2023-12-14 16:20:00',
    status: '已展示'
  },
  {
    id: 3,
    customer: '王五',
    rating: 2,
    productName: '戴森吹风机',
    content: '风力确实很大，但价格太贵了，性价比不高。',
    createTime: '2023-12-13 14:15:00',
    status: '已隐藏'
  },
  {
    id: 4,
    customer: '赵六',
    rating: 5,
    productName: '华为MateBook',
    content: '电脑轻薄，性能强大，屏幕显示效果非常棒！',
    createTime: '2023-12-12 09:45:00',
    status: '已展示'
  }
])

const totalEvaluations = computed(() => evaluationList.value.length)
const averageRating = computed(() => {
  const total = evaluationList.value.reduce((sum, item) => sum + item.rating, 0)
  return total / evaluationList.value.length
})
const recentEvaluations = 12 // 假设数据
const replyRate = 85 // 假设数据

const hideEvaluation = (id: number) => {
  const item = evaluationList.value.find(item => item.id === id)
  if (item) {
    item.status = '已隐藏'
    ElMessage.success('评价已隐藏')
  }
}

const showEvaluation = (id: number) => {
  const item = evaluationList.value.find(item => item.id === id)
  if (item) {
    item.status = '已展示'
    ElMessage.success('评价已展示')
  }
}

const replyToEvaluation = (id: number) => {
  ElMessageBox.prompt('请输入回复内容', '回复评价', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputType: 'textarea',
    inputPlaceholder: '请输入回复内容...'
  }).then(({ value }) => {
    if (value) {
      ElMessage.success('回复已提交')
    }
  })
}

const deleteEvaluation = (id: number) => {
  ElMessageBox.confirm('确定要删除这条评价吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    evaluationList.value = evaluationList.value.filter(item => item.id !== id)
    ElMessage.success('评价已删除')
  })
}
</script>

<style scoped>
.evaluation-page {
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

.filter-controls {
  display: flex;
  align-items: center;
}

.evaluation-list {
  padding: 10px 0;
}

.evaluation-item {
  padding: 20px;
  border-bottom: 1px solid #e1e4e8;
}

.evaluation-item:last-child {
  border-bottom: none;
}

.evaluation-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.evaluation-info {
  display: flex;
  align-items: center;
  gap: 15px;
}

.customer-name {
  font-weight: 500;
  color: #24292e;
}

.product-name {
  color: #586069;
  font-size: 14px;
}

.evaluation-time {
  color: #a0a0a0;
  font-size: 12px;
}

.evaluation-content {
  margin-bottom: 15px;
  line-height: 1.6;
  color: #333;
}

.evaluation-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.action-buttons {
  display: flex;
  gap: 8px;
}

.statistics {
  margin-top: 30px;
}

.stat-card {
  text-align: center;
  padding: 20px;
  background: #f6f8fa;
  border-radius: 8px;
  border: 1px solid #e1e4e8;
}

.stat-value {
  font-size: 32px;
  font-weight: 600;
  color: #0366d6;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #586069;
}

.page-info {
  margin-top: 20px;
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #ff9900;
}

.page-info p {
  color: #586069;
  margin: 5px 0;
}
</style>
