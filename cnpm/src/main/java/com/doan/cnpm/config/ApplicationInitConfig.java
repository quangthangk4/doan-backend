package com.doan.cnpm.config;

import com.doan.cnpm.entity.Customer;
import com.doan.cnpm.enums.Role;
import com.doan.cnpm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner applicationRunner(CustomerRepository customerRepository) {
        return args -> {
            if(customerRepository.findByEmail("admin@gmail.com").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());


                Customer customer = Customer.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                customerRepository.save(customer);
                log.warn("admin user has been created with default password: admin, please change password");
            }
        };
    }
}
