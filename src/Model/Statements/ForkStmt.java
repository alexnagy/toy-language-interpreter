package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.States.*;

import java.io.IOException;

public class ForkStmt implements IStmt {
    private IStmt stmt;

    public ForkStmt(IStmt _stmt) {
        this.stmt = _stmt;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        IExecStack<IStmt> newExecStack = new ExecStack<IStmt>();
        newExecStack.push(this.stmt);
        ISymTable newSymTable = new SymTable(currentState.getSymTable().getContent());
        PrgState newPrgState = new PrgState(newExecStack, newSymTable, currentState.getOut(),
                currentState.getFileTable(), currentState.getHeap(),
                currentState.getId() * 10);
        return newPrgState;
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }
}
