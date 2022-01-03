create table if not exists room_class
(
    id         int auto_increment
        primary key,
    room_class varchar(20) not null,
    constraint class_name_UNIQUE
        unique (room_class)
)
    comment 'consist different class of rooms' charset = utf8;

create table if not exists room
(
    id              int auto_increment
        primary key,
    room_class_id   int    not null,
    room_cost       double not null,
    persons_in_room int    not null,
    constraint room_class_id_fk
        foreign key (room_class_id) references room_class (id)
)
    comment 'consist rooms infromation' charset = utf8;

create index room_class_id_idx
    on room (room_class_id);

create table if not exists user
(
    id         bigint      not null
        primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    user_type  varchar(20) not null,
    email      varchar(20) null,
    password   varchar(24) null,
    account    double      not null
)
    charset = utf8;

create table if not exists client_order
(
    client_order_room_id int auto_increment,
    client_id            bigint not null,
    constraint id_UNIQUE
        unique (client_order_room_id),
    constraint client_id_fk
        foreign key (client_id) references user (id)
)
    charset = utf8;

create index client_id_fk_idx
    on client_order (client_id);

alter table client_order
    add primary key (client_order_room_id);

create table if not exists client_request
(
    client_request_id int auto_increment
        primary key,
    client_r_id       bigint not null,
    constraint client_r_id_fk
        foreign key (client_r_id) references user (id)
)
    charset = utf8;

create index client_r_id_fk_idx
    on client_request (client_r_id);

create table if not exists `order`
(
    client_order_id int         not null,
    request_id      int         not null,
    room_id         int         not null,
    check_in_date   date        not null,
    check_out_date  date        not null,
    order_status    varchar(50) not null,
    constraint client_order_room_id_fk
        foreign key (client_order_id) references client_order (client_order_room_id)
            on update cascade on delete cascade,
    constraint room_id_fk
        foreign key (room_id) references room (id)
)
    charset = utf8;

create index client_order_fk_id_idx
    on `order` (client_order_id);

create table if not exists request
(
    request_id     int         not null,
    persons_amount int         not null,
    room_class     varchar(10) not null,
    check_in_date  date        not null,
    check_out_date date        not null,
    request_status char(50)    not null,
    constraint request_id_fk
        foreign key (request_id) references client_request (client_request_id)
            on update cascade on delete cascade
)
    charset = utf8;

create index request_id_fk_idx
    on request (request_id);

create procedure charge_account(IN client_id_in mediumtext, IN charge_amount_in double)
BEGIN
    declare acc double;
    select account
    into acc
    from user
    where id = client_id_in;
    UPDATE user u SET u.account = (acc + charge_amount_in) WHERE u.id = client_id_in;
    select account from user where id = client_id_in;
END;

create procedure get_free_rooms(IN dateFrom date, IN dateTo date)
BEGIN
    drop temporary table if exists temp1;
    create temporary table if not exists temp1
    select distinct room_id
    from `order`
    where dateFrom between check_in_date and check_out_date
       or dateTo between check_in_date and check_out_date
       or dateFrom < check_in_date and dateTo > check_out_date;

    select r.id, rc.room_class
    from temp1 t1
             right join room r on t1.room_id = r.id
             join room_class rc on r.room_class_id = rc.id
    where t1.room_id is null
    order by id;
END;

create procedure insert_new_order(IN client_id_in bigint, IN request_id_in int, IN room_id_in int,
                                  IN check_in_date_in date, IN check_out_date_in date, IN order_status_in char(50),
                                  OUT id int)
BEGIN
    insert into client_order (client_id) value (client_id_in);
    select last_insert_id()
    into id;
    insert into `order`(client_order_id,
                        request_id,
                        room_id,
                        check_in_date,
                        check_out_date,
                        order_status)
    values (id,
            request_id_in,
            room_id_in,
            check_in_date_in,
            check_out_date_in,
            order_status_in);
    select id;
END;

create procedure insert_new_request(IN client_id_in bigint, IN persons_in int, IN room_class_in char(10),
                                    IN check_in_date_in date, IN check_out_date_in date, IN request_status_in char(50))
BEGIN
    declare id int;
    insert into client_request (client_r_id) value (client_id_in);
    select last_insert_id()
    into id;
    insert into request (request_id,
                         persons_amount,
                         room_class,
                         check_in_date,
                         check_out_date,
                         request_status)
    values (id,
            persons_in,
            room_class_in,
            check_in_date_in,
            check_out_date_in,
            request_status_in);
    select id;
END;

INSERT INTO hoteldb.room_class (room_class, room_image_link)
VALUES ('SINGLE',
        'https://www.travelline.ru/resource/images/rt/3652/637321324083617504-c802f669-089d-466d-a31c-d7cd2ba5afe8');
INSERT INTO hoteldb.room_class (room_class, room_image_link)
VALUES ('DOUBLE',
        'https://www.travelline.ru/resource/images/rt/19641/637321325354036836-a772be13-ef6f-474d-be09-5fd8af3b7b30');
INSERT INTO hoteldb.room_class (room_class, room_image_link)
VALUES ('SUITE',
        'https://www.travelline.ru/resource/images/rt/3650/637321325650296040-d8c427ef-b9ba-4699-a059-8e2fdbff6c44');
INSERT INTO hoteldb.room_class (room_class, room_image_link)
VALUES ('DELUXE',
        'https://www.travelline.ru/resource/images/rt/3649/637321325955926799-0183cc73-13c0-4933-9df3-7a910272a833');

INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (1, 46, 1);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (1, 46, 1);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (1, 46, 1);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (1, 46, 1);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (1, 46, 1);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (2, 88, 2);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (2, 88, 2);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (2, 88, 2);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (2, 88, 2);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (3, 124, 3);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (3, 124, 3);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (3, 124, 3);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (4, 250, 4);
INSERT INTO hoteldb.room (room_class_id, room_cost, persons_in_room)
VALUES (4, 250, 4);