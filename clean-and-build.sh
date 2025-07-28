#!/bin/bash

echo "正在清理项目..."
mvn clean

echo ""
echo "正在下载依赖..."
mvn dependency:resolve

echo ""
echo "正在编译项目..."
mvn compile

echo ""
echo "构建完成！" 