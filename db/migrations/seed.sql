CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL, 
    funcao VARCHAR(50) NOT NULL DEFAULT 'DENUNCIANTE',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP
);


CREATE TABLE denuncia (
    id BIGSERIAL PRIMARY KEY,
    protocolo TEXT NOT NULL UNIQUE,
    descricao TEXT NOT NULL,
    endereco_completo TEXT,
    cidade VARCHAR(100),
    estado CHAR(2),
    cep VARCHAR(9),
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    status VARCHAR(100) NOT NULL DEFAULT 'NOVA',
    prioridade VARCHAR(100) NOT NULL DEFAULT 'MEDIA',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    id_usuario_responsavel BIGINT,
    FOREIGN KEY (id_usuario_responsavel) REFERENCES usuario(id) ON DELETE SET NULL
);