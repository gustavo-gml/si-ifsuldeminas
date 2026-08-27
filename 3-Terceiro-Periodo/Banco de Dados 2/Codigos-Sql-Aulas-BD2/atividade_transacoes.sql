use bancobd22026;
create table cliente_2 like cliente;
insert into cliente_2 select * from cliente;

-- Exercício 1
select * from conta;
start transaction;
	update conta set saldo = saldo * 1.10 where idCONTA <> 0 and tipo = 'Poupança';
commit;
select * from conta;

-- Exercício 2
select * from cliente_2;
start transaction;
    insert into cliente_2 (nome, cpf, rg, dataNascimento, telefone) values
    ('Gabinho', '111.111.111-11', 'MG 888.888.88', '1997-11-25' ,'(35)99750-5578'),
	('Zezinho', '222.111.111-11', 'MG 222.888.88', '2006-07-01', '(35)93750-5578'),
	('Saulinho', '333.111.111-11', 'MG 333.888.88', '1933-11-25', '(35)94720-5578'),
	('Tiaguinho', '444.111.111-11', 'MG 444.888.88', '1999-11-25', '(35)93750-5578'),
	('Gustavos', '555.111.111-11', 'MG 999.888.88', '2006-11-25', '(35)99750-4478');
commit;
select * from cliente_2;

-- Exercício 3
select * from contavinculada;
start transaction;
	delete from contavinculada where CLIENTE_idCLIENTE 	=1;
commit;
select * from contavinculada;

-- Exercício 4
select * from cliente_2;
start transaction; 
	update cliente_2 
    set nome = UPPER(nome)
    where idCLIENTE <> 0 and telefone is not null;
commit;
select * from cliente_2;