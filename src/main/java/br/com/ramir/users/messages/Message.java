package br.com.ramir.users.messages;

public class Message {
    private String senderEmail;
    private String [] recipients;
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
