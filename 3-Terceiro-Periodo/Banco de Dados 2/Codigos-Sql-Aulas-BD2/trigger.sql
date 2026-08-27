create database if not exists teste_trigger;

use teste_trigger;

create table if not exists estoque(
	id_estoque int auto_increment primary key,
    nome_item_estoque varchar(45) not null,
    preco_item decimal(10,2) not null,
    preco_desconto decimal(10,2) not null,
    quantidade int not null
);

create table if not exists cliente(
	id_cliente int auto_increment primary key,
    nome varchar(100) not null,
    idade int not null,
    ano_nascimento year not null
    );
    



describe estoque;
select * from estoque;

-- Triger de desconto de 10%
create trigger tr_desconto_preco 
before insert on estoque
for each row
	set new.preco_desconto = new.preco_item * 0.90;
    
-- Triger para upper 
create trigger tr_upper_item
before insert on estoque
for each row
	set new.nome_item_estoque = Upper(new.nome_item_estoque);
    
-- alter trigger não existe, somente deleção para mudar
drop trigger if exists tr_idade_cliente;
    
show triggers;

insert into Estoque(nome_item_estoque, preco_item, quantidade) 
values('Placa de video', 1600, 10);

insert into Estoque(nome_item_estoque, preco_item, quantidade) 
values('hd 5000', 600, 10);


select * from estoque;



-- trigger para calcular idade
create trigger tr_idade_cliente
before insert on cliente
for each row
	set new.idade = year(now()) - new.ano_nascimento;


insert into cliente(nome, ano_nascimento) values ('Gustavo',2006);
select * from cliente;

-- Exericicio auditoria
create table auditoria(
	id_auditoria int primary key auto_increment,
    nome_item_estoque varchar(100) not null,
    preco_item decimal(10,2) not null,
    preco_desconto decimal(10,2) not null,
    data_insercao datetime default current_timestamp
);

create trigger tr_audita_estoque
after insert on Estoque
for each row
	insert into auditoria(nome_item_estoque, preco_item, preco_desconto)
    values (new.nome_item_estoque, new.preco_item, new.preco_desconto);
    
    
create table item_venda (
	id_item_venda int primary key auto_increment,
    quantidade int,
    data_venda datetime default current_timestamp,
	id_estoque int,
    foreign key (id_estoque) references estoque(id_estoque)
);

-- trigger para venda
create trigger tr_baixa_venda
after insert on item_venda
for each row
	update estoque set estoque.quantidade = estoque.quantidade - new.quantidade where new.id_estoque = estoque.id_estoque;



INSERT INTO item_venda (quantidade, id_estoque) values (1, 1);
INSERT INTO item_venda (quantidade, id_estoque) values (3, 2);

select * from estoque;
