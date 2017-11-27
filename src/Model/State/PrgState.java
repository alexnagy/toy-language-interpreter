package Model.State;

import Model.FileData;
import Model.Statements.IStmt;
import javafx.util.Pair;

import java.io.BufferedReader;

public class PrgState {
    private IExecStack<IStmt> execStack;
    private ISymTable<String, Integer> symTable;
    private IOut<Integer> out;
    private IFileTable<Integer, FileData> fileTable;
    private IHeap<Integer, Integer> heap;

    public PrgState() {
        this.execStack = new ExecStack<>();
        this.symTable = new SymTable<>();
        this.out = new Out<>();
        this.fileTable = new FileTable<>();
        this.heap = new Heap<>();
    }

    public PrgState(IExecStack<IStmt> _execStack, ISymTable<String, Integer> _symTable, IOut<Integer> _out, IFileTable<Integer, FileData> _fileTable, IHeap<Integer, Integer> _heap) {
        this.execStack = _execStack;
        this.symTable = _symTable;
        this.out = _out;
        this.fileTable = _fileTable;
        this.heap = _heap;
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

    @Override
    public String toString() {
        return "ExecStack:\r\n" + this.execStack.toString() + "\r\n" + "SymTable:\r\n" + this.symTable.toString() + "\r\n"
                + "FileTable:\r\n" + this.fileTable.toString() + "\r\n" + "Out:\r\n" + this.out.toString() + "\r\n"
                + "Heap:\r\n" + this.heap.toString() +"\r\n\r\n";
    }
}
