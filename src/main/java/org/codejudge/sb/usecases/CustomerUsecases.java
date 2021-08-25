package org.codejudge.sb.usecases;

import org.codejudge.sb.dtos.CreateUserDTO;
import org.codejudge.sb.dtos.CustomerRegistrationDTO;
import org.codejudge.sb.dtos.UpdateCustomerDTO;
import org.codejudge.sb.exceptions.accounts.AccountAlreadyExistsException;
import org.codejudge.sb.exceptions.validation.UsernameTakenException;
import org.codejudge.sb.models.Customer;
import org.codejudge.sb.models.Role;
import org.codejudge.sb.models.User;
import org.codejudge.sb.reposotories.CustomerRepository;
import org.codejudge.sb.reposotories.RoleRepository;
import org.codejudge.sb.reposotories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUsecases {
    private final UserRepository userRepository;
    private final UserUsecases userUsecases;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    public CustomerUsecases(UserRepository userRepository, UserUsecases userUsecases, CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userUsecases = userUsecases;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }
    // have very little logic
    // most of the stuff that it will accomplish
    // it will do it by "delegating"
    // things to repository / external-services

    // DTO - Data Transfer Object
    // When you move data across layers
    public Customer registerCustomer(CustomerRegistrationDTO details) {
        // check if the username/phone/email is unique
        if (userRepository.findUserByUsername(details.getUsername()).isPresent()) {
            throw new UsernameTakenException(details.getUsername() + " is not available");
        }
        if (customerRepository.findCustomerByPhone(details.getPhone()).isPresent()) {
            throw new AccountAlreadyExistsException("Account with phone " + details.getPhone() + " already exists. Please login");
        }
        if (customerRepository.findCustomerByEmail(details.getEmail()).isPresent()) {
            throw new AccountAlreadyExistsException("Account with email " + details.getEmail() + " already exists. Please login");
        }
        // checking whether the phone/email is structurally correct is not our responsibility
        ResponseEntity<User> user = userUsecases.createUser(new CreateUserDTO(details.getUsername(), details.getPassword()));
        Role customerRole = roleRepository.getRoleByName("customer").get();
        user.getBody().addRole(customerRole);
        userRepository.save(user.getBody());

        Customer customer = new Customer(user.getBody());
        customer.setCity(details.getCity());
        customer.setFullName(details.getFullName());
        customer.setPhone(details.getPhone());
        customer.setEmail(details.getEmail());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Customer customer) {
    }

    public Customer updateCustomer(Customer customer, UpdateCustomerDTO details) {
        if (details.getPhone() != null) {
            Optional<Customer> existing = customerRepository.findCustomerByPhone(details.getPhone());
            if (!existing.isPresent() || !existing.get().equals(customer)) {
                throw new AccountAlreadyExistsException("Account with phone " + details.getPhone() + " already exists. Please login");
            }
            customer.setPhone(details.getPhone());
        }
        if (details.getEmail() != null) {
            Optional<Customer> existing = customerRepository.findCustomerByPhone(details.getPhone());
            if (!existing.isPresent() || !existing.get().equals(customer)) {
                throw new AccountAlreadyExistsException("Account with email " + details.getEmail() + " already exists. Please login");
            }
            customer.setEmail(details.getEmail());
        }
        if (details.getCity() != null) {
            customer.setCity(details.getCity());
        }
        if (details.getFullName() != null) {
            customer.setFullName(details.getFullName());
        }
        customerRepository.save(customer);
        return customer;
    }
}
