#内容

FROM nginx

WORKDIR /usr/share/nginx/html/
USER root

COPY ./nginx.conf /etc/nginx/conf.d/default.conf

COPY ./dist  /usr/share/nginx/html/

#前端项目端口
EXPOSE 31452

CMD ["nginx", "-g", "daemon off;"]


