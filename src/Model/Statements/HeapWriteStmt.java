package Model.Statements;

import Model.Exceptions.AddressNotDefinedException;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Exceptions.VariableNotDefinedException;
import Model.Expressions.IExpr;
import Model.State.IHeap;
import Model.State.ISymTable;
import Model.State.PrgState;

import java.io.IOException;

public class HeapWriteStmt implements IStmt {
    private String varName;
    private IExpr expr;

    public HeapWriteStmt(String _varName, IExpr _expr) {
        this.varName = _varName;
        this.expr = _expr;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        ISymTable symTable = currentState.getSymTable();
        IHeap heap = currentState.getHeap();

        if(symTable.isKey(this.varName)) {
            int addr = (int) symTable.getValue(this.varName);
            if(heap.isKey(addr)) {
                int var = this.expr.eval(symTable, heap);
                heap.update(addr, var);
            }
            else {
                throw new AddressNotDefinedException(addr);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "wH(" + this.varName + ", " + this.expr.toString() + ")";
    }
}
