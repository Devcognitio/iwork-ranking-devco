FROM anapsix/alpine-java
ADD iwork-ranking-back-all-0.0.1-SNAPSHOT.jar /opt
ENTRYPOINT java -jar /opt/iwork-ranking-back-all-0.0.1-SNAPSHOT.jar -g 'daemon off;'
EXPOSE 8080