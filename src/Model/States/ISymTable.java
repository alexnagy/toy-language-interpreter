package Model.States;

import java.util.Map;

public interface ISymTable<String, Integer> {
    void add(String key, Integer value);
    void remove(String key, Integer value);
    void update(String key, Integer value);
    Integer getValue(String key);
    boolean isKey(String key);
    boolean isValue(Integer value);
    boolean isEmpty();
    Map<String, Integer> getContent();
    void setContent(Map<String, Integer> newSymTable);
    java.lang.String toString();
}
