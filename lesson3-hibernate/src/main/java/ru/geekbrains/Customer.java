package ru.geekbrains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
          name = "purchase",
          joinColumns = @JoinColumn(name = "customer_id"),
          inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<Product> products;

  public Customer() {
  }

  public Customer(String name) {
    this.name = name;
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}

