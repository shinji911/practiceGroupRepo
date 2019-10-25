package com.awesome.motoinventory.model;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Motorcycle {
    // Properties
    private Integer id;
    @NotNull(message = "The price must not be null.")
    @Positive(message = "The price must be positive.")
    @Digits(integer = 5, fraction = 2, message = "The price is in an invalid format")
    private BigDecimal price;
    @NotNull(message = "The vin must not be null.")
    @Size(min = 1, max = 20, message = "The vin must be between 1 and 20 characters in length.")
    private String vin;
    @NotNull(message = "The make must not be null.")
    @Size(min = 1, max = 20, message = "The make must be between 1 and 20 characters in length.")
    private String make;
    @NotNull(message = "The model must not be null.")
    @Size(min = 1, max = 20, message = "The model must be between 1 and 20 characters in length.")
    private String model;
    @NotNull(message = "The year must not be null.")
    @Size(min = 4, max = 4, message = "The year must be in the format YYYY.")
    private String year;
    @NotNull(message = "The color must not be null.")
    @Size(min = 1, max = 20, message = "The color must be between 1 and 20 characters in length.")
    private String color;

    // Constructors
    public Motorcycle() {

    }

    public Motorcycle(BigDecimal price, String vin, String make, String model, String year, String color) {
        this.price = price;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public Motorcycle(Integer id, BigDecimal price, String vin, String make, String model, String year, String color) {
        this.id = id;
        this.price = price;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getVin() {
        return this.vin;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getYear() {
        return this.year;
    }

    public String getColor() {
        return this.color;
    }

    // Setters
    public void setId(Integer idIn) {
        this.id = idIn;
    }

    public void setPrice(BigDecimal priceIn) {
        this.price = priceIn;
    }

    public void setVin(String vinIn) {
        this.vin = vinIn;
    }

    public void setMake(String makeIn) {
        this.make = makeIn;
    }

    public void setModel(String modelIn) {
        this.model = modelIn;
    }

    public void setYear(String yearIn) {
        this.year = yearIn;
    }

    public void setColor(String colorIn) {
        this.color = colorIn;
    }

    // equals(), hashCode(), toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorcycle that = (Motorcycle) o;
        return Objects.equals(id, that.id) &&
                price.equals(that.price) &&
                vin.equals(that.vin) &&
                make.equals(that.make) &&
                model.equals(that.model) &&
                year.equals(that.year) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, vin, make, model, year, color);
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "id=" + id +
                ", price=" + price +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
