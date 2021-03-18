package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    //парковка машины (неуд) без намеров.
    @Test
    public void whenCarHasNotValidNumberThenCarParkingFailed() {
        Parking parking = new Parking(2, 1, 2, 1);
        Car car = new Passenger_car("х111хх100");
        assertThat(parking.addCar(car), is(false));
    }

    //парковка легковой машины (уд).
    @Test
    public void whenPassengerCarSpaceIsFreeThenPassengerCarParkingSuccessful() {
        Parking parking = new Parking(2, 1, 2, 1);
        Car car = new Passenger_car("х111хх100");
        assertThat(parking.addCar(car), is(true));
    }

    //парковка легковой машины (неуд) нет легковых мест.
    @Test
    public void whenPassengerCarSpaceIsNoThenPassengerCarParkingFailed() {
        Parking parking = new Parking(1, 1, 2, 1);
        parking.addCar(new Passenger_car("х111хх100"));
        Car car = new Passenger_car("х222хх200");
        assertThat(parking.addCar(car), is(false));
    }

    //парковка грузовой машины (уд)
    @Test
    public void whenTruckSpaceIsFreeThenTruckParkingSuccessful() {
        Parking parking = new Parking(2, 1, 2, 1);
        Car car = new Truck("y111yy100");
        assertThat(parking.addCar(car), is(true));
    }

    //парковка грузовой машины (уд) нет груз.мест но есть легк.места.
    @Test
    public void whenCombinedPassengerCarSpaceIsFreeThenTruckParkingSuccessful() {
        Parking parking = new Parking(4, 1, 1, 1);
        parking.addCar(new Truck("y111yy100"));
        Car car = new Truck("y222yy200");
        assertThat(parking.addCar(car), is(true));
    }

    //парковка грузовой машины (неуд) нет мест.
    @Test
    public void whenPossibleSpaceIsNoThenTruckParkingFailed() {
        Parking parking = new Parking(3, 1, 1, 1);
        parking.addCar(new Truck("y111yy100"));
        Car car = new Truck("y222yy200");
        assertThat(parking.addCar(car), is(false));
    }

    //показ сводобных грузовых мест, включая объединённые легковые места.
    @Test
    public void whenThereAreParkingSpaceForTruckThenNumberOfFreeTruckSeats() {
        Parking parking = new Parking(8, 1, 2, 1);
        parking.addCar(new Passenger_car("х111хх100"));
        parking.addCar(new Truck("y111yy100"));
        assertThat(parking.getFreeSpaseTruck, is(2));
    }

    //показ свободных легковых мест.
    @Test
    public void whenThereAreParkingSpaceForPassengerCarThenNumberOfFreeCarSeats() {
        Parking parking = new Parking(8, 1, 2, 1);
        parking.addCar(new Passenger_car("х111хх100"));
        parking.addCar(new Truck("y111yy100"));
        assertThat(parking.getFreeSpasePassengerCar, is(7));
    }
}
