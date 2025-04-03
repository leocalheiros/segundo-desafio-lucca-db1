CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE matchHistory (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    playerOneMove VARCHAR(8) NOT NULL,
    playerOneName VARCHAR(30) NOT NULL,
    playerTwoMove VARCHAR(8) NOT NULL,
    playerTwoName VARCHAR(30) NOT NULL,
    date TIMESTAMP NOT NULL
);