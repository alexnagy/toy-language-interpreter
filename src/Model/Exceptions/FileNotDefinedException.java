package Model.Exceptions;

public class FileNotDefinedException extends RuntimeException {
    public FileNotDefinedException(int fileId) {
        super("File with ID " + String.valueOf(fileId) + " is not defined in the FileTable");
    }
}