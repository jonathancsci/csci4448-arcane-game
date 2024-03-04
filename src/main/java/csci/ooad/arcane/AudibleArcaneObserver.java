package csci.ooad.arcane;

public class AudibleArcaneObserver extends IObserver {
    private IObservable arcaneGame;
    private Integer delayInSeconds = 5;

    public void AudibleArcaneObserver(IObservable` arcaneGame) {
        this.arcaneGame = arcaneGame;
    }
    public void AudibleArcaneObserver(IObservable arcaneGame, Integer delayInSeconds) {
        this.arcaneGame = arcaneGame;
        this.delayInSeconds = delayInSeconds;
    }

    public void update(EventType eventDescription) {
        String[] cmd = {"say", eventDescription};
        Runtime.getRuntime().exec(cmd);
    }
}
