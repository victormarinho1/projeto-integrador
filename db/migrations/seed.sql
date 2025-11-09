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
                          rua VARCHAR(100),
                          bairro VARCHAR(100),
                          estado CHAR(2),
                          cep VARCHAR(9),
                          latitude DECIMAL(10, 8),
                          longitude DECIMAL(11, 8),
                          status VARCHAR(100) NOT NULL DEFAULT 'NOVA',
                          prioridade VARCHAR(100),
                          data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          data_atualizacao TIMESTAMP,
                          equipe_enviada BOOLEAN NOT NULL DEFAULT FALSE,
                          id_usuario_denunciante BIGINT,
                          id_usuario_responsavel BIGINT,
                          FOREIGN KEY (id_usuario_denunciante) REFERENCES usuario(id) ON DELETE SET NULL,
                          FOREIGN KEY (id_usuario_responsavel) REFERENCES usuario(id) ON DELETE SET NULL
);


CREATE TABLE denuncia_imagem (
                                 id BIGSERIAL PRIMARY KEY,
                                 id_denuncia BIGINT NOT NULL,
                                 url TEXT NOT NULL,
                                 descricao TEXT,
                                 data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (id_denuncia) REFERENCES denuncia(id) ON DELETE CASCADE
);


-- Inserção de usuários
INSERT INTO usuario (nome, sobrenome, email, senha, funcao, ativo, data_criacao)
VALUES
    ('Admin', '', 'admin@example.com', '$2a$10$cw1zoZM0DUaPVbYVPUqiEu9UEqmmXb4Ybga4Ca2ot1XnSileJF37S', 'ADMIN', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro', '', 'conselheiro@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro2', '', 'conselheiro2@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro3', '', 'conselheiro3@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro4', '', 'conselheiro4@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro5', '', 'conselheiro5@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro6', '', 'conselheiro6@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro7', '', 'conselheiro7@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro8', '', 'conselheiro8@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro9', '', 'conselheiro9@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Conselheiro10', '', 'conselheir10o@example.com', '$2a$10$MzxK.ZmoyHBUHluXWbsIZOKBC/yK20tZPQldtJSaUO6VT4bQAeiMi', 'CONSELHEIRO', TRUE, CURRENT_TIMESTAMP),
    ('Denunciante', '', 'denunciante@example.com', '$2a$10$OAkqGqS5Mc5plc9xAEoDAeFeMVV1T5ZImNhxy09KlfENO7HhriIUq', 'DENUNCIANTE', TRUE, CURRENT_TIMESTAMP);


-- Inserção de denúncias
INSERT INTO denuncia (
    protocolo, descricao, endereco_completo, cidade, estado, cep, latitude, longitude,
    status, prioridade, data_criacao, id_usuario_denunciante
) VALUES
      ('DEN-20251109131750-D63450B8', 'Rua esburacada, perigosa para pedestres.', 'Rua das Laranjeiras, 123', 'Rio de Janeiro', 'RJ', '22231-000', -22.906847, -43.172896, 'EM_ANDAMENTO', 'MEDIA', CURRENT_TIMESTAMP, 1),
      ('DEN-20251109132357-2EDAF644', 'Poste com risco de queda próximo à escola.', 'Av. Brasil, 456', 'São Paulo', 'SP', '03010-000', -23.550520, -46.633308, 'EM_ANDAMENTO', 'ALTA', CURRENT_TIMESTAMP, 2),
      ('DEN-20251109132830-F42F2879', 'Bueiro entupido causando alagamento.', 'Rua Água Verde, 789', 'Curitiba', 'PR', '80020-010', -25.428954, -49.267137, 'CONCLUIDA', 'BAIXA', CURRENT_TIMESTAMP, 3);


-- Inserção de imagens para denúncia 1
INSERT INTO denuncia_imagem (id_denuncia, url, descricao, data_criacao)
VALUES
    (1, 'trabalho-infantil-seed1.jpeg', 'Buraco no meio da rua', CURRENT_TIMESTAMP);

-- Inserção de imagens para denúncia 2
INSERT INTO denuncia_imagem (id_denuncia, url, descricao, data_criacao)
VALUES
    (2, 'trabalho-infantil-seed2.jpg', 'Poste inclinado perigosamente', CURRENT_TIMESTAMP);

-- Inserção de imagens para denúncia 3
INSERT INTO denuncia_imagem (id_denuncia, url, descricao, data_criacao)
VALUES
    (3, 'trabalho-infantil-seed3.png', 'Rua alagada', CURRENT_TIMESTAMP);
