use mysql;

show tables;

select * from user;

create user Gabinhous@'%' identified by '12345678';
create user Embaixador2 identified by '12345678';

GRANT ALL PRIVILEGES ON *.* TO 'Gabinhous'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
select * from user;

show grants for Embaixador2;
show grants for Embaixador@localhost;

grant select on sakila.actor to Embaixador2;

