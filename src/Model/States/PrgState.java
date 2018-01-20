package Model.States;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.EmptyExecStackException;
import Model.Exceptions.FileNotDefinedException;
import Model.Exceptions.UnknownOperationException;
import Model.FileData;
import Model.Statements.IStmt;

import java.io.IOException;

public class PrgState {
    private IExecStack<IStmt> execStack;
    private ISymTable<String, Integer> symTable;
    private IOut<Integer> out;
    private IFileTable<Integer, FileData> fileTable;
    private IHeap<Integer, Integer> heap;
    private int id;

    public PrgState() {
        this.execStack = new ExecStack<>();
        this.symTable = new SymTable<>();
        this.out = new Out<>();
        this.fileTable = new FileTable<>();
        this.heap = new Heap<>();
        this.id = 1;
    }

    public PrgState(IExecStack<IStmt> _execStack, ISymTable<String, Integer> _symTable, IOut<Integer> _out, IFileTable<Integer, FileData> _fileTable, IHeap<Integer, Integer> _heap, int _id) {
        this.execStack = _execStack;
        this.symTable = _symTable;
        this.out = _out;
        this.fileTable = _fileTable;
        this.heap = _heap;
        this.id = _id;
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

    public IFileTable<Integer, FileData> getFileTable() {
        return this.fileTable;
    }

    public IHeap<Integer, Integer> getHeap() {
        return this.heap;
    }

    public int getId() {
        return this.id;
    }

    public boolean isNotCompleted() {
        return !this.execStack.isEmpty();
    }

    public PrgState executeOne() throws EmptyExecStackException, DivisionByZeroException, UnknownOperationException, IOException, FileNotDefinedException {
        IStmt stmt = this.execStack.pop();
        return stmt.execute(this);
    }

    @Override
    public String toString() {
        return "ID: " + String.valueOf(this.id) + "\r\n" + "ExecStack:\r\n" + this.execStack.toString() + "\r\n"
                + "SymTable:\r\n" + this.symTable.toString() + "\r\n"
                + "FileTable:\r\n" + this.fileTable.toString() + "\r\n" + "Out:\r\n" + this.out.toString() + "\r\n"
                + "Heap:\r\n" + this.heap.toString() +"\r\n\r\n";
    }
}
