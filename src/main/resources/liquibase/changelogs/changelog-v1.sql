--liquibase formatted sql
				
--changeset andersillva:1
CREATE TABLE usuario (
	id_usuario			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nm_usuario			VARCHAR(60) NOT NULL,
	ds_email			VARCHAR(30) NOT NULL,
	ds_senha			VARCHAR(100) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_uk_email UNIQUE ( ds_email );

--rollback drop table usuario;

