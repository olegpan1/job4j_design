package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserWithoutEqualsHashCode {

    private final static Calendar BIRTHDAY = new GregorianCalendar(1990, Calendar.DECEMBER, 10);

    public static void main(String[] args) {
        Map<User, Object> usersMap = new HashMap<>();
        User user1 = new User("Ivan", 2, BIRTHDAY);
        User user2 = new User("Ivan", 2, BIRTHDAY);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

        System.out.println(usersMap.put(user1, 1));
        System.out.println(usersMap.put(user2, 2));

        usersMap.forEach((key, value) -> System.out.println("Key : " + key + "  Value : " + value));
    }
}
