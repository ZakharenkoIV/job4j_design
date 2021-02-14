package ru.job4j.examination;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms1G", "-Xmx1G"})
public class BenchmarkArticleTest {
    public static String s =
            "Мой дядя самых честных правил, "
                    + "Когда не в шутку занемог, "
                    + "Он уважать себя заставил "
                    + "И лучше выдумать не мог. "
                    + "Его пример другим наука; "
                    + "Но, боже мой, какая скука "
                    + "С больным сидеть и день и ночь, "
                    + "Не отходя ни шагу прочь! "
                    + "Какое низкое коварство "
                    + "Полуживого забавлять, "
                    + "Ему подушки поправлять, "
                    + "Печально подносить лекарство, "
                    + "Вздыхать и думать про себя: "
                    + "Когда же черт возьмет тебя!";
    public static String ss = "Мой дядя мог думать про Linux и Java день и ночь";

    @Benchmark
    public void usingArrays() {
        Article.generateUsingArrays(s, ss);
    }

    @Benchmark
    public void usingLambda() {
        Article.generateUsingLambda(s, ss);
    }

    @Benchmark
    public void usingMap() {
        Article.generateUsingMap(s, ss);
    }

    @Benchmark
    public void usingSet() {
        Article.generateUsingSet(s, ss);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkArticleTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}