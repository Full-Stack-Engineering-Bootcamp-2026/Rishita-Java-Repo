package com.mb.EmployeeManagementProject.repository;

import com.mb.EmployeeManagementProject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByEmployeeId(int employeeId);

}