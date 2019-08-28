package com.example.demo.service.implementations;

import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.model.OrderedProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderedProductRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ExceedProductStorageOrderException;
import com.example.demo.service.ZeroProductQuantityException;
import com.example.demo.service.intefaces.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {

    private OrderedProductRepository orderedProductRepository;
    private ProductRepository productRepository;

    public OrderedProductServiceImpl() {
    }

    @Autowired
    public OrderedProductServiceImpl(OrderedProductRepository orderedProductRepository,
                                     ProductRepository productRepository) {
        this.orderedProductRepository = orderedProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderedProduct> getAllOfOrder(Integer orderId) {
        return orderedProductRepository.findAllByOrderId(orderId);
    }

    @Override
    @Transactional
    public String addAll(List<CartOrderedProductDTO> list, Integer orderId) {

        if(checkProducts(list)) {
            for(CartOrderedProductDTO dto : list) {
                Product product = productRepository.findById(dto.getProductId()).get();
                Integer newQuantity = product.getQuantity() - dto.getQuantity();
                product.setQuantity(newQuantity);
                productRepository.save(product);
                orderedProductRepository.save(new OrderedProduct(dto, orderId));
            }
        }

        return "Successfully saved order with id: " + orderId;
    }

    private boolean checkProducts(List<CartOrderedProductDTO> list) {
        for (CartOrderedProductDTO dto : list) {
            Product product = productRepository.findById(dto.getProductId()).get();

            if (product.getQuantity() == 0) {
                throw new ZeroProductQuantityException(dto.getProductId());
            }

            Integer newQuantity = product.getQuantity() - dto.getQuantity();

            if (newQuantity < 0) {
                throw new ExceedProductStorageOrderException(dto.getProductId(),
                        product.getQuantity(),
                        dto.getQuantity());
            }
        }
        return true;
    }
}
