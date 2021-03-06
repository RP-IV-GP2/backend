package com.unipampa.crud.interfaces.service;

import java.util.List;

import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.User;

public interface IUserService {
	void saveUser(User user);

	Employee findEmployeeById(Long id);

	Customer findCustomerById(Long id);

	Owner findOwnerById(Long id);

	User findUserById(Long id);

	List<User> findAllUsers();

	void deleteUser(Long id);

	Employee updateEmployee(Employee employee);

	Customer updateCustomer(Customer customer);

	Owner updateOwner(Owner owner);

	Boolean existsByEmail(String email);

	Customer findCustomerByEmail(String email);
}
