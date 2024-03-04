package csci.ooad.arcane;

import java.io.IOException;
import java.util.List;

public class AudibleArcaneObserver implements IObserver {
    private IObservable arcaneGame;
    private List<EventType> interestedEvents;
    private Integer delayInSeconds = 3;

    public void AudibleArcaneObserver(IObservable arcaneGame) {
        this.arcaneGame = arcaneGame;
    }
    public void AudibleArcaneObserver(IObservable arcaneGame, List<EventType> eventTypeList, Integer delayInSeconds) {
        this.arcaneGame = arcaneGame;
        this.interestedEvents = eventTypeList;
        this.delayInSeconds = delayInSeconds;
    }
    public void AudibleArcaneObserver(IObservable arcaneGame, Integer delayInSeconds) {
        this.arcaneGame = arcaneGame;
        this.interestedEvents = List.of(EventType.All);
        this.delayInSeconds = delayInSeconds;
    }

    public void update(EventType eventType, String eventDescription) {
        if (this.interestedEvents.contains(eventType) ||
                        this.interestedEvents.contains(EventType.All)) {
            try {
                String[] cmd = {"say", eventDescription};
                Runtime.getRuntime().exec(cmd);
                Thread.sleep(delayInSeconds * 1000);
            } catch (IOException e) {
                throw new RuntimeException("Error in executing the say command", e);
            } catch (InterruptedException e) {
                throw new RuntimeException("Error in Thread.sleep()", e);
            }
        }
    }
}
