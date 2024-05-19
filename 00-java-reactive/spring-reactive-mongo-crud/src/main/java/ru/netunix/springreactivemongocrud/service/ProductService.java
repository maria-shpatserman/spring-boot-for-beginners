package ru.netunix.springreactivemongocrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.netunix.springreactivemongocrud.dto.ProductDto;
import ru.netunix.springreactivemongocrud.entity.Product;
import ru.netunix.springreactivemongocrud.repository.ProductRepository;
import ru.netunix.springreactivemongocrud.util.ProductMapper;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(ProductMapper::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(ProductMapper::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ProductMapper::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(ProductMapper::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(ProductMapper::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(ProductMapper::entityToDto);

    }
    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
