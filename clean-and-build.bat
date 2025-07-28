@echo off
echo 正在清理项目...
call mvn clean

echo.
echo 正在下载依赖...
call mvn dependency:resolve

echo.
echo 正在编译项目...
call mvn compile

echo.
echo 构建完成！
pause 