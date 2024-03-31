CREATE TABLE file
(
    hash              INT          NOT NULL COMMENT 'Hash code do conteúdo do arquivo',
    name              VARCHAR(20)  NOT NULL COMMENT 'Nome do arquivo',
    dat_added         DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'Data em que o registro foi inserido',
    dat_last_modified DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'Data de última atualização do registro',
    CONSTRAINT FILE_PK PRIMARY KEY (hash)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='Tabela usada para persistir o nome dos arquivos já processados';