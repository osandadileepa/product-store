FROM node:12.7-alpine as node
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN npm install -g @angular/cli@8.3.25
WORKDIR /app/source/src/main/webapp
RUN npm install
RUN ng build --prod --aot
RUN mkdir -p /app/source/src/main/resources/static
RUN cp -a /app/source/src/main/webapp/dist/webapp/* /app/source/src/main/resources/static


FROM openjdk:8-jdk-alpine as builder
RUN mkdir -p /app/source
COPY --from=node /app/source /app/source/
WORKDIR /app/source
RUN SPRING_PROFILES_ACTIVE=dev ./gradlew clean build -x test


FROM builder
COPY --from=builder /app/source/build/libs/*.jar product-store-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "product-store-0.0.1-SNAPSHOT.jar"]



