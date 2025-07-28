@echo off
echo 启动开发环境（支持热部署）...
echo.

REM 清理并编译项目
echo 正在编译项目...
call mvn clean compile

REM 启动Spring Boot应用
echo 正在启动应用...
call mvn spring-boot:run

pause 