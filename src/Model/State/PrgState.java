package Model.State;

import Model.Statements.IStmt;

public class PrgState {
    private IExecStack<IStmt> execStack;
    private ISymTable<String, Integer> symTable;
    private IOut<Integer> out;

    public PrgState() {
        this.execStack = new ExecStack<>();
        this.symTable = new SymTable<>();
        this.out = new Out<>();
    }

    public PrgState(IExecStack<IStmt> execStack, ISymTable<String, Integer> symTable, IOut<Integer> out) {
        this.execStack = execStack;
        this.symTable = symTable;
        this.out = out;
    }

    public IExecStack<IStmt> getExecStack() {
        return this.execStack;
    }

    public ISymTable<String, Integer> getSymTable() {
        return this.symTable;
    }

    public IOut<Integer> getOut() {
        return this.out;
    }

    @Override
    public String toString() {
        return "ExecStack:\r\n" + this.execStack.toString() + "\r\n" + "SymTable:\r\n" + this.symTable.toString() + "\r\n" + "Out:\r\n" + this.out.toString();
    }
}
