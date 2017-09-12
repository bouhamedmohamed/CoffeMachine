package domain;

public class EmailNotificationImplementation implements EmailNotification {
    @Override
    public void send(String drink) {
        System.out.println("Email sent to provide " + drink);
    }
}
