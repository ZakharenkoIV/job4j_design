package ru.job4j.srp;

public interface Drawing {
    public StringBuilder draw(String s);

    // FirstExampleOfViolation
    public void saveToFile(StringBuilder stringBuilder);
}


