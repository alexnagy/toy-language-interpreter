package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.States.IExecStack;
import Model.States.ISymTable;
import Model.States.PrgState;

public class AssignStmt implements IStmt {
    private String id;
    private IExpr expr;

    public AssignStmt(String _id, IExpr _expr) {
        this.id = _id;
        this.expr = _expr;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        IExecStack<IStmt> stack = currentState.getExecStack();
        ISymTable<String, Integer> symTable = currentState.getSymTable();
        int result = this.expr.eval(symTable, currentState.getHeap());
        if (symTable.isKey(id)) {
            symTable.update(id, result);
        }
        else {
            symTable.add(id, result);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.id + '=' + this.expr;
    }
}
