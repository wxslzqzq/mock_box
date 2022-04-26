#mock-box mock服务平台
##Compile
```bash
# 安装依赖或发布

mvn clean install -Dmaven.test.skip=true
# or
mvn clean package -Dmaven.test.skip=true

# clean compilation:
mvn clean 
```
##Run
```bash
# 基础运行命令
java -jar ${JAVA_OPTS} ${JAR_NAME}

# 建议命令，其中${JAVA_OPTS}为其他jvm可选参数如指定内存大小"-Xms512m -Xmx2048m" 
nohup java -Xms512m -Xmx2048m -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &

# 远程debug选择将${JAVA_OPTS}的值增加"-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 "
nohup java -Xms512m -Xmx2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &
```
