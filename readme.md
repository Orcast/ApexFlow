# ApexFlow - 电商信息管理系统

![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)
![Vue.js](https://img.shields.io/badge/Vue.js-3.x-42b883.svg)
![Java](https://img.shields.io/badge/Java-17-007396.svg)
![Tomcat](https://img.shields.io/badge/Tomcat-9.x-F8DC75.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1.svg)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)

## 📋 项目简介

**ApexFlow** 是一个开源的电商信息管理系统，专为中小型电商商家设计。系统采用前后端分离架构，前端使用Vue 3.x，后端基于Java Servlet技术，由Tomcat直接托管部署，无需Nginx等反向代理服务器。项目遵循GPL-3.0开源协议，完全免费使用和修改。

### ✨ 核心特性

- **一体化部署**：前后端统一由Tomcat托管，简化部署流程
- **模块化管理**：六大核心功能模块，覆盖电商运营全流程
- **现代化界面**：基于Vue 3 + Element Plus的响应式设计
- **RESTful API**：标准化接口设计，便于集成和扩展
- **开源自由**：GPL-3.0协议，支持二次开发和商业应用

## 🗂️ 功能模块

| 模块 | 核心功能 | 状态 |
|------|----------|------|
| **订单管理** | 订单创建/查询/筛选，订单状态跟踪，批量操作 | ✅ 已完成 |
| **物流管理** | 物流公司对接，运单管理，物流轨迹查询 | ✅ 已完成 |
| **售后管理** | 退货/换货申请处理，退款管理，售后记录 | ✅ 已完成 |
| **评价管理** | 商品评价查看，回复评价，评价数据分析 | 🚧 开发中 |
| **仓库管理** | 库存管理，入库/出库记录，库存预警 | 🚧 开发中 |
| **财务管理** | 收支统计，财务报表，账单管理 | 🔄 规划中 |

## 🛠️ 技术架构

### 技术栈

**前端 (Vue 3.x)**
- **框架**: Vue 3.x (组合式API)
- **UI组件库**: Element Plus
- **状态管理**: Pinia
- **路由管理**: Vue Router 4
- **HTTP客户端**: Axios
- **构建工具**: Vite
- **样式预处理**: SCSS

**后端 (Java Servlet)**
- **服务器**: Apache Tomcat 9.x
- **Java版本**: JDK 8+
- **Web框架**: Servlet 4.0
- **数据库**: MySQL 5.7+
- **连接池**: HikariCP
- **JSON处理**: Jackson
- **日志框架**: Log4j2

**开发工具**
- **IDE**: IntelliJ IDEA / Eclipse + VS Code
- **构建工具**: Maven 3.6+
- **数据库工具**: MySQL Workbench
- **版本控制**: Git

### 架构图

```
┌─────────────────────────────────────────┐
│           浏览器客户端 (Vue SPA)          │
└─────────────────┬───────────────────────┘
                  │ HTTP/HTTPS
┌─────────────────▼───────────────────────┐
│            Apache Tomcat 9.x            │
│   ┌─────────────────────────────────┐   │
│   │      apexflow-web (静态资源)     │   │
│   │  ├── index.html                 │   │
│   │  ├── static/                    │   │
│   │  └── assets/                    │   │
│   │                                 │   │
│   │      apexflow-server (Java)     │   │
│   │  ├── Servlet Controllers        │   │
│   │  ├── DAO/Service 层             │   │
│   │  └── Filters/Listeners          │   │
│   └─────────────────────────────────┘   │
│                  │                      │
│           ┌──────▼───────┐              │
│           │    MySQL     │              │
│           │   数据库      │              │
│           └──────────────┘              │
└─────────────────────────────────────────┘
```

## 📁 项目结构

```
apexflow/ (项目根目录)
├── apexflow-web/              # Vue前端源码（独立开发）
│   ├── public/
│   ├── src/
│   │   ├── api/              # API接口封装
│   │   ├── assets/           # 静态资源
│   │   ├── components/       # 组件
│   │   ├── router/           # 路由配置
│   │   ├── stores/           # Pinia状态管理
│   │   ├── views/            # 页面组件
│   │   └── utils/            # 工具函数
│   ├── package.json
│   └── vue.config.js         # 构建配置
│
└── apexflow-server/          # Java Web项目
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── apex/
    │   │   │           ├── config/           # 配置类
    │   │   │           │   ├── CORSFilter.java
    │   │   │           │   ├── SpaFilter.java
    │   │   │           │   └── DatabaseConfig.java
    │   │   │           ├── core/             # 核心业务
    │   │   │           │   ├── controller/   # 控制器层
    │   │   │           │   │   ├── OrderController.java
    │   │   │           │   │   ├── LogisticsController.java
    │   │   │           │   │   ├── AfterSalesController.java
    │   │   │           │   │   └── ...
    │   │   │           │   ├── service/      # 业务逻辑层
    │   │   │           │   ├── dao/          # 数据访问层
    │   │   │           │   ├── dto/          # 数据传输对象
    │   │   │           │   └── model/        # 数据模型
    │   │   │           └── util/             # 工具类
    │   │   ├── resources/
    │   │   │   ├── config.properties         # 配置文件
    │   │   │   ├── log4j2.xml               # 日志配置
    │   │   │   └── db.properties            # 数据库配置
    │   │   └── webapp/                      # Web应用目录
    │   │       ├── WEB-INF/
    │   │       │   ├── web.xml              # Web配置
    │   │       │   └── classes/
    │   │       ├── index.jsp                 # 跳转页面
    │   │       ├── index.html                # Vue入口页面
    │   │       └── static/                   # Vue构建的静态资源
    │   │           ├── css/
    │   │           ├── js/
    │   │           ├── fonts/
    │   │           └── img/
    │   └── test/
    │       └── java/
    ├── lib/                    # 第三方依赖库
    ├── pom.xml                # Maven配置
    └── README.md
```

## 🚀 快速开始

### 环境要求

- **Java**: JDK 17 或更高版本
- **Tomcat**: Apache Tomcat 9.x
- **MySQL**: MySQL 8.0 或更高版本
- **Node.js**: 16.x 或更高版本
- **Maven**: 3.6+（用于后端构建）

### 1. 数据库初始化

```sql
-- 创建数据库
CREATE DATABASE apexflow CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建用户并授权（可选）
CREATE USER 'apexflow'@'localhost' IDENTIFIED BY 'apexflow123';
GRANT ALL PRIVILEGES ON apexflow.* TO 'apexflow'@'localhost';
FLUSH PRIVILEGES;

-- 导入表结构（SQL文件位于 apexflow-server/src/main/resources/db/）
-- mysql -u root -p apexflow < apexflow-server/src/main/resources/db/schema.sql
```

### 2. 配置文件设置

**数据库配置** (`apexflow-server/src/main/resources/db.properties`):

```properties
# 数据库连接配置
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/apexflow?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
db.username=root
db.password=yourpassword
db.initialSize=5
db.maxActive=20
db.maxWait=60000
```

### 3. 项目构建与部署

#### 方案一：一键构建部署

```bash
# 1. 克隆项目
git clone https://github.com/yourusername/apexflow.git
cd apexflow

# 2. 安装前端依赖并构建
cd apexflow-web
npm install
npm run build  # 构建到后端项目的webapp目录

# 3. 构建后端项目
cd ../apexflow-server
mvn clean package

# 4. 部署到Tomcat
# 将target/apexflow.war复制到Tomcat的webapps目录
cp target/apexflow.war /path/to/tomcat/webapps/

# 5. 启动Tomcat
/path/to/tomcat/bin/startup.sh
```

#### 方案二：开发模式运行

```bash
# 1. 启动后端服务（在apexflow-server目录）
mvn tomcat7:run  # 或使用IDE启动

# 2. 启动前端开发服务器（在apexflow-web目录）
npm run serve

# 3. 访问应用
# 前端开发地址：http://localhost:3000
# 后端API地址：http://localhost:8080
```

### 4. 访问系统

1. 启动Tomcat服务器
2. 访问应用地址：http://localhost:8080/apexflow
3. 使用默认管理员账号登录：
    - 用户名：admin
    - 密码：admin123

## 🔧 配置文件说明

### 前端Vue配置 (`apexflow-web/vue.config.js`)

```javascript
const path = require('path');

module.exports = {
  // 构建输出到Java项目的webapp目录
  outputDir: path.resolve(__dirname, '../apexflow-server/src/main/webapp'),
  
  // 静态资源路径（相对路径，适应Tomcat部署）
  publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
  
  // 开发服务器代理配置
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/apexflow',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  
  // 生产环境配置
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
};
```

### Web应用配置 (`apexflow-server/src/main/webapp/WEB-INF/web.xml`)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <display-name>ApexFlow</display-name>
    
    <!-- 字符编码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- CORS跨域过滤器 -->
    <filter>
        <filter-name>corsFilter</filter-name>
        <filter-class>com.apex.config.CORSFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>corsFilter</filter-name>
        <url-pattern>/api/*</url-pattern>
    </filter-mapping>
    
    <!-- SPA应用过滤器 -->
    <filter>
        <filter-name>spaFilter</filter-name>
        <filter-class>com.apex.config.SpaFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>spaFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- 默认首页 -->
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>
    
    <!-- 错误页面配置 -->
    <error-page>
        <error-code>404</error-code>
        <location>/index.html</location>
    </error-page>
    
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
</web-app>
```

## 📊 数据库设计

主要数据表示例：

```sql
-- 订单表
CREATE TABLE `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
  `customer_id` BIGINT COMMENT '客户ID',
  `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '订单状态(1:待付款,2:待发货,3:已发货,4:已完成,5:已取消)',
  `payment_status` TINYINT COMMENT '支付状态',
  `shipping_address` TEXT COMMENT '收货地址',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  INDEX `idx_customer_id` (`customer_id`),
  INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 商品表
CREATE TABLE `products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_no` VARCHAR(50) NOT NULL COMMENT '商品编号',
  `name` VARCHAR(200) NOT NULL COMMENT '商品名称',
  `category_id` BIGINT COMMENT '分类ID',
  `price` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
  `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1:上架,2:下架)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_no` (`product_no`),
  INDEX `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';
```

完整数据库脚本请查看 `apexflow-server/src/main/resources/db/` 目录。

## 🔌 API接口文档

### 基础接口

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | `/api/orders` | 获取订单列表 |
| POST | `/api/orders` | 创建新订单 |
| GET | `/api/orders/{id}` | 获取订单详情 |
| PUT | `/api/orders/{id}` | 更新订单信息 |
| DELETE | `/api/orders/{id}` | 删除订单 |

### 订单接口示例

**获取订单列表**
```http
GET /api/orders?page=1&size=20&status=2
```

**响应示例**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "orderNo": "202312150001",
        "customerName": "张三",
        "totalAmount": 299.00,
        "status": 2,
        "createdAt": "2023-12-15 10:30:00"
      }
    ],
    "total": 100,
    "page": 1,
    "size": 20
  }
}
```

完整API文档请运行项目后访问：`http://localhost:8080/apexflow/api-doc`

## 🧪 测试

### 单元测试
```bash
# 运行后端单元测试
cd apexflow-server
mvn test

# 运行前端单元测试
cd ../apexflow-web
npm run test:unit
```

### 集成测试
```bash
# 运行端到端测试
cd apexflow-web
npm run test:e2e
```

## 🤝 贡献指南

我们欢迎所有形式的贡献！参与项目步骤如下：

1. **Fork 项目**：点击右上角的Fork按钮
2. **克隆仓库**：`git clone https://github.com/yourusername/apexflow.git`
3. **创建分支**：`git checkout -b feature/your-feature-name`
4. **提交更改**：`git commit -m 'Add some feature'`
5. **推送到分支**：`git push origin feature/your-feature-name`
6. **提交Pull Request**

### 开发规范
- Java代码遵循阿里巴巴Java开发手册
- Vue组件使用组合式API编写
- 提交信息使用英文描述，格式为：`type(scope): message`
- 新增功能需包含相应的单元测试

### 代码提交类型
- `feat`: 新功能
- `fix`: 修复bug
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建过程或辅助工具变动

## 📄 许可证

本项目基于 **GNU General Public License v3.0** 开源协议发布。

```
ApexFlow - 电商信息管理系统
Copyright (C) 2023 ApexFlow Contributors

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```

## 📞 支持与联系

- **项目主页**: [https://github.com/yourusername/apexflow](https://github.com/yourusername/apexflow)
- **问题反馈**: [GitHub Issues](https://github.com/yourusername/apexflow/issues)
- **讨论区**: [GitHub Discussions](https://github.com/yourusername/apexflow/discussions)
- **邮箱**: apexflow@example.com

## 🙏 致谢

感谢以下开源项目为ApexFlow提供支持：
- [Vue.js](https://vuejs.org/)
- [Element Plus](https://element-plus.org/)
- [Apache Tomcat](https://tomcat.apache.org/)
- [MySQL](https://www.mysql.com/)

---

**ApexFlow** - 简化电商管理，提升运营效率！