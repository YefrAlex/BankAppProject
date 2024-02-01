--liquibase formatted sql
--changeset bank:v.0.1.0-test-data

INSERT INTO die_bank.employees (id, first_name, last_name, role, email, password, phone, country, created_at, updated_at, is_blocked)
VALUES (UUID_TO_BIN('16200224-69e6-482b-a988-e0778ffd831e'), 'John', 'Deer', 'ADMIN', 'johndeer@bank.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+77788999', 'GERMANY', '2023-09-01 13:57:40', '2023-09-01 13:57:40', false),
       (UUID_TO_BIN('1976ecd1-6f3a-478d-a1b3-a260cd829b9b'), 'Alice', 'Coolidge','MANAGER', 'alicecoolidge@bank.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a','+77782999', 'GERMANY', '2023-09-02 13:57:40', '2023-09-02 13:57:40', false),
       (UUID_TO_BIN('990e37d2-10a8-4a3b-a2c4-a5727d2cd465'), 'Sid', 'Johnson','MANAGER', 'sidjohnson@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+777112234', 'GERMANY', '2023-09-03 13:57:40', '2023-09-03 13:57:40',false),
       (UUID_TO_BIN('9a6dadf0-28e0-41c6-9b8c-6e024ee9b227'), 'Emily', 'Blascovich','MANAGER', 'emelyblascovich@bank.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+345112244', 'LUXEMBOURG', '2023-09-04 13:57:40', '2023-09-04 13:57:40',false),
       (UUID_TO_BIN('36a0bc00-a281-47ec-be51-fb6c5eb42d10'), 'David', 'Goliafet','MANAGER', 'davidgoliafet@bank.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+779887655', 'GERMANY', '2023-09-05 13:57:40', '2023-09-05 13:57:40', false),
       (UUID_TO_BIN('2f889448-82ce-4f46-a130-1ebc3c136837'), 'Sarah', 'Condor','MANAGER', 'sarahcondor@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+877655634', 'NETHERLANDS', '2023-09-06 13:57:40', '2023-09-06 13:57:40', false),
       (UUID_TO_BIN('cc4b231b-baf5-47b1-a0d1-380419b95729'), 'James', 'Wilkinson','MANAGER', 'jameswilkinson@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+221121876', 'SWITZERLAND', '2023-09-07 13:57:40', '2023-09-07 13:57:40',false),
       (UUID_TO_BIN('de77a6e0-71ef-41ee-a139-24de7f1f3efe'), 'Olivia', 'Wayet','MANAGER', 'oliviawayet@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+777761232', 'GERMANY', '2023-09-08 13:57:40', '2023-09-08 13:57:40', false),
       (UUID_TO_BIN('08d4a869-e90d-48a4-a98d-f622400fa99b'), 'Klifford', 'Joneson','MANAGER', 'kliffordjohnson@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+7771111211', 'GERMANY', '2023-09-09 13:57:40', '2023-09-09 13:57:40', true),
       (UUID_TO_BIN('3b67ae93-65fb-42ea-be66-e8c2b7d44eb7'), 'Evelin', 'Whiterose','MANAGER', 'evelinewhiterose@bank.com',
        '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', '+777345345', 'GERMANY', '2023-09-11 13:57:40', '2023-09-11 13:57:40', true);

INSERT INTO die_bank.clients (id, first_name, last_name, tax_code, credit_rating, email, password, address, phone, country, created_at, updated_at, is_blocked)
VALUES (UUID_TO_BIN('7e78faba-3e6a-4e6c-9e12-b6054a9cd2b2'), 'Lukas', 'Skyhammer', '123-34234-123', '250', 'lukasskyhammer@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Unter den Linden 54, 10117 Berlin', '+789345345', 'GERMANY', '2023-10-01 13:57:40', '2023-10-01 13:57:40', false),
       (UUID_TO_BIN('949aaf01-4f60-4ad9-97bd-70f01d0af833'), 'Weider', 'FonDart', '123-12123-123', '290', 'weiderfondart@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Bismark platz 12, 80331 München', '+789897789', 'GERMANY', '2023-10-02 13:57:40', '2023-10-02 13:57:40', false),
       (UUID_TO_BIN('3403e154-a2ae-44e5-b9a1-f0c7a482adde'), 'Hans', 'Solod', '123-21312-123', '330', 'hanssolod@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Kurfürstendamm 216, 10719 Berlin', '+789321321', 'GERMANY', '2023-10-03 13:57:40', '2023-10-03 13:57:40', false),
       (UUID_TO_BIN('0f8a2be4-64fb-4f8c-9002-fbf95f57f1a0'), 'Lea', 'Zimerman', '123-22433-123', '370', 'leazimerman@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Heerstraße 12, 14052 Berlin', '+789443321', 'GERMANY', '2023-10-04 13:57:40', '2023-10-04 13:57:40', false),
       (UUID_TO_BIN('f85aa0cf-63e0-4e9c-961b-2eac0974dab2'), 'Obilon', 'Kenobish', '234-22433-123', '410', 'obilonkenobish@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Schlossplatz 1, 70173 Stuttgart', '+789554432', 'GERMANY', '2023-10-05 13:57:40', '2023-10-05 13:57:40', false),
       (UUID_TO_BIN('e95e05f9-b835-4a72-9fab-575d4d32b79b'), 'Gustav', 'Bolenbrok', '345-22433-333', '450', 'gustavbolenbrok@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Friedrichstraße 96, 10117 Berlin', '+789665543', 'GERMANY', '2023-10-06 13:57:40', '2023-10-06 13:57:40', false),
       (UUID_TO_BIN('59156a86-404f-4b49-8f81-24e8c9474724'), 'Otto', 'Stirliz', '345-89433-333', '490', 'ottostirliz@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Niederkirchnerstraße 8, 10117 Berlin', '+789776654', 'GERMANY', '2023-10-07 13:57:40', '2023-10-07 13:57:40', false),
       (UUID_TO_BIN('d5bad0e4-c6bf-4d96-aae7-dcd648b0508a'), 'Urgen', 'Rolf', '312-22567-333', '505', 'urgenrolf@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Waisenstrasse 14, 10117 Berlin', '+789545432', 'GERMANY', '2023-10-08 13:57:40', '2023-10-08 13:57:40', false),
       (UUID_TO_BIN('50f3d138-c2f5-426f-a695-e86d5acc5fc1'), 'Kurt', 'Aisman', '387-54367-333', '510', 'kurtaisman@exempl.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Behrenstraße 135, 10117 Berlin', '+789123678', 'GERMANY', '2023-10-09 13:57:40', '2023-10-09 13:57:40', false),
       (UUID_TO_BIN('daf07ca2-7e65-4fbf-aac4-fc3f08cd526e'), 'Friz', 'Shlag', '517-59835-247', '530', 'aresboo@gmail.com',
       '$2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a', 'Fischerinsel 38, 10117 Berlin', '+789900547', 'GERMANY', '2023-10-12 13:57:40', '2023-10-12 13:57:40', false);


INSERT INTO die_bank.accounts (id, client_id, main_manager_id, assistant_manager_id, account_number, type, balance, currency_code)
VALUES (UUID_TO_BIN('24b33ca1-9e86-420a-bec5-746236e105e1'), UUID_TO_BIN('7e78faba-3e6a-4e6c-9e12-b6054a9cd2b2'), UUID_TO_BIN('1976ecd1-6f3a-478d-a1b3-a260cd829b9b'),
                UUID_TO_BIN('9a6dadf0-28e0-41c6-9b8c-6e024ee9b227'),'AYB00012345', 'CREDIT', 125000.00, 'EUR'),
        (UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'), UUID_TO_BIN('949aaf01-4f60-4ad9-97bd-70f01d0af833'), UUID_TO_BIN('1976ecd1-6f3a-478d-a1b3-a260cd829b9b'),
                 null,'AYB00022345', 'DEBIT', 27400.00, 'EUR'),
        (UUID_TO_BIN('ee34abe1-b6ff-4b00-b2be-dc3c7d2f864d'), UUID_TO_BIN('3403e154-a2ae-44e5-b9a1-f0c7a482adde'), UUID_TO_BIN('1976ecd1-6f3a-478d-a1b3-a260cd829b9b'),
                 null,'AYB00032345', 'CREDIT', 20700.00, 'EUR'),
        (UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), UUID_TO_BIN('0f8a2be4-64fb-4f8c-9002-fbf95f57f1a0'), UUID_TO_BIN('1976ecd1-6f3a-478d-a1b3-a260cd829b9b'),
                 UUID_TO_BIN('9a6dadf0-28e0-41c6-9b8c-6e024ee9b227'),'AYB00042345', 'DEBIT', 220345.00, 'EUR'),
        (UUID_TO_BIN('2e372c05-46c9-46b4-990d-9db632bbddbc'), UUID_TO_BIN('f85aa0cf-63e0-4e9c-961b-2eac0974dab2'), UUID_TO_BIN('36a0bc00-a281-47ec-be51-fb6c5eb42d10'),
                 null,'AYB00052345', 'CREDIT', 5076.00, 'EUR'),
        (UUID_TO_BIN('6f39663f-4a03-411b-8e17-063c39104738'), UUID_TO_BIN('e95e05f9-b835-4a72-9fab-575d4d32b79b'), UUID_TO_BIN('36a0bc00-a281-47ec-be51-fb6c5eb42d10'),
                 null,'AYB00062345', 'CREDIT', 18055.00, 'EUR'),
        (UUID_TO_BIN('25e2695a-86b5-4cac-a329-5090151eb34a'), UUID_TO_BIN('59156a86-404f-4b49-8f81-24e8c9474724'), UUID_TO_BIN('36a0bc00-a281-47ec-be51-fb6c5eb42d10'),
                 UUID_TO_BIN('de77a6e0-71ef-41ee-a139-24de7f1f3efe'),'AYB00072345', 'DEBIT', 172400.00, 'EUR'),
        (UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'), UUID_TO_BIN('d5bad0e4-c6bf-4d96-aae7-dcd648b0508a'), UUID_TO_BIN('36a0bc00-a281-47ec-be51-fb6c5eb42d10'),
                 null,'AYB00082345', 'CREDIT', 7055.00, 'EUR'),
        (UUID_TO_BIN('c815287d-edbc-437a-bb9d-1969056841c6'), UUID_TO_BIN('50f3d138-c2f5-426f-a695-e86d5acc5fc1'), UUID_TO_BIN('cc4b231b-baf5-47b1-a0d1-380419b95729'),
                 null,'AYB00092345', 'OTHER', 355.00, 'EUR'),
        (UUID_TO_BIN('102c4789-8660-4935-8bad-b631e281a9ae'), UUID_TO_BIN('daf07ca2-7e65-4fbf-aac4-fc3f08cd526e'), UUID_TO_BIN('cc4b231b-baf5-47b1-a0d1-380419b95729'),
                 null,'AYB00102345', 'OTHER', 1000000.00, 'CZK');


INSERT INTO die_bank.products (id, product_typ, currency_code, interest_rate, limit_sum, limit_duration, description )
VALUES (1,'LOAN', 'EUR', 7.00, 50000.00, 24, 'simple cash credit'),
       (2,'AUTO_LOAN', 'EUR', 5.25, 155000.00, 36, 'car purchase credit'),
       (3,'MORTGAGE', 'EUR', 5.05, 400000.00, 180, 'loan for the purchase of residential real estate'),
       (4,'DEPOSIT', 'EUR', 3.75, 10000.00, 24, 'deposit without the possibility of early withdrawal'),
       (5,'DEBIT_CARD', 'EUR', 2.00, 25500.00, 60, 'interest is calculated on the balance'),
       (6,'CREDIT_CARD', 'EUR', 9.00, 6000.00, 24, '50 days of credit holidays');

INSERT INTO die_bank.agreements (id, account_id, product_id, interest_rate, amount, duration, description )
VALUES (1, UUID_TO_BIN('24b33ca1-9e86-420a-bec5-746236e105e1'), 2, 5.5, 125000.00, 36, 'car loan for a maximum period'),
       (2, UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'), 4, 3.75, 27400.00, 24, 'deposit without the possibility of early withdrawal'),
       (3, UUID_TO_BIN('ee34abe1-b6ff-4b00-b2be-dc3c7d2f864d'), 1, 7.00, 20700.00, 24, 'simple cash credit for a maximum period'),
       (4, UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), 5, 2.00, 220345.00, 48, 'current settlement account'),
       (5, UUID_TO_BIN('2e372c05-46c9-46b4-990d-9db632bbddbc'), 1, 7.00, 5076.00, 12, 'fast cash loan'),
       (6, UUID_TO_BIN('6f39663f-4a03-411b-8e17-063c39104738'), 1, 7.00, 18055.00, 12, 'fast cash loan'),
       (7, UUID_TO_BIN('25e2695a-86b5-4cac-a329-5090151eb34a'), 5, 2.00, 172400.00, 36, 'current settlement account'),
       (8, UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'), 1, 9.00, 7055.00, 24, 'credit for needs'),
       (9, UUID_TO_BIN('c815287d-edbc-437a-bb9d-1969056841c6'), 6, 9.00, 355.00, 24, 'credit carts for needs'),
       (10,UUID_TO_BIN('102c4789-8660-4935-8bad-b631e281a9ae'), 6, 9.00, 482.00, 24, 'credit carts for needs');

INSERT INTO die_bank.transactions (id, debit_account_id, credit_account_id, type, amount, currency_code, description)
VALUES (UUID_TO_BIN('42baa5b0-454b-41aa-b5fb-2adf40c04dd1'), UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), UUID_TO_BIN('102c4789-8660-4935-8bad-b631e281a9ae'),
                'TRANSFER', 107.45, 'EUR', 'transfer 1'),
       (UUID_TO_BIN('78df9147-1941-4f2e-b42b-62d235f3b640'), UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), UUID_TO_BIN('c815287d-edbc-437a-bb9d-1969056841c6'),
                'TRANSFER', 55.90, 'EUR', 'transfer 2'),
       (UUID_TO_BIN('20ef04a8-d06b-4847-960e-2e86f836e1a2'), UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'), UUID_TO_BIN('102c4789-8660-4935-8bad-b631e281a9ae'),
                'TRANSFER', 83.00, 'EUR', 'transfer 3'),
       (UUID_TO_BIN('ac99fa67-d957-4d02-8d11-cc7e4f8b8e2e'), UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'), UUID_TO_BIN('c815287d-edbc-437a-bb9d-1969056841c6'),
                'TRANSFER', 90.56, 'EUR', 'transfer 4'),
       (UUID_TO_BIN('42b817f1-9e44-4424-a57c-733fa11dc996'), UUID_TO_BIN('25e2695a-86b5-4cac-a329-5090151eb34a'), UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'),
                'TRANSFER', 150.00, 'EUR', 'transfer 5'),
       (UUID_TO_BIN('7932a84b-b25b-4484-85a1-1fe798643aa8'), UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'), UUID_TO_BIN('cbce97ac-dd09-4cf1-8771-0b767b801740'),
                'TRANSFER', 5.67, 'EUR', 'transfer 6'),
       (UUID_TO_BIN('be4153ae-85d3-4327-a138-4489632305b3'), UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'),
                'TRANSFER', 7500.00, 'EUR', 'transfer 7'),
       (UUID_TO_BIN('bd04c9eb-0085-4c67-bbc8-6a8f8f658ff6'), UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'), UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'),
                'TRANSFER', 540.00, 'EUR', 'transfer 8'),
       (UUID_TO_BIN('59381ba7-5d78-458f-8e0a-1eeb12c7f2a3'), UUID_TO_BIN('36350729-583f-4195-bc7f-307ba8ba80cc'), UUID_TO_BIN('f1eb4d13-9ed9-4589-947b-8f564067a6d3'),
                'TRANSFER', 540.00, 'EUR', 'transfer 9'),
       (UUID_TO_BIN('d8f16be8-566c-4ef8-861f-0cd91db678ce'), UUID_TO_BIN('6f39663f-4a03-411b-8e17-063c39104738'), UUID_TO_BIN('102c4789-8660-4935-8bad-b631e281a9ae'),
                'TRANSFER', 124.00, 'EUR', 'transfer 10');