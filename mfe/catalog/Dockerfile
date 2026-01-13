FROM node:20.15-alpine AS build
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.27
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
