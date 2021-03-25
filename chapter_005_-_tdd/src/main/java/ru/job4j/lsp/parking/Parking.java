package ru.job4j.lsp.parking;

public class Parking {
    private Car[] passCars;
    private Car[] trucks;
    private int truckCorrection;

    public Parking(int passCars, int trucks) {
        this.passCars = new Car[passCars];
        this.trucks = new Car[trucks];
        this.truckCorrection = passCars;
    }

    public int park(Car car) {
        int carSize = car.getSize();
        int freeSpace = getFreeSpace(carSize);
        if (freeSpace != 0 && car.getNumber().split("").length == 9) {
            addCar(car, freeSpace);
        } else {
            freeSpace = 0;
        }
        return freeSpace;
    }

    public Car dropCar(int parkingSpace) {
        Car car;
        if (parkingSpace <= truckCorrection) {
            car = getCar(passCars, parkingSpace);
        } else {
            car = getCar(trucks, parkingSpace - truckCorrection);
        }
        return car;
    }

    public Integer getFreeSpaceTruck() {
        return getFreeSpaces(trucks, 1) + getFreeSpaces(passCars, 4);
    }

    public Integer getFreeSpacePassengerCar() {
        return getFreeSpaces(passCars, 1);
    }

    private Car getCar(Car[] cars, int parkingSpace) {
        Car car = cars[parkingSpace - 1];
        clearSpaces(cars, parkingSpace, car);
        return car;
    }

    private void clearSpaces(Car[] cars, int parkingSpace, Car car) {
        for (int i = parkingSpace - 1; i < cars.length; i++) {
            if (cars[i].equals(car)) {
                cars[i] = null;
            } else {
                break;
            }
        }
    }

    private int getFreeSpaces(Car[] cars, int seatingCapacity) {
        int freeSpace = 0;
        int freeSpaces = 0;
        for (Car car : cars) {
            if (car == null) {
                freeSpaces++;
            } else {
                freeSpaces = 0;
            }
            if (freeSpaces == seatingCapacity) {
                freeSpace++;
                freeSpaces = 0;
            }
        }
        return freeSpace;
    }

    private int getFreeSpace(int carSize) {
        int freeSpace = 0;
        if (carSize == 4) {
            freeSpace = findFreeSpace(trucks, 1) + truckCorrection;
        }
        if (freeSpace == 0 || freeSpace == truckCorrection) {
            freeSpace = findFreeSpace(passCars, carSize);
        }
        return Math.max(freeSpace, 0);
    }

    private int findFreeSpace(Car[] cars, int carSize) {
        int spaceNumber = 0;
        int freeSpaces = 0;
        while (spaceNumber <= cars.length - carSize) {
            for (int sequencePlaces = 1; sequencePlaces <= carSize; sequencePlaces++) {
                if (cars[spaceNumber] == null) {
                    spaceNumber++;
                    freeSpaces++;
                } else {
                    spaceNumber++;
                    freeSpaces = 0;
                }
                if (freeSpaces == carSize) {
                    break;
                }
            }
            if (freeSpaces == carSize) {
                break;
            }
        }
        if (freeSpaces == 0) {
            spaceNumber = 0;
        }
        return spaceNumber - carSize + 1;
    }

    private void addCar(Car car, int freeSpace) {
        if (freeSpace > truckCorrection) {
            freeSpace = freeSpace - truckCorrection;
            takesPlaces(car, freeSpace, trucks, 1);
        } else {
            takesPlaces(car, freeSpace, passCars, car.getSize());
        }
    }

    private void takesPlaces(Car car, int freeSpace, Car[] cars, int seatingCapacity) {
        for (int i = 0; i < seatingCapacity; i++) {
            cars[freeSpace - 1 + i] = car;
        }
    }
}
