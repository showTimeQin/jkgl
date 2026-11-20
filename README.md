# jkgl 健康管理系统

毕业课设，基于 Spring Boot 的健康管理系统。围绕饮食与运动做记录、查询和健康方案管理，分 **管理员 / 健康顾问 / 用户 / 微信端** 四种角色。技术栈与作者其他 Spring Boot 项目一脉相承（MyBatis + 通用 Mapper + PageHelper + FreeMarker）。

## 功能

- **膳食库与能量计算**：内置食物库（`diet_storage`），按 27 个分类浏览、按拼音检索，结合度量单位表（`energy_measurement`）计算摄入能量
- **膳食记录**：用户记录每日饮食（`diet_record`）
- **运动管理**：运动项目（`sport`）与用户运动记录（`sport_item`）
- **健康方案**：顾问为用户制定健康方案（`health_scheme`）
- **角色与登录**：管理员、顾问、用户三端控制器 + 微信端（`wx`），ticket + Cookie 登录，会话日志（`session_log`）
- **辅助**：Excel 导出（POI）、AOP、全局异常处理、MyBatis Generator 代码生成

## 技术栈

| 层 | 技术 |
| --- | --- |
| 框架 | Spring Boot 1.5.10、Spring MVC、AOP |
| 持久层 | MyBatis 1.3.1 + tk.mybatis 通用 Mapper 1.1.5、PageHelper 1.2.3、MySQL |
| 视图 | FreeMarker、Bootstrap + jQuery |
| 工具 | Apache POI、HttpClient + Jsoup、Lombok、Fastjson、Commons Lang3 |

## 数据表

`user`（用户）、`admin`（管理员）、`adviser`（顾问）、`sport`（运动项目）、`sport_item`（用户运动）、`diet_storage`（膳食库）、`diet_record`（膳食记录）、`energy_measurement`（度量单位）、`health_scheme`（健康方案）、`login_ticket`（登录票据）、`session_log`（会话日志）。建表语句见 `src/main/resources/init.sql`。

## 快速开始

**1. 建库**

```bash
mysql -u root -p < src/main/resources/init.sql
```

**2. 配置数据库**

编辑 `src/main/resources/application-dev.yml`（默认激活 `dev`）：

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jkgl?characterEncoding=utf-8
    username: root
    password: 你的密码
```

**3. 启动**

```bash
./mvnw spring-boot:run
```

访问 `http://localhost:8081/`。

## 目录结构

```
src/main/java/com/showtime/jkgl
├── controller        # admin / adviser / user / wx 四端，及 Food/Login/Index
├── service           # 业务层
├── mapper            # MyBatis Mapper
├── model             # entity / base / view
├── interceptor       # 登录与角色校验
├── configuration     # Web / MyBatis 配置
├── exception/handle  # 异常与全局处理
├── constant/enums    # 常量与枚举
└── utils             # 工具类
src/main/resources
├── init.sql          # 建表脚本
├── mapper/*.xml      # SQL 映射
├── templates/        # FreeMarker 页面
└── generator/        # MyBatis Generator 配置
```

## 说明

本项目为个人毕业课设，仅供学习参考。生产使用请替换硬编码的数据库凭据、关闭测试账号并加强登录与权限策略。
