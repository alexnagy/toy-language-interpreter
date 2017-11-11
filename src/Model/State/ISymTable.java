package Model.State;

public interface ISymTable<String, Integer> {
    void add(String key, Integer value);
    void remove(String key, Integer value);
    void update(String key, Integer value);
    Integer getValue(String key);
    boolean isKey(String key);
    boolean isValue(Integer value);
    boolean isEmpty();
    java.lang.String toString();
}
