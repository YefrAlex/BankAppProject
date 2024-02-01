
create table if not exists die_bank.employees
(
    id         binary(16) primary key,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    role       varchar (10) not null,
    email      varchar(60) not null,
    password   varchar(60) not null,
    phone      varchar(16) not null,
    country    varchar(16) not null,
    created_at timestamp   DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_blocked  boolean        not null default false,
    unique (email)
);

create table if not exists die_bank.clients
(
    id            binary(16) primary key,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    tax_code      varchar(20),
    credit_rating int DEFAULT 0 not null,
    email         varchar(60) not null,
    password      varchar(60) not null,
    address       varchar(80) not null,
    phone         varchar(16) not null,
    country       varchar(16) not null,
    created_at    timestamp   DEFAULT CURRENT_TIMESTAMP,
    updated_at    timestamp   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_blocked    boolean    not null default false,
    unique (tax_code, email)
);

create table if not exists die_bank.accounts
(
    id                      binary(16) primary key,
    client_id               binary(16)  not null,
    main_manager_id         binary(16)  not null,
    assistant_manager_id    binary(16)  ,
    account_number          varchar(20) not null,
    type                    varchar(10)      not null,
    balance                 decimal(15, 2)       DEFAULT 0 not null,
    currency_code           varchar(3)  not null,
    created_at              timestamp   DEFAULT CURRENT_TIMESTAMP,
    updated_at              timestamp   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_blocked              boolean     not null default false,
    foreign key (main_manager_id) references employees (id),
    foreign key (assistant_manager_id) references employees (id),
    foreign key (client_id) references clients (id),
    unique (account_number)
);

create table if not exists die_bank.products
(
    id              int primary key auto_increment,
    product_typ     varchar(20)   not null,
    currency_code   varchar(3)    not null,
    interest_rate   decimal(4,2) not null,
    limit_sum       decimal (12,2),
    limit_duration  int,
    description     varchar(255),
    created_at      timestamp    DEFAULT CURRENT_TIMESTAMP,
    updated_at       timestamp    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_blocked      boolean      not null default false
);

create table if not exists die_bank.agreements
(
    id            bigint primary key auto_increment,
    account_id    binary(16)     not null,
    product_id    int            not null,
    interest_rate decimal(4, 2)  not null,
    amount        decimal(12, 2) not null,
    duration      int            not null,
    description   varchar(255),
    created_at    timestamp    DEFAULT CURRENT_TIMESTAMP,
    updated_at    timestamp    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_blocked    boolean      not null default false,
    foreign key (account_id) references accounts (id),
    foreign key (product_id) references products (id)
);

create table if not exists die_bank.transactions
(
    id                binary(16) primary key,
    debit_account_id  binary(16)     not null,
    credit_account_id binary(16)     not null,
    type              varchar(10)    not null,
    amount            decimal(12, 2) not null,
    currency_code     varchar(3)     not null,
    description       varchar(255),
    created_at        timestamp      DEFAULT CURRENT_TIMESTAMP,
    foreign key (debit_account_id) references accounts (id),
    foreign key (credit_account_id) references accounts (id)
);

create table if not exists die_bank.payments
(
    id                    bigint         primary key auto_increment,
    agreement_id          bigint,
    payment_sum           decimal(12, 2) not null,
    expected_payment_date timestamp  DEFAULT CURRENT_TIMESTAMP,
    real_payment_date     timestamp  DEFAULT CURRENT_TIMESTAMP,
    is_paid               boolean      not null default false,
    foreign key (agreement_id ) references agreements (id)
);

