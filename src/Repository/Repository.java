package Repository;

import Model.State.PrgState;

import java.io.*;
import java.util.Scanner;

public class Repository implements IRepository {
    private PrgState prgState;
    private String logFilePath;

    public Repository() {
        prgState = new PrgState();
        logFilePath = new String();
        readLogFilePath();
    }

    public void addProgramState(PrgState prgState) {
        this.prgState = prgState;
    }

    @Override
    public PrgState getCurrentProgramState() {
         return this.prgState;
    }


    @Override
    public void readLogFilePath() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input the log file path:");
        this.logFilePath = keyboard.next();
    }

    @Override
    public void logPrgStateExec() throws IOException {
        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
        logFile.print(this.prgState.getOut());
        logFile.close();
    }
}
