CREATE DATABASE db;
CREATE TABLE IF NOT EXISTS role_table (
                                          role_id SERIAL PRIMARY KEY,
                                          role_name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_table (
                                          user_id SERIAL PRIMARY KEY,
                                          login VARCHAR NOT NULL UNIQUE,
                                          password VARCHAR NOT NULL,
                                          email VARCHAR NOT NULL,
                                          first_name VARCHAR NOT NULL,
                                          last_name VARCHAR NOT NULL,
                                          birthday DATE NOT NULL,
                                          role_id INTEGER NOT NULL,
                                          FOREIGN KEY (role_id) REFERENCES role_table(role_id)
);

INSERT INTO role_table (role_name) values ('user');
INSERT INTO role_table (role_name) values ('admin');
INSERT INTO user_table (login, password, email, first_name, last_name, birthday, role_id)
values('user', '$2a$12$F5Rd3VWRILUbMNSroauNG.U/uBIEWRp1yG69HIoAkFuK1eSGyE2eW', 'user@gmail.com', 'Joshua', 'Gosling', '2003-12-12', 1);
INSERT INTO user_table (login, password, email, first_name, last_name, birthday, role_id)
values('admin', '$2a$12$4pg3/c7SIMDxSL10kn76h.n1m.s2t96YWLbjLGAme.0V43fZn25ne', 'admin@gmail.com', 'James', 'Bloch', '2003-08-30', 2);
