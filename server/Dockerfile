FROM tomcat:9.0.0.M13
RUN apt-get --force-yes update && apt-get install -y git
COPY apache-maven-3.3.9-bin.tar.gz /
RUN tar xvf /apache-maven-3.3.9-bin.tar.gz -C /
ENV M2_HOME /apache-maven-3.3.9
ENV PATH $M2_HOME/bin:$PATH
COPY tomcat-users.xml $CATALINA_BASE/conf/tomcat-users.xml
RUN ls
COPY settings.xml $M2_HOME/settings.xml
RUN ls
WORKDIR /usr
RUN mkdir led-config
WORKDIR led-config
RUN git clone https://github.com/bitunified/led-config.git
WORKDIR led-config
RUN ls
RUN mvn clean install deploy -Dmaven.multiModuleProjectDirectory=/usr/led-config/led-config

CMD ["catalina.sh", "run"]