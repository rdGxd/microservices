package org.example.bookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author", nullable = false, length = 180)
  private String author;
  @Column(name = "title", nullable = false, length = 250)
  private String title;
  @Column(name = "launch_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date launchDate;
  @Column(name = "price", nullable = false)
  private Double price;

  @Transient
  private String currency;
  @Transient // O @Transient é uma anotação que indica que o atributo não deve ser persistido no banco de dados.
  private String environment;

  public Book(Long id, String author,String title, Date launchDate, Double price,  String currency, String environment) {
    this.id = id;
    this.author = author;
    this.launchDate = launchDate;
    this.price = price;
    this.title = title;
    this.currency = currency;
    this.environment = environment;
  }

  public Book() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(launchDate, book.launchDate) && Objects.equals(price, book.price) && Objects.equals(currency, book.currency) && Objects.equals(environment, book.environment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author, title, launchDate, price, currency, environment);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }
}
