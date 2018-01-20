package Model.States;

import Model.Exceptions.EmptyExecStackException;

public interface IExecStack<T> {
    void push(T e);
    T pop() throws EmptyExecStackException;
    boolean isEmpty();
    Object clone() throws CloneNotSupportedException;
}
