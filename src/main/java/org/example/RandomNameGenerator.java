package org.example;

import java.util.Random;

public class RandomNameGenerator {
    public RandomNameGenerator() {
    }

    private static final String[] FIRST_NAMES = {
            "Александр", "Дмитрий", "Александр", "Михаил", "Игорь", "Евгений", "Никита", "Фёдор", "Григорий",
            "Виктор", "Максим", "Виталий", "Иван", "Стас", "Олег", "Кирилл", "Денис", "Николай", "Юрий", "Андрей"
    };

    private static final String[] LAST_NAMES = {
            "Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Потапов", "Кибирев", "Матвейчук", "Игнатьев",
            "Попов", "Ковалев", "Новиков", "Морозов", "Соловьев", "Соколов", "Бабурганов", "Бродовский", "Мельников",
            "Савельев", "Созонов"
    };

    public static String getRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

}

