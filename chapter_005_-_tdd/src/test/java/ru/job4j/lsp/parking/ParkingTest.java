package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    //парковка машины (неуд) без намеров.
    @Test
    public void whenCarHasNotValidNumberThenCarParkingFailed() {
        Parking parking = new Parking(2, 2);
        Car car = new PassengerCar(1, "х111хх100");
        assertThat(parking.addCar(car), is(0));
    }

    //парковка легковой машины (уд).
    @Test
    public void whenPassengerCarSpaceIsFreeThenPassengerCarParkingSuccessful() {
        Parking parking = new Parking(2, 2);
        Car car = new PassengerCar(1, "х111хх100");
        assertThat(parking.addCar(car), is(1));
    }

    //парковка легковой машины (неуд) нет легковых мест.
    @Test
    public void whenPassengerCarSpaceIsNoThenPassengerCarParkingFailed() {
        Parking parking = new Parking(1, 2);
        parking.addCar(new PassengerCar(1, "х111хх100"));
        Car car = new PassengerCar(1, "х222хх200");
        assertThat(parking.addCar(car), is(0));
    }

    //парковка грузовой машины (уд)
    @Test
    public void whenTruckSpaceIsFreeThenTruckParkingSuccessful() {
        Parking parking = new Parking(2, 2);
        Car car = new Truck(4, "y111yy100");
        assertThat(parking.addCar(car), is(3));
    }

    //парковка грузовой машины (уд) нет груз.мест но есть легк.места.
    @Test
    public void whenCombinedPassengerCarSpaceIsFreeThenTruckParkingSuccessful() {
        Parking parking = new Parking(4, 1);
        parking.addCar(new Truck(4, "y111yy100"));
        Car car = new Truck(4, "y222yy200");
        assertThat(parking.addCar(car), is(1));
    }

    //парковка грузовой машины (неуд) нет мест.
    @Test
    public void whenPossibleSpaceIsNoThenTruckParkingFailed() {
        Parking parking = new Parking(3, 1);
        parking.addCar(new Truck(4, "y111yy100"));
        Car car = new Truck(4, "y222yy200");
        assertThat(parking.addCar(car), is(0));
    }

    //показ сводобных грузовых мест, включая объединённые легковые места.
    @Test
    public void whenThereAreParkingSpaceForTruckThenNumberOfFreeTruckSeats() {
        Parking parking = new Parking(8, 2);
        parking.addCar(new PassengerCar(1, "х111хх100"));
        parking.addCar(new Truck(4, "y111yy100"));
        assertThat(parking.getFreeSpaceTruck(), is(8));
    }

    //показ свободных легковых мест.
    @Test
    public void whenThereAreParkingSpaceForPassengerCarThenNumberOfFreeCarSeats() {
        Parking parking = new Parking(8, 2);
        parking.addCar(new PassengerCar(1, "х111хх100"));
        parking.addCar(new Truck(4, "y111yy100"));
        assertThat(parking.getFreeSpacePassengerCar(), is(7));
    }

    @Test
    public void whenDropCarThenPlaceIsFree() {
        Parking parking = new Parking(1, 2);
        Car car = new PassengerCar(1, "х111хх100");
        parking.addCar(car);
        assertThat(parking.dropCar(1), is(car));
    }
}
