CREATE TABLE users(
    user_id INT PRIMARY KEY UNIQUE NOT NULL DEFAULT nextval('users_seq'),
    user_name TEXT NOT NULL,
    user_cpf CHAR(11) NOT NULL,
    user_email TEXT,
    user_password TEXT,
    active BOOLEAN
);