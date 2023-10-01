package com.epam.mjc.nio;

import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;


public class FileReader {
    static Logger log = Logger.getLogger(FileReader.class.getName());
    public Profile getDataFromFile(File file) {
        InputStream targetStream = null;
        try{
            targetStream = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            log.info(e.toString());
        }

        byte[] bytes = new byte[0];
        try {
            bytes = targetStream.readAllBytes();
        } catch (IOException e) {
            log.info(e.toString());
        } finally {
            try{
                targetStream.close();
            } catch (IOException e) {
                log.info(e.toString());
            }
        }

        String st = new String(bytes, StandardCharsets.UTF_8);

        String name = "";
        Integer age = null;
        String email = "";
        Long phone = null;
        String[] lines = st.split("\\r?\\n", -1);

        for (int i = 0; i < lines.length - 1; i++) {
            KeyValue keyValue = new KeyValue(lines[i]);
            if(keyValue.key.equals("Name")) {
                name = keyValue.value;
            } else if (keyValue.key.equals("Age")) {
                age = Integer.parseInt(keyValue.value);
            } else if (keyValue.key.equals("Email")) {
                email = keyValue.value;
            } else if (keyValue.key.equals("Phone")) {
                phone = Long.parseLong(keyValue.value);
            }
        }
        return new Profile(name, age, email, phone);
    }
}
