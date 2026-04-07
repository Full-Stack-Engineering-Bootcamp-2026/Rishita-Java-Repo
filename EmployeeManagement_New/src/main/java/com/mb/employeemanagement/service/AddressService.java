package com.mb.employeemanagement.service;

import com.mb.employeemanagement.dto.request.AddressRequest;
import com.mb.employeemanagement.dto.response.AddressResponse;
import java.util.List;

public interface AddressService {
    AddressResponse create(AddressRequest request, int employeeId);
    List<AddressResponse> findAll();
    AddressResponse findById(int id);
    AddressResponse findByEmployeeId(int employeeId);
    AddressResponse update(int id, AddressRequest request);
    void delete(int id);
}
