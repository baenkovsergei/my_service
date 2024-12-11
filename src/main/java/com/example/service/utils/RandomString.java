package com.example.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomString {

    public static String str = "abcdefghijklmnopqrstuvwxyz";
    public static Random random = new Random();

    public static String getRandomWord(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString().toUpperCase();
    }

    public static String getRandomComment(int words, int length) {
        StringBuilder comments = new StringBuilder();
        for (int i = 0; i < words; i++) {
            comments.append(getRandomWord(length)+ " ");
        }
        return comments.toString();
    }

    public static List<Integer> getRandListInteger(int size, int max) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        int min = 1;
        for (int i = 0; i < size; i++) {
            int randomNum = random.nextInt(max - min + 1) + min; // Генерация числа в диапазоне [min, max]
            randomNumbers.add(randomNum);
        }
        return randomNumbers;
    }


}
