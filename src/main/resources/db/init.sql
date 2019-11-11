-- ROOM Table
create table room
(
    room_id integer default nextval('room_seq'::regclass) not null
        constraint room_pkey
            primary key,
    row     integer                                       not null,
    col     integer                                       not null
);

alter table room
    owner to chooseadventure;

create unique index room_id_idx
    on room (room_id);

create unique index room_row_col_idx
    on room (row, col);

create sequence room_seq start 1 increment 1;

-- DOOR Table
create table door
(
    door_id   integer default nextval('door_seq'::regclass) not null
        constraint door_pk
            primary key,
    direction varchar                                       not null,
    room_id   integer
);

alter table door
    owner to chooseadventure;

create unique index door_direction_room
    on door (direction, room_id);

