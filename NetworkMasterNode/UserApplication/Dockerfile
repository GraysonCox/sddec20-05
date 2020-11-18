######################
#####Angular App######
#####Step 1 of 2######
######################

FROM node:12.2.0 as build

WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH

COPY package.json /app/package.json
RUN npm install

COPY . /app

RUN ng build --prod

######################
#####Angular App######
#####Step 2 of 2######
######################

FROM nginx:1.16.0-alpine

COPY --from=build /app/dist/user-application /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 4200

CMD ["nginx", "-g", "daemon off;"]
