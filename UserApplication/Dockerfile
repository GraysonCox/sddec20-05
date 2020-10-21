######################
#####Angular App######
#####Step 1 of 2######
######################

FROM node:12.2.0 as build

WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH


COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@9.1.0

COPY . /app

RUN ng build --prod

######################
#####Angular App######
#####Step 2 of 2######
######################

FROM nginx:1.16.0-alpine

COPY --from=build /app/dist/sd-ui-demo /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

