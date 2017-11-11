package Model.Expressions;

import Model.State.ISymTable;

public class ConstExpr implements IExpr {
    private int number;

    public ConstExpr(int number) {
        this.number = number;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable) {
        return this.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
