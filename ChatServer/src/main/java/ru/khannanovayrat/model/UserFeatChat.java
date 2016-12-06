package ru.khannanovayrat.model;

/**
 * Created by Ayrat on 06.12.2016.
 */
public class UserFeatChat {

    private int chatId;
    private int userId;

    public UserFeatChat() {
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
