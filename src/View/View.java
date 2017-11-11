package View;

import Controller.Controller;
import Model.Expressions.ArithExpr;
import Model.Expressions.ConstExpr;
import Model.Expressions.VarExpr;
import Model.State.*;
import Model.Statements.*;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOException;
import java.util.Scanner;

public class View {
    private int selectProgram() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please select one of the following programs:");
        System.out.println("1. v=2;Print(v)");
        System.out.println("2. a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. a=2-2;\n" +
                           "(If a Then v=2 Else v=3);\n" +
                           " Print(v)");

        int input;
        input = keyboard.nextInt();
        return input;
    }

    private int selectNrOfExec() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please select the number of executions:");
        System.out.println("1. Execute one");
        System.out.println("2. Execute all");

        int input;
        input = keyboard.nextInt();
        return input;
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

        IExecStack execStack = new ExecStack<>();
        ISymTable<String, Integer> symTable = new SymTable<>();
        IOut<Integer> messageList = new Out<>();
        PrgState prgState = new PrgState(execStack, symTable, messageList);
        IRepository repository = new Repository();
        repository.addProgramState(prgState);
        Controller controller = new Controller(repository);

        View view = new View();
        int prg = view.selectProgram();

        switch (prg) {
            case 1: {
                execStack.push(ex1);
                break;
            }
            case 2: {
                execStack.push(ex2);
                break;
            }
            case 3: {
                execStack.push(ex3);
                break;
            }
            default: {
                System.out.println("No program corresponding to this number");
            }
        }

        int nrOfExec = view.selectNrOfExec();

        switch (nrOfExec) {
            case 1: {
                controller.executeOne();
                return;
            }
            case 2: {
                try {
                    controller.executeAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            default: {
                System.out.println("No option corresponding to this number");
            }
        }

    }
}