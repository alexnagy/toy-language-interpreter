package Controller;


import Model.Exceptions.*;
import Model.FileData;
import Model.State.IOut;
import Model.Statements.CloseRFileStmt;
import Repository.IRepository;
import Model.State.PrgState;
import Model.Statements.IStmt;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller
{
    private IRepository repository;
    private boolean display;

    public Controller(IRepository _repository, boolean _display){

        this.repository = _repository;
        this.display = _display;
    }

    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void closeOpenedFiles(Map<Integer, FileData> fileTable) throws IOException {
        fileTable.forEach((key, value) -> {
            try {
                value.getFileDescriptor().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
            prgState.getHeap().setContent(this.conservativeGarbageCollector(
                    prgState.getSymTable().getContent().values(), prgState.getHeap().getContent()
            ));
            this.repository.logPrgStateExec();
        }

        this.closeOpenedFiles(prgState.getFileTable().getContent());

        IOut out = prgState.getOut();
        if(!out.toString().isEmpty()) {
            System.out.println(out);
        }
    }
}
