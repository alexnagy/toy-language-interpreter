package Model.Expressions;

import Model.Exceptions.VariableNotDefinedException;
import Model.State.ISymTable;

public class VarExpr implements IExpr {
    private String id;

    public VarExpr(String _id) {
        this.id = _id;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable) throws VariableNotDefinedException {
        if (symTable.isKey(this.id))
            return symTable.getValue(this.id);
        else {
            throw new VariableNotDefinedException(this.id);
        }
    }

    @Override
    public String toString() {
        return this.id;
    }
}
