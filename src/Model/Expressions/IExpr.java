package Model.Expressions;

import Model.Exceptions.AddressNotDefinedException;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Exceptions.VariableNotDefinedException;
import Model.States.IHeap;
import Model.States.ISymTable;

public interface IExpr {
    int eval(ISymTable<String, Integer> symTable, IHeap<Integer, Integer> heap) throws DivisionByZeroException, UnknownOperationException, VariableNotDefinedException, AddressNotDefinedException;
    String toString();
}
