package com.mb.employeemanagement.service.impl;

import com.mb.employeemanagement.service.AddressService;

import com.mb.employeemanagement.dto.request.AddressRequest;
import com.mb.employeemanagement.dto.response.AddressResponse;
import com.mb.employeemanagement.entity.Address;
import com.mb.employeemanagement.entity.Employee;
import com.mb.employeemanagement.repository.AddressRepository;
import com.mb.employeemanagement.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final EmployeeRepository employeeRepo;
    private final ModelMapper modelMapper;


    //create
    //create
    public AddressResponse create(AddressRequest request, int employeeId) {
    	Employee emp = employeeRepo.findById(employeeId)
    		    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Address address = modelMapper.map(request, Address.class);
    		address.setEmployee(emp);
    		return modelMapper.map(addressRepo.save(address), AddressResponse.class);
    }

   //get all
    public List<AddressResponse> findAll() {
        return addressRepo.findAll().stream()
                .map(a -> modelMapper.map(a, AddressResponse.class))
                .collect(Collectors.toList());
    }
    
    //get by id
    public AddressResponse findById(int id) {
    	Address address = addressRepo.findById(id)
    		    .orElseThrow(() -> new RuntimeException("Address not found"));
        return modelMapper.map(address, AddressResponse.class);
    }

    //get by emp id
    public AddressResponse findByEmployeeId(int employeeId) {
        Address address = addressRepo.findByEmployeeId(employeeId).orElse(null);
        return address == null ? null : modelMapper.map(address, AddressResponse.class);
    }

    //update
    public AddressResponse update(int id, AddressRequest request) {
        Optional<Address> existing = addressRepo.findById(id);

        if (existing.isPresent()) {
            Address addr = existing.get();
            addr.setStreet(request.getStreet());
            addr.setCity(request.getCity());
            addr.setState(request.getState());
            addr.setPincode(request.getPincode());

            return modelMapper.map(addressRepo.save(addr), AddressResponse.class);
        }

        return null;
    }

    //delete
   
    public void delete(int id) {
        addressRepo.deleteById(id);
    }
}
