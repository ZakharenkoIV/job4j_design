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
    private final List<String> botText = new ArrayList<>();
    private final List<String> logText = new ArrayList<>();

    ConsoleChat() {
        try (BufferedReader in = new BufferedReader(new FileReader("consoleChatBotWords.txt"))) {
            in.lines().forEach(botText::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void work() {
        logText.add("-------------------"
                + System.lineSeparator()
                + "Запуск программы"
                + System.lineSeparator()
                + ZonedDateTime.now().toString()
                + System.lineSeparator());
        Scanner in = new Scanner(System.in);
        String quotationEntered;
        do {
            quotationEntered = in.nextLine();
            logText.add("Пользователь: " + quotationEntered);
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
                    logText.add("Бот: " + randomResponse);
                }
            }
        } while (!quotationEntered.equals(exitWord));
        writeToLog(logText);
    }

    private void writeToLog(List<String> logText) {
        try {
            File consoleChatLog = new File("consoleChatLog.txt");
            consoleChatLog.createNewFile();
            Files.write(Paths.get("consoleChatLog.txt"),
                    logText, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRandomResponse() {
        return botText.get((int) (Math.random() * botText.size()));
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.work();
    }
}
