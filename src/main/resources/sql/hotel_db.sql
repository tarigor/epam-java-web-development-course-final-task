create schema if not exists hotelDB collate utf8mb4_0900_ai_ci;

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
    password   varchar(24) null
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

create table if not exists client_order_room
(
    client_order_id int         not null,
    room_id         int         not null,
    check_in_date   date        not null,
    check_out_date  date        not null,
    order_status    varchar(20) not null,
    constraint client_order_room_id_fk
        foreign key (client_order_id) references client_order (client_order_room_id)
            on update cascade on delete cascade,
    constraint room_id_fk
        foreign key (room_id) references room (id)
)
    charset = utf8;

create index client_order_fk_id_idx
    on `order` (client_order_id);

create
    definer = administrator@localhost procedure delete_record_from_client_order_room(IN orderID int, IN roomID int)
BEGIN
    declare result int;
    SELECT COUNT(1)
    into result
    FROM `order`
    WHERE client_order_id = orderID;
    IF result = 1 then
        DELETE
        FROM client_order
        where client_order_room_id = orderID;
    else
        DELETE
        FROM `order`
        WHERE room_id = roomID;
    end if;
END;

create
    definer = administrator@localhost procedure get_free_rooms(IN dateFrom date, IN dateTo date)
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

create
    definer = administrator@localhost procedure insert_new_order(IN client_id_in bigint, IN room_id_in int,
                                                                 IN check_in_date_in date, IN check_out_date_in date,
                                                                 IN order_status_in char(20), OUT id int)
BEGIN
    insert into client_order (client_id) value (client_id_in);
    select last_insert_id()
    into id;
    insert into `order`(client_order_id,
                        room_id,
                        check_in_date,
                        check_out_date,
                        order_status)
    values (id,
            room_id_in,
            check_in_date_in,
            check_out_date_in,
            order_status_in);
    select id;
END;

