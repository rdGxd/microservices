package org.example.bookservice.proxy;

import org.example.bookservice.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service", url = "localhost:8000")
public interface CambioProxy {

  @GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
  public Cambio getCambio(@PathVariable(value = "amount") Double amount, @PathVariable("from") String from, @PathVariable("to") String to);
}
