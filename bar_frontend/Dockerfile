FROM node:alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install && npm install -g gzipper
COPY . ./
RUN npm run build
FROM nginx:alpine
COPY --from=builder /app/dist/bar_frontend /usr/share/nginx/html/
COPY /nginx.conf /etc/nginx/conf.d/default.conf
COPY fullchain.pem /etc/nginx/
COPY privkey.pem /etc/nginx/
COPY fullchain1.pem /etc/nginx/
COPY privkey1.pem /etc/nginx/
COPY /certificate.crt /etc/nginx/
COPY /private.key /etc/nginx/
EXPOSE 7777
ENTRYPOINT ["nginx","-g","daemon off;"]
