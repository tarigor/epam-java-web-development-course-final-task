use testHotelDB;

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
END;;