package ru.netunix.springreactivemongocrud.util;

import org.springframework.beans.BeanUtils;
import ru.netunix.springreactivemongocrud.dto.ProductDto;
import ru.netunix.springreactivemongocrud.entity.Product;

public class ProductMapper {
    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;

    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
