package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.State.PrgState;

public interface IStmt {
    PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException;
    String toString();
}
