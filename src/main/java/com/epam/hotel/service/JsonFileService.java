package com.epam.hotel.service;

import com.epam.hotel.entity.HttpCommand;

import java.util.HashMap;

public interface JsonFileService {
    HashMap<String, HttpCommand> getMapOfCommandFromJson();
}
