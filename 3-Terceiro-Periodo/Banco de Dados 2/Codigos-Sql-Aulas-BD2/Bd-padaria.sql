create database if not exists padariaDB;
use padariaDB;
-- Tabela Cliente
create table cliente (
id_cliente int auto_increment primary key,
nome_cliente varchar(150) not null,
telefone_cliente varchar(20),
cidade_cliente varchar(100)
);
-- Tabela Produto
create table produto (
id_produto int auto_increment primary key,
nome_produto varchar(150) not null,
preco_produto decimal (10,2) not null,
estoque_produto int not null default 0,
categoria_produto enum('Pão','Bolo','Salgado','Bebida') not null
);
-- Tabela Venda
Create table venda (
id_venda int auto_increment primary key,
data_venda datetime default current_timestamp,
id_cliente int,
constraint fk_venda_cliente
foreign key (id_cliente)
references cliente(id_cliente)
on delete set null
on update cascade
);
-- Tabela Item venda
create table item_venda (
id_venda int,
id_produto int,
quantidade_vendida int not null,
preco_unitario decimal (10,2) not null,
primary key (id_venda, id_produto),
constraint fk_item_venda_venda
foreign key (id_venda)
references venda(id_venda)
on delete cascade
on update cascade,
constraint fk_item_venda_produto
foreign key (id_produto)
references produto(id_produto)
on delete cascade
on update cascade
);
-- Inserts Cliente
insert into cliente (nome_cliente, telefone_cliente, cidade_cliente) values
('João', '1111','São Paulo'),
('Maria','2222','Machado');

-- Inserts Produtos
insert into produto (nome_produto, preco_produto, estoque_produto, categoria_produto) values
('Pão frances',0.6,500,'Pão'),
('Pão integral', 1.2,200,'Pão'),
('Bolo de Chocolate',15.00, 25,'Bolo');
-- Inserts Venda (A data vai ser colocada automaticamente)
insert into venda (id_cliente) values
(1),(2),(1),(2);
-- Inserts iten_venda
insert into item_venda (id_venda, id_produto, quantidade_vendida, preco_unitario) values
-- Venda João
(1,1,10,0.6),
(1,2,2,1.2),
-- Venda Maria
(2,3,1,15.0),
(2,2,2,1.2),
-- Nova venda João
(3,1,10,0.6),
(3,3,2,15.0),
-- Nova venda Maria
(4,3,3,15.0),
(4,2,2,1.2);


use padariadb;
-- com inner join
SELECT venda.id_venda, venda.data_venda, cliente.nome_cliente
FROM venda
INNER JOIN cliente
ON venda.id_cliente = cliente.id_cliente;

-- com select from e where
select v.id_venda 'Codigo da venda', c.nome_cliente 'Nome Cliente'
from venda as v, cliente as c
where v.id_cliente = c.id_cliente;


show databases;
show tables;
-- Relatório itens vendidos com o produto e cliente
-- Select p.produto "Nome Produto", iv.quantidade


-- Relatório de vendas indicando o total vendido em cada venda 
/* Select p.nome_produto "Nome Produto", 
iv.quantidade_vendida "Quantidade vendida", 
Sum(iv.quantidade_vendida * iv.preco_unitario) as Total
from produto as p
join p.id_produto = iv.id_produto
group by p.nome_produto; Corrigir*/

SELECT 
    p.nome_produto AS "Nome Produto", 
    SUM(iv.quantidade_vendida) AS "Quantidade vendida", 
    SUM(iv.quantidade_vendida * iv.preco_unitario) AS "Total"
FROM produto AS p
INNER JOIN item_venda AS iv ON p.id_produto = iv.id_produto
GROUP BY p.nome_produto;


use padariadb;
select nome_produto into @nometeste 
from produto limit 1;

select @nometeste;






