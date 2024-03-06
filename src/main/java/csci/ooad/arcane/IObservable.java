package csci.ooad.arcane;

public interface IObservable {
    public void attach(IObserver observer);
    public void notifyObservers(EventType postedEventType, String postedEventDescription);
}
