package ru.job4j.gc.ref.cache;

//        Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы из системы
//        и выдавать текст при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные.
//        По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории. Пример.
//        Names.txt, Address.txt - файлы в системе. При запросе по ключу Names.txt - кеш должен
//        вернуть содержимое файла Names.txt.

import java.io.FileNotFoundException;

public class TextFileReader {
    private MyCache cache = new MyCache();

    public String getText(String fileName) throws FileNotFoundException {
        return cache.getText(fileName).orElseThrow(FileNotFoundException::new);
    }

    public static void main(String[] args) throws FileNotFoundException {
        TextFileReader reader = new TextFileReader();
        String s = reader.getText("Loggc.txt");
        System.out.println(s);
    }
}

