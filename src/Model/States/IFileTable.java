package Model.States;

import java.util.Map;

public interface IFileTable<K, V> {
    void add(K key, V value);
    void remove(K key);
    boolean fileIdExists(K fileId);
    boolean fileNameExists(String fileName);
    public V getValue(K key);
    public int getSize();
    void setContent(Map<K, V> newFileTable);
    Map<K, V> getContent();
    Iterable<Map.Entry<K, V>> getAll();
}
