package com.epam.hotel.service;

import com.epam.hotel.service.impl.JsonFileService;

public enum ServiceType {
    JSON_FILE_SERVICE(new JsonFileService());
    private final Service service;

    ServiceType(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}
