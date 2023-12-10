package com.example.android77.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    private String name; // Album name
    private List<Photo> photos; // List of photos in the album

    // Constructor
    public Album(String name) {
        this.name = name;
        this.photos = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    // Add a photo to the album
    public void addPhoto(Photo photo) {
        if (!photos.contains(photo)) {
            photos.add(photo);
        }
    }

    // Remove a photo from the album
    public boolean removePhoto(Photo photo) {
        return photos.remove(photo);
    }

    // Find a photo in the album (based on some identifier, e.g., filename)
    public Photo findPhoto(String identifier) {
        for (Photo photo : photos) {
            if (photo.getId().equals(identifier)) {
                return photo;
            }
        }
        return null; // Photo not found
    }

    // Additional methods as needed, such as for displaying album info, etc.

    // Implement toString for easy debugging and display
    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", photos=" + photos +
                '}';
    }
}
