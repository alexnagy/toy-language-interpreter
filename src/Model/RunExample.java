package Model;

import Controller.Controller;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.EmptyExecStackException;
import Model.Exceptions.FileNotDefinedException;
import Model.Exceptions.UnknownOperationException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String _key, String _desc, Controller _ctrl){
        super(_key, _desc);
        this.ctrl = _ctrl;
    }
    @Override
    public void execute() {
        try {
            this.ctrl.executeAll();
        } catch ( FileNotDefinedException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

