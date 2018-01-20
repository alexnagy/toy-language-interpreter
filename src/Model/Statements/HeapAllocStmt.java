package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.ConstExpr;
import Model.Expressions.IExpr;
import Model.States.IHeap;
import Model.States.ISymTable;
import Model.States.PrgState;

import java.io.IOException;

public class HeapAllocStmt implements IStmt{
    private String varName;
    private IExpr expr;

    public HeapAllocStmt(String _varName, IExpr _expr) {
        this.varName = _varName;
        this.expr = _expr;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        IHeap heap = currentState.getHeap();
        ISymTable symTable = currentState.getSymTable();
        int addr = heap.getSize() + 1;
        int val = this.expr.eval(symTable, heap);
        heap.add(addr, val);
        IStmt assignStmt = new AssignStmt(this.varName, new ConstExpr(addr));
        assignStmt.execute(currentState);
        return null;
    }

    @Override
    public String toString() {
        return "new(" + this.varName + ", " + this.expr.toString() + ")";
    }
}
