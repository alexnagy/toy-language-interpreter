package Model.States;

import java.util.List;

public interface IOut<T> {
    T getLast();
    void append(T elem);
    void remove(int index);
    void update(int index, T elem);
    boolean isEmpy();
    List<T> getContent();
    String toString();
}
