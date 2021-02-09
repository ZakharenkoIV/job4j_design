package ru.job4j.gc.ref.cache;

//         Создать структуру данных типа кеш. Кеш должен быть абстрактный.
//         То есть необходимо, что бы можно было задать ключ получения объекта кеша
//         и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyCache {
    private Map<String, SoftReference<TextValues>> cache = new HashMap<>();

    private Path fileDirectoryPath = Paths.get("chapter_004_-_jvm_and_jmm/src/main/resources");

    public MyCache() throws IOException {
        if (!Files.isDirectory(fileDirectoryPath)) {
            throw new IOException("No default directory");
        }
    }

    public MyCache(Path fileDirectoryPath) throws IOException {
        changeDirectory(fileDirectoryPath);
    }

    public void setFileDirectoryPath(Path fileDirectoryPath) throws IOException {
        changeDirectory(fileDirectoryPath);
    }

    public void changeDirectory(Path fileDirectoryPath) throws IOException {
        if (!Files.isDirectory(fileDirectoryPath)) {
            throw new IOException("No such directory");
        }
        this.fileDirectoryPath = fileDirectoryPath;
    }

    public String getText(String fileName) throws IOException {
        Path path = fileDirectoryPath.resolve(fileName);
        checkFileAvailability(path);
        return getTextFromCache(fileName, path);
    }

    private String getTextFromCache(String fileName, Path path) throws FileNotFoundException {
        TextValues textValues = null;
        if (cache.size() != 0) {
            textValues = cache.get(fileName).get();
        }
        if (!checkWhetherFileIsCached(textValues, path)) {
            textValues = loadFileTextInCache(fileName, path);
        }
        return textValues.getText();
    }

    private boolean checkWhetherFileIsCached(TextValues textValues, Path path) {
        checkForIncompletePair();
        return textValues != null
                && textValues.getText() != null
                && path.toFile().lastModified()
                == textValues.getLastModifiedTime();
    }

    private TextValues loadFileTextInCache(String fileName, Path path) throws FileNotFoundException {
        TextValues textValues = new TextValues(path.toFile().lastModified(), loadFileText(path));
        cache.put(fileName, new SoftReference<>(textValues));
        return textValues;
    }

    private String loadFileText(Path path) throws FileNotFoundException {
        String text = null;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (text == null) {
            throw new FileNotFoundException("Unable to read file");
        }
        return text;
    }

    private void checkFileAvailability(Path path) throws IOException {
        if (!Files.isReadable(path)) {
            throw new IOException("File does not exist or cannot be accessed");
        }
    }

    private void checkForIncompletePair() {
        for (String key : cache.keySet()) {
            if (cache.get(key) == null) {
                cache.remove(key);
            }
        }
    }

    class TextValues {
        long lastModifiedTime;
        String text;

        public TextValues(long lastModifiedTime, String text) {
            this.lastModifiedTime = lastModifiedTime;
            this.text = text;
        }

        public long getLastModifiedTime() {
            return lastModifiedTime;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TextValues that = (TextValues) o;
            return lastModifiedTime == that.lastModifiedTime &&
                    Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lastModifiedTime, text);
        }
    }

}
