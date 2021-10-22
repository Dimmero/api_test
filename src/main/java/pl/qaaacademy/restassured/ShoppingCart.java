package pl.qaaacademy.restassured;

import java.util.Arrays;

public class ShoppingCart {
    private String id;
    private Customer customer;
    private OrderItem[] items;

    public ShoppingCart(String id, Customer customer, OrderItem[] items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
    }

    public ShoppingCart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public void setItems(OrderItem[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
