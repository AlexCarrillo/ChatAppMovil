package ma.alexcarrilloar.android.chatapplication.contactlist.events;

import ma.alexcarrilloar.android.chatapplication.entities.User;

/**
 * Created by alexcarrilloar on 9/24/16.
 */
public class ContactListEvent {
    public final static int onContactAdded = 0;
    public final static int onContactChanged = 1;
    public final static int onContactRemoved =2;


    private User user;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int eventType;
}
