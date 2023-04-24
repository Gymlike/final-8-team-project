#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/final-8-team-project-0.0.1-SNAPSHOT.jar"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(pgrep -f $JAR_FILE)

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  MAX_RETRIES=5
  CURRENT_RETRIES=0
  while true; do
    sudo kill -9 $CURRENT_PID
    if [ $? -eq 0 ]; then
      break
    fi
    CURRENT_RETRIES=$((CURRENT_RETRIES+1))
    if [ $CURRENT_RETRIES -eq $MAX_RETRIES ]; then
      echo "Failed to kill the process after $MAX_RETRIES retries. Giving up."
      exit 1
    fi
    sleep 2
  done
fi

