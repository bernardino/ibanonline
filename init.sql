CREATE DATABASE `ibanonline`;

CREATE TABLE ibanonline.transactions (
transactionId varchar(36),
userId varchar(20),
transactionType varchar(20),
fromUser varchar(20),
toUser varchar(20),
currency varchar(10),
amount double,
timestamp timestamp,
description text,

CONSTRAINT PK_Transaction PRIMARY KEY (transactionId)
);

INSERT INTO ibanonline.transactions(transactionId, userId,transactionType,fromUser,toUser,currency,amount,timestamp,description)
VALUES ("1", "1", "debit", "1", "2", "EUR", 123, now(), "debit");

INSERT INTO ibanonline.transactions(transactionId, userId,transactionType,fromUser,toUser,currency,amount,timestamp,description)
VALUES ("2", "1", "debit", "1", "2", "EUR", 123, now(), "debit");

INSERT INTO ibanonline.transactions(transactionId, userId,transactionType,fromUser,toUser,currency,amount,timestamp,description)
VALUES ("3", "1", "debit", "1", "2", "EUR", 1, now(), "debit");

INSERT INTO ibanonline.transactions(transactionId, userId,transactionType,fromUser,toUser,currency,amount,timestamp,description)
VALUES ("4", "1", "debit", "1", "2", "EUR", 500, now(), "debit");

commit;
