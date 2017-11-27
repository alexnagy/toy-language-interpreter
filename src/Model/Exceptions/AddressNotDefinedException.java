package Model.Exceptions;

public class AddressNotDefinedException extends RuntimeException {
    public AddressNotDefinedException(int addr) {
        super("Address " + String.valueOf(addr) + " is not defined in the Heap");
    }
}
