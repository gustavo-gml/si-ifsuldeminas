-- 1 Criar Banco de dados Biblioteca
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Criando tabela de usuários
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefone VARCHAR(20)
);

-- Criando tabela de Autores
CREATE TABLE autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nome_autor VARCHAR(150) NOT NULL
);

-- Criando tabela de livros

CREATE TABLE livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_publicacao INT,
    id_autor INT,
    quantidade_total INT NOT NULL,
    quantidade_disponivel INT NOT NULL,

    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);

-- Criando Tabela de Empréstimos
CREATE TABLE emprestimo (
    id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    data_emprestimo DATE NOT NULL,
    data_prevista DATE NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Criando Tabela Item Emprestado
CREATE TABLE item_emprestimo (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_emprestimo INT,
    id_livro INT,
    data_devolucao DATE,

    FOREIGN KEY (id_emprestimo) REFERENCES emprestimo(id_emprestimo),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);

-- Inserções iniciais basicas:
-- Tabela de Usuários
INSERT INTO usuario (nome, email, telefone) VALUES
('João Silva', 'joao@email.com', '11999999999'),
('Maria Souza', 'maria@email.com', '11988888888');

-- Tabela de Autores
INSERT INTO autor (nome_autor) VALUES
('Machado de Assis'),
('Clarice Lispector');

-- Tabela de Livros
INSERT INTO livro (titulo, ano_publicacao, id_autor, quantidade_total, quantidade_disponivel) VALUES
('Dom Casmurro', 1899, 1, 5, 5),
('A Hora da Estrela', 1977, 2, 3, 3);

-- Tabela de Empréstimos
INSERT INTO emprestimo (id_usuario, data_emprestimo, data_prevista) VALUES
(1, '2026-05-01', '2026-05-10');

-- Tabela de Itens de empréstimo
INSERT INTO item_emprestimo (id_emprestimo, id_livro, data_devolucao) VALUES
(1, 1, NULL);



-- Resolução user(); pegar usuario logado

-- 2 alter table ADD COLUMN nome_da_nova_coluna tipo_de_dado;
use biblioteca;
alter table autor 
add creation_date datetime,
add creation_user varchar(60),
add last_update_date datetime,
add last_updated_by_user varchar(60);

alter table emprestimo
add creation_date datetime,
add creation_user varchar(60),
add last_update_date datetime,
add last_updated_by_user varchar(60);

alter table item_emprestimo 
add creation_date datetime,
add creation_user varchar(60),
add last_update_date datetime,
add last_updated_by_user varchar(60);

alter table livro 
add creation_date datetime,
add creation_user varchar(60),
add last_update_date datetime,
add last_updated_by_user varchar(60);

alter table usuario
add creation_date datetime,
add creation_user varchar(60),
add last_update_date datetime,
add last_updated_by_user varchar(60);

-- 2.b criar tabelas de auditorias ------------------
create table auditoria_autor(
	id_auditoria_autor INT AUTO_INCREMENT PRIMARY KEY,
    id_autor INT,
    nome_autor VARCHAR(150) NOT NULL,
    action_date datetime,
    action_type varchar(30),
    action_user varchar(60)
);


create table if not exists auditoria_emprestimo(
    id_auditoria_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
	id_emprestimo INT,
    id_usuario INT,
    data_emprestimo DATE NOT NULL,
    data_prevista DATE NOT NULL,
	action_date datetime,
    action_type varchar(30),
    action_user varchar(60)
);

create table auditoria_item_emprestimo(
    id_auditoria_item_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT,
    id_emprestimo INT,
    id_livro INT,
    data_devolucao DATE,
	action_date datetime,
    action_type varchar(30),
    action_user varchar(60)
);

create table auditoria_livro(
	id_auditoria_livro INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT ,
    titulo VARCHAR(200) NOT NULL,
    ano_publicacao INT,
    id_autor INT,
    quantidade_total INT NOT NULL,
    quantidade_disponivel INT NOT NULL,
	action_date datetime,
    action_type varchar(30),
    action_user varchar(60)
);
create table auditoria_usuario(
	id_auditoria_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
	action_date datetime,
    action_type varchar(30),
    action_user varchar(60)
);

-- --------------------------------------------------------------

-- 2.c criar triggers de alimentação-------------------------------
-- complemento das tabelas originais
create trigger tr_creation_autor 
before insert on autor
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_autor 
before update on autor
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    
    
create trigger tr_creation_emprestimo 
before insert on emprestimo
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_emprestimo 
before update on emprestimo
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    

create trigger tr_creation_item_emprestimo
before insert on item_emprestimo
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_item_emprestimo
before update on item_emprestimo
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    
create trigger tr_creation_livro 
before insert on livro
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_livro
before update on livro
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    
    
create trigger tr_creation_usuario
before insert on usuario
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_usuario
before update on usuario
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    
    
 insert into autor(nome_autor) values("Teste Triggers 2");
 update autor set nome_autor = "Tolstoi" where id_autor =  3;

-- triggers para popular as auditorias-----------------------------------    

-- autor------------------------------------------
delimiter $$
create trigger tr_auditoria_autor_in
after insert on autor
for each row
begin
	insert into auditoria_autor(id_autor, nome_autor, action_date, action_type, action_user)
    values (new.id_autor, new.nome_autor, now(), 'INSERT', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_autor_up
after update on autor
for each row
begin
	insert into auditoria_autor(id_autor, nome_autor, action_date, action_type, action_user)
    values (new.id_autor, new.nome_autor, now(), 'UPDATE', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_autor_del
after delete on autor
for each row
begin
	insert into auditoria_autor(id_autor, nome_autor, action_date, action_type, action_user)
    values (old.id_autor, old.nome_autor, now(), 'DELETE', old.creation_user);
end $$
delimiter ;

-- autor------------------------------------------


-- emprestimo------------------------------------------
delimiter $$
create trigger tr_auditoria_emprestimo_in
after insert on emprestimo
for each row
begin
	insert into auditoria_emprestimo(id_emprestimo, id_usuario, data_emprestimo, data_prevista,
    action_date, action_type, action_user)
    values (new.id_emprestimo, new.id_usuario, new.data_emprestimo, new.data_prevista, now(), 'INSERT', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_emprestimo_up
after update on emprestimo
for each row
begin
	insert into auditoria_emprestimo(id_emprestimo, id_usuario, data_emprestimo, data_prevista,
    action_date, action_type, action_user)
    values (new.id_emprestimo, new.id_usuario, new.data_emprestimo, new.data_prevista, now(), 'UPDATE', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_emprestimo_del
after delete on emprestimo
for each row
begin
	insert into auditoria_emprestimo(id_emprestimo, id_usuario, data_emprestimo, data_prevista,
    action_date, action_type, action_user)
    values (old.id_emprestimo, old.id_usuario, old.data_emprestimo, old.data_prevista, now(), 'DELETE', old.creation_user);
end $$
delimiter ;

-- emprestimo ------------------------------------------
insert into emprestimo(id_usuario, data_emprestimo, data_prevista) values (1, now(), now());


-- item emprestimo------------------------------------------
delimiter $$
create trigger tr_auditoria_item_emprestimo_in
after insert on item_emprestimo
for each row
begin
	insert into auditoria_item_emprestimo(id_emprestimo, id_livro, data_devolucao,
    action_date, action_type, action_user)
    values (new.id_emprestimo, new.id_livro, new.data_devolucao, now(), 'INSERT', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_item_emprestimo_up
after update on item_emprestimo
for each row
begin
	insert into auditoria_item_emprestimo(id_emprestimo, id_livro, data_devolucao,
    action_date, action_type, action_user)
    values (new.id_emprestimo, new.id_livro, new.data_devolucao, now(), 'UPDATE', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_item_emprestimo_del
after delete on item_emprestimo
for each row
begin
    insert into auditoria_item_emprestimo(id_emprestimo, id_livro, data_devolucao,
    action_date, action_type, action_user)
    values (old.id_emprestimo, old.id_livro, old.data_devolucao, now(), 'DELETE', old.creation_user);
end $$
delimiter ;
-- item emprestimo ------------------------------------------


-- livro --------------------------------------------------
delimiter $$
create trigger tr_auditoria_livro_in
after insert on livro
for each row
begin
	insert into auditoria_livro(titulo, ano_publicacao, id_autor, quantidade_total, quantidade_disponivel, action_date, action_type, action_user)
    values (new.titulo, new.ano_publicacao, new.id_autor, new.quantidade_total, new.quantidade_disponivel, now(), 'INSERT', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_livro_up
after update on livro
for each row
begin
	insert into auditoria_livro(titulo, ano_publicacao, id_autor, quantidade_total, quantidade_disponivel, action_date, action_type, action_user)
    values (new.titulo, new.ano_publicacao, new.id_autor, new.quantidade_total, new.quantidade_disponivel, now(), 'UPDATE', new.creation_user);
end $$
delimiter ;


delimiter $$
create trigger tr_auditoria_livro_del
after delete on livro
for each row
begin
	insert into auditoria_livro(titulo, ano_publicacao, id_autor, quantidade_total, quantidade_disponivel, action_date, action_type, action_user)
    values (old.titulo, old.ano_publicacao, old.id_autor, old.quantidade_total, old.quantidade_disponivel, now(), 'DELETE', old.creation_user);
end $$
delimiter ;
-- --------------------------------------------------------------------------

-- usuario ---------------------
delimiter $$
create trigger tr_auditoria_usuario_in
after insert on usuario
for each row
begin
	insert into auditoria_usuario(nome, email, telefone, action_date, action_type, action_user)
    values (new.nome, new.email, new.telefone, now(), 'INSERT', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_usuario_up
after update on usuario
for each row
begin
	insert into auditoria_usuario(nome, email, telefone, action_date, action_type, action_user)
    values (new.nome, new.email, new.telefone, now(), 'UPDATE', new.creation_user);
end $$
delimiter ;

delimiter $$
create trigger tr_auditoria_usuario_del
after delete on usuario
for each row
begin
	insert into auditoria_usuario(nome, email, telefone, action_date, action_type, action_user)
    values (old.nome, old.email, old.telefone, now(), 'DELETE', old.creation_user);
end $$
delimiter ;

-- -----------------------------------------------------------------

-- 2.d tabela para contro de usuarios 
CREATE TABLE `acesso` (
  `id_acesso` int NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(150) NOT NULL,
  `email` varchar(60) unique not null,
  `senha` varchar(60) not null,
  `creation_date` datetime DEFAULT NULL,
  `creation_user` varchar(60) DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by_user` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_acesso`)
);

CREATE TABLE `auditoria_acesso` (
   `id_auditoria`  int primary key auto_increment,
  `id_acesso` int NOT NULL,
  `nome_usuario` varchar(150) NOT NULL,
  `email` varchar(60) not null,
  `senha` varchar(60) not null,
  `action_date` datetime DEFAULT NULL,
  `action_type` varchar(30) DEFAULT NULL,
  `action_user` varchar(60) DEFAULT NULL
);


create trigger tr_creation_acesso 
before insert on acesso
for each row

set 
    new.creation_date = now(),
    new.creation_user = user();
    
create trigger tr_update_acesso 
before update on acesso
for each row
set 
    new.last_update_date = now(),
    new.last_updated_by_user = user();
    
    
DELIMITER $$
CREATE TRIGGER tr_auditoria_acesso_in
AFTER INSERT ON acesso
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_acesso(id_acesso, nome_usuario, email, senha, action_date, action_type, action_user)
    VALUES (new.id_acesso, new.nome_usuario, new.email, new.senha, now(), 'INSERT', new.creation_user);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tr_auditoria_acesso_up
AFTER UPDATE ON acesso
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_acesso(id_acesso, nome_usuario, email, senha, action_date, action_type, action_user)
    VALUES (new.id_acesso, new.nome_usuario, new.email, new.senha, now(), 'UPDATE', new.last_updated_by_user);
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tr_auditoria_acesso_del
AFTER DELETE ON acesso
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_acesso(id_acesso, nome_usuario, email, senha, action_date, action_type, action_user)
    VALUES (old.id_acesso, old.nome_usuario, old.email, old.senha, now(), 'DELETE', USER());
END $$
DELIMITER ;


-- 2.e triggers adicionais (realizar/devolver emprestimos)

delimiter $$
create  trigger  devolver_estoque_livro 
after update on item_emprestimo 
for each row
begin
	if old.data_devolucao is null and new.data_devolucao is not null then
		update livro 
        set quantidade_disponivel = quantidade_disponivel + 1
        where id_livro = new.id_livro;
	end if;
end $$

delimiter ;

delimiter $$
create trigger realizar_emprestimo_item
before insert on item_emprestimo

for each row
begin
	declare quant_estoque int;

	select quantidade_disponivel into quant_estoque
	from livro 
	where id_livro = new.id_livro;


	if quant_estoque = 0 then
		signal sqlstate '45000'
        set message_text = 'Erro: livro não disponível no estoque no momento';
    else
		update livro 
		set quantidade_disponivel = quantidade_disponivel - 1
        where id_livro = new.id_livro;
	end if;
end $$
delimiter ;

/*Correção para garantir o funcionamento da trigger: uma trigger que é disparada por um 
INSERT, UPDATE ou DELETE  não pode fazer um SELECT na mesma tabela que disparou a trigger*/

ALTER TABLE usuario 
ADD COLUMN qtd_emprestimos_ativos INT DEFAULT 0;

delimiter $$
create trigger limite_emprestimo_usuario
before insert on emprestimo
for each row
begin
	declare quant_emprestimos int;
    
	SELECT qtd_emprestimos_ativos INTO quant_emprestimos 
    FROM usuario 
    WHERE id_usuario = NEW.id_usuario;
    
    if quant_emprestimos >= 2 then
		signal sqlstate '45000'
        set message_text = 'Erro: Usuario já possui 2 empréstimos ainda não devolvidos';
	end if;
end $$
delimiter ;

-- triger auxiliar para resolver o problema do select 
DELIMITER $$
CREATE TRIGGER tr_atualiza_contador_emprestimo
AFTER INSERT ON emprestimo
FOR EACH ROW
BEGIN
    UPDATE usuario 
    SET qtd_emprestimos_ativos = qtd_emprestimos_ativos + 1
    WHERE id_usuario = NEW.id_usuario;
END $$

DELIMITER ;


-- 2.f multas 

create table multa(
id_multa int primary key auto_increment,
id_usuario int not null,
id_emprestimo int not null,
valor_multa decimal (10,2) not null,
data_geracao datetime not null,
status enum('Pendente', 'Paga', 'Cancelada') not null,

foreign key (id_emprestimo) references emprestimo(id_emprestimo)  
);

delimiter $$
create trigger tr_gerar_multa_atraso
after update on item_emprestimo 
for each row
begin
    declare v_data_prevista datetime;
    declare v_id_usuario int;
    declare v_multa_existe int default 0;

    -- 1. verifica se a ação é uma devolução 
    if old.data_devolucao is null and new.data_devolucao is not null then
        
        -- 2. busca a data prevista e o id do usuário na tabela principal
        select data_prevista, id_usuario 
        into v_data_prevista, v_id_usuario
        from emprestimo
        where id_emprestimo = new.id_emprestimo;
        
        -- 3. verifica se devolveu com atraso (data devolução > data prevista)
        if date(new.data_devolucao) > date(v_data_prevista) then
            
            -- 4. verifica se já existe uma multa para este empréstimo (evita multa duplicada se ele devolver vários livros atrasados)
            select count(*) into v_multa_existe 
            from multa 
            where id_emprestimo = new.id_emprestimo;
            
            -- 5. se não tem multa, aplica a foice 
            if v_multa_existe = 0 then
                insert into multa (id_usuario, id_emprestimo, valor_multa, data_geracao, status)
                values (v_id_usuario, new.id_emprestimo, 10.00, now(), 'pendente');
            end if;
            
        end if;
        
    end if;end $$

delimiter ;


USE biblioteca;

-- ==============================================================================
-- 1. TESTES DE INSERT
-- ==============================================================================

USE biblioteca;

-- 1. Inserindo novo usuário e capturando o ID
INSERT INTO usuario (nome, email, telefone) 
VALUES ('Carlos Mendes', 'carlos@email.com', '11977777777');
SET @novo_usuario_id = LAST_INSERT_ID();

-- 2. Inserindo novo autor e capturando o ID
INSERT INTO autor (nome_autor) 
VALUES ('Jorge Amado');
SET @novo_autor_id = LAST_INSERT_ID();

-- 3. Inserindo novo livro usando o ID dinâmico do autor
INSERT INTO livro (titulo, ano_publicacao, id_autor, quantidade_total, quantidade_disponivel) 
VALUES ('Capitães da Areia', 1937, @novo_autor_id, 4, 4);
SET @novo_livro_id = LAST_INSERT_ID();

-- 4. Inserindo novo empréstimo usando o ID dinâmico do usuário
INSERT INTO emprestimo (id_usuario, data_emprestimo, data_prevista) 
VALUES (@novo_usuario_id, '2026-05-09', '2026-05-15');
SET @novo_emprestimo_id = LAST_INSERT_ID();

-- 5. Inserindo novo item no empréstimo usando os IDs dinâmicos gerados
INSERT INTO item_emprestimo (id_emprestimo, id_livro, data_devolucao) 
VALUES (@novo_emprestimo_id, @novo_livro_id, NULL);


-- ==============================================================================
-- 2. TESTES DE UPDATE (Deve acionar as triggers de last_update_date e a MULTA)
-- ==============================================================================

-- Atualizando usuário (Testa a trigger de UPDATE no usuário)
UPDATE usuario 
SET telefone = '11900000000' 
WHERE id_usuario = 1;

-- Atualizando autor (Testa a trigger de UPDATE no autor)
UPDATE autor 
SET nome_autor = 'Clarice Lispector (Revisado)' 
WHERE id_autor = 2;

-- Atualizando livro (Testa a trigger de UPDATE no livro)
UPDATE livro 
SET quantidade_total = 6, quantidade_disponivel = 6 
WHERE id_livro = 1;

-- Atualizando empréstimo (Testa a trigger de UPDATE no empréstimo)
UPDATE emprestimo 
SET data_prevista = '2026-05-12' 
WHERE id_emprestimo = 1;

-- TESTE DA TRIGGER DE MULTA (tr_gerar_multa_atraso)
-- Cenário A: Devolução DENTRO do prazo (Empréstimo 2: prev 15/05, devolvido 10/05)
UPDATE item_emprestimo 
SET data_devolucao = '2026-05-10' 
WHERE id_item = 2; 
-- Resultado: A trigger de multa NÃO deve inserir nada na tabela multa.

-- Cenário B: Devolução FORA do prazo (Empréstimo 1: prev 10/05, devolvido 20/05)
-- (Estes dados vieram dos seus INSERTS iniciais)
UPDATE item_emprestimo 
SET data_devolucao = '2026-05-20' 
WHERE id_item = 1; 
-- Resultado: A trigger de multa DEVE identificar o atraso e gerar o registro na tabela multa com R$ 10.00!


-- ==============================================================================
-- 3. TESTES DE DELETE (Testa triggers de exclusão)
-- ==============================================================================

-- Primeiro deletamos as dependências (filhos)
DELETE FROM item_emprestimo WHERE id_item = 2;

DELETE FROM emprestimo WHERE id_emprestimo = 2;

DELETE FROM livro WHERE id_livro = 3;

DELETE FROM autor WHERE id_autor = 3;

DELETE FROM usuario WHERE id_usuario = 2;
	