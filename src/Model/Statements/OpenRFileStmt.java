package Model.Statements;

import Model.FileData;
import Model.State.IExecStack;
import Model.State.IFileTable;
import Model.State.ISymTable;
import Model.State.PrgState;


import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    private String varFileId;
    private String fileName;

    public OpenRFileStmt(String _varFileId, String _fileName) {
        this.varFileId = _varFileId;
        this.fileName = _fileName;
    }

    @Override
    public PrgState execute(PrgState currentState) throws KeyAlreadyExistsException, FileNotFoundException {
        IFileTable<Integer, FileData> fileTable = currentState.getFileTable();
        if(fileTable.fileNameExists(this.fileName))
            throw new KeyAlreadyExistsException();
        int fileId = fileTable.getSize() + 1;
        BufferedReader fileDescriptor = new BufferedReader(new FileReader(this.fileName));
        FileData fileData = new FileData(this.fileName, fileDescriptor);
        fileTable.add(fileId, fileData);
        ISymTable symTable = currentState.getSymTable();

        if (symTable.isKey(this.varFileId)) {
            symTable.update(this.varFileId, fileId);
        }
        else {
            symTable.add(this.varFileId, fileId);
        }
        return currentState;
    }

    @Override
    public String toString() {
        return "OpenRFile(" + this.varFileId + "," + this.fileName + ")";
    }
}
