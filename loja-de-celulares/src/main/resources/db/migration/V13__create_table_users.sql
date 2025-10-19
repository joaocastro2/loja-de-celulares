CREATE TABLE users(
    user_id INT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('users_seq'),
    user_name TEXT NOT NULL,
    user_ssn CHAR(9) NOT NULL,
    user_email TEXT,
    active BOOLEAN
);