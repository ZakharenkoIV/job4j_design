package ru.job4j.isp;

public class ViolationExample {
    //1 пример.
    interface Plane {
        int refuel();

        boolean fly();

        boolean shoot();
    }

    //2 пример.
    interface Teapot {
        boolean boil();

        int showDegrees();
    }

    //3 пример.
    interface Postcard {
        boolean open();

        boolean play();
    }

}
