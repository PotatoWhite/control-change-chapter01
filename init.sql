create database changeControl;

create user 'potato'@'%' identified by '1234';

grant all privileges on changeControl.* to 'potato'@'%';