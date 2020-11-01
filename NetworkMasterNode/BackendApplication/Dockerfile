######################
#####Gradle  App######
#####Step 1 of 1######
######################
FROM gradle:jdk14 
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
CMD ./gradlew bootRun