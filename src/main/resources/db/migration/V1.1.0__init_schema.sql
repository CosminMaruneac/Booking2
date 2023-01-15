CREATE TABLE account
(
    id                 bigint generated always as identity,
    password           VARCHAR,
    first_name         VARCHAR,
    last_name          VARCHAR,
    email              VARCHAR UNIQUE,
    phone_number       VARCHAR,
    account_type       VARCHAR,
    billing_address    VARCHAR,
    profile_image_link VARCHAR,
    birth_date         DATE,
    is_deleted         boolean,
    PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id                 bigint generated always as identity,
    date_from          DATE,
    date_to            DATE,
    price              double precision,
    reservation_status varchar,
    owner_id           bigint,
    isCanceled         BOOLEAN,
    owner_name         varchar,
    PRIMARY KEY (id)
);

CREATE TABLE room
(
    id              bigint generated always as identity,
    title           varchar,
    room_number     integer,
    capacity        integer,
    price_per_night double precision,
    bed_number      integer,
    clean_status    VARCHAR,
    rate            integer,
    room_type       varchar,
    pet_friendly    boolean,
    image_url       varchar,
    description     varchar,
    PRIMARY KEY (id)
);

CREATE TABLE facility
(
    id   bigint generated always as identity,
    name VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE reservation_room
(
    reservation_id bigint,
    room_id        bigint,
    FOREIGN KEY (reservation_id) REFERENCES reservation (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE room_facility
(
    room_id     bigint,
    facility_id bigint,
    FOREIGN KEY (room_id) REFERENCES room (id),
    FOREIGN KEY (facility_id) REFERENCES facility (id)
);

