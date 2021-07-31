package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int ch = 0;
        int del;
        Map<Integer, String> prevMap = new HashMap<>();
        for (User userMap : previous) {
            prevMap.put(userMap.getId(), userMap.getName());
        }
        for (User user : current) {
            if (!prevMap.containsKey(user.getId())) {
                add++;
            } else {
                if (!user.getName().equals(prevMap.remove(user.getId()))) {
                    ch++;
                }
            }
        }
        del = prevMap.size();
        return new Info(add, ch, del);
    }
}