--liquibase formatted sql
				
--changeset andersillva:2
CREATE TABLE if not exists usuario_jogo (
	id_usuario_jogo			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_usuario				INT NOT NULL,
	id_jogo					INT NOT NULL,
	tp_inclusao				VARCHAR(1) NOT NULL,
	dt_inclusao				DATE NOT NULL
);

ALTER TABLE usuario_jogo ADD CONSTRAINT usuario_jogo_uk UNIQUE ( id_usuario, id_jogo );

ALTER TABLE usuario_jogo
    ADD CONSTRAINT usuario_jogo_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );
