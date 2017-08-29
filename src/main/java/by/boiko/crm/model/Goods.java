package by.boiko.crm.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    @Id
    @Column(name = "sku")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sku;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CATEGORY")
    private int category;

    public Goods() {
    }

    public Goods(int sku, String name, double price, int category) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "sku=" + sku +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
