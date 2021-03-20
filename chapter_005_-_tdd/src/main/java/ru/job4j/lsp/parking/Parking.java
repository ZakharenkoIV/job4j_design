package ru.job4j.lsp.parking;

public class Parking {
    private PassengerCar[] passengerCars;
    private Truck[] trucks;

    public Parking(int passCars, int trucks) {
        this.passengerCars = new PassengerCar[passCars];
        this.trucks = new Truck[trucks];
    }

    public int addCar(Car car) {
        return 0;
    }

    public Car dropCar(int parkingSpace) {
        return new PassengerCar(0, "");
    }

    public Integer getFreeSpaceTruck() {
        return 0;
    }

    public Integer getFreeSpacePassengerCar() {
        return 0;
    }
}
