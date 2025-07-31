# IDE热部署设置说明

## IntelliJ IDEA 设置

### 1. 启用自动编译
1. 打开 `File` -> `Settings` (Windows) 或 `IntelliJ IDEA` -> `Preferences` (Mac)
2. 导航到 `Build, Execution, Deployment` -> `Compiler`
3. 勾选 `Build project automatically`
4. 勾选 `Compile independent modules in parallel`

### 2. 启用运行时自动编译
1. 按 `Ctrl+Shift+Alt+/` (Windows) 或 `Cmd+Shift+Alt+/` (Mac)
2. 选择 `Registry`
3. 找到并勾选 `compiler.automake.allow.when.app.running`

### 3. 项目设置
1. 右键点击项目根目录
2. 选择 `Open Module Settings`
3. 在 `Modules` 中确保 `Sources` 标记正确
4. 确保 `src/main/java` 被标记为 `Sources`
5. 确保 `src/main/resources` 被标记为 `Resources`

## Eclipse 设置

### 1. 启用自动构建
1. 打开 `Project` -> `Build Automatically`
2. 确保项目被勾选

### 2. 项目属性
1. 右键点击项目 -> `Properties`
2. 在 `Java Build Path` 中确保源文件夹配置正确

## VS Code 设置

### 1. 安装扩展
- Spring Boot Extension Pack
- Java Extension Pack

### 2. 设置
1. 打开设置 (`Ctrl+,`)
2. 搜索 `java.autobuild`
3. 确保启用自动构建

## 验证热部署

### 测试步骤
1. 启动应用：`mvn spring-boot:run`
2. 修改任意Java文件（如Controller）
3. 保存文件
4. 观察控制台是否显示重启信息
5. 刷新浏览器验证更改是否生效

### 常见问题解决

#### 问题1：热部署不工作
- 检查 `spring-boot-devtools` 依赖是否正确
- 确保 `application.yml` 中 `devtools.restart.enabled=true`
- 检查IDE的自动编译设置

#### 问题2：静态资源不更新
- 确保 `application.yml` 中 `thymeleaf.cache=false`
- 检查浏览器缓存，强制刷新 (`Ctrl+F5`)

#### 问题3：数据库相关更改不生效
- 数据库结构更改需要重启应用
- 配置文件更改通常需要重启

### 推荐开发流程
1. 使用 `mvn spring-boot:run` 启动应用
2. 在IDE中修改代码
3. 保存文件，观察自动重启
4. 刷新浏览器验证更改

### 性能优化
- 在 `application.yml` 中排除不需要监控的目录
- 使用 `devtools.restart.exclude` 排除静态资源
- 开发环境关闭不必要的功能（如缓存） 