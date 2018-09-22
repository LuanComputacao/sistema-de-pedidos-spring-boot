INSERT INTO cliente(cpf, nome, sobrenome)
VALUES
-- 1
('00000000000', 'Nome1', 'Sobrenome1'),
-- 2
('00000000001', 'Nome2', 'Sobrenome2');

INSERT INTO produto (descricao)
VALUES
-- 1
('Lápis 6B'),
-- 2
('Lápis 2B'),
-- 3
('Caneta Esferográfica BIC Azul'),
-- 4
('Caneta Esferográfica Pentel Azul'),
-- 5
('Caneta Esferográfica BIC Preta'),
-- 6
('Caneta Esferográfica Pentel Preta');

INSERT INTO pedido (data_pedido, id_cliente)
VALUES
-- 1
(CURRENT_DATE(), 1),
-- 2
(CURRENT_DATE(), 1),
-- 3
(CURRENT_DATE(), 2);

INSERT INTO item_do_pedido (id_pedido, id_produto, qtdade)
VALUES
-- Pedido 1
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
-- Pedido 2
(2, 1, 1),
(2, 2, 1),
(2, 3, 1),
(2, 6, 1);