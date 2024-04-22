#!/bin/bash

BUILD_JAR=$(ls /home/ec2-user/action/build/libs/*SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "## build file name : $JAR_NAME" >> /home/ec2-user/action/spring-deploy.log

echo "## copy build file" >> /home/ec2-user/action/spring-deploy.log
DEPLOY_PATH=/home/ec2-user/action/
cp $BUILD_JAR $DEPLOY_PATH

## 찾고자 하는 문자열을 갖고오는 명령어
echo "## current pid" >> /home/ec2-user/action/spring-deploy.log
CURRENT_PID=$(ps -ef | grep "java" | awk 'NR==1 {print $2}')

if [ -z $CURRENT_PID ]
then
  echo "## 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/action/spring-deploy.log
else
  echo "## kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

## 로그 메시지를 마지막줄에 입력
DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "## deploy JAR file"   >> /home/ec2-user/action/spring-deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ec2-user/action/spring-deploy.log 2> /home/ec2-user/action/spring-deploy_err.log &
