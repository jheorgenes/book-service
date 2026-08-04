package br.com.erudio.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
    @Retry(name = "foo-bar") //Declarando que esse método será observado pelo Retry e tentatá chamá-lo a quantidade de vezes especificada pelo resilience4j max-attempts
    public String fooBar() {
        logger.info("Request to foo-bar is received!");
        // Simulando uma requisição para uma rota que não existe, para falhar e observar o comportameno dessa falha no circuit breaker
        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }
}
