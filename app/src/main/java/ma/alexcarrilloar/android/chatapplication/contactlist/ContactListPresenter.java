package ma.alexcarrilloar.android.chatapplication.contactlist;

import ma.alexcarrilloar.android.chatapplication.contactlist.events.ContactListEvent;

/**
 * Created by alexcarrilloar on 9/24/16.
 */
public interface ContactListPresenter {

    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();

    void signOff();
    void removeContact(String email);
    String getCurrentUserEmail();
    void onEventMainThread(ContactListEvent event);

}
