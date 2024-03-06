package csci.ooad.arcane;

import java.util.ArrayList;
import java.util.List;

public class EventBus implements IObservable {
    private static EventBus eventBus;
    private List<IObserver> observerList = new ArrayList<>();
    private EventBus() {

    }
    public static EventBus getInstance() {
        if(eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }
    public void attach(IObserver observer) {
        this.observerList.add(observer);
    }
    public void notifyObservers(EventType postedEventType, String postedEventDescription) {
        for (IObserver observer : this.observerList) {
            observer.update(postedEventType, postedEventDescription);
        }
    }
}
