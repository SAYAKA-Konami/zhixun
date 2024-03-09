# 该镜像需要依赖的基础镜像
FROM openjdk:17

RUN mkdir -p /home/log & mkdir -p /home/arthas

# 将当前目录下的jar包复制到docker容器的/目录下
ADD ./target/bj-zhixun-1.0.0-SNAPSHOT.jar ./bj-zhixun.jar

# 将本地的 arthas-boot.jar 复制到容器的 /home/arthas 目录下
# 注意：在 Dockerfile 中使用 ~ 或 .. 是不被允许的，因为这可能导致构建过程访问到构建上下文之外的文件，这是出于安全考虑的。
COPY ./arthas-boot.jar /home/arthas/arthas-boot.jar

# 声明服务运行在8080端口
EXPOSE 8090

# 设置日志路径
ENV LOG_PATH /home/log

#生产环境用以下参数
#ENV JAVA_OPTS="-XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:-OmitStackTraceInFastThrow -Xlog:gc*=debug:file=${LOG_PATH}/gc%t.log:utctime,level,tags:filecount=50,filesize=100M -Xlog:jit+compilation=info:file=${LOG_PATH}/jit_compile%t.log:utctime,level,tags:filecount=10,filesize=10M -Xlog:safepoint=debug:file=${LOG_PATH}/safepoint%t.log:utctime,level,tags:filecount=10,filesize=10M -Xlog:async -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -Dnetworkaddress.cache.ttl=10 -XX:MaxRAMPercentage=45 -XX:InitialRAMPercentage=45 -XX:+AlwaysPreTouch -Xss512k -XX:MaxDirectMemorySize=256m -XX:MaxMetaspaceSize=384m -XX:ReservedCodeCacheSize=256m -XX:+DisableExplicitGC -XX:MaxGCPauseMillis=100 -XX:-UseBiasedLocking -XX:GuaranteedSafepointInterval=0 -XX:+UseCountedLoopSafepoints -XX:+SafepointTimeout -XX:SafepointTimeoutDelay=1000 -XX:StartFlightRecording=disk=true,maxsize=4096m,maxage=3d,filename=${LOG_PATH}/jvm.jfr -XX:FlightRecorderOptions=maxchunksize=128m --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.io=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED --add-opens java.base/java.net=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/java.security=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/jdk.internal.access=ALL-UNNAMED --add-opens java.base/jdk.internal.misc=ALL-UNNAMED"

#测试环境用以下参数
ENV JAVA_OPTS="-Xmx1G -Xms1G -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:-OmitStackTraceInFastThrow -Xlog:gc*=debug:file=${LOG_PATH}/gc%t.log:utctime,level,tags:filecount=50,filesize=100M -Xlog:jit+compilation=info:file=${LOG_PATH}/jit_compile%t.log:utctime,level,tags:filecount=10,filesize=10M -Xlog:safepoint=debug:file=${LOG_PATH}/safepoint%t.log:utctime,level,tags:filecount=10,filesize=10M -Xlog:async -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -Dnetworkaddress.cache.ttl=10 -XX:+AlwaysPreTouch -Xss512k -XX:+DisableExplicitGC -XX:MaxGCPauseMillis=100 -XX:-UseBiasedLocking -XX:GuaranteedSafepointInterval=0 -XX:+UseCountedLoopSafepoints -XX:+SafepointTimeout -XX:SafepointTimeoutDelay=1000 -XX:StartFlightRecording=disk=true,maxsize=4096m,maxage=3d,filename=${LOG_PATH}/jvm.jfr -XX:FlightRecorderOptions=maxchunksize=128m --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.io=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED --add-opens java.base/java.net=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/java.security=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/jdk.internal.access=ALL-UNNAMED --add-opens java.base/jdk.internal.misc=ALL-UNNAMED"

# 指定docker容器启动时运行jar包
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /bj-zhixun.jar"]

# 指定维护者的名字
MAINTAINER wn