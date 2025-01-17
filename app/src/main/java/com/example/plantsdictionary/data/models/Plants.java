package com.example.plantsdictionary.data.models;

/**
 * Растение
 */
public class Plants {

    private int id;
    private String name;
    private String location;
    private String description;
    private String family;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getImage() {
        return image;
    }

}
