FROM php:8.1-fpm

RUN pecl install redis
RUN docker-php-ext-install pdo pdo_mysql

COPY config/php.ini /usr/local/etc/php/

WORKDIR /var/www/html

COPY . .

EXPOSE 8000

CMD php artisan serve --host=0.0.0.0 --port=8000
