package com.example.api_scotia.service;

import com.example.api_scotia.entities.CustomerEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportsService {
    ByteArrayInputStream customersToExcel(List<CustomerEntity> customers);
}
