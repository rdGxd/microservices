package org.example.bookservice.controller;

import org.example.bookservice.proxy.CambioProxy;
import org.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.bookservice.model.Book;

@RestController
@RequestMapping("book-service")
public class BookController {

  @Autowired
  private Environment environment;

  @Autowired
  private BookRepository repository;

  @Autowired
  private CambioProxy proxy;

  @GetMapping("/{id}/{currency}")
  public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
    if(repository.findById(id).isEmpty()) throw new RuntimeException("Book not found");
    Book book = repository.findById(id).get();
    String port = environment.getProperty("local.server.port");

    var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

    book.setEnvironment( "Book port" + port + " Cambio port " + cambio.getEnvironment());
    book.setPrice(cambio.getConvertedValue());

    return book;
  }

/*
  @GetMapping("/{id}/{currency}")
  public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
    if(repository.findById(id).isEmpty()) throw new RuntimeException("Book not found");
    Book book = repository.findById(id).get();
    String port = environment.getProperty("local.server.port");

    HashMap<String, String> params = new HashMap<>();
    params.put("amount", book.getPrice().toString());
    params.put("from", "USD");
    params.put("to", currency);

    var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
    var cambio = response.getBody();

    book.setEnvironment(port);
    book.setPrice(cambio.getConvertedValue());

    return book;
  }
  */
}
