package com.epam.hotel.dao;

import java.sql.Date;
import java.util.List;

public interface RoomDAO {
    List getRoomsWithinRange(Date dateFrom, Date dateTo);
}
