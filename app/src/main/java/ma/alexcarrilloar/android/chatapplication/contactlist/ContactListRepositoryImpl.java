package ma.alexcarrilloar.android.chatapplication.contactlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import ma.alexcarrilloar.android.chatapplication.contactlist.events.ContactListEvent;
import ma.alexcarrilloar.android.chatapplication.domain.FirebaseHelper;
import ma.alexcarrilloar.android.chatapplication.entities.User;
import ma.alexcarrilloar.android.chatapplication.lib.EventBus;
import ma.alexcarrilloar.android.chatapplication.lib.EventBusImpl;

/**
 * Created by alexcarrilloar on 9/24/16.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;
    private EventBus eventBus;

    public ContactListRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = EventBusImpl.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void changeConnectionStatus(Boolean online) {
        try
        {
            helper.changeUserConnectionStatus(online);

        }catch(Exception ex)
        {

        }

    }

    @Override
    public void subscribeToContactListEvents() {
        if(contactEventListener ==null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot,ContactListEvent.onContactAdded);
                }



                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot,ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot,ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
    }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);

    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type,user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);

    }

    @Override
    public void unsubscribeToContactListEvents() {
        if(contactEventListener !=null)
        {
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactsReference(currentUserEmail,email).removeValue();
        helper.getOneContactsReference(email,currentUserEmail).removeValue();

    }
}
