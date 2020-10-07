package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private String stopWord = "стоп";
    private String continueWord = "продолжить";
    private String exitWord = "закончить";
    private boolean continueDialogue = true;

    public void work() {
        writeToLog("Запуск программы"
                + System.lineSeparator()
                + ZonedDateTime.now().toString()
                + System.lineSeparator()
                + "-------------------"
                + System.lineSeparator());
        Scanner in = new Scanner(System.in);
        String quotationEntered;
        do {
            quotationEntered = in.nextLine();
            writeToLog("Пользователь: " + quotationEntered + System.lineSeparator());
            if (!quotationEntered.equals(exitWord)) {
                if (quotationEntered.equals(stopWord)) {
                    continueDialogue = false;
                }
                if (quotationEntered.equals(continueWord)) {
                    continueDialogue = true;
                }
                if (continueDialogue) {
                    String randomResponse = getRandomResponse();
                    System.out.println("Бот: " + randomResponse);
                    writeToLog("Бот: " + randomResponse + System.lineSeparator());
                }
            }
        } while (!quotationEntered.equals(exitWord));
    }

    private void writeToLog(String string) {
        try {
            File consoleChatLog = new File("consoleChatLog.txt");
            consoleChatLog.createNewFile();
            Files.write(Paths.get("consoleChatLog.txt"),
                    string.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRandomResponse() {
        String result = null;
        try (BufferedReader in = new BufferedReader(new FileReader("consoleChatBotWords.txt"))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            result = lines.get((int) (Math.random() * lines.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.work();
    }
}
