INSERT INTO author (id, name) VALUES
(10001, 'Stephen King'),
(10002, 'J. R. R. Tolkien'),
(10003, 'Ernest Hemingway');

INSERT INTO writing (id, title, pages, category, date_of_publication, author_id) VALUES
(10001, 'The Shining', 442, 'HORROR', TO_DATE('1977', 'YYYY'), 10001),
(10002, 'It', 359, 'HORROR', TO_DATE('1986', 'YYYY'), 10001),
(10003, 'Misery', 298, 'HORROR', TO_DATE('1987', 'YYYY'), 10001),
(10004, 'Cujo', 188, 'HORROR', TO_DATE('1981', 'YYYY'), 10001),
(10005, 'The Lord of the Rings', 1171, 'FANTASY', TO_DATE('1954', 'YYYY'), 10002),
(10006, 'The Hobbit', 305, 'FANTASY', TO_DATE('1937', 'YYYY'), 10002),
(10007, 'The Sillmarillion', 487, 'FANTASY', TO_DATE('1977', 'YYYY'), 10002),
(10008, 'The Old Man and the Sea', 155, 'NOVEL', TO_DATE('1952', 'YYYY'), 10003),
(10009, 'A Farewell to Arms', 355, 'NOVEL', TO_DATE('1929', 'YYYY'), 10003),
(10010, 'For Whom the Bell Tolls', 388, 'NOVEL', TO_DATE('1940', 'YYYY'), 10003);

INSERT INTO subscription (id, max_lendable_books, price_per_month) VALUES
(10001, 5, 3),
(10002, 10, 5),
(10003, 100, 10);

INSERT INTO lib_user (id, username, password, enabled, subscription_id) VALUES
(10001, 'GombocA', '$2a$10$OOOsasaVLniXBTpGC7SNuuuFeRg98ixHWcGy/OhSZKw.rOF5MmvL.', true, 10002),
(10002, 'BookLover69', '$2a$10$OOOsasaVLniXBTpGC7SNuuuFeRg98ixHWcGy/OhSZKw.rOF5MmvL.', true, 10003),
(10003, 'HorrorNerd666', '$2a$10$OOOsasaVLniXBTpGC7SNuuuFeRg98ixHWcGy/OhSZKw.rOF5MmvL.', true, 10001);

INSERT INTO role (id, name) VALUES
(10001, 'USER'),
(10002, 'ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES
(10001, 10001),
(10001, 10002),
(10002, 10001),
(10003, 10001);

INSERT INTO book (id, writing_id, user_id, lend_time, return_date) VALUES
(10001, 10001, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10002, 10002, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10003, 10003, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10004, 10004, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10005, 10005, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10006, 10006, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10007, 10007, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10008, 10008, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10009, 10009, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10010, 10010, 10003, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY'));