package Model.Exceptions;

public class EmptyExecStackException extends Exception {
    public EmptyExecStackException() {
        super("ExecStack is empty!");
    }
}
