create user zeze@'10.0.25.15' identified by '1234';
grant select on sakila.country to zeze@'10.0.25.15';
grant select on sakila.country to zeze@'10.0.25.15';

show grants for zeze@'10.0.25.15';

use mysql;

select * from user;