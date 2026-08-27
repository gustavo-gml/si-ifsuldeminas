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


-- Adicionando os campos de controle de conclusão na matrícula
ALTER TABLE cursos_alunos 
ADD COLUMN status_matricula VARCHAR(30) DEFAULT 'Em andamento',
ADD COLUMN data_conclusao DATE DEFAULT NULL;

-- ==============================================================================================================
-- inserções 
-- ==============================================================================================================

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
insert into alunos (nome_aluno, cod_matricula, cpf, data_nascimento) values 
('lucas almeida', 'mat-202601', '222.333.444-55', '2000-05-15'), -- id_aluno: 1
('mariana costa', 'mat-202602', '333.444.555-66', '1998-09-20'), -- id_aluno: 2
('pedro santos',  'mat-202603', '444.555.666-77', '2001-02-10'), -- id_aluno: 3
('julia ribeiro', 'mat-202604', '666.777.888-99', '1999-11-30'), -- id_aluno: 4
('bruno oliveira','mat-202605', '777.888.999-00', '2002-07-05'); -- id_aluno: 5



-- 7. vínculo de alunos aos cursos
insert into cursos_alunos (id_curso, id_aluno) values 
(1, 1), -- lucas no curso 1
(1, 2), -- mariana no curso 1
(1, 3), -- pedro no curso 1
(1, 4), -- julia no curso 1
(1, 5); -- bruno no curso 1


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


-- ==================================================================================
-- Views
-- ===================================================================================

-- Criar uma view que mostre todos os alunos matriculados em cada curso com seus respectivos tópicos.
create view vw_aluno_cursos  as 
	select a.nome_aluno, c.nome_curso, s.numero_semana, t.descricao "nome_do_topico" from alunos a
    join cursos_alunos ca on a.id_aluno = ca.id_aluno
    join cursos c on ca.id_curso = c.id_curso
    join steps s on c.id_curso = s.id_curso
    join topico_step tp on tp.id_step = s.id_step
    join topicos t on tp.id_topico = t.id_topico;
    
/*O que faz: Lista os alunos que estão cadastrados no banco de dados e até matriculados em um curso, 
mas que não estão vinculados a nenhum Step no momento (ou seja, não estão cursando nenhuma semana ativamente).
Tabelas envolvidas: alunos e steps_alunos (usando uma lógica de junção para encontrar quem não tem correspondência).*/

create view vw_aluno_sem_step as 
	select a.nome_aluno, a.cod_matricula, a.data_nascimento, c.nome_curso from alunos a
    join cursos_alunos ca on a.id_aluno = ca.id_aluno
    join cursos c on ca.id_curso = c.id_curso
    where a.id_aluno not in (select id_aluno from steps_alunos);

-- matriculando alunosem steps
INSERT INTO alunos (nome_aluno, cod_matricula, cpf, data_nascimento) 
VALUES ('Felipe Rocha', 'MAT-202606', '888.999.000-11', '2003-08-12');

INSERT INTO cursos_alunos (id_curso, id_aluno) 
VALUES (1, 6);



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

-- VIEW4 O que faz: Mostra apenas os alunos que compraram tópicos separados, indicando o nome do aluno, a matrícula e qual foi o tópico avulso adquirido
create view vw_topicos_avulsos as 
select 
    a.id_aluno,
    a.nome_aluno,
    t.id_topico,
    t.descricao as descricao_topico
from alunos a
inner join topicos_avulsos_alunos taa on a.id_aluno = taa.id_aluno
inner join topicos t on taa.id_topico = t.id_topico;


-- 3 log

create table if not exists tabela_log (
    id_log int primary key auto_increment,
    nome_tabela varchar(50) not null,   
    acao varchar(20) not null,        
    id_registro int not null,             
    data_hora datetime default current_timestamp,
    descricao varchar(255)            
);




– trigger cadastro alunos
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

– cadastro de cursos

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

– cadastro de matriculas 
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


