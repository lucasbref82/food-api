insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Beneplacito', 3.50, 1)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Porteira Velha', 7.20, 2)

insert into forma_pagamento (descricao) values ('Dinheiro')
insert into forma_pagamento (descricao) values ('Cartao')
insert into forma_pagamento (descricao) values ('Pix')

insert into permissao (nome, descricao) values ('Inserir', 'Inserir registros')
insert into permissao (nome, descricao) values ('Deletar', 'Deletar registros')
insert into permissao (nome, descricao) values ('Listar', 'Listar registros')
insert into permissao (nome, descricao) values ('Atualizar', 'Atualizar registros')

insert into estado (nome) values ('Minas Gerais')
insert into estado (nome) values ('Rio de Janeiro')

insert into cidade (nome, estado_id) values ('Belo Horizonte', 1)
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 2)