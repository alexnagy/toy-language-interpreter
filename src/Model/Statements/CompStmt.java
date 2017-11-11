package Model.Statements;

import Model.State.IExecStack;
import Model.State.PrgState;

import java.util.Scanner;

public class CompStmt implements IStmt {
    private IStmt first, second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState currentState) {
        IExecStack<IStmt> stack = currentState.getExecStack();
        stack.push(this.second);
        stack.push(this.first);
        return currentState;
    }

    @Override
    public String toString() {
        return "(" + this.first + ";" + this.second + ")\n";
    }
}
