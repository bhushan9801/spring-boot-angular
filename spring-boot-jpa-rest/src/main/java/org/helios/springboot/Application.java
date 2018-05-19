package org.helios.springboot;

import org.helios.springboot.domain.Customer;
import org.helios.springboot.repository.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner init(CustomerRepository customerRepository) {
        return args -> {
            try {
                Stream.of(Customer.create("Bruce Banner", "bbanner@usa.net"),
                        Customer.create("Stephen Strange", "sstrange@usa.net"),
                        Customer.create("Peter Parker", "pparker@usa.net"),
                        Customer.create("Tony Stark", "tstark@usa.net"),
                        Customer.create("Steven Rogers", "srogers@usa.net"),
                        Customer.create("Clinton Barton", "cbarton@usa.net"),
                        Customer.create("Natasha Romanova", "nromanova@usa.net")
                ).forEach(customer -> {
                    Optional<Customer> byEmail = customerRepository.findByEmail(customer.getEmail());
                    if (!byEmail.isPresent()) {
                        customerRepository.save(customer);
                    }
                });
            } catch (Exception ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        };
    }
}
