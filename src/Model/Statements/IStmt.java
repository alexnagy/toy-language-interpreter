package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.States.PrgState;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException;
    String toString();
}
