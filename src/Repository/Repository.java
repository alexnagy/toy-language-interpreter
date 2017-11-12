package Repository;

import Model.State.PrgState;

import java.io.*;
import java.util.Scanner;

public class Repository implements IRepository {
    private PrgState prgState;
    private String logFilePath;
    private boolean newLog = false;

    public Repository(String _logFilePath) {
        this.prgState = new PrgState();
        this.logFilePath = _logFilePath;
        this.newLog = true;
    }

    public Repository(PrgState _prgState, String _logFilePath) {
        this.prgState = _prgState;
        this.logFilePath = _logFilePath;
        this.newLog = true;
    }

    public void addProgramState(PrgState prgState) {
        this.prgState = prgState;
    }

    @Override
    public PrgState getCurrentProgramState() {
         return this.prgState;
    }

    @Override
    public void logPrgStateExec() throws IOException {
        if(this.newLog) {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath)));
            writer.print("");
            writer.close();
            this.newLog = false;
        }

        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        logFile.print(this.prgState);
        logFile.close();
    }
}
