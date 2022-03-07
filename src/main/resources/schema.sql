CREATE SCHEMA online_bank;

CREATE TABLE online_bank.account (
    id bigint NOT NULL PRIMARY KEY,
    
    account_number CHAR(8) NOT NULL,
    current_balance NUMERIC(10,3) NOT NULL,
    owing_balance NUMERIC(10,3) NOT NULL,
    owner_name VARCHAR(50) NOT NULL,
    
);


CREATE TABLE online_bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    source_account_id bigint NOT NULL REFERENCES online_bank.account(id),
    target_account_id bigint NOT NULL REFERENCES online_bank.account(id),
    target_owner_name varchar(50) NOT NULL,
    amount NUMERIC(10,3) NOT NULL,
   
    
);
