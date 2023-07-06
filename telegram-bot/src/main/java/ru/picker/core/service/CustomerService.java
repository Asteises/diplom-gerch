package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Customer;
import ru.picker.core.repository.CustomerRepository;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }


    public Customer findById(Long id) {
        return customerRepository.findByChatId(id).orElseThrow(() ->
                new NotFoundException(String.format("Customer with ID: %s not found", id)));
    }
}
