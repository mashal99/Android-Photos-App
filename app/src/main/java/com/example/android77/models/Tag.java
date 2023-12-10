package com.example.android77.models;

import java.io.Serializable;

public class Tag implements Serializable {

    private String type; // Type of the tag (e.g., "person", "location")
    private String value; // Value of the tag (e.g., "John Doe", "Paris")

    // Constructor
    public Tag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Implement toString for easy debugging
    @Override
    public String toString() {
        return "Tag{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    // Override equals and hashCode for proper functioning in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (!type.equals(tag.type)) return false;
        return value.equals(tag.value);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
