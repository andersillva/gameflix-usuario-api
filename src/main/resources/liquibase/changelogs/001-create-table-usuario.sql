--liquibase formatted sql
				
--changeset andersillva:1
CREATE TABLE if not exists usuario (
	id_usuario			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nm_usuario			VARCHAR(60) NOT NULL,
	nr_cpf				VARCHAR(11) NOT NULL,
	dt_nascimento		DATE NOT NULL,
	ds_email			VARCHAR(30) NOT NULL,
	ds_senha			VARCHAR(100) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_uk_email UNIQUE ( ds_email );
