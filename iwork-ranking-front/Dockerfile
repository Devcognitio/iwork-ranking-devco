FROM nginx:latest

COPY default.conf /etc/nginx/conf.d/default.conf.templ
COPY dist /usr/share/nginx/html
RUN chown -R nginx:nginx /usr/share/nginx/html/

EXPOSE 80 443

