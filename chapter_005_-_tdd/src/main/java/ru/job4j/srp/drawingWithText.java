package ru.job4j.srp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class drawingWithText implements drawing {
    @Override
    public StringBuilder draw(String s) {
        // SecondExampleOfViolation
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(dateFormat.format(date)).append(s);
    }

    @Override
    public void saveToFile(StringBuilder stringBuilder) {
        //ThirdExampleOfViolation
        try (FileOutputStream fos = new FileOutputStream("C://SomeDir//picture.txt")) {
            byte[] buffer = stringBuilder.toString().getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
