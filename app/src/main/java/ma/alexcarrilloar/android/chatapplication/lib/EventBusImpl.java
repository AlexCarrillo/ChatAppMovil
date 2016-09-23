package ma.alexcarrilloar.android.chatapplication.lib;

/**
 * Created by alexcarrilloar on 9/18/16.
 */
public class EventBusImpl implements EventBus {
    private org.greenrobot.eventbus.EventBus eventBus;

    public EventBusImpl() {
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    public static class SingletoneHolder {
        private static final EventBusImpl INSTANCE = new EventBusImpl();
    }

    public static EventBusImpl getInstance(){
        return SingletoneHolder.INSTANCE;
    }

    @Override
    public void register(Object suscriber) {
        eventBus.register(suscriber);
    }

    @Override
    public void unregister(Object suscriber) {
        eventBus.unregister(suscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
