CREATE DEFINER=`administrator`@`localhost` PROCEDURE `insert_new_order`(IN client_id_in bigint, IN room_id_in int,
                                                        IN check_in_date_in date, IN check_out_date_in date,
                                                        IN order_status_in CHAR(20))
BEGIN
    declare id int;
    insert into client_order (client_id) value (client_id_in);
    select last_insert_id()
    into id;
    insert into client_order_room(client_order_id,
                                room_id,
                                check_in_date,
                                check_out_date,
                                order_status)
    values (id,
            room_id_in,
            check_in_date_in,
            check_out_date_in,
            order_status_in);
END