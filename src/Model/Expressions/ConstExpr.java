package Model.Expressions;

import Model.States.IHeap;
import Model.States.ISymTable;

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
