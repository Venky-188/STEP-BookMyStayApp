package uc7;

/**
 * Represents an Add-On Service
 */
public class Service {

    private String name;
    private int price;

    public Service(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
