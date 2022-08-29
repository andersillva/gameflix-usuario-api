--liquibase formatted sql
				
--changeset andersillva:1
ALTER TABLE usuario_jogo ADD nm_jogo VARCHAR(60) NOT NULL;