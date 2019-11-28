INSERT INTO author (id, name) VALUES
(10001, 'Stephen King'),
(10002, 'J. R. R. Tolkien'),
(10003, 'Ernest Hemingway');

INSERT INTO writing (id, title, pages, category, date_of_publication, author_id, description) VALUES
(10001, 'The Shining', 442, 'HORROR', TO_DATE('1977', 'YYYY'), 10001, 'A novelist - Jack Torrance takes a job interview as winter caretaker of the isolated, old, huge and beautiful Overlook Hotel. Jack brings his wife - Wendy and his son Danny. It happens that Danny, has a mysterious power known as The Shining.'),
(10002, 'It', 359, 'HORROR', TO_DATE('1986', 'YYYY'), 10001, 'It is a 1986 horror novel by American author Stephen King. It was his 22nd book, and his 17th novel written under his own name. The story follows the experiences of seven children as they are terrorized by an evil entity that exploits the fears of its victims to disguise itself while hunting its prey.'),
(10003, 'Misery', 298, 'HORROR', TO_DATE('1987', 'YYYY'), 10001, 'Misery is an American psychological horror thriller novel written by Stephen King and first published by Viking Press on June 8, 1987. The novels narrative is based on the relationship of its two main characters – the popular writer Paul Sheldon and his psychotic fan Annie Wilkes.'),
(10004, 'Cujo', 188, 'HORROR', TO_DATE('1981', 'YYYY'), 10001, 'Cujo was a massive, male St. Bernard owned by the Camber Family. Cujo was once a friendly and playful companion, but when he got rabies from a bat bite, he went insane and was turned into a vicious killer that attacked anybody who came near him.'),
(10005, 'The Lord of the Rings', 1171, 'FANTASY', TO_DATE('1954', 'YYYY'), 10002, 'The Lord of the Rings is the saga of a group of sometimes reluctant heroes who set forth to save their world from consummate evil. Its many worlds and creatures were drawn from Tolkiens extensive knowledge of philology and folklore.'),
(10006, 'The Hobbit', 305, 'FANTASY', TO_DATE('1937', 'YYYY'), 10002, 'Hobbits are a fictional human-like race in the novels of J. R. R. Tolkien. About half the height of humans, they are also referred to as Halflings. They live barefooted, and live in underground houses which have windows, as they are typically built into the sides of hills.'),
(10007, 'The Sillmarillion', 487, 'FANTASY', TO_DATE('1977', 'YYYY'), 10002, 'The Silmarillion is actually Tolkiens first book and also his last. In origin it precedes even The Hobbit, and is the story of the First Age of Tolkiens Middle Earth. It shows us the ancient history to which characters in The Lord of the Rings look back, talk, rhyme and sing about.'),
(10008, 'The Old Man and the Sea', 155, 'NOVEL', TO_DATE('1952', 'YYYY'), 10003, 'The Old Man and the Sea tells the story of a battle between an aging, experienced fisherman, Santiago, and a large marlin. The story opens with Santiago having gone 84 days without catching a fish, and now being seen as "salao", the worst form of unluckiness.'),
(10009, 'A Farewell to Arms', 355, 'NOVEL', TO_DATE('1929', 'YYYY'), 10003, 'A Farewell to Arms is a novel by Ernest Hemingway set during the Italian campaign of World War I. First published in 1929, it is a first-person account of an American, Frederic Henry, serving as a lieutenant ("tenente") in the ambulance corps of the Italian Army.'),
(10010, 'For Whom the Bell Tolls', 388, 'NOVEL', TO_DATE('1940', 'YYYY'), 10003, 'For Whom the Bell Tolls is a novel by Ernest Hemingway published in 1940. It tells the story of Robert Jordan, a young American volunteer attached to a Republican guerrilla unit during the Spanish Civil War. As a dynamiter, he is assigned to blow up a bridge during an attack on the city of Segovia.');

INSERT INTO subscription (id, name, max_lendable_books, price_per_month, max_lend_time) VALUES
(10001, 'Silver', 5, 3, 30),
(10002, 'Gold' ,10, 5, 50),
(10003, 'Diamond' ,100, 10, 180);

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
(10007, 10007, 10001, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10008, 10008, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10009, 10009, 10002, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10010, 10010, 10001, 30, TO_DATE('18/12/2019', 'DD/MM/YYYY')),
(10011, 10001, null, 0, null),
(10012, 10001, null, 0, null);