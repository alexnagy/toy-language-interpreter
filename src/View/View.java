package View;

import Controller.Controller;
import Model.ExitCommand;
import Model.Expressions.*;
import Model.RunExample;
import Model.State.*;
import Model.Statements.*;
import Repository.IRepository;
import Repository.Repository;
import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {
    public String getLogFilePath() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input the log file path:");
        return keyboard.next();
    }

    public static void main(String args[]) {

        IStmt ex1 = new CompStmt(
                new AssignStmt("v", new ConstExpr(2)),
                new PrintStmt(new VarExpr("v")));

        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExpr('+',new ConstExpr(2),new
                ArithExpr('*',new ConstExpr(3), new ConstExpr(5)))),
                new CompStmt(new AssignStmt("b",new ArithExpr('+',new VarExpr("a"), new
                        ConstExpr(1))), new PrintStmt(new VarExpr("b"))));

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExpr('-',new ConstExpr(2), new
                ConstExpr(2))),
                new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)), new
                        AssignStmt("v", new ConstExpr(3))), new PrintStmt(new VarExpr("v"))));

        IStmt ex4 = new CompStmt(
                new OpenRFileStmt("var_f", "test.in"),
        new CompStmt(
                new ReadFileStmt(new VarExpr("var_f"), "var_c"),
                new CompStmt(
                        new PrintStmt(new VarExpr("var_c")),
                        new CompStmt(
                                new IfStmt(
                                        new VarExpr("var_c"),
                                        new CompStmt(
                                                new ReadFileStmt(new VarExpr("var_f"), "var_c"),
                                                new PrintStmt(new VarExpr("var_c"))
                                        ),
                                        new PrintStmt(new ConstExpr(0))
                                ),
                                new CloseRFileStmt(new VarExpr("var_f"))
                        )
                )
        ));

        IStmt ex5 = new CompStmt(
                new OpenRFileStmt("var_f", "test.in"),
                new CompStmt(
                        new ReadFileStmt(new ArithExpr('+', new VarExpr("var_f"), new ConstExpr(2)), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new ReadFileStmt(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(new VarExpr("var_c"))
                                                ),
                                                new PrintStmt(new ConstExpr(0))
                                        ),
                                        new CloseRFileStmt(new VarExpr("var_f"))
                                )
                        )
                )
        );

        IStmt ex6 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new HeapAllocStmt("v", new ConstExpr(20)),
                        new CompStmt(
                                new HeapAllocStmt("a", new ConstExpr(22)),
                                new CompStmt(
                                        new HeapWriteStmt("a", new ConstExpr(30)),
                                        new CompStmt(
                                                new PrintStmt(new VarExpr("a")),
                                                new CompStmt(
                                                        new PrintStmt(new HeapReadExpr("a")),
                                                        new AssignStmt("a", new ConstExpr(0)))))
                        )
                )
        );

        IStmt ex7 = new CompStmt(
                new AssignStmt("v", new ConstExpr(6)),
                new CompStmt(
                        new WhileStmt(new ArithExpr('-', new VarExpr("v"), new ConstExpr(4)),
                                new CompStmt(
                                        new PrintStmt(new VarExpr("v")),
                                        new AssignStmt("v", new ArithExpr('-', new VarExpr("v"), new ConstExpr(1)))
                                )),
                        new PrintStmt(new VarExpr("v")))
        );

        IStmt ex8 = new CompStmt(
                new OpenRFileStmt("var_f", "test.in"),
                new CompStmt(
                        new ReadFileStmt(new VarExpr("var_f"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new IfStmt(
                                        new VarExpr("var_c"),
                                        new CompStmt(
                                                new ReadFileStmt(new VarExpr("var_f"), "var_c"),
                                                new PrintStmt(new VarExpr("var_c"))
                                        ),
                                        new PrintStmt(new ConstExpr(0))
                                )
                        )
                ));

        IStmt ex9 = new CompStmt(
                new CompStmt(
                        new AssignStmt("v", new ConstExpr(10)),
                        new HeapAllocStmt("a", new ConstExpr(22))
                ),
                new CompStmt(
                        new ForkStmt(
                                new CompStmt(
                                        new HeapWriteStmt("a", new ConstExpr(30)),
                                        new CompStmt(
                                                new AssignStmt("v", new ConstExpr(32)),
                                                new CompStmt(
                                                        new PrintStmt(new VarExpr("v")),
                                                        new PrintStmt(new HeapReadExpr("a"))
                                                )
                                        )
                                )
                        ),
                        new CompStmt(
                                new PrintStmt(new VarExpr("v")),
                                new PrintStmt(new HeapReadExpr("a"))
                        )
                )
        );

        String logFilePath = new View().getLogFilePath();

        PrgState prg1 = new PrgState();
        prg1.getExecStack().push(ex1);
        IRepository repo1 = new Repository(prg1, logFilePath);
        Controller ctrl1 = new Controller(repo1, false);

        PrgState prg2 = new PrgState();
        prg2.getExecStack().push(ex2);
        IRepository repo2 = new Repository(prg2, logFilePath);
        Controller ctrl2 = new Controller(repo2, false);

        PrgState prg3 = new PrgState();
        prg3.getExecStack().push(ex3);
        IRepository repo3 = new Repository(prg3, logFilePath);
        Controller ctrl3 = new Controller(repo3, false);

        PrgState prg4 = new PrgState();
        prg4.getExecStack().push(ex4);
        IRepository repo4 = new Repository(prg4, logFilePath);
        Controller ctrl4 = new Controller(repo4, false);

        PrgState prg5 = new PrgState();
        prg5.getExecStack().push(ex5);
        IRepository repo5 = new Repository(prg5, logFilePath);
        Controller ctrl5 = new Controller(repo5, false);

        PrgState prg6 = new PrgState();
        prg6.getExecStack().push(ex6);
        IRepository repo6 = new Repository(prg6, logFilePath);
        Controller ctrl6 = new Controller(repo6, false);

        PrgState prg7 = new PrgState();
        prg7.getExecStack().push(ex7);
        IRepository repo7 = new Repository(prg7, logFilePath);
        Controller ctrl7 = new Controller(repo7, false);

        PrgState prg8 = new PrgState();
        prg8.getExecStack().push(ex8);
        IRepository repo8 = new Repository(prg8, logFilePath);
        Controller ctrl8 = new Controller(repo8, false);

        PrgState prg9 = new PrgState();
        prg9.getExecStack().push(ex9);
        IRepository repo9 = new Repository(prg9, logFilePath);
        Controller ctrl9 = new Controller(repo9, false);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2",ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3",ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4",ex4.toString(), ctrl4));
        menu.addCommand(new RunExample("5",ex5.toString(), ctrl5));
        menu.addCommand(new RunExample("6",ex6.toString(), ctrl6));
        menu.addCommand(new RunExample("7",ex7.toString(), ctrl7));
        menu.addCommand(new RunExample("8",ex8.toString(), ctrl8));
        menu.addCommand(new RunExample("9",ex9.toString(), ctrl9));

        menu.show();



    }
}