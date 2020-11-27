package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.Customer;
import ru.geekbrains.persist.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping
  public String indexProductPage(Model model) {
    logger.info("Customer page update");
    model.addAttribute("customers", customerRepository.findAll());
    System.out.println(customerRepository.findAll().get(0));
    return "customer";
  }

  @GetMapping("/{id}")
  public String editProduct(@PathVariable(value = "id") Long id, Model model) {
    logger.info("Edit customer with id {}", id);
    model.addAttribute("customer", customerRepository.findById(id));
    return "customer_form";
  }

  @GetMapping("/new")
  public String newProduct(Customer customer) {
    logger.info("Added customer");
    customerRepository.insert(customer);
    return "customer_form";
  }

  @PostMapping("/update")
  public String updateProduct(Customer customer) {
    customerRepository.update(customer);
    return "redirect:/customer";
  }

  @GetMapping("/delete_{id}")
  public String deleteProduct(@PathVariable(value = "id") Long id) {
    logger.info("Delete customer with id {}", id);
    customerRepository.delete(id);
    return "redirect:/customer";
  }
}
