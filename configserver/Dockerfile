FROM coreos/apache

RUN apt-get update -y && \
    apt-get install -y wget && \
    cd /opt && \
    wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn-pub/java/jdk/8u202-b08/1961070e4c9b4e26a04e7f5a083f551e/jdk-8u202-linux-i586.tar.gz" && \
    tar -zxf jdk-8u202-linux-i586.tar.gz && \
    apt-get install -y libc6-i386 && \
    update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_202/bin/java 1
