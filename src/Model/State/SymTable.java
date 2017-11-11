package Model.State;

import java.util.HashMap;
import java.util.Map;

public class SymTable<String, Integer> implements ISymTable<String, Integer> {

    private Map<String, Integer> dict;

    public SymTable() {
        this.dict = new HashMap<>();
    }

    public SymTable(Map<String, Integer> dict) {
        this.dict = dict;
    }

    @Override
    public void add(String key, Integer value) {
        this.dict.put(key, value);
    }

    @Override
    public void remove(String key, Integer value) {
        this.dict.remove(key, value);
    }

    @Override
    public void update(String key, Integer value) {
        this.dict.replace(key, value);
    }

    @Override
    public Integer getValue(String key) {
        return this.dict.get(key);
    }

    @Override
    public boolean isKey(String key) {
        return this.dict.containsKey(key);
    }

    @Override
    public boolean isValue(Integer value) {
        return this.dict.containsValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.dict.size() == 0;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder buff = new StringBuilder();
        for(String key: this.dict.keySet()) {
            buff.append(key + "->" + this.getValue(key).toString());
            buff.append("\n");
        }
        return buff.toString();
    }
}
