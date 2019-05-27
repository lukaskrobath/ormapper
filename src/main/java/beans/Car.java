package beans;

import annotations.Entity;

@Entity
public class Car {
    private String brand;
    private String licensePlate;
    private int horsePower;
    private Double price;

    public Car(String brand, String licensePlate, int horsePower, Double price) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.horsePower = horsePower;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %d hp (%1.2f)",brand,licensePlate,horsePower,price);
    }
}
