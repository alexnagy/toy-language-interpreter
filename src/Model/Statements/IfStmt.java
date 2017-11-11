package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.NotDefinedException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.Expressions.VarExpr;
import Model.State.ISymTable;
import Model.State.PrgState;

public class IfStmt implements IStmt {
    private IExpr ifS;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(VarExpr IExpr, IStmt thenS, IStmt elseS) {
        this.ifS = IExpr;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException {
        ISymTable<String, Integer> symTable = currentState.getSymTable();
        try {
            int result = this.ifS.eval(symTable);
            if(result != 0) {
                this.thenS.execute(currentState);
            }
            else {
                this.elseS.execute(currentState);
            }

        }
        catch (DivisionByZeroException | UnknownOperationException | NotDefinedException e) {
            System.out.println(e.toString());
        }
        return currentState;
    }

    public String toString() {
        return "IF(" + this.ifS + ") THEN(" + this.thenS + ")ELSE(" + this.elseS + ")";
    }
}
