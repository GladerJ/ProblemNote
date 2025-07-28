# 题目笔记系统

一个基于Spring Boot的题目笔记管理系统，支持科目管理、题目记录等功能。

## 热部署配置

本项目已配置热部署功能，支持代码修改后自动重启，无需手动重启应用。

### 配置说明

#### 1. Maven依赖
已在 `pom.xml` 中添加了 Spring Boot DevTools 依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

#### 2. 应用配置
在 `application.yml` 中配置了热部署相关设置：
```yaml
spring:
  devtools:
    restart:
      enabled: true  # 启用热部署
      additional-paths: src/main/java  # 监控的目录
      exclude: static/**,public/**,templates/**  # 排除的目录
    livereload:
      enabled: true  # 启用LiveReload
      port: 35729  # LiveReload端口
```

### 使用方法

#### IntelliJ IDEA 用户

1. **启用自动编译**：
   - 打开 `File` → `Settings` → `Build, Execution, Deployment` → `Compiler`
   - 勾选 `Build project automatically`
   - 勾选 `Compile independent modules in parallel`

2. **启用运行时自动编译**：
   - 按 `Ctrl + Shift + Alt + /` (Windows/Linux) 或 `Cmd + Shift + Alt + /` (Mac)
   - 选择 `Registry`
   - 勾选 `compiler.automake.allow.when.app.running`

3. **运行应用**：
   - 右键点击 `ProblemNoteApplication.java`
   - 选择 `Run 'ProblemNoteApplication'`

#### Eclipse 用户

1. **启用自动构建**：
   - 打开 `Project` → `Build Automatically`

2. **运行应用**：
   - 右键点击项目
   - 选择 `Run As` → `Spring Boot App`

#### VS Code 用户

1. **安装扩展**：
   - Spring Boot Extension Pack
   - Java Extension Pack

2. **运行应用**：
   - 打开命令面板 (`Ctrl+Shift+P`)
   - 输入 `Spring Boot Dashboard`
   - 点击运行按钮

### 热部署功能

#### 支持的修改类型

✅ **自动重启**：
- Java 类文件修改
- 配置文件修改 (`application.yml`, `application.properties`)
- Mapper XML 文件修改

✅ **无需重启**：
- 静态资源修改 (`static/` 目录下的文件)
- 模板文件修改 (`templates/` 目录下的文件)
- CSS/JS 文件修改

#### 注意事项

1. **排除目录**：静态资源和模板文件被排除在重启监控之外，修改这些文件不会触发重启
2. **LiveReload**：浏览器会自动刷新页面，无需手动刷新
3. **开发环境**：热部署仅在开发环境中启用，生产环境会自动禁用

### 开发建议

1. **使用IDE运行**：建议使用IDE运行应用，而不是命令行，以获得更好的开发体验
2. **保存文件**：修改代码后记得保存文件，IDE会自动触发编译
3. **查看日志**：热部署重启时会在控制台显示相关信息
4. **浏览器缓存**：如果页面没有自动刷新，可以手动刷新浏览器

### 故障排除

#### 热部署不工作

1. **检查依赖**：确保 `spring-boot-devtools` 依赖已正确添加
2. **检查配置**：确保 `application.yml` 中的热部署配置正确
3. **IDE设置**：确保IDE的自动编译功能已启用
4. **重启IDE**：有时候需要重启IDE才能生效

#### 重启太频繁

1. **调整监控目录**：在 `application.yml` 中调整 `additional-paths`
2. **排除不需要的目录**：在 `exclude` 中添加不需要监控的目录

### 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── top/mygld/problemnote/
│   │       ├── controller/     # 控制器
│   │       ├── service/        # 服务层
│   │       ├── mapper/         # MyBatis映射器
│   │       └── entity/         # 实体类
│   └── resources/
│       ├── static/             # 静态资源 (CSS, JS, 图片)
│       ├── templates/          # Thymeleaf模板
│       ├── mapper/             # MyBatis XML映射文件
│       └── application.yml     # 应用配置文件
```

### 启动应用

1. 确保MySQL数据库已启动
2. 确保数据库 `problem_note` 已创建
3. 运行 `ProblemNoteApplication.java`
4. 访问 `http://localhost:8080`

### Maven依赖问题解决

如果遇到Maven插件或依赖下载问题，请按以下步骤操作：

#### 方法一：使用项目内置脚本
- **Windows用户**：双击运行 `clean-and-build.bat`
- **Linux/Mac用户**：运行 `./clean-and-build.sh`

#### 方法二：手动执行命令
```bash
# 清理项目
mvn clean

# 重新下载依赖
mvn dependency:resolve

# 编译项目
mvn compile

# 运行应用
mvn spring-boot:run
```

#### 方法三：使用IDE
1. 在IDE中右键点击项目
2. 选择 `Maven` → `Reload Project`
3. 等待依赖下载完成
4. 运行应用

#### 网络问题解决
项目已配置阿里云和华为云镜像源，如果仍有网络问题：
1. 检查网络连接
2. 尝试使用VPN
3. 等待一段时间后重试

### 技术栈

- **后端**：Spring Boot 2.6.13
- **数据库**：MySQL 8.0
- **ORM**：MyBatis
- **模板引擎**：Thymeleaf
- **前端**：HTML5, CSS3, JavaScript
- **构建工具**：Maven 