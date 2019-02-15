package etu.uportal.domain;

import java.util.HashMap;
import java.util.Map;

public class Role {

    public static Map<Integer, String> getMap() {
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "Читатель");
        data.put(2, "Редактор");
        data.put(3, "Администратор");
        return data;
    }
}
