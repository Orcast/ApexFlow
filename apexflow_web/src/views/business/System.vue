<template>
  <div class="system-page">
    <h2>⚙️ 系统设置</h2>

    <el-card class="settings-card">
      <el-tabs v-model="activeTab" class="settings-tabs">
        <el-tab-pane label="基本设置" name="basic">
          <div class="settings-form">
            <el-form :model="basicSettings" label-width="120px">
              <el-form-item label="系统名称">
                <el-input v-model="basicSettings.systemName" placeholder="请输入系统名称" />
              </el-form-item>
              <el-form-item label="系统Logo">
                <el-upload
                  class="logo-upload"
                  action="#"
                  :show-file-list="false"
                  :auto-upload="false"
                  :on-change="handleLogoChange"
                >
                  <img v-if="basicSettings.logoUrl" :src="basicSettings.logoUrl" class="logo-preview" />
                  <el-icon v-else class="logo-upload-icon"><Plus /></el-icon>
                </el-upload>
                <div class="upload-hint">点击上传Logo，建议尺寸 200x60px</div>
              </el-form-item>
              <el-form-item label="版权信息">
                <el-input
                  v-model="basicSettings.copyright"
                  type="textarea"
                  :rows="3"
                  placeholder="请输入版权信息"
                />
              </el-form-item>
              <el-form-item label="系统版本">
                <el-tag type="info">{{ basicSettings.version }}</el-tag>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveBasicSettings">保存设置</el-button>
                <el-button @click="resetBasicSettings">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-tab-pane>

        <el-tab-pane label="通知设置" name="notification">
          <div class="settings-form">
            <el-form label-width="120px">
              <el-form-item label="新订单通知">
                <el-switch v-model="notificationSettings.newOrder" />
                <span class="setting-desc">开启后，有新订单时会发送通知</span>
              </el-form-item>
              <el-form-item label="库存预警通知">
                <el-switch v-model="notificationSettings.lowStock" />
                <span class="setting-desc">开启后，库存不足时会发送通知</span>
              </el-form-item>
              <el-form-item label="售后申请通知">
                <el-switch v-model="notificationSettings.aftersales" />
                <span class="setting-desc">开启后，有新的售后申请会发送通知</span>
              </el-form-item>
              <el-form-item label="通知方式">
                <el-checkbox-group v-model="notificationSettings.methods">
                  <el-checkbox label="站内消息" />
                  <el-checkbox label="邮件通知" />
                  <el-checkbox label="短信通知" />
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="通知频率">
                <el-radio-group v-model="notificationSettings.frequency">
                  <el-radio label="realtime">实时通知</el-radio>
                  <el-radio label="hourly">每小时汇总</el-radio>
                  <el-radio label="daily">每日汇总</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveNotificationSettings">保存设置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-tab-pane>

        <el-tab-pane label="安全设置" name="security">
          <div class="settings-form">
            <el-form label-width="120px">
              <el-form-item label="登录验证">
                <el-switch v-model="securitySettings.twoFactorAuth" />
                <span class="setting-desc">开启双重认证</span>
              </el-form-item>
              <el-form-item label="密码强度">
                <el-select v-model="securitySettings.passwordStrength" style="width: 200px;">
                  <el-option label="低强度（6位以上）" value="low" />
                  <el-option label="中强度（8位以上，含字母数字）" value="medium" />
                  <el-option label="高强度（10位以上，含大小写字母、数字、特殊字符）" value="high" />
                </el-select>
              </el-form-item>
              <el-form-item label="登录超时">
                <el-input-number
                  v-model="securitySettings.sessionTimeout"
                  :min="5"
                  :max="480"
                  style="width: 120px;"
                />
                <span class="setting-unit">分钟</span>
              </el-form-item>
              <el-form-item label="登录历史">
                <el-button type="info" @click="viewLoginHistory">查看登录记录</el-button>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveSecuritySettings">保存设置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-tab-pane>

        <el-tab-pane label="备份与恢复" name="backup">
          <div class="settings-form">
            <el-form label-width="120px">
              <el-form-item label="自动备份">
                <el-switch v-model="backupSettings.autoBackup" />
              </el-form-item>
              <el-form-item label="备份频率" v-if="backupSettings.autoBackup">
                <el-select v-model="backupSettings.frequency" style="width: 200px;">
                  <el-option label="每天" value="daily" />
                  <el-option label="每周" value="weekly" />
                  <el-option label="每月" value="monthly" />
                </el-select>
              </el-form-item>
              <el-form-item label="备份保留">
                <el-input-number
                  v-model="backupSettings.retentionDays"
                  :min="1"
                  :max="365"
                  style="width: 120px;"
                />
                <span class="setting-unit">天</span>
              </el-form-item>
              <el-form-item label="上次备份">
                <span class="backup-info">{{ backupSettings.lastBackup || '暂无备份记录' }}</span>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="createBackup" :loading="backupLoading">
                  {{ backupLoading ? '备份中...' : '立即备份' }}
                </el-button>
                <el-button type="warning" @click="restoreBackup">恢复备份</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <div class="page-info">
      <p>这是一个系统设置页面，用于管理系统的基本配置。</p>
      <p>演示了表单、开关、上传等组件的使用，分为多个标签页组织设置项。</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const activeTab = ref('basic')

const basicSettings = reactive({
  systemName: 'ApexFlow 电商管理系统',
  logoUrl: '',
  copyright: '© 2023 ApexFlow. All rights reserved.',
  version: 'v1.0.0'
})

const notificationSettings = reactive({
  newOrder: true,
  lowStock: true,
  aftersales: true,
  methods: ['站内消息', '邮件通知'],
  frequency: 'realtime'
})

const securitySettings = reactive({
  twoFactorAuth: false,
  passwordStrength: 'medium',
  sessionTimeout: 30
})

const backupSettings = reactive({
  autoBackup: true,
  frequency: 'daily',
  retentionDays: 30,
  lastBackup: '2023-12-14 23:00:00'
})

const backupLoading = ref(false)

const handleLogoChange = (file: any) => {
  // 在实际项目中这里应该上传到服务器
  const reader = new FileReader()
  reader.onload = (e) => {
    basicSettings.logoUrl = e.target?.result as string
    ElMessage.success('Logo上传成功')
  }
  reader.readAsDataURL(file.raw)
}

const saveBasicSettings = () => {
  ElMessage.success('基本设置已保存')
}

const resetBasicSettings = () => {
  basicSettings.systemName = 'ApexFlow 电商管理系统'
  basicSettings.logoUrl = ''
  basicSettings.copyright = '© 2023 ApexFlow. All rights reserved.'
  ElMessage.info('设置已重置')
}

const saveNotificationSettings = () => {
  ElMessage.success('通知设置已保存')
}

const saveSecuritySettings = () => {
  ElMessage.success('安全设置已保存')
}

const viewLoginHistory = () => {
  ElMessage.info('查看登录历史功能')
}

const createBackup = () => {
  backupLoading.value = true
  setTimeout(() => {
    backupLoading.value = false
    backupSettings.lastBackup = new Date().toLocaleString()
    ElMessage.success('备份创建成功')
  }, 1500)
}

const restoreBackup = () => {
  ElMessage.warning('恢复备份功能')
}
</script>

<style scoped>
.system-page {
  padding: 20px;
}

h2 {
  color: #0366d6;
  margin-bottom: 20px;
}

.settings-card {
  margin-bottom: 20px;
}

:deep(.settings-tabs .el-tabs__header) {
  margin-bottom: 0;
}

.settings-form {
  padding: 20px;
}

.logo-upload {
  width: 200px;
  height: 60px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-upload:hover {
  border-color: #0366d6;
}

.logo-preview {
  max-width: 100%;
  max-height: 100%;
}

.logo-upload-icon {
  font-size: 28px;
  color: #8c939d;
}

.upload-hint {
  margin-top: 8px;
  color: #586069;
  font-size: 12px;
}

.setting-desc {
  margin-left: 10px;
  color: #586069;
  font-size: 14px;
}

.setting-unit {
  margin-left: 10px;
  color: #586069;
}

.backup-info {
  color: #1890ff;
  font-weight: 500;
}

.page-info {
  padding: 15px;
  background: #f6f8fa;
  border-radius: 8px;
  border-left: 4px solid #1890ff;
}

.page-info p {
  color: #586069;
  margin: 5px 0;
}
</style>
