BUILD_ID=DONTKILLME
#!/bin/bash
#编译+部署guns-password站点

#需要配置如下参数
# 项目路径, 在Execute Shell中配置项目路径, pwd 就可以获得该项目路径
export PROJ_PATH=`pwd`
echo $PROJ_PATH

cd ./target
FILENAME=$(find -name guns-admin-1.0.0.jar)
echo $FILENAME
JARNAME=${FILENAME##*/}
echo $JARNAME

PID=$(ps -ef | grep $JARNAME | grep -v grep | awk '{ print $2 }')
if [ -z "$PID"]
then
	echo $JARNAME is already stopped
else 
	echo kill $PID
	kill -9 $PID
fi

cd /home
cp /root/.jenkins/workspace/guns-password/guns-admin/target/$JARNAME .

nohup java -jar $JARNAME > 1.log &

echo "shell end"