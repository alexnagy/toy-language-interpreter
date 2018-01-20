package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.States.IFileTable;
import Model.States.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private IExpr fileIdExpr;

    public CloseRFileStmt(IExpr _fileIdExpr) {
        this.fileIdExpr = _fileIdExpr;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        int fileId = fileIdExpr.eval(currentState.getSymTable(), currentState.getHeap());
        BufferedReader fileDescriptor = currentState.getFileTable().getValue(fileId).getFileDescriptor();
        fileDescriptor.close();

        IFileTable fileTable = currentState.getFileTable();
        fileTable.remove(fileId);

        return null;
    }

    @Override
    public String toString() {
        return "CloseRFile(" + this.fileIdExpr.toString() + ")";
    }
}
