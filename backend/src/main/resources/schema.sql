DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS  user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(150) UNIQUE NOT NULL,
    password   VARCHAR(255) NOT NULL,
    locked     SMALLINT DEFAULT 0 CHECK (locked IN (0, 1)),
    is_employee SMALLINT DEFAULT 0 CHECK (is_employee IN (0,1)),
    login_count INTEGER DEFAULT 0 CHECK (login_count >= 0)
);

CREATE TABLE IF NOT EXISTS artist (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS news (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    entry       VARCHAR(255) NOT NULL,
    title       VARCHAR(50) NOT NULL,
    abstract    VARCHAR(100) NOT NULL,
    image       VARCHAR(1024) NULL
);

CREATE TABLE IF NOT EXISTS event (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    abstract    VARCHAR(255) NOT NULL,
    contents    VARCHAR(511),
    category    VARCHAR(25),
    duration    DECIMAL CHECK (duration >= 0 AND duration <= 10),
    employee    BIGINT REFERENCES user(id),
    CONSTRAINT event_unique UNIQUE (title, category)
);

CREATE TABLE IF NOT EXISTS artist_creates_event (
    event    BIGINT REFERENCES event(id),
    artist   BIGINT REFERENCES artist(id),
    CONSTRAINT artist_event_pk PRIMARY KEY (event, artist)
);

CREATE TABLE IF NOT EXISTS employee_adds_news (
    employee    BIGINT REFERENCES user(id),
    news     BIGINT REFERENCES news(id),
    event    BIGINT REFERENCES event(id),
    CONSTRAINT admin_adds_news PRIMARY KEY (employee, news, event)
);

CREATE TABLE IF NOT EXISTS location (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    street      VARCHAR(50) NOT NULL,
    city        VARCHAR(50) NOT NULL,
    postal_code BIGINT,
    CONSTRAINT unique_location UNIQUE (name, street, city)
);

CREATE TABLE IF NOT EXISTS room (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    location BIGINT NOT NULL REFERENCES location(id)
);

CREATE TABLE IF NOT EXISTS section (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    letter      VARCHAR(1) CHECK (letter IN ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')),
    price_category  VARCHAR(10) CHECK (price_category IN ('EXPENSIVE', 'CHEAP')),
    room    BIGINT NOT NULL REFERENCES room(id),
    seats_selectable SMALLINT CHECK (seats_selectable IN (0, 1))
);

CREATE TABLE IF NOT EXISTS seat (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_number     INTEGER NOT NULL,
    row_letter      VARCHAR(1) NOT NULL,
    section     BIGINT NOT NULL REFERENCES section(id)
);

CREATE TABLE IF NOT EXISTS is_performed_at (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event   BIGINT REFERENCES event(id),
    room BIGINT REFERENCES room(id),
    `date`    DATETIME NOT NULL,
    CONSTRAINT is_performed_at_pk UNIQUE (event, room, `date`)
);

CREATE TABLE IF NOT EXISTS ticket (
      id          BIGINT AUTO_INCREMENT PRIMARY KEY,
      event       BIGINT REFERENCES is_performed_at(event),
      room    BIGINT REFERENCES is_performed_at(room),
      seat        BIGINT REFERENCES seat(id),
      status      VARCHAR(50) CHECK (status IN ('AVAILABLE', 'RESERVED', 'BOUGHT'))
);


CREATE TABLE IF NOT EXISTS user_buys_ticket (
    user BIGINT REFERENCES user(id),
    ticket BIGINT REFERENCES ticket(id),
    CONSTRAINT customer_buys_ticket_pk PRIMARY KEY (user, ticket)
);

CREATE TABLE IF NOT EXISTS customer_news (
     user    BIGINT REFERENCES user(id),
     news        BIGINT REFERENCES news(id),
     read        SMALLINT CHECK (read IN (0, 1)),
     CONSTRAINT  customer_news_pk PRIMARY KEY (user, news)
);


