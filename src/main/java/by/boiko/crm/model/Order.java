package by.boiko.crm.model;

import java.util.List;

public class Order {

    private String name;
    private String number;
    private String email;
    private String address;
    private List<Product> nameProduct;
    private String priceProduct;

    public Order() {
    }

    public Order(String name, String number, String email, String address, List<Product> nameProduct, String priceProduct) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    public List<Product> getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(List<Product> nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }
}
