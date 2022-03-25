#!/bin/bash

docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=securedpassword -d --restart=always mysql:8.0.28