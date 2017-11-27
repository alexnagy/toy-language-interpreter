package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.State.IExecStack;
import Model.State.IHeap;
import Model.State.ISymTable;
import Model.State.PrgState;

import java.io.IOException;

public class WhileStmt implements IStmt {
    private IExpr expr;
    private IStmt stmt;

    public WhileStmt(IExpr _expr, IStmt _stmt) {
        this.expr = _expr;
        this.stmt = _stmt;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        ISymTable symTable = currentState.getSymTable();
        IHeap heap = currentState.getHeap();
        IExecStack execStack = currentState.getExecStack();
        int res = this.expr.eval(symTable, heap);
        if(res != 0) {
            execStack.push(this);
            execStack.push(this.stmt);
        }
        return currentState;
    }

    @Override
    public String toString() {
        return "While(" + this.expr.toString() + ") " + this.stmt.toString();
    }
}
