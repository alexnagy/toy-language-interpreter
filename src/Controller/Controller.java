package Controller;


import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.EmptyExecStackException;
import Model.Exceptions.UnknownOperationException;
import Model.State.IOut;
import Repository.IRepository;
import Model.State.PrgState;
import Model.Statements.IStmt;

import java.io.IOException;

public class Controller
{
    private IRepository repository;
    private boolean display = false;

    public Controller(IRepository repository){

        this.repository = repository;
    }

    public PrgState executeOne() {
        PrgState prgState = repository.getCurrentProgramState();

        try {
            IStmt stmt = prgState.getExecStack().pop();
            stmt.execute(prgState);
        }
        catch(EmptyExecStackException | UnknownOperationException | DivisionByZeroException e) {
            System.out.println(e.toString());
        }

        if(this.display) {
            System.out.println(prgState);
        }

        this.repository.addProgramState(prgState);
        return prgState;
    }

    public void executeAll() throws IOException {
        PrgState prgState = repository.getCurrentProgramState();

        while(!prgState.getExecStack().isEmpty()) {
            PrgState tmpPrgState = this.executeOne();
            this.repository.logPrgStateExec();
        }

        IOut out = prgState.getOut();
        if(!out.toString().isEmpty()) {
            System.out.println(out);
        }
    }
}
