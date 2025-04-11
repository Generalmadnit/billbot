CREATE TABLE invoice (
    clientid VARCHAR(50),
    clientname VARCHAR(50),
    email VARCHAR(50),
    phone BIGINT,
    address VARCHAR(100),
    product VARCHAR(50),
    quantity INTEGER,
    price NUMERIC(5,2),
    datecreated DATE
);

-- ALTER TABLE invoice
-- ALTER COLUMN price TYPE NUMERIC(10, 2);

-- ALTER TABLE invoice
-- ADD CONSTRAINT pk_clientid PRIMARY KEY (clientid);

-- alter table invoice drop constraint pk_clientid;
