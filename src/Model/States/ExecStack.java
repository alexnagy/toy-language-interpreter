package Model.States;

import Model.Exceptions.EmptyExecStackException;

import java.util.Stack;

public class ExecStack<T> implements IExecStack<T> {
    private Stack<T> stack;

    public ExecStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public void push(T e) {
        this.stack.push(e);
    }

    public T pop() throws EmptyExecStackException {
        if(this.stack.empty()) {
            throw new EmptyExecStackException();
        }
        return this.stack.pop();
    }

    public boolean isEmpty() {
        return this.stack.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for(T elem : this.stack) {
            buffer.append(elem);
            buffer.append("\r\n");
        }

        return buffer.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
