FROM tomcat:9.0.0.M13
RUN apt-get --force-yes update && apt-get install -y git
RUN apt-get install maven -y
COPY tomcat-users.xml $CATALINA_BASE/conf/tomcat-users.xml
COPY settings.xml $M2_HOME/settings.xml
WORKDIR /usr
RUN mkdir led-config
WORKDIR led-config
RUN git clone https://github.com/bitunified/led-config.git
WORKDIR led-config
RUN ls
RUN mvn clean install deploy -Dmaven.multiModuleProjectDirectory=/usr/led-config/led-config

CMD ["catalina.sh", "run"]