
if [ "$1" != "" ]; then
    export DOCKER_PASSWORD
    DOCKER_PASSWORD=$1
    export DOCKER_USERNAME
    DOCKER_USERNAME="bitunified"
    MAVEN_HOME="/c/Program Files/apache-maven-3.3.9"
    export MAVEN_HOME
    PATH=$PATH:$MAVEN_HOME/bin
    JAVA_HOME="/c/Program Files/Java/jdk1.8.0_111"
    export PATH=$JAVA_HOME/bin:$PATH
    mvn clean install
    cd web-steps/led-config
    ng build --prod --base-href server
    cd ..
    cd ..
    docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
    docker build -t bitunified/led-config-web-steps .
    docker push bitunified/led-config-web-steps
    ssh root@192.168.16.4 'docker stop $(docker ps -a -q); docker pull bitunified/led-config-web-steps; docker run -i -p 8080:8080 bitunified/led-config-web-steps'
else
    echo "No password"
fi
