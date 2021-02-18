package ru.job4j.ocp;

import java.util.ArrayList;

public class ViolationExample {

    //Реализация листа вместо интерфейса
    //создание экземпляра вместо получении от конструктора
    private ArrayList<Aeroplane> aeroplanesList = new ArrayList<>();

    //Возвращаемое значение не абстракция
    public AeroplaneMonoplane getNewAeroplane() {

        //Вместо интерфейса его реализация
        //Создание объекта вместо получения его через параметр
        AeroplaneMonoplane aeroplane = new AeroplaneMonoplane();
        aeroplanesList.add(aeroplane);
        return aeroplane;
    }
}

