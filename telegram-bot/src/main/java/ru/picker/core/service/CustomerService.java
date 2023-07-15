package ru.picker.core.service;

import ru.picker.core.entity.Customer;

public interface CustomerService {

    void save(Customer customer);
    Customer findById(Long id);
    
}
