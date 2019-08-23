package com.example.demo.dto;

import java.util.List;

public class CartDTO {

    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private List<CartOrderedProductDTO> orderedProductsList;

    public CartDTO(String customerFirstName,
                   String customerLastName,
                   String customerAddress,
                   String customerPhone,
                   String customerEmail,
                   List<CartOrderedProductDTO> orderedProductsList) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.orderedProductsList = orderedProductsList;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<CartOrderedProductDTO> getOrderedProductsList() {
        return orderedProductsList;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setOrderedProductsList(List<CartOrderedProductDTO> orderedProductsList) {
        this.orderedProductsList = orderedProductsList;
    }
}
