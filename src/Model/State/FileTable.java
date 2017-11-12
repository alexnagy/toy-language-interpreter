package Model.State;

import Model.FileData;
import java.util.HashMap;
import java.util.Map;

public class FileTable<K, V> implements IFileTable<K, V> {
    private Map<K, V> dict;

    public FileTable() {
        dict = new HashMap<>();
    }

    public void add(K key, V value) {
        this.dict.put(key, value);
    }

    public void remove(K key) {
        this.dict.remove(key);
    }

    @Override
    public boolean fileIdExists(K fileId) {
        return this.dict.containsKey(fileId);
    }

    public boolean fileNameExists(String fileName) {
        for(K key: this.dict.keySet()) {
            FileData tuple = (FileData) this.dict.get(key);
            if(tuple.getFileName() == fileName)
                return true;
        }
        return false;
    }

    public V getValue(K key) {
        return this.dict.get(key);
    }

    public int getSize() {
        return this.dict.size();
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        for(K key: this.dict.keySet()) {
            buff.append(key).append("->").append(this.getValue(key).toString());
            buff.append("\r\n");
        }
        return buff.toString();
    }
}
