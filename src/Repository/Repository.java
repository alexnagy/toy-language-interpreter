package Repository;

import Model.State.PrgState;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository implements IRepository {
    private List<PrgState> prgList;
    private String logFilePath;
    private boolean newLog = false;

    public Repository(String _logFilePath) {
        this.prgList = new ArrayList<PrgState>();
        this.logFilePath = _logFilePath;
        this.newLog = true;
    }

    public Repository(PrgState _prgState, String _logFilePath) {
        this.prgList = new ArrayList<PrgState>();
        this.prgList.add(_prgState);
        this.logFilePath = _logFilePath;
        this.newLog = true;
    }

    public List<PrgState> getPrgList() {
        return this.prgList;
    }

    public void setPrgList(List<PrgState> newPrgList) {
        this.prgList = newPrgList;
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException {
        if(this.newLog) {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath)));
            writer.print("");
            writer.close();
            this.newLog = false;
        }

        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        logFile.print(prgState);
        logFile.close();
    }

    public void openLogFile() throws IOException {
        File file = new File(System.getProperty("user.dir").toString(), logFilePath);
        Desktop.getDesktop().open(file);
    }
}
