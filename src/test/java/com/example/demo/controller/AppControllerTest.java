package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.dto.CreateProductDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;
import com.example.demo.service.*;
import com.example.demo.service.intefaces.CategoryService;
import com.example.demo.service.intefaces.OrderService;
import com.example.demo.service.intefaces.OrderedProductService;
import com.example.demo.service.intefaces.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
class AppControllerTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedProductService orderedProductService;

    @Autowired
    private ProductService productService;

    private Integer product_1_Id;
    private Integer product_2_Id;
    private Integer product_3_Id;

    @BeforeEach
    public void init() {
        CreateProductDTO product1 = new CreateProductDTO();
        product1.setName("Name1");
        product1.setDescription("abc");
        product1.setPhoto("photo001.jpg");
        product1.setPrice(100.00);
        product1.setQuantity(1);
        product1.setCategoryId(1);
        product_1_Id = productService.addNew(product1).getProductId();

        CreateProductDTO product2 = new CreateProductDTO();
        product2.setQuantity(new Integer(0));
        product2.setName("Name1");
        product2.setDescription("abc");
        product2.setPhoto("photo001.jpg");
        product2.setPrice(100.00);
        product2.setCategoryId(1);
        product_2_Id = productService.addNew(product2).getProductId();

        product_3_Id = Integer.MAX_VALUE;
    }

    @AfterEach
    public void done() {
        productService.delete(product_1_Id);
        productService.delete(product_2_Id);
    }

    @Test
    public void saveCart_whenAddNewOrder_thenSuccess() {
        // Given

        // Preparing ordered product to be inserted into the test order
        Integer testProductId = product_1_Id;
        Double testSellPrice = new Double(100.00);
        Integer testQuantity = new Integer(1);

        CartOrderedProductDTO cartOrderedProductDTO = new CartOrderedProductDTO();
        cartOrderedProductDTO.setProductId(testProductId);
        cartOrderedProductDTO.setSellPrice(testSellPrice);
        cartOrderedProductDTO.setQuantity(testQuantity);

        //Preparing customer details for the order
        String testFirstName = "First Name";
        String testLastName = "Last Name";
        String testAddress = "Address";
        String testPhone = "1234567890";
        String testEmail = "test@example.com";
        List<CartOrderedProductDTO> orderedProductsList = new ArrayList<>();
        orderedProductsList.add(cartOrderedProductDTO);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerFirstName(testFirstName);
        cartDTO.setCustomerLastName(testLastName);
        cartDTO.setCustomerAddress(testAddress);
        cartDTO.setCustomerPhone(testPhone);
        cartDTO.setCustomerEmail(testEmail);
        cartDTO.setOrderedProductsList(orderedProductsList);

        //Creating an instance of the tested class
        AppController appController = new AppController(categoryService, orderService, orderedProductService);

        //Expected
        List expected = new ArrayList();
        String expectedResponse = "200 OK";
        Integer startProductQuantity = productService.getOne(testProductId).getQuantity();
        Integer expectedQuantity = startProductQuantity - testQuantity;

        expected.add(expectedResponse);
        expected.add(expectedQuantity);
        expected.add(testFirstName);
        expected.add(testLastName);
        expected.add(testAddress);
        expected.add(testPhone);
        expected.add(testEmail);
        expected.add(testProductId);
        expected.add(testSellPrice);
        expected.add(testQuantity);

        //When

        ResponseEntity<String> response = appController.saveCart(cartDTO);
        String actualResponse = response.getStatusCode().toString();

        Integer expectedorderId = Integer.parseInt(response.getBody().substring(34));
        Order expectedOrder = orderService.getOne(expectedorderId);
        List<OrderedProduct> actualOrderedProductList = orderedProductService.getAllOfOrder(expectedorderId);

        Integer actualQuantity = productService.getOne(testProductId).getQuantity();

        String actualFirstName = expectedOrder.getFirstName();
        String actualLastName = expectedOrder.getLastName();
        String actualAddress = expectedOrder.getAddress();
        String actualPhone = expectedOrder.getPhone();
        String actualEmail = expectedOrder.getEmail();

        Integer actualProductId = actualOrderedProductList.get(0).getProductId();
        Double actualSellPrice = actualOrderedProductList.get(0).getSellPrice();
        Integer actualProductQuantity = actualOrderedProductList.get(0).getQuantity();

        //Actual
        List actual = new ArrayList();
        actual.add(actualResponse);
        actual.add(actualQuantity);
        actual.add(actualFirstName);
        actual.add(actualLastName);
        actual.add(actualAddress);
        actual.add(actualPhone);
        actual.add(actualEmail);
        actual.add(actualProductId);
        actual.add(actualSellPrice);
        actual.add(actualProductQuantity);

        //Then
        assert actual.equals(expected);
    }

    @Test
    public void saveCart_whenAddNewOrder_thenThrowZeroProductQuantityException() {
        // Preparing ordered product to be inserted into the test order
        Integer testProductId = product_2_Id;
        Double testSellPrice = new Double(100.00);
        Integer testQuantity = new Integer(1);

        CartOrderedProductDTO cartOrderedProductDTO = new CartOrderedProductDTO();
        cartOrderedProductDTO.setProductId(testProductId);
        cartOrderedProductDTO.setSellPrice(testSellPrice);
        cartOrderedProductDTO.setQuantity(testQuantity);

        //Preparing customer details for the order
        String testFirstName = "First Name";
        String testLastName = "Last Name";
        String testAddress = "Address";
        String testPhone = "1234567890";
        String testEmail = "test@example.com";
        List<CartOrderedProductDTO> orderedProductsList = new ArrayList<>();
        orderedProductsList.add(cartOrderedProductDTO);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerFirstName(testFirstName);
        cartDTO.setCustomerLastName(testLastName);
        cartDTO.setCustomerAddress(testAddress);
        cartDTO.setCustomerPhone(testPhone);
        cartDTO.setCustomerEmail(testEmail);
        cartDTO.setOrderedProductsList(orderedProductsList);

        //Creating an instance of the tested class
        AppController appController = new AppController(categoryService, orderService, orderedProductService);

        //When, Then
        assertThrows(ZeroProductQuantityException.class, () -> { appController.saveCart(cartDTO); });
    }

    @Test
    public void saveCart_whenAddNewOrder_thenExceedProductStorageOrderException() {
        // Preparing ordered product to be inserted into the test order
        Integer testProductId = product_1_Id;
        Double testSellPrice = new Double(100.00);
        Integer testQuantity = new Integer(Integer.MAX_VALUE);

        CartOrderedProductDTO cartOrderedProductDTO = new CartOrderedProductDTO();
        cartOrderedProductDTO.setProductId(testProductId);
        cartOrderedProductDTO.setSellPrice(testSellPrice);
        cartOrderedProductDTO.setQuantity(testQuantity);

        //Preparing customer details for the order
        String testFirstName = "First Name";
        String testLastName = "Last Name";
        String testAddress = "Address";
        String testPhone = "1234567890";
        String testEmail = "test@example.com";
        List<CartOrderedProductDTO> orderedProductsList = new ArrayList<>();
        orderedProductsList.add(cartOrderedProductDTO);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerFirstName(testFirstName);
        cartDTO.setCustomerLastName(testLastName);
        cartDTO.setCustomerAddress(testAddress);
        cartDTO.setCustomerPhone(testPhone);
        cartDTO.setCustomerEmail(testEmail);
        cartDTO.setOrderedProductsList(orderedProductsList);

        //Creating an instance of the tested class
        AppController appController = new AppController(categoryService, orderService, orderedProductService);

        //When, Then
        assertThrows(ExceedProductStorageOrderException.class, () -> { appController.saveCart(cartDTO); });
    }

    @Test
    public void saveCart_whenAddNewOrder_thenThrowNoSuchElementException() {
        // Preparing ordered product to be inserted into the test order
        Integer testProductId = product_3_Id;
        Double testSellPrice = new Double(100.00);
        Integer testQuantity = new Integer(1);

        CartOrderedProductDTO cartOrderedProductDTO = new CartOrderedProductDTO();
        cartOrderedProductDTO.setProductId(testProductId);
        cartOrderedProductDTO.setSellPrice(testSellPrice);
        cartOrderedProductDTO.setQuantity(testQuantity);

        //Preparing customer details for the order
        String testFirstName = "First Name";
        String testLastName = "Last Name";
        String testAddress = "Address";
        String testPhone = "1234567890";
        String testEmail = "test@example.com";
        List<CartOrderedProductDTO> orderedProductsList = new ArrayList<>();
        orderedProductsList.add(cartOrderedProductDTO);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerFirstName(testFirstName);
        cartDTO.setCustomerLastName(testLastName);
        cartDTO.setCustomerAddress(testAddress);
        cartDTO.setCustomerPhone(testPhone);
        cartDTO.setCustomerEmail(testEmail);
        cartDTO.setOrderedProductsList(orderedProductsList);

        //Creating an instance of the tested class
        AppController appController = new AppController(categoryService, orderService, orderedProductService);

        //When, Then
        assertThrows(NoSuchElementException.class, () -> { appController.saveCart(cartDTO); });
    }
}