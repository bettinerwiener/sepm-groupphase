insert into user (id, first_name, last_name, email, password)
    values (1, 'Hugo', 'Deval', 'hugo@gmail.com', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq'),
           (2, 'Susan', 'Bigfoot', 'susan@gmx.at', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq');

insert into user (id, first_name, last_name, email, password, is_employee)
    values (3, 'Hugo', 'Deval', 'hugo1@gmail.com', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq', 1),
(           4, 'Susan', 'Bigfoot', 'susan1@gmx.at', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq', 1);


insert into artist (id, first_name, last_name)
    values (1, 'Steven', 'Spielberg'),
           (2, 'Kathryn', 'Bigelow');

insert into customer_order (id, user_id)
    values (1, 3),
           (2, 4),
           (3, 1),
           (4, 1),
           (5, 1),
           (6, 3);


insert into event (id, title, abstract, contents, category, duration, employee)
    values (1, 'Star Wars', 'In a galaxy far far ...', 'The Star Wars universe ...', 'FILM', 2.5, 1),
           (2, 'Star Trek', 'May you live long ...', 'May you live long and prosper', 'FILM', 2.4, 2);

insert into location (id, name, street, city, postal_code)
    values (1, 'Metropol', 'Hernalser Hauptstraße 12', 'Wien', 1170),
           (2, 'Metropol', 'Karl-Krause-Strasse 45', 'Graz', 8020);

insert into room (id, name, location)
    values (1, 'Schubert', 1),
           (2, 'Zweig', 2);

insert into section (id, letter, room, seats_selectable, price_category)
    values (1, 'A', 1, 0, 'CHEAP'),
           (2, 'B', 1, 0, 'CHEAP'),
           (3, 'C', 1, 0, 'CHEAP'),
           (4, 'A', 2, 0, 'CHEAP'),
           (5, 'B', 2, 0, 'CHEAP'),
           (6, 'C', 2, 0, 'CHEAP'),
           (7, 'D', 2, 0, 'CHEAP');

insert into seat (id, seat_number, row_letter, section)
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

insert into is_performed_at(event, room, date)
values (1, 1, NOW()),
       (2, 2, NOW());

insert into ticket (id, event, room, seat, status)
    values (1, 1, 1, 1, 'AVAILABLE'),
           (2, 1, 1, 2, 'AVAILABLE'),
           (3, 1, 1, 3, 'AVAILABLE'),
           (4, 1, 1, 4, 'AVAILABLE'),
           (5, 1, 1, 5, 'AVAILABLE'),
           (6, 1, 1, 6, 'AVAILABLE'),
           (7, 1, 1, 7, 'AVAILABLE'),
           (8, 1, 1, 8, 'AVAILABLE'),
           (9, 1, 1, 9, 'AVAILABLE'),
           (10, 2, 2, 10, 'AVAILABLE'),
           (11, 2, 2, 11, 'AVAILABLE'),
           (12, 2, 2, 12, 'AVAILABLE'),
           (13, 2, 2, 13, 'AVAILABLE');


