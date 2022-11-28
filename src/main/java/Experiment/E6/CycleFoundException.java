package Experiment.E6;

public class CycleFoundException extends Exception {
    public CycleFoundException() {
        super("Cycle Found!");
    }
}
