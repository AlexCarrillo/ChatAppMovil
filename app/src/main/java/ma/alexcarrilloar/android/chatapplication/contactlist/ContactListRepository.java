package ma.alexcarrilloar.android.chatapplication.contactlist;

/**
 * Created by alexcarrilloar on 9/24/16.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(Boolean online);


    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void destroyListener();
    void removeContact(String email);
}
