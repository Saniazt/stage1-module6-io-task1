package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {
    public static void main(String[] args) {
        Profile profile = getDataFromFile(new File("src/main/resources/Profile.txt"));
        System.err.print(profile);
    }

    public static Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        FileInputStream fileInputStream = null;
        StringBuilder text = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                text.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] splitText = text.toString().split(System.lineSeparator());

        String[][] arrayText = new String[splitText.length][];
        for (int i = 0; i < splitText.length; i++) {
            arrayText[i] = splitText[i].split(" ");
        }

        profile.setName(arrayText[0][1]);
        int age = Integer.parseInt(arrayText[1][1]);
        long phone = Long.parseLong(arrayText[3][1]);
        profile.setAge(age);
        profile.setEmail(arrayText[2][1]);
        profile.setPhone(phone);
        return new Profile(profile.getName(), profile.getAge(), profile.getEmail(), profile.getPhone());
    }
}
