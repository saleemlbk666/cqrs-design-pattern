package com.cqrs.ProductService.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {

        Product product = new Product();

        BeanUtils.copyProperties(productCreatedEvent, product);

        productRepository.save(product);

        throw new Exception("Some Exception");
    }

    @ExceptionHandler
    public void handleException(Exception exception) throws Exception {
        throw  exception;
    }
}
