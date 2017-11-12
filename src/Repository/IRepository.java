package Repository;

import Model.State.PrgState;

import java.io.IOException;

public interface IRepository {
    void addProgramState(PrgState prgState);
    PrgState getCurrentProgramState();
    void logPrgStateExec() throws IOException;
}
