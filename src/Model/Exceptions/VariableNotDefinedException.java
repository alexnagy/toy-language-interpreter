package Model.Exceptions;

public class VariableNotDefinedException extends RuntimeException {
    public VariableNotDefinedException(String var) {
        super("Variable " + var + " is not defined in the SymTable");
    }
}
