FROM maven:3-jdk-11 AS mvn_build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD . $HOME
RUN mvn package

FROM tomcat:9-jdk11
COPY --from=mvn_build /usr/app/target/AuthorizationSystem.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8084
CMD ["catalina.sh", "run"]