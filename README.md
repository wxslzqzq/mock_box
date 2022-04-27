# mock-box mock服务平台
## Compile
```bash
# install dependencies or package

mvn clean install -Dmaven.test.skip=true
# or
mvn clean package -Dmaven.test.skip=true

# Clean compilation:
mvn clean 
```
## Run
```bash
# start command
java -jar ${JAVA_OPTS} ${JAR_NAME}

# linux nohup start command ，the ${JAVA_OPTS} parameters should can be adjusted according to actual needs like "-Xms512m -Xmx2048m" 
nohup java -Xms512m -Xmx2048m -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &

# remote debug can choose   "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 "
nohup java -Xms512m -Xmx2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Duser.timezone=Asia/Shanghai -jar mock-box-web-0.0.1.jar  > /dev/null 2>&1 &
```
