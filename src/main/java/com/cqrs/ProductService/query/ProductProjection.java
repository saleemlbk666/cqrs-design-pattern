package com.cqrs.ProductService.query;

import com.cqrs.ProductService.command.Product;
import com.cqrs.ProductService.command.ProductRepository;
import com.cqrs.ProductService.command.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){

        List<Product> products = productRepository.findAll();

        List<ProductRestModel> productRestModels =  products.stream()
                .map(product -> ProductRestModel.builder()
                        .quantity(product.getQuantity())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build()).toList();
        return productRestModels;
    }
}
