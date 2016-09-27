package ma.alexcarrilloar.android.chatapplication.chat;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setChatRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();

    void changeConnectionStatus(boolean online);
}
