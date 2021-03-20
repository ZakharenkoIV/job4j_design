package ru.job4j.lsp.parking;

import java.util.Objects;

public class PassengerCar implements Car {
    private int size;
    private String number;

    public PassengerCar(int size, String number) {
        this.size = size;
        this.number = number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return size == that.size && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number);
    }
}
