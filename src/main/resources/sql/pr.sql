CREATE PROCEDURE `get_free_rooms`(IN dateFrom date, IN dateTo date)
BEGIN
    drop temporary table if exists temp1;
    create temporary table if not exists temp1
    select distinct room_id
    from client_order_room
    where dateFrom between check_in_date and check_out_date
       or dateTo between check_in_date and check_out_date
       or dateFrom < check_in_date and dateTo > check_out_date;

    select r.id, rc.room_class
    from temp1 t1
             right join room r on t1.room_id = r.id
             join room_class rc on r.room_class_id = rc.id
    where t1.room_id is null
    order by id;
END
