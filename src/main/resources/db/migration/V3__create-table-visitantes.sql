CREATE TABLE visitantes(
id BIGINT GENERATED ALWAYS AS IDENTITY,
nome_completo_visitante VARCHAR(150) NOT NULL,
nome_completo_morador VARCHAR(150) NOT NULL,
cpf VARCHAR(11) NOT NULL UNIQUE,
data_visita TIMESTAMP NOT NULL,
morador_id BIGINT NOT NULL,

CONSTRAINT fk_visitantes_morador_id FOREIGN KEY (morador_id) REFERENCES moradores(id)
);