package com.example.b07project;

import java.util.UUID;

public class AnnouncementIDGenerator {
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
