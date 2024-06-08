package com.alibou.ecommerce.customer;

import com.alibou.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public CustomerResponse findById(String id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No Customer found with the provided Id: %s",
                                customerRequest.getId())));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    public boolean existsById(String id) {
        return this.customerRepository.findById(id).isPresent();
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.getFirstname())) {
            customer.setFirstname(customerRequest.getFirstname());
        }
        if (StringUtils.isNotBlank(customerRequest.getLastname())) {
            customer.setLastname(customerRequest.getLastname());
        }
        if (StringUtils.isNotBlank(customerRequest.getEmail())) {
            customer.setEmail(customerRequest.getEmail());
        }
        if (customerRequest.getAddress() != null) {
            customer.setAddress(customerRequest.getAddress());
        }
    }
}
