#!/bin/bash
readonly PROC_HOME="/home/oddiadm/app/scheduler"
readonly PROC_NAME="scheduler"
readonly DAEMON="${PROC_HOME}/${PROC_NAME}.jar"
readonly OPTS="-Duser.timezone=Asia/Seoul -Dspring.profiles.active=op"


start()
{
    echo "Starting  ${PROC_NAME}..."
    local PID=$(get_status)
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is already running"
        exit 0
    fi
    java -jar ${OPTS} "${DAEMON}" &
    local PID=${!}

    if [ -n ${PID} ]; then
        echo " - Starting..."
    else
        echo " - failed to start."
    fi
}

stop()
{
    echo "Stopping ${PROC_NAME}..."
    local DAEMON_PID=$(get_status)

    if [ "$DAEMON_PID" -lt 3 ]; then
        echo "${PROC_NAME} was not  running."
    else
        kill $DAEMON_PID
        echo " - Shutdown ...."
    fi
}

status()
{
    local PID=$(get_status)
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is running"
    else
        echo "${PROC_NAME} is stopped"
    fi
}

get_status()
{
    ps ux | grep java | grep java | grep ${PROC_NAME} | awk '{print $2}'
}

case "$1" in
    start)
        start
        sleep 2
        ;;
    stop)
        stop
        sleep 2
        ;;
    restart)
        stop
        start
        ;;
    status)
        status "${PROC_NAME}"
        ;;
    *)
    echo "Usage: $0 {start | stop | restart | status }"
esac
exit 0
