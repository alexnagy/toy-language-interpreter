package Model.State;

import java.util.HashMap;
import java.util.Map;

public class Heap<A, V> implements IHeap<A, V> {
    private Map<A, V> dict;

    public Heap() {
        this.dict = new HashMap<>();
    }

    @Override
    public void add(A key, V value) {
        this.dict.put(key, value);
    }

    @Override
    public void remove(A key, V value) {
        this.dict.remove(key, value);
    }

    @Override
    public void update(A key, V value) {
        this.dict.replace(key, value);
    }

    @Override
    public V getValue(A key) {
        return this.dict.get(key);
    }

    @Override
    public boolean isKey(A key) {
        return this.dict.containsKey(key);
    }

    @Override
    public boolean isValue(V value) {
        return this.dict.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.dict.size() == 0;
    }

    @Override
    public int getSize() {
        return this.dict.size();
    }

    @Override
    public Map<A, V> getContent() {
        return this.dict;
    }

    @Override
    public void setContent(Map<A, V> newHeap) {
        this.dict = newHeap;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder buff = new StringBuilder();
        for(A key: this.dict.keySet()) {
            buff.append(key.toString()).append("->").append(this.getValue(key).toString());
            buff.append("\r\n");
        }
        return buff.toString();
    }
}
