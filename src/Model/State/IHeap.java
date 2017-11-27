package Model.State;

import java.util.Map;

public interface IHeap<A, V> {
    void add(A key, V value);
    void remove(A key, V value);
    void update(A key, V value);
    V getValue(A key);
    boolean isKey(A key);
    boolean isValue(V value);
    boolean isEmpty();
    int getSize();
    Map<A, V> getContent();
    void setContent(Map<A, V> newHeap);
    public String toString();
}
