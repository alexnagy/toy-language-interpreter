package View;

import Model.Exceptions.EmptyExecStackException;
import Model.Expressions.ArithExpr;
import Model.Expressions.ConstExpr;
import Model.Expressions.HeapReadExpr;
import Model.Expressions.VarExpr;
import Model.Statements.*;
import Model.States.PrgState;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectProgram implements Initializable {
    @FXML
    private ListView<String> prgStateList;

    @FXML
    private Button selectBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PrgState> prgList = this.getPrgList();

        List<String> strList = prgList.stream().map(p -> {
            try {
                return p.getExecStack().peek().toString();
            } catch (EmptyExecStackException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
        ObservableList<String> obsvList = FXCollections.observableList(strList);

        prgStateList.setItems(obsvList);

        final List<PrgState> prgStatesFinal = prgList;

        selectBtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                int index = prgStateList.getSelectionModel().getSelectedIndex();

                if(-1 == index){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No program State Selected!");
                    alert.show();
                    return;
                }

                PrgState selectedProgram = prgStatesFinal.get(index);

                try {
                    selectBtn.getScene();
                    Stage oldStage = (Stage) selectBtn.getScene().getWindow();
                    oldStage.setIconified(true);

                    Stage secondStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("RunProrgram.fxml"));
                    Parent root2 = loader.load();
                    secondStage.setTitle("ToyLanguageInterpreter");
                    secondStage.setScene(new Scene(root2));

                    RunProgram runPrg = loader.getController();

                    IRepository repo = new Repository(selectedProgram, "log.txt");

                    runPrg.initialize(selectedProgram, repo);

                    secondStage.show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private List<PrgState> getPrgList() {
        List<PrgState> prgList = new ArrayList<>();

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

        PrgState prg1 = new PrgState();
        prg1.getExecStack().push(ex1);
        prgList.add(prg1);

        PrgState prg2 = new PrgState();
        prg2.getExecStack().push(ex2);
        prgList.add(prg2);

        PrgState prg3 = new PrgState();
        prg3.getExecStack().push(ex3);
        prgList.add(prg3);

        PrgState prg4 = new PrgState();
        prg4.getExecStack().push(ex4);
        prgList.add(prg4);

        PrgState prg5 = new PrgState();
        prg5.getExecStack().push(ex5);
        prgList.add(prg5);

        PrgState prg6 = new PrgState();
        prg6.getExecStack().push(ex6);
        prgList.add(prg6);

        PrgState prg7 = new PrgState();
        prg7.getExecStack().push(ex7);
        prgList.add(prg7);

        PrgState prg8 = new PrgState();
        prg8.getExecStack().push(ex8);
        prgList.add(prg8);

        PrgState prg9 = new PrgState();
        prg9.getExecStack().push(ex9);
        prgList.add(prg9);

        return prgList;
    }
}
