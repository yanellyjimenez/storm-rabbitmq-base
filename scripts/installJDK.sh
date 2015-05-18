#!/bin/sh
#sudo apt-get intall default-jre
#sudo apt-get install default-jdk
#sudo apt-get update
echo "Instalando JDK"
chmod 775 /jdk-###/.bin 
cd /root
yes | /qnue_data/jdk-####.bin
/bin/mv /root/jdk-#### /opt
/bin/rm -rv /usr/bin/javac
/bin/ln -s /opt/jdk-###/bin/java /usr/bin 
/bin/ln -s /opt/jdk-###/bin/javac /usr/bin 
export JAVA_HOME=/opt/jdk-###
export PATH=$PATH:$JAVA_HOME/bin


