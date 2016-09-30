package ma.alexcarrilloar.android.chatapplication.chat;

import org.greenrobot.eventbus.Subscribe;

import ma.alexcarrilloar.android.chatapplication.chat.events.ChatEvent;
import ma.alexcarrilloar.android.chatapplication.chat.ui.ChatView;
import ma.alexcarrilloar.android.chatapplication.entities.User;
import ma.alexcarrilloar.android.chatapplication.lib.EventBus;
import ma.alexcarrilloar.android.chatapplication.lib.EventBusImpl;

/**
 * Created by alexcarrilloar on 9/26/16.
 */
public class ChatPresenterImpl implements  ChatPresenter{

    private ChatView view;
    private EventBus eventBus;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;


    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        eventBus = EventBusImpl.getInstance();
        chatInteractor = new ChatInteractorImpl();
        sessionInteractor = new ChatSessionInteractorImpl();
    }




    @Subscribe
    @Override
    public void onEventMainThread(ChatEvent event) {
        if(view !=null)
        {
            view.onMessageReceived(event.getMessage());
        }
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setChatRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }


}
