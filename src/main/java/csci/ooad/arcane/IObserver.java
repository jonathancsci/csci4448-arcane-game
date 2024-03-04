package csci.ooad.arcane;

public interface IObserver {
    void update(EventType eventType, String eventDescription);
}
