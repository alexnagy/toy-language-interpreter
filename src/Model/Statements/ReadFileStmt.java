package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.FileNotDefinedException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.States.ISymTable;
import Model.States.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    private IExpr fileIdExpr;
    private String varName;

    public ReadFileStmt(IExpr _fileIdExpr, String _varName) {
        this.fileIdExpr = _fileIdExpr;
        this.varName = _varName;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException, FileNotDefinedException {
        int fileId = this.fileIdExpr.eval(currentState.getSymTable(), currentState.getHeap());
        if(!currentState.getFileTable().fileIdExists(fileId))
            throw new FileNotDefinedException(fileId);
        BufferedReader fileDescriptor = currentState.getFileTable().getValue(fileId).getFileDescriptor();
        String line = fileDescriptor.readLine();
        int lineValue;
        if(line == null) {
            lineValue =0;
        }
        else {
            lineValue = Integer.parseInt(line);
        }
        ISymTable symTable = currentState.getSymTable();
        if(symTable.isKey(this.varName)) {
            symTable.update(this.varName, lineValue);
        }
        else {
            currentState.getSymTable().add(this.varName, lineValue);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ReadFile(" + this.fileIdExpr.toString() + "," + this.varName + ")";
    }
}
