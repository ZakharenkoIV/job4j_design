package ru.job4j.dip;

import java.util.ArrayList;

public class ViolationExample {

    //1 нарушение DIP. Не выделена абстракция у реализации машины.
    class Bmw {
    }

    //2 нарушение DIP. Поле типа конкретной реализации.
    //Создание объекта конкретной реализации обнуляет преемущества абстракции поля list.
    // Нужно не создавать, а получать извне потомка абстракции.
    private ArrayList<Bmw> list = new ArrayList();

    //3 нарушение DIP. Возвращаемое значение не абстракция.
    public Bmw getItem() {
        return new Bmw();
    }
}
