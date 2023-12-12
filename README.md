# Android Photo Album App

## Overview

This Android app, tailored specifically for the Android platform. Designed to run on the Nexus 4 emulator, it allows users to manage photo albums and search for photos by tags.


## Development Environment

- **IDE**: Android Studio only
- **Language**: Java
- **Compile SDK**: 34
- **Target SDK**: 34
- **Min SDK**: Android 10.0 (Q) - API level 29

## Emulator Configuration

- **Device**: Nexus 4 (4.7 inch, 768x1280, xhdpi)
- **SDK Version**: API 34

## Features

- **Home Screen**: Load and display album/photo data.
- **Album Management**: Open, create, delete, and rename albums.
- **Photo Management**: Add, remove, display photos, and run a slideshow.
- **Tagging**: Add and delete 'person' or 'location' tags on photos.
- **Photo Movement**: Move a photo from one album to another.
- **Search**: Search for photos by tag-value pairs with auto-completion.

## Data Serialization

In this app, data serialization plays a vital role in persisting the state of user-created albums and photos between sessions. Here's how it's used:

### Photo Serialization

Photos are serialized by storing references to their file paths within the device's storage rather than the image data itself. This approach keeps the serialized data small and avoids duplicating the image files already stored in the device's gallery.

### Album Serialization

Each album is serialized with its metadata, including the album name and the list of serialized photos it contains. This metadata is saved in a simple text-based format or as a serialized object, depending on the chosen implementation strategy.

### Serialization Format

For the purposes of this project, serialization is handled using simple text files with a custom format or using Java's built-in serialization capabilities.

### Implementation Details

1. **Photo References**: Each photo's URI is serialized to a file corresponding to its album.
2. **Album Data**: Albums are serialized to a master file that records their names and the count of photos they contain.
3. **Tag Serialization**: Tags associated with photos are serialized alongside the photo references within the album file.

### Example

When a user adds a photo to an album, the app does the following:

1. Stores the photo's URI in the album's file.
2. If tags are added to the photo, they are serialized as strings following the photo's URI.
3. When the app launches, it deserializes the album and photo data, reconstructing the album and photo objects along with their tags.

### Benefits of Using Serialization for Photos

- **Efficiency**: Only references to images are stored, which is space-efficient.
- **State Preservation**: The state of the app, including user-created albums and tags, is preserved across different sessions.
- **Simplicity**: Using built-in serialization methods makes the code easier to write and maintain.

### Handling Image Data

- The actual image files are not serialized; only references to them are.
- Upon deserialization, the app checks if the image file still exists at the URI. If not, the photo object is considered invalid and handled accordingly.

### Search Functionality and Serialization

- Serialized data allows for quick searching by iterating over the stored tag values and matching them with user input.
- Auto-completion for search is implemented by partial matching against the serialized tag data.

Remember to handle the serialization and deserialization processes carefully to ensure that your app's performance remains optimal and that all user data is preserved accurately between sessions.
