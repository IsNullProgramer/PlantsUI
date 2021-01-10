package com.example.plantsdictionary.data.models;

import java.util.List;

/**
 * Семейство
 */
public class FamilyPlant {
    private String name;

    public FamilyPlant(String name, List<Plants> plants) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
