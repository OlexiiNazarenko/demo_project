package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllOfCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getOne(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product addNew(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Product product = productRepository.findById(updatedProduct.getProductId()).get();
        if(product != null) {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setPhoto(updatedProduct.getPhoto());
            product.setQuantity(updatedProduct.getQuantity());
            product.setCategoryId(updatedProduct.getCategoryId());
        } else {
            product = updatedProduct;
        }
        return  productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
