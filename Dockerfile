FROM tomcat:9.0.0.M13
RUN apt-get --force-yes update && apt-get install -y git
COPY apache-maven-3.3.9-bin.tar.gz /
RUN tar xvf /apache-maven-3.3.9-bin.tar.gz -C /
ENV M2_HOME /apache-maven-3.3.9
ENV PATH $M2_HOME/bin:$PATH
COPY server/tomcat-users.xml $CATALINA_BASE/conf/tomcat-users.xml
COPY server/target/server-1.0.0-SNAPSHOT.war $CATALINA_HOME/webapps/server.war
COPY web-steps/led-config/dist/ $CATALINA_HOME/webapps/ROOT/
RUN ls
COPY server/settings.xml $M2_HOME/settings.xml
RUN ls
COPY server/pkcs8_key /usr/local/tomcat/temp/pkcs8_key
WORKDIR /usr
RUN mkdir led-config
WORKDIR led-config
RUN ls

CMD ["catalina.sh", "run"]