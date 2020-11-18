######################
#####Gradle  App######
#####Step 1 of 1######
######################
FROM gradle:jdk14 

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN apt-get update -y
RUN apt-get install nmap -y

CMD ./gradlew bootRun
