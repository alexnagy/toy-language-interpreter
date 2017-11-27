package Model.Expressions;

import Model.Exceptions.AddressNotDefinedException;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Exceptions.VariableNotDefinedException;
import Model.State.IHeap;
import Model.State.ISymTable;

public class HeapReadExpr implements IExpr {
    private String varName;

    public HeapReadExpr(String _varName) {
        this.varName = _varName;
    }

    @Override
    public int eval(ISymTable<String, Integer> symTable, IHeap<Integer, Integer> heap) throws DivisionByZeroException, UnknownOperationException, VariableNotDefinedException, AddressNotDefinedException {
        if(symTable.isKey(this.varName)) {
            int addr = symTable.getValue(this.varName);
            if(heap.isKey(addr)) {
                return heap.getValue(addr);
            }
            else {
                throw new AddressNotDefinedException(addr);
            }
        }
        else {
            throw new VariableNotDefinedException(this.varName);
        }
    }

    @Override
    public String toString() {
        return "rH(" + this.varName + ")";
    }
}
