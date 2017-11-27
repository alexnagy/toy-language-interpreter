package Model.Expressions;

import Model.State.IHeap;
import Model.State.ISymTable;

public class ConstExpr implements IExpr {
    private int number;

    public ConstExpr(int _number) {
        this.number = _number;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable,  IHeap<Integer, Integer> heap) {
        return this.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
