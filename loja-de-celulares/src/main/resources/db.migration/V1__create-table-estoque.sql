CREATE TABLE estoque (
    codigo_produto TEXT PRIMARY KEY UNIQUE NOT NULL,
    nome_produto TEXT NOT NULL,
    preco_em_centavos INT NOT NULL,
    ativo BOOLEAN
);