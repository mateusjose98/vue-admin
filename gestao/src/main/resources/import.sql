INSERT INTO series(id, nome) VALUES(1, '1 ANO EM'), (2, '2 ANO EM'), (3,'3 ANO EM');
INSERT INTO salas(id, nome, capacidade) VALUES(1, '103', 45), (2, '202', 37), (3, '301', 50);
INSERT INTO disciplinas(id, nome) values (100, 'MATEMATICA'),(101,'GRAMÁTICA'),(102, 'ESPANHOL'), (103, 'HISTÓRIA DO MARANHÃO'),(104,'ED. FÍSICA'),(105, 'LIBRAS');

INSERT INTO ACESSO(id, descricao) VALUES(100,'ROLE_ADMIN'),(101,'ROLE_ALUNO');
INSERT INTO TURMAS(id, ano, descricao, vagas_disponiveis, sala_id, serie_id, valor_base, ativo) VALUES (100, 2023, 'Turma Primeiro Ano A', 40, 1, 1, 340.60, true);
