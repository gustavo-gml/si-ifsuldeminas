create database bd_teste;
use bd_teste;

create table produto(
	id_produto int auto_increment primary key,
    nome_produto varchar(50),
    estoque int not null -- quantidade de itens no banco
);

create table item_venda (
	id_item_venda int auto_increment primary key,
    id_produto int,
    quantidade int ,
    
    foreign key (id_produto) references produto(id_produto)
);

-- verificar estoque
-- atualizar a tabela produto

delimiter $$
create trigger tr_valida_estoque 
before insert on item_venda
for each row
begin
    -- criar variavel para receber estoque do produto
    declare quant_estoque int;
    
    -- procurar quant estoque em produto (Correção: adicionado FROM produto)
    select estoque into quant_estoque FROM produto
    where id_produto = new.id_produto;
    
    if quant_estoque < new.quantidade then
        signal sqlstate '45000'
        set message_text = 'Erro: estoque insuficiente';
    else 
        update produto set estoque = estoque - new.quantidade 
        where id_produto = new.id_produto;
    end if;
    
end$$
delimiter ;

drop trigger tr_valida_estoque;


insert into produto(nome_produto, estoque) values('beterraba', 21);

insert into item_venda(id_produto, quantidade) values(1,2);

