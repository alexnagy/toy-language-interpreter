package Repository;

import Model.State.PrgState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrgList);
    void logPrgStateExec(PrgState prgState) throws IOException;
    void openLogFile() throws IOException;
}
