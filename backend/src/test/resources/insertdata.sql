INSERT INTO customer (first_name, last_name, email, password, locked)
    VALUES ('Hugo', 'Deval', 'hugo@gmail.com', 'hugo', 0),
           ('Susan', 'Bigfoot', 'susan@gmx.at', 'susan', 0);

insert into employee (first_name, last_name, email, password)
    values (1, 'Hugo', 'Deval', 'hugo@gmail.com', 'hugo'),
(           2, 'Susan', 'Bigfoot', 'susan@gmx.at', 'susan');

insert into artist (id, first_name, last_name)
    values (1, 'Steven', 'Spielberg'),
           (2, 'Kathryn', 'Bigelow');

insert into event (id, title, abstract, contents, type, duration, employee)
    values (1, 'Star Wars', 'In a galaxy far far ...', 'The Star Wars universe ...', 'FILM', 2.5, 1),
           (1, 'Star Trek', 'May you live long ...', 'May you live long and prosper', 'FILM', 2.4, 2);

insert into location (id, name, street, city, postal_code)
    values (1, 'Metropol', 'Hernalser Hauptstraße 12', 'Wien', 1170),
           (2, 'Metropol', 'Karl-Krause-Strasse 45', 'Graz', 8020);

insert into room (id, name, location)
    values (1, 'Schubert', 1),
           (2, 'Zweig', 2);

insert into section (id, letter, room)
    values (1, 'A', 1),
           (2, 'B', 1),
           (3, 'C', 1),
           (4, 'A', 2),
           (5, 'B', 2),
           (6, 'C', 2),
           (7, 'D', 2);

insert into seat (id, number, row, section)
    values (1, 1, 'A', 1),
           (2, 2, 'A', 2),
           (3, 3, 'A', 3),
           (4, 4, 'A', 1),
           (5, 1, 'B', 1),
           (6, 2, 'B', 1),
           (7, 3, 'B', 2),
           (8, 4, 'B', 2),
           (9, 1, 'A', 5),
           (10, 2, 'A', 6),
           (11, 3, 'A', 6),
           (12, 1, 'C', 6),
           (13, 2, 'C', 7);

insert into ticket (id, event, seat)
    values (1, 1, 1),
           (1, 1, 2),
           (1, 1, 3),
           (1, 1, 4),
           (1, 1, 5),
           (1, 1, 6),
           (1, 1, 7),
           (1, 1, 8),
           (1, 1, 9),
           (1, 2, 10),
           (1, 2, 11),
           (1, 2, 12),
           (1, 2, 13);

