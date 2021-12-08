use testHotelDB;

create table room_class
(
    id         int auto_increment
        primary key,
    room_class varchar(20) not null,
    constraint class_name_UNIQUE
        unique (room_class)
)
    comment 'consist different class of rooms' charset = utf8;

create table room
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

create table user
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

create table client_order
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

create table client_request
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

create table `order`
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

create table request
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

DELIMITER //
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `insert_new_request`(IN client_id_in bigint, IN persons_in int,
                                                                IN room_class_in char(10), IN check_in_date_in date,
                                                                IN check_out_date_in date,
                                                                IN request_status_in char(50))
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
//
DELIMITER ;

DELIMITER //
create
    definer = administrator@localhost procedure charge_account(IN client_id_in long, IN charge_amount_in double)
BEGIN
    declare acc double;
    select account
    into acc
    from user
    where id = client_id_in;
    UPDATE user u SET u.account = (acc + charge_amount_in) WHERE u.id = client_id_in;
    select account from user where id = client_id_in;
END;
//
DELIMITER ;

INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (88574861, 'Galina', 'Pupkina', 'CLIENT', 'pupkina@mail.com', 'J2bE8mb/kck=', 146.4);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (215011968, 'Oleg', 'Elkin', 'CLIENT', 'elkin@mail.com', 'iiWZavo//60=', 43.6000000000001);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (547668666, 'Igor', 'Taren', 'ADMIN', 'admin@mail.com', 'he3u1Zp/v8U=', 45.3);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (884474596, 'Vasya', 'Pupkin', 'CLIENT', 'pupkin@mail.com', 'f19rVCMSBfA=', 23.9);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (1043998575, 'Fedya', 'Lisichkin', 'CLIENT', 'lisichkin@mail.com', 'jhhLjIC0fgI=', 233.3);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (1176749246, 'Petya', 'Pugovkin', 'CLIENT', 'pugovkin@mail.com', 'IOH4Nbq8XBg=', 0);
INSERT INTO user (id, first_name, last_name, user_type, email, password, account)
VALUES (1417843400, 'Igor', 'Taren', 'ADMIN', 'igor@mail.com', 'wyE76DPD/lg=', 434.23);
