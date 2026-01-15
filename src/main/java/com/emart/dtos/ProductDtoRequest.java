package com.emart.dtos;

public class ProductDtoRequest {

    private String name;
    private Double price;

    public ProductDtoRequest() {
    }

    public ProductDtoRequest(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDtoRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
