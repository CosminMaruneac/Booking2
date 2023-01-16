CREATE TABLE room_image
(
    id      bigint generated always as identity,
    url     varchar,
    room_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);

ALTER TABLE room
    DROP COLUMN image_url;