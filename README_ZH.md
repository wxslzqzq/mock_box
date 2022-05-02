# mock-box mock服务平台
## Compile
```bash
# 安装依赖或发布

mvn clean install -Dmaven.test.skip=true
# or
mvn clean package -Dmaven.test.skip=true

# Clean compilation:
mvn clean 
```
## Run

### 基础运行命令
```bash
java -jar ${JAVA_OPTS} ${JAR_NAME}
```
### 挂起执行" 
```bash
nohup java -Xms512m -Xmx2048m -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &
```

### 远程debug"
```
nohup java -Xms512m -Xmx2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &
```
