package Model.State;

public interface IFileTable<K, V> {
    void add(K key, V value);
    void remove(K key);
    boolean fileIdExists(K fileId);
    boolean fileNameExists(String fileName);
    public V getValue(K key);
    public int getSize();
}
