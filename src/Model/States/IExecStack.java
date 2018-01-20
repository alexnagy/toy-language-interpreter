package Model.States;

import Model.Exceptions.EmptyExecStackException;

import java.util.Stack;

public interface IExecStack<T> {
    void push(T e);
    T pop() throws EmptyExecStackException;
    public T peek() throws EmptyExecStackException;
    boolean isEmpty();
    Object clone() throws CloneNotSupportedException;
    Stack getStack();
}
