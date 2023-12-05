package com.example.b07project;

import java.util.Random;

public class NotificationType {

    private static final int ANNOUNCEMENT_RANGE_START = 0;
    private static final int ANNOUNCEMENT_RANGE_END = 99999;
    private static final int EVENT_RANGE_START = 100000;
    private static final int EVENT_RANGE_END = 199999;

    public static int generateRandomAnnouncementId() {
        return new Random().nextInt(ANNOUNCEMENT_RANGE_END - ANNOUNCEMENT_RANGE_START + 1)
                + ANNOUNCEMENT_RANGE_START;
    }

    public static int generateRandomEventId() {
        return new Random().nextInt(EVENT_RANGE_END - EVENT_RANGE_START + 1)
                + EVENT_RANGE_START;
    }

    public static String checkNotificationType(int notificationId) {
        if (notificationId >= ANNOUNCEMENT_RANGE_START && notificationId <= ANNOUNCEMENT_RANGE_END) {
            return "Announcement";
        } else if (notificationId >= EVENT_RANGE_START && notificationId <= EVENT_RANGE_END) {
            return "Event";
        } else {
            return "MISCNotification";
        }
    }
}
