# PageHelper分页功能说明

## 功能概述

本项目已集成PageHelper分页插件，为科目管理和题目收藏功能提供分页支持，每页最多显示10条记录。

## 配置说明

### 1. Maven依赖

在`pom.xml`中添加了PageHelper依赖：

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.6</version>
</dependency>
```

### 2. 应用配置

在`application.yml`中添加了PageHelper配置：

```yaml
# PageHelper分页插件配置
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
  page-size-zero: true
```

## 实现细节

### 1. 分页结果类

创建了`PageResult<T>`类来封装分页信息：

- `list`: 当前页的数据列表
- `total`: 总记录数
- `pageNum`: 当前页码
- `pageSize`: 每页大小
- `pages`: 总页数
- `hasNextPage`: 是否有下一页
- `hasPreviousPage`: 是否有上一页

### 2. 服务层修改

#### SubjectService
- 添加了`getSubjectsByPage(int pageNum, int pageSize)`方法
- 使用PageHelper进行分页查询

#### ProblemService
- 添加了`getFavoriteProblemsByPage(int pageNum, int pageSize)`方法
- 支持题目收藏的分页查询

### 3. 控制器层修改

#### SubjectController
- 修改`/subject/list`接口，支持`pageNum`参数
- 默认页码为1，每页10条记录

#### PageController
- 修改`/problem/collection`接口，支持`pageNum`参数
- 默认页码为1，每页10条记录

### 4. 前端模板

#### 分页组件
在`subject_list.html`和`problem_collection.html`中添加了分页组件：

- 显示总记录数、当前页、总页数
- 上一页/下一页导航
- 页码导航（显示当前页前后2页）
- 当前页高亮显示

#### 样式设计
在`style.css`中添加了分页组件的样式：

- 现代化的分页按钮设计
- 响应式布局，适配移动端
- 悬停效果和过渡动画

## 使用方式

### 1. 科目列表分页

访问：`/subject/list?pageNum=1`

参数说明：
- `pageNum`: 页码，默认为1

### 2. 题目收藏分页

访问：`/problem/collection?pageNum=1`

参数说明：
- `pageNum`: 页码，默认为1

## 分页组件特性

### 1. 智能显示
- 只有当有数据时才显示分页组件
- 自动计算总页数和导航状态

### 2. 用户体验
- 显示详细的页码信息
- 上一页/下一页按钮
- 当前页高亮显示
- 页码范围智能显示（当前页前后2页）

### 3. 响应式设计
- 桌面端：完整的分页组件
- 移动端：紧凑的分页按钮，适配小屏幕

## 技术特点

### 1. 性能优化
- 使用PageHelper自动生成分页SQL
- 只查询当前页需要的数据
- 避免一次性加载大量数据

### 2. 代码简洁
- 服务层只需调用`PageHelper.startPage()`
- 自动处理分页逻辑
- 统一的`PageResult`封装

### 3. 扩展性
- 易于添加新的分页功能
- 支持自定义每页大小
- 可配置的分页参数

## 注意事项

1. **数据库连接**：当前题目收藏功能使用临时实现，避免数据库连接问题
2. **依赖下载**：如果遇到Maven依赖下载问题，请检查网络连接或使用镜像源
3. **分页参数**：页码从1开始，不是从0开始
4. **每页大小**：当前固定为10条记录，可根据需要调整

## 后续优化

1. 添加每页大小选择功能
2. 支持跳转到指定页
3. 添加分页缓存机制
4. 优化大数据量下的分页性能 