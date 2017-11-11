package Model.Expressions;

import Model.Exceptions.NotDefinedException;
import Model.State.ISymTable;

public class VarExpr implements IExpr {
    private String id;

    public VarExpr(String id) {
        this.id = id;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable) throws NotDefinedException {
        if (symTable.isKey(this.id))
            return symTable.getValue(this.id);
        else {
            throw new NotDefinedException("Not found!");
        }
    }

    @Override
    public String toString() {
        return this.id;
    }
}
