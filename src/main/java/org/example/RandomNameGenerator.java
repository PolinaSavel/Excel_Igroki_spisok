package org.example;

import java.util.Random;

public class RandomNameGenerator {
    public RandomNameGenerator() {
    }

    private static final String[] FIRST_NAMES = {
            "���������", "�������", "���������", "������", "�����", "�������", "������", "Ը���", "��������",
            "������", "������", "�������", "����", "����", "����", "������", "�����", "�������", "����", "������"
    };

    private static final String[] LAST_NAMES = {
            "������", "������", "�������", "��������", "�������", "�������", "�������", "���������", "��������",
            "�����", "�������", "�������", "�������", "��������", "�������", "����������", "����������", "���������",
            "��������", "�������"
    };

    public static String getRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

}

