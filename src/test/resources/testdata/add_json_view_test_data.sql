INSERT INTO users (user_id, user_name, user_surname, user_email)
VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Иван', 'Иванов', 'ivan.ivanov@gmail.com');

INSERT INTO orders (order_id, order_amount, order_status, user_id)
VALUES
('660e8400-e29b-41d4-a716-446655440001', 1500.00, 'PENDING', '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO order_products (order_id, product_id, product_name, product_cost)
VALUES
('660e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440001', 'Keyboard', 1000.00);

INSERT INTO order_products (order_id, product_id, product_name, product_cost)
VALUES
('660e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440002', 'Mouse', 500.00);