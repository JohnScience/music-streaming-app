# Сделано на основе https://milanwittpohl.com/projects/tutorials/Full-Stack-Web-App/dockerizing-our-front-and-backend
FROM node:21-alpine3.17 as build

RUN mkdir -p /app

WORKDIR /app

COPY ./package.json ./package.json ./

RUN npm install

COPY ./public/ ./public
COPY ./src/ ./src
COPY ./index.html ./
COPY ./vite.config.js ./

RUN npm run build

FROM scratch as export

COPY --from=build /app/dist /dist
