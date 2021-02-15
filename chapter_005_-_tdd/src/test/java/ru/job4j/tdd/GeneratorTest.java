package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Test
    public void whenProduceWithEqualNumberOfKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator generator = new GeneratorText();
        String result = generator.produce(template, args);
        assertThat(result, is("I am a Petr Arsentev, Who are you?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWithMoreKeyOnMap() throws IllegalArgumentException {
        String template = "I am a ${name}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator generator = new GeneratorText();
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceWithMissingKeyOnMap() throws IllegalArgumentException {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        Generator generator = new GeneratorText();
        generator.produce(template, args);
    }
}
