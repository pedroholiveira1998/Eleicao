-- ----------------     << PREFEITO >>     --------------------
--
--                    SCRIPT DO TRABALHO FINAL
--
-- Data Criacao ...........: 10/02/2020
-- Autor(es) ..............: Pedro Henrique
-- Banco de Dados .........: MySQL
-- Banco de Dados(nome) ...: javaVerao
--
--
-- PROJETO => 01 Base de Dados
--         => 01 Tabela
-- -------------------------------------------------------------------


CREATE DATABASE IF NOT EXISTS javaVerao;


USE javaVerao;


CREATE TABLE prefeito (
	nome    VARCHAR(100)NOT NULL,
	partidoAssociado   VARCHAR(10) NOT NULL,
	numeroLegenda INT(2)      NOT NULL
);


-- Instrucao que apaga a tabela prefeito
  -- DROP TABLE prefeito;
