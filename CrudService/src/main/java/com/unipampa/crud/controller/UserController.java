package com.unipampa.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unipampa.crud.dto.UserDTO;
import com.unipampa.crud.interfaces.service.IPropertyService;
import com.unipampa.crud.interfaces.service.IUserService;
import com.unipampa.crud.model.Customer;
import com.unipampa.crud.model.Employee;
import com.unipampa.crud.model.Owner;
import com.unipampa.crud.model.Property;
import com.unipampa.crud.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "API Crud Users")
public class UserController {

	private IUserService userService;

	private IPropertyService propertyService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	// Find all users
	@GetMapping("/all")
	@ApiOperation(value = "Retorna todos os usuarios cadastrados")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Add an employee user
	@PostMapping("/employee/add")
	@ApiOperation(value = "Adiciona um usuario do tipo empregado")
	public void saveEmployee(@RequestBody UserDTO userDto) {
		Employee employee = new Employee();
		employee.setEmail(userDto.getEmail());
		employee.setName(userDto.getName());
		employee.setId(userDto.getId());
		employee.setPassword(userDto.getPassword());
		userService.saveUser(employee);
	}

	// Get an employee user
	@GetMapping("/employee/find/{id}")
	@ApiOperation(value = "Retorna um usuario do tipo empregado pelo id")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = userService.findEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	// Put employee
	@PutMapping("/employee/update")
	@ApiOperation(value = "Atualiza um usuario cadastrado do tipo empregado")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Employee updateEmployee = userService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}

	// Add an customer user
	@PostMapping("/customer/add")
	@ApiOperation(value = "Adiciona um usuario do tipo cliente")
	public void saveCustomer(@RequestBody UserDTO userDto) {
		Customer customer = new Customer();
		customer.setEmail(userDto.getEmail());
		customer.setName(userDto.getName());
		customer.setPassword(userDto.getPassword());
		customer.setId(userDto.getId());
		customer.setAddress(userDto.getAddress());
		customer.setPhone(userDto.getPhone());
		customer.setCpf(userDto.getCpf());
		userService.saveUser(customer);
	}

	// Get an customer user
	@GetMapping("/customer/find/{email}")
	@ApiOperation(value = "Retorna um usuario do tipo cliente pelo email")
	public ResponseEntity<?> getCustomerById(@PathVariable("email") String email) {
		Customer customer = userService.findCustomerByEmail(email);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Check customer email (to check if it already exists before creating a new
	// user)
	@PostMapping("/checkEmail")
	@ApiOperation(value = "Retorna true se o email existe no banco de dados")
	public ResponseEntity<?> getCustomerEmail(@RequestBody UserDTO userDTO) {
		Boolean result = userService.existsByEmail(userDTO.getEmail());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// Put customer
	@PutMapping("/customer/update")
	@ApiOperation(value = "Atualiza um usuario do tipo cliente pelo id")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		userService.saveUser(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Add an owner user
	@PostMapping("/owner/add")
	@ApiOperation(value = "Adiciona um usuario do tipo proprietario")
	public void saveOwner(@RequestBody UserDTO userDto) {
		Owner owner = new Owner();
		owner.setEmail(userDto.getEmail());
		owner.setName(userDto.getName());
		owner.setPassword(userDto.getPassword());
		owner.setId(userDto.getId());
		owner.setAddress(userDto.getAddress());
		owner.setPhone(userDto.getPhone());
		owner.setCpf(userDto.getCpf());
		userService.saveUser(owner);
	}

	// Get an owner user
	@GetMapping("/owner/find/{id}")
	@ApiOperation(value = "Retorna um usuario do tipo proprietario pelo id")
	public ResponseEntity<?> getOwnerById(@PathVariable("id") Long id) {
		Owner owner = userService.findOwnerById(id);
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}

	// Put owner
	@PutMapping("/owner/update")
	@ApiOperation(value = "Atualiza um usuario do tipo proprietario")
	public ResponseEntity<?> updateOwner(@RequestBody Owner owner) {
		Owner updateOwner = userService.updateOwner(owner);
		return new ResponseEntity<>(updateOwner, HttpStatus.OK);
	}

	// Delete user
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Remove um usuario pelo seu id")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/relation/{id}")
	public void rentToUser(@PathVariable("id") Long id, Property property) {
		User user = userService.findUserById(id);
		property.setUser(user);
		propertyService.saveProperty(property);
	}

}
