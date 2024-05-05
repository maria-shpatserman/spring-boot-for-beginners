package ru.netunix.springreactivemongocrud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.netunix.springreactivemongocrud.controller.ProductController;
import ru.netunix.springreactivemongocrud.dto.ProductDto;
import ru.netunix.springreactivemongocrud.service.ProductService;

import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class SpringReactiveMongoCrudApplicationTests {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductService service;

    @Test
    public void addProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 2000));
        when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);
        var response = webTestClient.post().uri("/products")
                .body(productDtoMono, ProductDto.class)
                .exchange()
                .expectStatus().isOk();
        System.out.println(response);
    }
    @Test
    public void getProductsTest(){
        Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102", "mobile", 1, 2000),
                new ProductDto("103", "iPad", 10, 200000));
        when(service.getProducts()).thenReturn(productDtoFlux);
        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto("102", "mobile", 1, 2000))
                .expectNext(new ProductDto("103", "iPad", 10, 200000))
                .verifyComplete();

    }


}
