package ru.khannanovayrat.model;

/**
 * Created by Ayrat on 06.12.2016.
 */
public class UserMessage {

    private int userId;
    private int chatId;
    private int lastMessageId;

    public UserMessage() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(int lastMessageId) {
        this.lastMessageId = lastMessageId;
    }
}
