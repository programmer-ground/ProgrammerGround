# Build Stage
FROM node:12-alpine AS builder
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

COPY package*.json ./
RUN yarn install

COPY . .

RUN yarn build

# Runtime Stage
FROM nginx:1.19.6-alpine
ENV APP_HOME=/usr/app

COPY --from=builder $APP_HOME/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]




