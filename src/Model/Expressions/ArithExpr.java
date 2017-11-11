package Model.Expressions;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.State.ISymTable;

public class ArithExpr implements IExpr {
    private char op;
    private IExpr left, right;

    public ArithExpr(char op, IExpr left, IExpr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable) throws DivisionByZeroException, UnknownOperationException {
        int left = 0, right = 0;

        left = this.left.eval(symTable);
        right = this.right.eval(symTable);

        switch (this.op) {
            case '+': {
                return left + right;
            }
            case '-': {
                return left - right;
            }
            case '*': {
                return left * right;
            }
            case '/': {
                if (right == 0) {
                    throw new DivisionByZeroException();
                }
                return left / right;
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
