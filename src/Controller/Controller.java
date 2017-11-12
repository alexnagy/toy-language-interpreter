package Controller;


import Model.Exceptions.*;
import Model.State.IOut;
import Repository.IRepository;
import Model.State.PrgState;
import Model.Statements.IStmt;

import java.io.IOException;

public class Controller
{
    private IRepository repository;
    private boolean display;

    public Controller(IRepository _repository, boolean _display){

        this.repository = _repository;
        this.display = _display;
    }

    public PrgState executeOne() throws EmptyExecStackException, DivisionByZeroException, UnknownOperationException, IOException, FileNotDefinedException {
        PrgState prgState = repository.getCurrentProgramState();

        IStmt stmt;
        stmt = prgState.getExecStack().pop();
        stmt.execute(prgState);

        if(this.display) {
            System.out.println(prgState);
        }

        this.repository.addProgramState(prgState);
        return prgState;
    }

    public void executeAll() throws UnknownOperationException, EmptyExecStackException, DivisionByZeroException, IOException, FileNotDefinedException {
        PrgState prgState = repository.getCurrentProgramState();

        while(!prgState.getExecStack().isEmpty()) {
            this.executeOne();
            this.repository.logPrgStateExec();
        }

        IOut out = prgState.getOut();
        if(!out.toString().isEmpty()) {
            System.out.println(out);
        }
    }
}
