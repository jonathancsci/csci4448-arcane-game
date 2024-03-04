package csci.ooad.arcane;

import java.io.IOException;

public interface IObserver {
    void update(EventType eventType, String eventDescription);
}
