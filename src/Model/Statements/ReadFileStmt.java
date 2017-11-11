package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.State.PrgState;

public class ReadFileStmt implements IStmt {
    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        return null;
    }
}
