package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private final Iterator<Iterator<Integer>> inputIt = it;
            private Iterator<Integer> positionIterator;
            private boolean validHasNext = isValidHasNext();

            @Override
            public boolean hasNext() {
                if (!positionIterator.hasNext()) {
                    validHasNext = isValidHasNext();
                }
                return validHasNext;
            }

            private boolean isValidHasNext() {
                boolean result = false;
                while (inputIt.hasNext()) {
                    positionIterator = inputIt.next();
                    if (positionIterator.hasNext()) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return positionIterator.next();
            }
        };
    }
}