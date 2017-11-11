package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.State.IOut;
import Model.State.ISymTable;
import Model.State.PrgState;

public class PrintStmt implements IStmt {
    private Model.Expressions.IExpr IExpr;

    public PrintStmt(IExpr e) {
        this.IExpr = e;
    }

    @Override
    public PrgState execute (PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        IOut<Integer> out = currentState.getOut();
        ISymTable<String, Integer> SymTable = currentState.getSymTable();
        int res = this.IExpr.eval(SymTable);
        out.append(res);
        return currentState;
    }

    @Override
    public String toString() {
        return "Print(" + this.IExpr + ")" + "\n";
    }
}
