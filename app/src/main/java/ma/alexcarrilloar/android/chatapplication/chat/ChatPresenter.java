package ma.alexcarrilloar.android.chatapplication.chat;

import ma.alexcarrilloar.android.chatapplication.chat.events.ChatEvent;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent typeEvent);
}
