package Model.Expressions;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Exceptions.VariableNotDefinedException;
import Model.State.ISymTable;

public interface IExpr {
    int eval(ISymTable<String, Integer> symTable) throws DivisionByZeroException, UnknownOperationException, VariableNotDefinedException;
    String toString();
}
