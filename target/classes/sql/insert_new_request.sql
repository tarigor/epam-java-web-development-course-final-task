CREATE
    DEFINER = `administrator`@`localhost` PROCEDURE `insert_new_request`(in client_id_in bigint, in persons_in int,
                                                                         in room_class_in char(10),
                                                                         in check_in_date_in date,
                                                                         in check_out_date_in date)
BEGIN
    declare id int;
    insert into client_request (client_r_id) value (client_id_in);
    select last_insert_id()
    into id;
    insert into hotelDB.request (request_id,
                                 persons_amount,
                                 room_class,
                                 check_in_date,
                                 check_out_date)
    values (id,
            persons_in,
            room_class_in,
            check_in_date_in,
            check_out_date_in);
END