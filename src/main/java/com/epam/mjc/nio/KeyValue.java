package com.epam.mjc.nio;

public class KeyValue {
    String key;
    String value;

    public KeyValue(String line) {
        String[] splitted = line.split(": ");
        this.key = splitted[0];
        this.value = splitted[1];
    }
}