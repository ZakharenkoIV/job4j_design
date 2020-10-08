package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 28;
        short sing = 355;
        int id = 741852963;
        long phoneNumber = 89991010101L;
        float iq = 96.6F;
        double account = 65651.58;
        boolean isLive = true;
        char luckyChar = 'J';
        LOG.debug("Object info age: {}, sing: {}, id: {}, phoneNumber: {}, "
                        + "iq: {}, account: {}, isLive: {}, luckyChar: {} ",
                age, sing, id, phoneNumber, iq, account, isLive, luckyChar);
    }
}