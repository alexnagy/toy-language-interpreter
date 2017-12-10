package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.VariableNotDefinedException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.IExpr;
import Model.Expressions.VarExpr;
import Model.State.ISymTable;
import Model.State.PrgState;

import java.io.IOException;

public class IfStmt implements IStmt {
    private IExpr ifS;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(VarExpr _ifS, IStmt _thenS, IStmt _elseS) {
        this.ifS = _ifS;
        this.thenS = _thenS;
        this.elseS = _elseS;
    }

    @Override
    public PrgState execute(PrgState currentState) throws DivisionByZeroException, UnknownOperationException, IOException {
        ISymTable<String, Integer> symTable = currentState.getSymTable();
        try {
            int result = this.ifS.eval(symTable, currentState.getHeap());
            if(result != 0) {
                this.thenS.execute(currentState);
            }
            else {
                this.elseS.execute(currentState);
            }

        }
        catch (DivisionByZeroException | UnknownOperationException | VariableNotDefinedException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String toString() {
        return "IF(" + this.ifS + ")THEN(" + this.thenS + ")ELSE(" + this.elseS;
    }
}
