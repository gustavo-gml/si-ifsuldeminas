SHOW DATABASES;

USE mysql;



describe user;

use sakila;

-- Seleciona todos
select * from actor;

-- Seleciona específica e ordena
select first_name FROM actor order by first_name asc;


select first_name FROM actor  where first_name = 'albert';

select count(first_name) from actor where first_name = 'albert';

select count(*) from actor;

-- operador like
use sakila;
select  * from actor where first_name like 'a%';
select  * from actor where first_name like '%a';
select  * from actor where first_name like '%a%';
select  * from actor where first_name like 'a___';


use sakila;
describe payment;

-- SOMA
SELECT SUM(AMOUNT) FROM PAYMENT where customer_id = 3;

-- IMPORTANTE: (10,2) QUANTIDADE DE NUMEROS / QUANTIDADE DE CASAS DECIMAIS

-- MÉDIA
SELECT AVG(AMOUNT) FROM PAYMENT;

-- Maior valor
select max(amount) from payment;


-- Subconsultas 
use sakila;
SELECT * FROM film where rental_duration >
(
	select avg(rental_duration) from film
)
order by rental_duration desc;

use sakila;
SELECT * FROM film where rental_duration =
(
	select max(rental_duration) from film
)
order by rental_rate desc;

/*FROM produto AS p
INNER JOIN item_venda AS iv ON p.id_produto = iv.id_produto*/
SELECT 
f.title,
f.release_year,
l.name,
f.reatal_duration
from
film as f where rental_duration >
(
	select avg(rental_duration) from film
)
order by rental_rate desc;

show tables;


USE sakila;

-- verificação das engines das tabelas 
select table_schema, table_name, engine
from information_schema.tables;

-- alterando a engine de uma tabela especifica 
alter table world.city engine = InnoDB;

select @@autocommit; -- variaveis do sistema
select @autocommit; -- variaveis da sessão 
-- set @@autocommit para alterar o valor de uma variavel do sistema

set @auto = 3;
select @auto;


-- criando um banco para um banco
create database youbank;
use youbank;

-- tabela conta
create table conta (
id_conta int primary key auto_increment,
nome_cliente varchar(100) not null,
saldo decimal(10,2) not null default 0
);

-- tabela transacao 
create table transacao (
id_transacao int auto_increment primary key,
id_conta_origem int,
id_conta_destino int,
valor decimal (10,2),
tipo varchar (50),
data_transacao datetime default current_timestamp,

foreign key (id_conta_origem) references conta(id_conta),
foreign key (id_conta_destino) references conta(id_conta)
);

-- Popolar tabela conta 
insert into conta (nome_cliente,saldo) values
('Gabinho1', 20000.00),
('Gustavous', 111111.00),
('Zezinho', 30000.00),
('Tiaguinho', 332323.00),
('Saulinho', 22222222.00);

show tables;
show create table conta;
describe conta;

select * from conta;

-- Gabinho pagou 1000 reais para gustavous 

-- rollback
start transaction;
update conta set saldo = saldo - 1000 where id_conta = 16;
select * from conta;
rollback;
select * from conta;


-- commit
start transaction;
	update conta set saldo = saldo - 1000 where id_conta = 16;
	select * from conta;
	update conta set saldo = saldo + 1000 where id_conta = 17;
	select * from conta;
commit;



-- em outra conexão essas alterações não são visiveis antes do commit 
start transaction;
	select * from conta;
    update conta set saldo = saldo - 1111 where id_conta = 17;
    select * from conta;
	update conta set saldo = saldo + 1111 where id_conta = 16;
	select * from conta;
commit;

select * from conta;
select * from transacao;
insert into transacao (id_conta_origem, id_conta_destino, valor, tipo) values 
(17, 16, 1111, 'Sonegação de imposto 
');
select * from transacao;

start transaction;
	-- comandos 
    
-- commit ou rollback


start transaction;
	delete from transacao where  id_transacao <> '';
    select * from transacao;
rollback;
	select * from transacao;

-- como copiar uma tabela    

-- versão 1: se o banco não reclamar (usando um create com select *)
create table teste as select * from cliente;

-- versão 2: criar estrutura com base na tabela existente
create table cliente_2 like cliente;
describe cliente_2;

-- inserindo os dados na tabela nova 
insert into cliente_2 select * from cliente;
select * from cliente_2;

-- Atencao truncate não tem rollback (Comando DML)
start transaction; 
	delete from cliente_2 where idCLIENTE != 0; 
    select * from cliente_2;
	rollback;
use bancobd22026;
show tables;
select * from cliente_2;
    
