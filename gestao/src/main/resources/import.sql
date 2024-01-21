INSERT INTO series(id, nome) VALUES(1, '1 ANO EM'), (2, '2 ANO EM'), (3,'3 ANO EM');
INSERT INTO salas(id, nome, capacidade) VALUES(1, '103', 45), (2, '202', 37), (3, '301', 50);
INSERT INTO disciplinas(id, nome) values(100, 'MATEMATICA'),(101,'GRAMÁTICA'),(102, 'ESPANHOL');

INSERT INTO ACESSO(id, descricao) VALUES(100,'ROLE_ADMIN'),(101,'ROLE_ALUNO');
INSERT INTO TURMAS(id, ano, descricao, vagas_disponiveis, sala_id, serie_id, valor_base) VALUES (100, 2023, 'Turma Primeiro Ano A', 40, 1, 1, 340.60);

INSERT INTO public.alunos (data_nascimento, id, uf, usuario_id, date_created, last_updated, cpf, email, foto, matricula, nome, telefone) VALUES('2000-02-01', 100, 'MA', null, now(), now(), '60070080012', 'josemateus.ufma@gmail.com', null,'23120932', 'Fulano', '98988883333');

INSERT INTO public.matriculas(aluno_id, ativo, carne_id, id, turma_id, data_matricula) VALUES(100, true, null, 100, 100, '2000-02-01');