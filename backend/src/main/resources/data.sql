insert into user (id, first_name, last_name, email, password,locked, is_employee, )
    values (1, 'Hugo', 'Deval', 'hugo@gmail.com', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq',0,0),
           (2, 'Susan', 'Bigfoot', 'susan@gmx.at', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq',0,0);

insert into user (id, first_name, last_name, email, password,locked, is_employee)
    values (3, 'Hugo', 'Deval', 'hugo1@gmail.com', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq',0,1),
           (4, 'Susan', 'Bigfoot', 'susan1@gmx.at', '$2a$10$wk46a9s3N.R8a4DfWbDcQe03KQZ73hkXbMu62oqxozVeCLZkKb.Wq',0,1);

insert into artist (id, first_name, last_name)
    values (1, 'Steven', 'Spielberg'),
           (2, 'Kathryn', 'Bigelow');

insert into customer_order (id, user_id)
    values (3, 1),
           (4, 4),
           (5, 1);

insert into news (id, title, abstract, entry, published_at)
    values (1, 'Star Trek', 'It all depends', 'That is not fair', '2019-12-17T15:59:00.0');

insert into event (id, title, abstract, contents, category, duration, employee)
    values (1, 'Star Wars', 'In a galaxy far far ...', 'The Star Wars universe ...', 'FILM', 2.5, 1),
           (2, 'Star Trek', 'May you live long ...', 'May you live long and prosper', 'FILM', 2.4, 2);

insert into employee_adds_news (employee, news, event)
    values (3, 1, 1),
           (3, 1, 2);

insert into location (id, name, street, city, postal_code)
    values (1, 'Metropol', 'Hernalser Hauptstrasse 12', 'Wien', 1170),
           (2, 'Metropol', 'Karl-Krause-Strasse 45', 'Graz', 8020);

insert into room (id, name, location)
    values (1, 'Schubert', 1),
           (2, 'Zweig', 2);

insert into section (id, letter, room, seats_selectable)
    values (1, 'A', 1, 0),
           (2, 'B', 1, 0),
           (3, 'C', 1, 0),
           (4, 'A', 2, 0),
           (5, 'B', 2, 0),
           (6, 'C', 2, 0),
           (7, 'D', 2, 0);

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

insert into is_performed_at(id, event, room, perf_date)
values (1, 1, 1, '2019-12-17T15:59:00.0'),
       (2, 2, 2, '2019-12-17T15:59:30.0');

insert into ticket (id, price,is_performed_at_id, customer_order_id, seat_id, status)
    values (1, 200, 1, null, 1, 'AVAILABLE'),
           (2, 100, 1, 3, 2, 'BOUGHT'),
           (3, 100, 1, null, 3, 'AVAILABLE'),
           (4, 100, 1, 3, 4, 'BOUGHT'),
           (5, 100, 1, null, 5, 'AVAILABLE'),
           (6, 100, 1, null, 6, 'AVAILABLE'),
           (7, 100, 1, null, 7, 'AVAILABLE'),
           (8, 100, 1, null, 8, 'AVAILABLE'),
           (9, 30,2, 4, 6, 'BOUGHT'),
           (10, 24,2, null, 5, 'AVAILABLE'),
           (11, 35,2, 5, 6, 'RESERVED'),
           (12, 22,2, 5, 5, 'RESERVED'),
           (13, 200, 1, null , 1, 'AVAILABLE'),
           (14, 100, 1, 3, 4, 'BOUGHT'),
           (15, 30,1, 4, 6, 'RESERVED'),
           (16, 24,2, null , 5, 'AVAILABLE'),
           (17, 35,2, 5, 6, 'RESERVED'),
           (18, 22,2, 5, 5, 'RESERVED'),
           (19, 24,2, null , 5, 'AVAILABLE'),
           (20, 24,2, null , 5, 'AVAILABLE'),
           (21, 24,2, null , 5, 'AVAILABLE');