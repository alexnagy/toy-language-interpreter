package Model.Expressions;

import Model.Exceptions.AddressNotDefinedException;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Exceptions.VariableNotDefinedException;
import Model.States.IHeap;
import Model.States.ISymTable;

public class BoolExpr implements IExpr {
    private String op;
    private IExpr left, right;

    public BoolExpr(String _op, IExpr _left, IExpr _right) {
        this.op = _op;
        this.left = _left;
        this.right = _right;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable, IHeap<Integer, Integer> heap) throws DivisionByZeroException, UnknownOperationException, VariableNotDefinedException, AddressNotDefinedException {
        int left = 0, right = 0;

        left = this.left.eval(symTable, heap);
        right = this.right.eval(symTable, heap);

        switch (this.op) {
            case "<": {
                if(left < right)
                    return 1;
                return 0;
            }
            case "<=": {
                if(left <= right)
                    return 1;
                return 0;
            }
            case "==": {
                if(left == right)
                    return 1;
                return 0;
            }
            case "!=": {
                if(left != right)
                    return 1;
                return 0;
            }
            case ">" : {
                if(left > right)
                    return 1;
                return 0;
            }
            case ">=": {
                if(left >= right)
                    return 1;
                return 0;
            }
            default: {
                throw new UnknownOperationException(this.op);
            }
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + this.op + this.right.toString();
    }
}
