package csci.ooad.arcane;

public interface IObservable {
    public void attach(IObserver observer);
    public void detach(IObserver observer);
    public void notifyObservers();
}
