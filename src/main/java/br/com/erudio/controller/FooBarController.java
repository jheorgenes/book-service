package br.com.erudio.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Foobar endpoint")
@RestController
@RequestMapping("book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
//    @Retry(name = "foo-bar") // Faz com que o Resilience4j tente executar este método novamente quando ocorrer uma exceção, conforme a configuração da instância "foo-bar".
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") //Depois que todas as tentativas falharem, chamará esse método abaixo cujo o nome é o mesmo declarado aqui.
//    @CircuitBreaker(name = "foo-bar", fallbackMethod = "fallbackMethod") Monitora falhas consecutivas. Quando detectar muitas falhas, interrompe temporariamente novas chamadas para evitar sobrecarregar um serviço indisponível.
//    @RateLimiter(name = "default") //Limita a quantidade de chamadas permitidas por período de tempo.
    @Bulkhead(name = "default") // Limita quantas chamadas podem executar simultaneamente este método.
    public String fooBar() {
        logger.info("Request to foo-bar is received!");
//        // Simulando uma requisição para uma rota que não existe, para falhar e observar o comportameno dessa falha no circuit breaker
//        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Foo Bar!!!";
    }

    public String fallbackMethod(Exception ex) {
        return "fallbackMethod foo-bar!!!";
    }
}
