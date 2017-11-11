package Model.Expressions;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.State.ISymTable;

public interface IExpr {
    int eval(ISymTable<String, Integer> symTable) throws DivisionByZeroException, UnknownOperationException;
    String toString();
}
