BUILD_ID=DONTKILLME
#!/bin/bash
#编译+部署guns-password站点

#需要配置如下参数
# 项目路径, 在Execute Shell中配置项目路径, pwd 就可以获得该项目路径
#export PROJ_PATH=`pwd`
echo $PROJ_PATH

DIR=/home
JARNAME=guns-admin-1.0.0.jar


#删除原来的包
cd $DIR
if [ -e "$JARNAME" ]
then
    rm -f $JARNAME
fi

echo $JARNAME

PID=$(ps -ef | grep $JARNAME | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
	echo $JARNAME is already stopped
else 
	echo kill $PID
	kill -9 $PID
fi

cd $DIR
cp /root/.jenkins/workspace/GUNS-PASSWORD/guns-password/guns-admin/target/$JARNAME .

nohup java -jar $JARNAME > 1.log &

echo "shell end"