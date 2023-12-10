package com.example.android77.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {

    private String id; // Unique identifier for the photo
    private String filePath; // The file path or identifier for the photo
    private List<Tag> tags; // List of tags associated with the photo

    // Constructor
    public Photo(String id, String filePath) {
        this.id = id;
        this.filePath = filePath;
        this.tags = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    // Add a tag to the photo
    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    // Remove a tag from the photo
    public boolean removeTag(Tag tag) {
        return tags.remove(tag);
    }

    // Additional methods as needed

    // Implement toString for easy debugging
    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", filePath='" + filePath + '\'' +
                ", tags=" + tags +
                '}';
    }
}
