package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.State.IOut;
import Model.State.ISymTable;
import Model.State.PrgState;

public class PrintStmt implements IStmt {
    private IExpr IExpr;

    public PrintStmt(IExpr _expr) {
        this.IExpr = _expr;
    }

    @Override
    public PrgState execute (PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        IOut<Integer> out = currentState.getOut();
        ISymTable<String, Integer> symTable = currentState.getSymTable();
        int res = this.IExpr.eval(symTable, currentState.getHeap());
        out.append(res);
        return currentState;
    }

    @Override
    public String toString() {
        return "Print(" + this.IExpr + ")";
    }
}
