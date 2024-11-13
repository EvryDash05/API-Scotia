INSERT INTO test_db_rapimoney.tb_client
    (client_id, name, last_name, phone, address, email, password)
    VALUES ('CST833', 'Ever', 'Ccencho Santacruz', '123123123',
            'Comas', 'ever123@gmail.com', '$2a$10$z2RizxJv4PAP5idZWsd8PuKtX5blJNpvutXR9C.8XagFQZ0KAAVke');

INSERT INTO test_db_rapimoney.customer_role (client_id, role_id)
    VALUES ('CST833', 'RLE001');
