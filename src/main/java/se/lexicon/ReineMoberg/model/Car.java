package se.lexicon.ReineMoberg.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {

    private String regNumber;
    private String brand;
    private String model;
    private LocalDate registrationDate;

    public Car() {
    }

    public Car(String regNumber, String brand, String model, LocalDate registrationDate) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.registrationDate = registrationDate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
