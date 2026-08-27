

-- ====================================================================
-- sql
-- ====================================================================

-- criação do banco de dados para cursos de ti
create database if not exists cursos_ti;
use cursos_ti;

-- 1. tabela de cursos
create table if not exists cursos (
  id_curso int primary key auto_increment,
  nome_curso varchar(200) not null,
  quantidade_semanas int not null
);

-- 2. tabela de instrutores
create table if not exists instrutores(
  id_instrutor int primary key auto_increment,
  codigo_instrutor varchar(45) unique not null, 
  nome_instrutor varchar(200) not null,
  cpf varchar(14) unique, 
  titulacao varchar(200) not null
);

-- 3. tabela de tópicos (relação 1:n - um instrutor pode lecionar vários tópicos)
create table if not exists topicos(
  id_topico int primary key auto_increment,
  descricao varchar(200) not null,
  id_instrutor int,
  
  foreign key (id_instrutor) references instrutores(id_instrutor)
);

-- 4. tabela de steps
create table if not exists steps(
  id_step int primary key auto_increment,
  numero_semana int not null, -- para identificar se é a semana 1, 2, 3...
  id_curso int not null,
  
  foreign key (id_curso) references cursos(id_curso)
);

-- 5. tabela associativa: tópicos e steps (muitos para muitos)
create table if not exists topico_step( 
  id_topico_step int primary key auto_increment,
  id_step int not null,
  id_topico int not null,
  
  foreign key (id_step) references steps(id_step),
  foreign key (id_topico) references topicos(id_topico) 
);

-- 6. tabela de alunos
create table if not exists alunos(
  id_aluno int primary key auto_increment,
  nome_aluno varchar(200) not null,
  cod_matricula varchar(45) unique,
  cpf varchar(14) unique,           
  data_nascimento date not null    
);

-- 7. tabela associativa: cursos e alunos
create table if not exists cursos_alunos(
  id_curso_aluno int primary key auto_increment,
  id_curso int not null,
  id_aluno int not null,
  
  foreign key (id_curso) references cursos(id_curso),
  foreign key (id_aluno) references alunos(id_aluno)
);

-- 8. tabela associativa: steps e alunos
create table if not exists steps_alunos(
  id_step_aluno int primary key auto_increment,
  id_step int not null,
  id_aluno int not null,
  
  foreign key (id_step) references steps(id_step),
  foreign key (id_aluno) references alunos(id_aluno)
);

-- 9. tabela associativa: tópicos avulsos e alunos
create table if not exists topicos_avulsos_alunos(
  id_topicos_avulsos_alunos int primary key auto_increment,
  id_topico int not null,
  id_aluno int not null,
  
  foreign key (id_topico) references topicos(id_topico),
  foreign key (id_aluno) references alunos(id_aluno)
);

USE cursos_ti;


-- adicionando os campos de controle de conclusão na matrícula
alter table cursos_alunos 
add column status_matricula varchar(30) default 'em andamento',
add column data_conclusao date default null;


-- email
ALTER TABLE alunos ADD COLUMN email VARCHAR(255);

-- ====================================================================
-- inserções 
-- ====================================================================

-- 1. cadastro do curso 
insert into cursos (nome_curso, quantidade_semanas) 
values ('formação full stack developer', 5);

-- 2. cadastro de instrutores
insert into instrutores (codigo_instrutor, nome_instrutor, cpf, titulacao) values 
('inst-001', 'ana silva', '111.222.333-44', 'mestre em ciência da computação'),
('inst-002', 'carlos souza', '555.666.777-88', 'especialista em engenharia de software');


-- 3. cadastro de tópicos
insert into topicos (descricao, id_instrutor) values 
('introdução ao html5 e css3', 1),          -- id_topico: 1 (lecionado por ana)
('lógica de programação com javascript', 1),-- id_topico: 2 (lecionado por ana)
('banco de dados relacional (mysql)', 2),   -- id_topico: 3 (lecionado por carlos)
('desenvolvimento backend com node.js', 2), -- id_topico: 4 (lecionado por carlos)
('arquitetura de apis e deploy', 2);        -- id_topico: 5 (lecionado por carlos)



-- 4. cadastro dos steps (as 5 semanas pertencentes ao curso 1)
insert into steps (numero_semana, id_curso) values 
(1, 1), -- id_step: 1 (semana 1 do curso full stack)
(2, 1), -- id_step: 2 (semana 2 do curso full stack)
(3, 1), -- id_step: 3 (semana 3 do curso full stack)
(4, 1), -- id_step: 4 (semana 4 do curso full stack)
(5, 1); -- id_step: 5 (semana 5 do curso full stack)


-- 5. vínculo de tópicos aos steps (matriz curricular do curso)
insert into topico_step (id_step, id_topico) values 
(1, 1), -- semana 1 tem o tópico de html/css
(2, 2), -- semana 2 tem o tópico de javascript
(3, 3), -- semana 3 tem o tópico de mysql
(4, 4), -- semana 4 tem o tópico de node.js
(5, 5); -- semana 5 tem o tópico de apis/deploy


-- 6. cadastro de alunos (exigência: pelo menos 5 alunos)
INSERT INTO alunos (nome_aluno, cod_matricula, cpf, data_nascimento, email) VALUES 
('Lucas Almeida', 'MAT-202601', '222.333.444-55', '2000-05-15', 'lucas.almeida@email.com'), -- id_aluno: 1
('Mariana Costa', 'MAT-202602', '333.444.555-66', '1998-09-20', 'mariana.costa@email.com'), -- id_aluno: 2
('Pedro Santos',  'MAT-202603', '444.555.666-77', '2001-02-10', 'pedro.santos@email.com'),  -- id_aluno: 3
('Julia Ribeiro', 'MAT-202604', '666.777.888-99', '1999-11-30', 'julia.ribeiro@email.com'), -- id_aluno: 4
('Bruno Oliveira','MAT-202605', '777.888.999-00', '2002-07-05', 'bruno.oliveira@email.com');-- id_aluno: 5



-- 7. vínculo de alunos aos cursos
insert into cursos_alunos (id_curso, id_aluno, status_matricula, data_conclusao) values 
(1, 1, 'concluído', '2026-05-10'), 
(1, 2, 'em andamento', null),      
(1, 3, 'em andamento', null),     
(1, 4, 'trancado', null),            
(1, 5, 'em andamento', null);        



-- 8. vínculo de alunos aos steps 
insert into steps_alunos (id_step, id_aluno) values 
(1, 1), -- lucas está cursando a semana 1
(1, 2), -- mariana está cursando a semana 1
(2, 3), -- pedro já avançou e está cursando a semana 2
(3, 4), -- julia está na semana 3
(3, 5); -- bruno está na semana 3


-- 9. matrícula em tópicos avulsos
insert into topicos_avulsos_alunos (id_topico, id_aluno) values 
(3, 1), -- lucas (que está no step 1) comprou o tópico de mysql (tópico 3) por fora.
(1, 4); -- julia (que está no step 3) quis rever a parte de html/css (tópico 1) de forma avulsa.














-- ====================================================================
-- views
-- ====================================================================

-- Criar uma view que mostre todos os alunos matriculados em cada curso com seus respectivos tópicos.

-- VIEW 0
create view vw_aluno_cursos  as 
	select a.nome_aluno, c.nome_curso, s.numero_semana, t.descricao "nome_do_topico" from alunos a
    join cursos_alunos ca on a.id_aluno = ca.id_aluno
    join cursos c on ca.id_curso = c.id_curso
    join steps s on c.id_curso = s.id_curso
    join topico_step tp on tp.id_step = s.id_step
    join topicos t on tp.id_topico = t.id_topico;

-- VIEW1  Lista os alunos que estão cadastrados no banco de dados e até matriculados em um curso, mas que não estão vinculados a nenhum Step no momento

create view vw_aluno_sem_step as 
	select a.nome_aluno, a.cod_matricula, a.data_nascimento, c.nome_curso from alunos a
    join cursos_alunos ca on a.id_aluno = ca.id_aluno
    join cursos c on ca.id_curso = c.id_curso
    where a.id_aluno not in (select id_aluno from steps_alunos);

-- matriculando aluno sem steps
insert into alunos (nome_aluno, cod_matricula, cpf, data_nascimento) 
values ('felipe rocha', 'mat-202606', '888.999.000-11', '2003-08-12');

insert into cursos_alunos (id_curso, id_aluno) 
values (1, 6);


-- VIEW2 O que faz: Mostra a ementa completa do curso, listando o nome do curso, o número da semana (step) e a descrição dos tópicos ensinados naquela semana.

create view vw_grade_curricular as 
select
	c.nome_curso as 'Curso',
    s.numero_semana as 'Semana',
    t.descricao as 'Conteúdo/Tópico'
    from cursos c
    inner join steps s on c.id_curso = s.id_curso
    inner join topico_step ts on s.id_step = ts.id_step
    inner join topicos t on ts.id_topico = t.id_topico
    order by c.nome_curso, s.numero_semana;

select * from vw_grade_curricular;

-- VIEW3 O que faz: Lista o nome do instrutor, sua titulação e todos os tópicos que ele é responsável por lecionar.

create view vw_alocacao_instrutores as
select
	i.nome_instrutor as 'Instrutor',
    i.titulacao as 'Titulação',
    t.descricao as 'Tópico Ministrado'
from instrutores i
left join topicos t on i.id_instrutor = t.id_instrutor
order by i.nome_instrutor;

select * from vw_alocacao_instrutores;

-- VIEW4 O que faz: Mostra apenas os alunos que compraram tópicos separados, indicando o nome do aluno, a matrícula e qual foi o tópico avulso adquirido.
create view vw_topicos_avulsos as 
select 
    a.id_aluno,
    a.nome_aluno,
    t.id_topico,
    t.descricao as descricao_topico
from alunos a
inner join topicos_avulsos_alunos taa on a.id_aluno = taa.id_aluno
inner join topicos t on taa.id_topico = t.id_topico;






-- ====================================================================
-- triggers 
-- ====================================================================

-- fazer as triggers 1 e 2 

-- 1 Verificar CPF e impedir matrícula em mais de 2 cursos completos ao mesmo tempo 

delimiter // 
create trigger trg_verifica_matricula
before insert on cursos_alunos
for each row
begin
	declare qtd_cursos int;
	declare aluno_cpf varchar(14);
 
	-- pega o cpf do aluno
	select cpf into aluno_cpf
	from alunos
	where id_aluno = new.id_aluno;
 
	-- conta quantos cursos ativos esse cpf já possui
	select count(*)
	into qtd_cursos
	from cursos_alunos ca
	inner join alunos a on ca.id_aluno = a.id_aluno
	where a.cpf = aluno_cpf
  	and ca.status_matricula <> 'concluído';
 
	-- se já tiver 2 cursos ativos, bloqueia
	if qtd_cursos >= 2 then
    	signal sqlstate '45000'
    	set message_text = 'aluno não pode estar matriculado em mais de 2 cursos simultaneamente';
	end if;
end //
 
delimiter ;


-- 2 Atualizar data de modificação do aluno 
alter table alunos
add column data_modificacao datetime default current_timestamp;
 
delimiter //
 
create trigger trg_atualiza_data_aluno
before update on alunos
for each row
begin
	set new.data_modificacao = current_timestamp;
end //
 
delimiter ;





-- 3 Crie as triggers necessárias que registrem em uma tabela de log,
create table if not exists tabela_log (
    id_log int primary key auto_increment,
    nome_tabela varchar(50) not null,   
    acao varchar(20) not null,        
    id_registro int not null,             
    data_hora datetime default current_timestamp,
    descricao varchar(255)            
);




-- trigger cadastro alunos
delimiter //

create trigger trg_log_alunos_update
after update on alunos
for each row
begin
    insert into tabela_log (nome_tabela, acao, id_registro, descricao)
    values (
        'alunos', 
        'update', 
        new.id_aluno, 
        concat('nome alterado de: ', old.nome_aluno, ' para: ', new.nome_aluno)
    );
end //

delimiter ;

-- cadastro de cursos

delimiter //

create trigger trg_log_cursos_update
after update on cursos
for each row
begin
    insert into tabela_log (nome_tabela, acao, id_registro, descricao)
    values (
        'cursos', 
        'update', 
        new.id_curso, 
        concat('curso alterado de: ', old.nome_curso, ' para: ', new.nome_curso)
    );
end //

delimiter ;


--cadastro de tópicos

delimiter //

create trigger trg_log_topicos_update
after update on topicos
for each row
begin
    insert into tabela_log (nome_tabela, acao, id_registro, descricao)
    values (
        'topicos', 
        'update', 
        new.id_topico, 
        concat('tópico alterado. antigo: ', old.descricao, ' | novo: ', new.descricao)
    );
end //

delimiter ;

-- cadastro de matriculas 
delimiter //

create trigger trg_log_matricula_update
after update on cursos_alunos
for each row
begin
    insert into tabela_log (nome_tabela, acao, id_registro, descricao)
    values (
        'cursos_alunos', 
        'update', 
        new.id_curso_aluno, 
        concat('matrícula alterada. id curso antigo: ', old.id_curso, ' | id curso novo: ', new.id_curso)
    );
end //

delimiter ;



-- alterações 
update alunos set nome_aluno = 'lucas almeida silva' where id_aluno = 1;


select * from tabela_log;
UPDATE cursos 
SET nome_curso = 'Formação Full Stack Developer Avançado' 
WHERE id_curso = 1;


UPDATE topicos 
SET descricao = 'Banco de Dados Relacional Avançado (MySQL e PostgreSQL)' 
WHERE id_topico = 3;



INSERT INTO cursos (nome_curso, quantidade_semanas) 
VALUES ('Análise de Dados com Python', 6);

UPDATE cursos_alunos 
SET id_curso = 2 
WHERE id_curso_aluno = 2;

select * from tabela_log;



-- ====================================================================
-- store procedures 
-- ====================================================================

-- 1 Matricular aluno em todos os tópicos de um step 

delimiter //
 
create procedure matricular_aluno_step(
	in p_cod_matricula varchar(45),
	in p_numero_semana int
)
begin
	declare v_id_aluno int;
	declare v_id_step int;
 
	-- localizar aluno
	select id_aluno into v_id_aluno
	from alunos
	where cod_matricula = p_cod_matricula;
 
	-- localizar step
	select id_step into v_id_step
	from steps
	where numero_semana = p_numero_semana;
 
	-- matricular no step
	insert into steps_alunos(id_step, id_aluno)
	values(v_id_step, v_id_aluno);
 
end //
 
delimiter ;



-- 2 atualizar dados aluno
DELIMITER //

CREATE PROCEDURE sp_AtualizarDadosAluno (
    IN p_cod_matricula VARCHAR(45),
    IN p_nome_aluno VARCHAR(200),
    IN p_data_nascimento DATE,
    IN p_email VARCHAR(255),
    IN p_cpf VARCHAR(14) -- Informação extra adicionada!
)
BEGIN
    UPDATE alunos
    SET nome_aluno = p_nome_aluno,
        data_nascimento = p_data_nascimento,
        email = p_email,
        cpf = p_cpf
    WHERE cod_matricula = p_cod_matricula;
END //

DELIMITER ;


-- 3 listar topicos de um curso
DELIMITER //

CREATE PROCEDURE sp_ListarTopicosCurso (
    IN p_id_curso INT
)
BEGIN
    SELECT 
        c.nome_curso,
        s.numero_semana AS step_semana,
        t.descricao AS nome_topico
    FROM cursos c
    JOIN steps s ON c.id_curso = s.id_curso
    JOIN topico_step ts ON s.id_step = ts.id_step
    JOIN topicos t ON ts.id_topico = t.id_topico
    WHERE c.id_curso = p_id_curso
    ORDER BY s.numero_semana ASC; -- Organiza do Step 1 em diante
END //

DELIMITER ;


-- 4 alunos em topicos
DELIMITER //


CREATE PROCEDURE sp_AlunosPorTopico (
    IN p_id_topico INT
)
BEGIN
    -- Busca #1: Alunos matriculados no Step regular que contém este Tópico
    SELECT DISTINCT a.nome_aluno, a.cod_matricula, 'Regular (Via Step)' AS tipo_vinculo
    FROM alunos a
    JOIN steps_alunos sa ON a.id_aluno = sa.id_aluno
    JOIN topico_step ts ON sa.id_step = ts.id_step
    WHERE ts.id_topico = p_id_topico
    
    UNION
    
    -- Busca #2: Alunos matriculados de forma Avulsa direto neste Tópico
    SELECT a.nome_aluno, a.cod_matricula, 'Matrícula Avulsa' AS tipo_vinculo
    FROM alunos a
    JOIN topicos_avulsos_alunos taa ON a.id_aluno = taa.id_aluno
    WHERE taa.id_topico = p_id_topico;
END //

DELIMITER ;


-- testes
-- 0 matruculas/alunos/steps
CALL matricular_aluno_step('MAT-202602', 2);
-- 1. Executa a atualização dos dados do Lucas
CALL sp_AtualizarDadosAluno(
    'MAT-202601',                  -- Matrícula dele
    'Lucas Almeida Silva',         -- Novo Nome completo
    '2000-05-15',                  -- Data de Nascimento
    'lucas.silva@novoemail.com',   -- Novo E-mail
    '222.333.444-99'               -- CPF Atualizado
);

-- 2. Verifica se a alteração foi aplicada na tabela de alunos
SELECT * FROM alunos WHERE cod_matricula = 'MAT-202601';
-- verifica o log
SELECT * FROM tabela_log;


-- Executa a listagem dos tópicos do curso de ID 1
CALL sp_ListarTopicosCurso(1);


-- Executa a busca de alunos vinculados ao tópico de ID 3
CALL sp_AlunosPorTopico(3);

