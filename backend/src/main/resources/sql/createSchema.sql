CREATE TABLE IF NOT EXISTS  customer
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(150) NOT NULL,
    password   VARCHAR(20) NOT NULL,
    locked     SMALLINT CHECK (locked IN (0, 1))
);

CREATE TABLE IF NOT EXISTS employee (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    username    VARCHAR(50) NOT NULL,
    password    VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS artist (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ticket (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    event       BIGINT REFERENCES event(id),
    location    BIGINT REFERENCES location(id),
    status      VARCHAR(50) CHECK (status IN ('AVAILABLE', 'RESERVED', 'BOUGHT'))
);

CREATE TABLE IF NOT EXISTS news (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    entry       VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS event (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    abstract    VARCHAR(255) NOT NULL,
    contents    VARCHAR(511),
    type        VARCHAR(25) CHECK (type IN ('CONCERT', 'FILM')),
    duration    DECIMAL DEFAULT 0 CHECK (duration >= 0 AND duration <= 10)//the time is given in h
);

CREATE TABLE IF NOT EXISTS artist_creates_event (
    event    BIGINT REFERENCES event(id),
    artist   BIGINT REFERENCES artist(id),
    CONSTRAINT artist_event_pk PRIMARY KEY (event, artist)
);

CREATE TABLE IF NOT EXISTS employee_adds_event (
    employee   BIGINT REFERENCES  employee(id),
    event   BIGINT REFERENCES event(id),
    CONSTRAINT employee_adds_event_pk PRIMARY KEY (employee, event)
);

CREATE TABLE IF NOT EXISTS employee_adds_news (
    employee    BIGINT REFERENCES employee(id),
    news     BIGINT REFERENCES news(id),
    event    BIGINT REFERENCES event(id),
    CONSTRAINT admin_adds_news PRIMARY KEY (employee, news, event)
);

CREATE TABLE IF NOT EXISTS location (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50),
    street      VARCHAR(50),
    city        VARCHAR(50),
    postal_code BIGINT,
);

CREATE TABLE IF NOT EXISTS room (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50),
    location BIGINT REFERENCES location(id)
);

CREATE TABLE IF NOT EXISTS section (
    letter      VARCHAR(1) PRIMARY KEY CHECK (letter IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')),
    price_category  VARCHAR(10) CHECK (price_category IN ('EXPENSIVE', 'CHEAP')),
    room    BIGINT REFERENCES room(id),
    seats_selectable SMALLINT CHECK (seats_selectable IN (0, 1)),
);

CREATE TABLE IF NOT EXISTS seat (
    number      INTEGER,
    row         VARCHAR(1),
    section     VARCHAR(1) REFERENCES section(letter),
    CONSTRAINT seat_pk PRIMARY KEY (row, number)
);

CREATE TABLE IF NOT EXISTS is_performed_at (
    event   BIGINT REFERENCES event(id),
    location BIGINT REFERENCES location(id),
    date    DATETIME,
    CONSTRAINT is_performed_at_pk PRIMARY KEY (event, location)
);

CREATE TABLE IF NOT EXISTS customer_buys_ticket (
    customer BIGINT REFERENCES customer(id),
    ticket BIGINT REFERENCES ticket(id)
);

CREATE TABLE IF NOT EXISTS employee_buys_ticket (
    employee BIGINT REFERENCES employee(id),
    ticket BIGINT REFERENCES ticket(id)
);



