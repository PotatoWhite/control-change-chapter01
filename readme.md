# MariaDB 설정
``` shell script
docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 mariadb
```

``` sql
create database changeControl;

create uodeser 'potato'@'%' identified by '1234';

grant all privileges on changeControl.* to 'potato'@'%';
```

