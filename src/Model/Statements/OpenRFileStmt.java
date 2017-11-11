package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.State.IExecStack;
import Model.State.PrgState;

public class OpenRFileStmt implements IStmt {
    private int fileId;
    private String fileName;

    public OpenRFileStmt(int fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        IExecStack<IStmt> stack = currentState.getExecStack();
        return currentState;
    }
}
