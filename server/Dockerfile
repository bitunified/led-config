FROM tomcat:9.0.0.M13
RUN apt-get --force-yes update && apt-get install -y git
RUN apt-get install maven -y
WORKDIR /usr
RUN mkdir led-config
WORKDIR led-config
RUN git clone https://github.com/bitunified/led-config.git
WORKDIR led-config
RUN ls
RUN mvn clean install package -Dmaven.multiModuleProjectDirectory=/usr/led-config/led-config
RUN cp led-config/web/target $CATALINA_HOME/webapps