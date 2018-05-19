package org.helios.springboot.controllers;

import org.helios.springboot.domain.Customer;
import org.helios.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerRepository.findAll(Pageable.unpaged()).getContent();
    }

    @GetMapping("{id}")
    @Transactional(readOnly = true)
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerRepository.findById(id).orElse(new Customer());
    }

    @GetMapping("byEmail/{email}")
    @Transactional(readOnly = true)
    public Customer getCustomer(@PathVariable("email") String email) {
        return customerRepository.findByEmail(email).orElse(new Customer());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

}
