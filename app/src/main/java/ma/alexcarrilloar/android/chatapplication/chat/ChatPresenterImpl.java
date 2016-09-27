package ma.alexcarrilloar.android.chatapplication.chat;

import org.greenrobot.eventbus.Subscribe;

import ma.alexcarrilloar.android.chatapplication.chat.events.ChatEvent;
import ma.alexcarrilloar.android.chatapplication.chat.ui.ChatActivity;
import ma.alexcarrilloar.android.chatapplication.chat.ui.ChatView;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public class ChatPresenterImpl implements  ChatPresenter{

    private ChatView view;
    public ChatPresenterImpl(ChatView view) {
        this.view = view;
    }




    @Subscribe
    @Override
    public void onEventMainThread(ChatEvent typeEvent) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setChatRecipient(String recipient) {

    }

    @Override
    public void sendMessage(String msg) {

    }


}
