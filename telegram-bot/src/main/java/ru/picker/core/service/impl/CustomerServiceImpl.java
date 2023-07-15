package ru.picker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Customer;
import ru.picker.core.repository.CustomerRepository;
import ru.picker.core.service.CustomerService;

import javax.ws.rs.NotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findByChatId(id).orElseThrow(() ->
                new NotFoundException(String.format("Customer with ID: %s not found", id)));
    }

}
