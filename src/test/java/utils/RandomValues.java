package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomValues {
    Faker ruFaker = new Faker(new Locale("ru"));

    public String getRandomGameOfThronePerson() {
        return ruFaker.gameOfThrones().character();
    }
    public String getRandomUserFirstName() {
        return ruFaker.name().firstName();
    }

    public String getRandomUserLastName() {
        return ruFaker.name().lastName();
    }
}
