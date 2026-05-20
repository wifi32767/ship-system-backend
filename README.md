# README

## 概述
本项目是数据化转型信息数据库系统的后端服务，基于Spring Boot构建，主要用于新闻设备信息的管理、审计、统计分析以及用户、权限、国家管理等功能，同时转发前端向爬虫数据录入系统的请求。系统采用微服务架构，结合Spring Cloud、Spring Cloud OpenFeign、MyBatis等技术，实现了高效的新闻设备信息管理和用户权限控制。

## 项目结构
```
ship-system-backend/
├── backend-app/
├── backend-interfaces/
├── backend-infra/
├── backend-common/
├── docs/
├── pom.xml
```

### backend-app
- **功能**: 应用程序的核心模块，负责业务逻辑处理。
- **关键技术**: Spring Boot, Spring Cloud OpenFeign, MyBatis
- **依赖**: `backend-common`, `spring-cloud-starter-openfeign`

### backend-interfaces
- **功能**: 提供HTTP接口，供前端或其他系统调用。
- **关键技术**: Spring Boot, Spring MVC, Springdoc-openapi
- **依赖**: `backend-common`, `spring-boot-starter-web`, `springdoc-openapi-starter-webmvc-ui`, `fastjson`

### backend-infra
- **功能**: 基础设施模块，包括数据库访问、缓存服务等。
- **关键技术**: MyBatis, Redisson, Spring Security Crypto
- **依赖**: `backend-common`, `mybatis-spring-boot-starter`, `spring-security-crypto`, `redisson-spring-boot-starter`

### backend-common
- **功能**: 公共模块，提供通用的工具类、配置等。
- **关键技术**: Lombok, Spring Cloud Context
- **依赖**: `lombok`, `spring-cloud-context`

### docs
- **功能**: 存放项目文档，包括开发文档、部署文档等。

## 运行环境
- **Java版本**: 17
- **构建工具**: Maven
- **数据库**: MySQL
- **缓存**: Redis
- **注册配置中心**: Consul

## 运行脚本
- **run.sh**: 用于启动应用，配置了Java环境变量、Spring Boot启动参数等。

## 使用方法
1. **配置数据库和Redis**:
   - 修改`backend-app/src/main/resources/application.yml`文件中的数据库连接信息和Redis连接信息。

2. **构建项目**:
   - 打开命令行，进入项目根目录。
   - 使用命令`mvn clean install`构建项目。

3. **启动服务**:
   - 在`localhost:6379`启动Redis服务
   - 使用`docs/shells/consul.sh`脚本启动Consul服务。也可以使用其中的命令在其他机器部署Consul服务，需要在配置文件中指定正确的地址。如果运行失败，需要使用`sudo`命令提供权限。
     - 全局设置需要在Consul中创建Key/Value配置:
        ```yaml
        # config/application/data
        # config/application/token
        token: <TOKEN>
        ```
   - 使用`docs/shells/run.sh`脚本启动服务。

4. **访问接口文档**:
   - 项目启动后，可以通过访问`http://localhost:8091/swagger-ui/index.html`来查看和测试接口。