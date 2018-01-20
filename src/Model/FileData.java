package Model;

import java.io.BufferedReader;

public class FileData {
    private String fileName;
    private BufferedReader fileDescriptor;

    public FileData(String _fileName, BufferedReader _fileDescriptor){
        this.fileName = _fileName;
        this.fileDescriptor = _fileDescriptor;
    }

    public String getFileName() {
        return this.fileName;
    }

    public BufferedReader getFileDescriptor() {
        return this.fileDescriptor;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
