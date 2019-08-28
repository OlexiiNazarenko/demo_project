package com.example.demo.service.implementations;

import com.example.demo.dto.CreateProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.intefaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Product getOne(Integer id) throws NoSuchElementException {
        return productRepository.findById(id).get();
    }

    @Override
    public Product addNew(CreateProductDTO newProduct) {
        Product product = new Product(
                null,
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getPhoto(),
                newProduct.getQuantity(),
                newProduct.getCategoryId()
        );

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(CreateProductDTO updatedProduct, Integer id) throws NoSuchElementException {
        Product product = productRepository.findById(id).get();

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setPhoto(updatedProduct.getPhoto());
        product.setQuantity(updatedProduct.getQuantity());
        product.setCategoryId(updatedProduct.getCategoryId());

        return  productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
