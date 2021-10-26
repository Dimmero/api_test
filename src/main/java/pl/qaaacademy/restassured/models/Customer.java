package pl.qaaacademy.restassured.models;

public class Customer {
    private String id;
    private String password;
    private int customerStatus;
    private Address address;
    private ShoppingCart shoppingCart;
    private Person person;

    public Customer(String id, String password, int customerStatus, Address address, ShoppingCart cart, Person person) {
        this.id = id;
        this.password = password;
        this.customerStatus = customerStatus;
        this.address = address;
        this.shoppingCart = cart;
        this.person = person;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", customerStatus=" + customerStatus +
                ", address=" + address +
                ", cart=" + shoppingCart +
                ", person=" + person +
                '}';
    }
}
