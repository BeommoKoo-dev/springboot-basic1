package org.prgrms.kdt.customer.controller;

import org.prgrms.kdt.customer.service.CustomerService;
import org.prgrms.kdt.customer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/v1/customers")
    @ResponseBody
    public List<Customer> findCustomers() {
        return customerService.getAllcustomers();
    }

    @GetMapping("/api/v1/customers/{customerId}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<Customer> findCustomer(@PathVariable("customerId") UUID customerId) {
        var customer = customerService.getCustomer(customerId);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/v1/customers/{customerId}")
    @ResponseBody
    public CustomerDto saveCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customer) {
        logger.info("Got customer save request {}", customer);
        return customer;
    }

    @GetMapping("/customers")
    public String viewCustomersPage(Model model) {
        var allCustomers = customerService.getAllcustomers();
        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("customers", allCustomers);
        return "views/customers";
    }

    @GetMapping("/customers/{customerId}")
    public String findCustomer(@PathVariable("customerId") UUID customerId, Model model) {
        var maybeCustomer = customerService.getCustomer(customerId);
        if (maybeCustomer.isPresent()) {
            model.addAttribute("customer", maybeCustomer.get());
            return "views/customer-details";
        } else {
            return "views/404";
        }
    }

    @GetMapping("/customers/new")
    public String viewNewCustomerPage() {
        return "views/new-customers";
    }

    @PostMapping("/customers/new")
    public String addNewCustomer(CreateCustomerRequest createCustomerRequest) {
        customerService.createCustomer(createCustomerRequest.email(), createCustomerRequest.name());
        return "redirect:/customers";
    }

}