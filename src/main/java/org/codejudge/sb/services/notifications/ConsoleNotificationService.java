package org.codejudge.sb.services.notifications;

import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyRefundInitiated(String message, String phone) {
        System.out.println("Notification: " + phone + ": " + message);
    }
}
