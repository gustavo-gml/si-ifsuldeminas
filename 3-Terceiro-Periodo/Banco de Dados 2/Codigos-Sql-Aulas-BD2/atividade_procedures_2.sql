use burger_2026;

-- 1
delimiter #
create procedure sp_altera_email
	(
    in p_cod int,
	in p_email varchar(200)
    )
begin 
	update cliente 
    set email = p_email 
    where codCliente = p_cod;
end #
delimiter ;

call sp_altera_email(1 , "carlitosreidelas@gmail.com");

-- 2
delimiter #
create procedure sp_insere_marca
	(
	in p_marca varchar(200)
    )
begin 
	insert into marca(nome)
    values (p_marca);
    
end #
delimiter ;

-- 3

delimiter #
create procedure sp_altera_marca
	(
    in p_cod int,
	in p_marca varchar(200)
    )
begin 
	update marca
    set nome = p_marca
    where codMarca = p_cod;
end #
delimiter ;

-- 4

delimiter #
create procedure sp_apaga_marca
	(
    in p_cod int
    )
begin 
	delete from marca
    where codMarca = p_cod;
end #
delimiter ;


-- 5


delimiter #
create procedure sp_insere_categoria
	(
	in p_categoria varchar(200)
    )
begin 
	insert into categoria(nome)
    values (p_categoria);
    
end #
delimiter ;

-- 6

delimiter #
create procedure sp_insere_cliente
	(
	in p_nome varchar(200),
    in p_data date
    )
begin 
	insert into cliente(nome, dataNascimento)
    values (p_nome, p_data);
end #
delimiter ;


-- 7 

delimiter #
create procedure sp_altera_margem_produto
	(
    in p_cod int,
	in p_margem decimal(5,2)
    )
begin 
	update produto
    set margemLucro = p_margem
    where codProduto = p_cod;
    
    if row_count() = 0 then
		signal sqlstate '45000'
        set message_text= 'Erro: Código do produto não encontrado!';
    end if;
    
end #
delimiter ;



-- 8

delimiter #
create procedure sp_insere_produto
	(
    in p_nome varchar(200),
	in p_preco_custo decimal(5,2),
	in p_preco_venda decimal(5,2),
    in p_categoria int,
    in p_marca int
    )
begin 
	  insert into 
      produto(nome, precoCusto, precoVenda, CATEGORIA_codCategoria, MARCA_codMarca) 
      values (p_nome, p_preco_custo, p_preco_venda, p_categoria, p_marca);
end #
delimiter ;


-- 9 


delimiter #

create procedure sp_consulta_cliente_nome
(
    in p_nome varchar(100)
)
begin
    select * 
    from cliente 
    where nome like concat('%', p_nome, '%');
end #

delimiter ;







