<template>
  <div class="logistics-page">
    <h2>🚚 物流跟踪</h2>

    <el-card>
      <template #header>
        <div class="card-header">
          <span>物流信息查询</span>
          <el-input
            v-model="trackingNumber"
            placeholder="请输入物流单号"
            style="width: 300px; margin-right: 10px;"
            clearable
          />
          <el-button type="primary" icon="Search" @click="queryTracking">查询</el-button>
        </div>
      </template>

      <div v-if="trackingInfo" class="tracking-result">
        <div class="tracking-header">
          <div class="tracking-number">物流单号: {{ trackingInfo.trackingNumber }}</div>
          <div class="tracking-status">
            <el-tag :type="getStatusType(trackingInfo.status)" size="large">
              {{ trackingInfo.status }}
            </el-tag>
          </div>
        </div>

        <div class="tracking-timeline">
          <el-timeline>
            <el-timeline-item
              v-for="(event, index) in trackingInfo.events"
              :key="index"
              :timestamp="event.time"
              :type="event.type"
              :color="event.color"
            >
              {{ event.location }} - {{ event.description }}
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <div v-else class="empty-state">
        <el-empty description="请输入物流单号进行查询" />
      </div>
    </el-card>

    <div class="page-info">
      <p>这是一个物流跟踪页面，演示了时间轴组件的使用。</p>
      <p>实际项目中会对接真实的物流API（如顺丰、圆通、京东等）。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { ref } from 'vue'

const trackingNumber = ref('SF1234567890123')
const trackingInfo = ref<any>(null)

const queryTracking = () => {
  if (!trackingNumber.value) {
    ElMessage.warning('请输入物流单号')
    return
  }

  // 模拟API调用
  trackingInfo.value = {
    trackingNumber: trackingNumber.value,
    status: '运输中',
    events: [
      {
        time: '2023-12-15 10:30:00',
        location: '北京市朝阳区',
        description: '快件已揽收',
        type: 'primary',
        color: '#0bbd87'
      },
      {
        time: '2023-12-15 14:20:00',
        location: '北京转运中心',
        description: '快件已发出',
        type: 'primary',
        color: '#e54545'
      },
      {
        time: '2023-12-15 18:45:00',
        location: '上海市转运中心',
        description: '快件已到达',
        type: 'primary',
        color: '#e54545'
      },
      {
        time: '2023-12-16 08:30:00',
        location: '上海市黄浦区',
        description: '派送中',
        type: 'primary',
        color: '#e54545'
      }
    ]
  }
}

const getStatusType = (status: string) => {
  const typeMap: Record<string, string> = {
    '已揽收': 'success',
    '运输中': 'primary',
    '派送中': 'warning',
    '已签收': 'success',
    '异常': 'danger'
  }
  return typeMap[status] || ''
}
</script>

<style scoped>
.logistics-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  align-items: center;
  gap: 10px;
}

.tracking-result {
  padding: 20px;
}

.tracking-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e1e4e8;
}

.tracking-number {
  font-size: 16px;
  font-weight: 500;
  color: #24292e;
}

.tracking-status .el-tag {
  font-size: 14px;
}

.tracking-timeline {
  padding: 20px;
}

.empty-state {
  padding: 40px 0;
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
