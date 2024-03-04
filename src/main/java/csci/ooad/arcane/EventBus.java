package csci.ooad.arcane;

public class EventBus {
    private static EventBus eventBus;
    private EventBus() {

    }
    public static EventBus getInstance() {
        if(eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }
    public void attach(IObserver observer, EventType eventType) {

    }
    public void postMessage(EventType eventType, String eventDescription) {

    }
}
