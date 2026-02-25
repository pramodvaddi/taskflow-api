package com.pramodvaddiraju.taskflow.email;

public interface EmailService {
    void sendTaskCreatedEmail(String to, String title);
    void sendTaskCompletedEmail(String to, String title);


}
