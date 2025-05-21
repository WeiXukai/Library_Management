# 图书管理系统

## 项目简介
本项目是一个基于SpringBoot和Vue的图书管理系统，集成了图书管理、借阅、评分和推荐功能。系统支持两种推荐方式：热门推荐和基于协同过滤的个性化推荐。

## 技术栈
- **后端**：SpringBoot、MyBatis-Plus、Redis
- **前端**：Vue、Element Plus
- **数据库**：MySQL
- **缓存**：Redis

## 功能模块
1. **图书管理**：图书的增删改查
2. **借阅管理**：用户借阅、归还图书
3. **评分系统**：用户对图书进行评分
4. **推荐系统**：
   - **热门推荐**：根据图书借阅量排序，推荐最受欢迎的图书
   - **协同过滤推荐**：基于用户评分数据，计算用户相似度，推荐个性化图书

## 推荐系统原理
### 热门推荐
- 直接根据图书的借阅量（borrowCount）降序排列，推荐借阅量最高的图书
- 适合新用户或无历史数据的用户

### 协同过滤推荐（UserCF）
- **算法流程**：
  1. **预处理**：构建用户-图书评分矩阵
  2. **相似度计算**：使用余弦相似度计算用户之间的兴趣相似度
  3. **加权预测**：根据相似用户的评分和相似度，预测当前用户未评分图书的兴趣分
- **适用场景**：有历史评分数据的用户，提供个性化推荐

## 项目结构
```
项目根目录
│
├── library_manager.sql           # 数据库建表及初始化脚本
├── README.md
├── .gitignore
│
├── SpringBoot/                   # 后端SpringBoot项目
│   ├── pom.xml                   # Maven依赖配置
│   └── src/
│       └── main/
│           ├── java/com/example/springboot/
│           │   ├── Application.java         # 启动类
│           │   ├── entity/                  # 实体类（Book、User、Borrow、Rating等）
│           │   ├── controller/              # 控制器（BookController、UserController、RecommendController等）
│           │   ├── service/                 # 业务接口
│           │   │   ├── RecommendService.java
│           │   │   └── impl/                # 业务实现类
│           │   ├── mapper/                  # MyBatis-Plus Mapper接口
│           │   ├── utils/                   # 工具类
│           │   └── common/                  # 通用类（Result、配置等）
│           └── resources/
│               ├── application.yml          # 配置文件
│               ├── static/                  # 静态资源
│               └── templates/               # 模板（如有）
│
├── vue/                         # 前端Vue项目
│   ├── package.json              # 前端依赖配置
│   ├── public/                   # 公共资源
│   └── src/
│       ├── main.js               # 入口文件
│       ├── App.vue               # 根组件
│       ├── views/                # 主要页面（Recommend.vue、Book.vue、User.vue等）
│       ├── components/           # 公共组件（Header、Aside等）
│       ├── utils/                # 工具（如request.js）
│       ├── router/               # 路由配置
│       ├── store/                # 状态管理
│       ├── layout/               # 布局组件
│       ├── assets/               # 静态资源（图片、样式等）
│       └── img/                  # 图片资源
```

## 数据库结构
- 主要表：book（图书）、user（用户）、borrow（借阅记录）、rating（评分）等
- 初始化脚本见 `library_manager.sql`

## 使用说明
### 1. 数据库初始化
- 使用MySQL，执行 `library_manager.sql` 脚本，创建并初始化数据库。
- 修改 `SpringBoot/src/main/resources/application.yml`，配置数据库连接、Redis等信息。

### 2. 启动后端服务
```bash
cd SpringBoot
mvn spring-boot:run
```
- 默认端口：8080
- 后端接口文档可参考controller包下各类注释。

### 3. 启动前端服务
```bash
cd vue
npm install
npm run serve
```
- 默认端口：9999
- 访问 http://localhost:9999 进入系统

### 4. 推荐功能使用
- 登录系统后，进入"推荐"页面（Recommend.vue）。
- 可切换"热门推荐"与"协同过滤推荐"两种方式：
  - **热门推荐**：无需登录即可使用，支持按图书名称搜索。
  - **协同过滤推荐**：需登录且有评分记录，系统会根据你的兴趣个性化推荐。
- 推荐接口：
  - 热门推荐：`GET /recommend?type=hot&pageNum=1&pageSize=5&name=xxx`
  - 协同过滤：`GET /recommend?type=cf&userId=xxx&pageNum=1&pageSize=5`
- 推荐结果分页展示，支持切换页码和每页条数。

### 5. 其他功能
- 图书管理、借阅、归还、评分等功能请参考前端各页面（Book.vue、BookWithUser.vue等）。
- 用户注册、登录、密码找回等功能已集成。

## 贡献者
- WXK

## 许可证
MIT 
