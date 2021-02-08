package ru.job4j.gc.ref.cache;

//         Создать структуру данных типа кеш. Кеш должен быть абстрактный.
//         То есть необходимо, что бы можно было задать ключ получения объекта кеша
//         и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MyCache {
    private Map<String, SoftReference<String>> cache = new HashMap<>();
    private Path fileDirectoryPath = Paths.get("chapter_004_-_jvm_and_jmm/src/main/resources");

    public MyCache() {
    }

    public MyCache(Path fileDirectoryPath) {
        this.fileDirectoryPath = fileDirectoryPath;
    }

    public Optional<String> getText(String fileName) {
        Path path = fileDirectoryPath.resolve(fileName);
        Optional<String> text = Optional.empty();
        if (!checkWhetherFileIsCached(fileName) && checkFileForAvailability(path)) {
            loadFileTextInCache(fileName, path);
        }
        if (checkWhetherFileIsCached(fileName)) {
            text = Optional.ofNullable(cache.get(fileName).get());
        }
        checkForIncompletePair();
        return text;
    }

    private boolean checkFileForAvailability(Path path) {
        return Files.isReadable(path);
    }

    private boolean checkWhetherFileIsCached(String fileName) {
        return cache.containsKey(fileName) && cache.get(fileName) != null;
    }

    private void loadFileTextInCache(String fileName, Path path) {
        try {
            cache.put(fileName, new SoftReference<>(new String(Files.readAllBytes(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForIncompletePair() {
        for (String key : cache.keySet()) {
            if (cache.get(key) == null) {
                cache.remove(key);
            }
        }
    }
}
