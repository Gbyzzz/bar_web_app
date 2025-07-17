FROM node:alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install && npm install -g gzipper
ARG ENV

COPY . ./
COPY src/environments/environment.$ENV.ts ./src/environments/environment.ts

RUN npm run build

FROM nginx:alpine
COPY --from=builder /app/dist/bar_frontend/browser /usr/share/nginx/html
COPY /nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 3001
ENTRYPOINT ["nginx","-g","daemon off;"]
