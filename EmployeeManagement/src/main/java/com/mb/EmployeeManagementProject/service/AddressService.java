package com.mb.EmployeeManagementProject.service;

import com.mb.EmployeeManagementProject.model.Address;
import com.mb.EmployeeManagementProject.model.Employee;
import com.mb.EmployeeManagementProject.repository.AddressRepository;
import com.mb.EmployeeManagementProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository addressRepo;
    private EmployeeRepository employeeRepo;

    public AddressService(AddressRepository addressRepo, EmployeeRepository employeeRepo) {
        this.addressRepo = addressRepo;
        this.employeeRepo = employeeRepo;
    }
    
    //create
    public Address create(Address address, int employeeId) {
        Optional<Employee> emp = employeeRepo.findById(employeeId);
        if (emp.isPresent()) {
            address.setEmployee(emp.get());
            return addressRepo.save(address);
        }

        return null; 
    }

   //get all
    public List<Address> findAll() {
        return addressRepo.findAll();
    }
    
    //get by id
    public Address findById(int id) {
        return addressRepo.findById(id).orElse(null);
    }

    //get by emp id
    public Address findByEmployeeId(int employeeId) {
        return addressRepo.findByEmployeeId(employeeId).orElse(null);
    }

    //update
    public Address update(int id, Address updated) {
        Optional<Address> existing = addressRepo.findById(id);

        if (existing.isPresent()) {
            Address addr = existing.get();
            addr.setStreet(updated.getStreet());
            addr.setCity(updated.getCity());
            addr.setState(updated.getState());
            addr.setPincode(updated.getPincode());

            return addressRepo.save(addr);
        }

        return null;
    }

    //delete
   
    public void delete(int id) {
        addressRepo.deleteById(id);
    }
}