package View;

import Controller.Controller;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.EmptyExecStackException;
import Model.Exceptions.UnknownOperationException;
import Model.FileData;
import Model.Statements.IStmt;
import Model.States.IExecStack;
import Model.States.PrgState;
import Repository.IRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RunProgram {
    @FXML
    TableView<Map.Entry<Integer, Integer>> heapTableFX;

    @FXML
    TableColumn<Map.Entry<Integer, Integer>, Integer> addressColFX;

    @FXML
    TableColumn<Map.Entry<Integer, Integer>, Integer> valueHeapColFX;

    @FXML
    TableView<Map.Entry<Integer, FileData>> fileTableFX;

    @FXML
    TableColumn<Map.Entry<Integer, FileData>, Integer> idColFX;

    @FXML
    TableColumn<Map.Entry<Integer, FileData>, String> fileNameColFX;

    @FXML
    TableView<Map.Entry<String, Integer>> symTableFX;

    @FXML
    TableColumn<Map.Entry<String, Integer>, String> variableColFX;

    @FXML
    TableColumn<Map.Entry<String, Integer>, Integer> valueColFX;

    @FXML
    ListView<Integer> outFX;

    @FXML
    ListView<Integer> prgStateIDsFX;

    @FXML
    ListView<String> execStackFX;

    @FXML
    Button oneStepButtonFX;

    @FXML
    Label prgStatesNrFX;


    public void initialize(PrgState selectedPrg, IRepository repository) {

        Controller ctrl = new Controller(repository, false);

        this.populate(selectedPrg, repository);
        prgStatesNrFX.setText("PrgStates: " + String.valueOf(ctrl.getProgramStatesNumber()));

        oneStepButtonFX.setOnAction(event -> {
            try {
                ctrl.executeAllGUI();
                PrgState newPrgState = new PrgState();
                try {
                    newPrgState = repository.getById(prgStateIDsFX.getSelectionModel().getSelectedItem());
                    if(newPrgState==null) {
                        setPrgStateIDsFX(repository);
                        newPrgState = repository.getById(prgStateIDsFX.getItems().get(0));
                    }
                }
                catch (NullPointerException e) {
                    newPrgState = repository.getById(prgStateIDsFX.getItems().get(0));
                }
                if(newPrgState != null)
                    populate(newPrgState, repository);
                prgStatesNrFX.setText("PrgStates: " + String.valueOf(ctrl.getProgramStatesNumber()));
            } catch (InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "The program has ended!");
                alert.show();
            }
        });

        prgStateIDsFX.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Integer>()
                {
                    public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
                    {
                        if (newValue == null) {
                            setPrgStateIDsFX(repository);
                            prgStateIDsFX.refresh();
                            newValue = prgStateIDsFX.getItems().get(0);
                        }
                        PrgState newPrgState = repository.getById(newValue);

                        setExecStackFX(newPrgState);
                        setSymTableFX(newPrgState);
                    };
                });


    }

    private void populate(PrgState selectedPrg, IRepository repository) {
        setHeapTableFX(selectedPrg);
        setFileTableFX(selectedPrg);
        setSymTableFX(selectedPrg);
        setOutFX(selectedPrg);
        setPrgStateIDsFX(repository);
        setExecStackFX(selectedPrg);
    }

    private void setHeapTableFX(PrgState selectedPrg) {
        Iterable<Map.Entry<Integer, Integer>> heap = selectedPrg.getHeap().getAll();
        List<Map.Entry<Integer, Integer>> heapList = new ArrayList<>();
        heap.forEach(heapList::add);
        ObservableList<Map.Entry<Integer, Integer>> heapObs = FXCollections.observableArrayList(heapList);

        addressColFX.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueHeapColFX.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        heapTableFX.setItems(heapObs);
        heapTableFX.refresh();
    }

    private void setFileTableFX(PrgState selectedPrg) {
        Iterable<Map.Entry<Integer, FileData>> fileTable = selectedPrg.getFileTable().getAll();
        List<Map.Entry<Integer, FileData>> fileTableList = new ArrayList<>();
        fileTable.forEach(fileTableList::add);
        ObservableList<Map.Entry<Integer, FileData>> fileTableObs = FXCollections.observableArrayList(fileTableList);

        idColFX.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        fileNameColFX.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().toString()));

        fileTableFX.setItems(fileTableObs);
        fileTableFX.refresh();
    }

    private void setSymTableFX(PrgState selectedPrg) {
        Iterable<Map.Entry<String, Integer>> symTbl = selectedPrg.getSymTable().getAll();
        List<Map.Entry<String, Integer>> symList = new ArrayList<>();
        symTbl.forEach(symList::add);
        ObservableList<Map.Entry<String, Integer>> symObs = FXCollections.observableArrayList(symList);

        variableColFX.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        valueColFX.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        symTableFX.setItems(symObs);
        symTableFX.refresh();
    }

    private void setOutFX(PrgState selectedPrg) {
        Iterable<Integer> out = selectedPrg.getOut().getContent();
        List<Integer> outList = new ArrayList<>();
        out.forEach(outList::add);
        ObservableList<Integer> outObs = FXCollections.observableArrayList(outList);
        outFX.setItems(outObs);
        outFX.refresh();
    }

    private void setPrgStateIDsFX(IRepository repository) {
        List<Integer> prgStateIDs = repository.getPrgList().stream().map(PrgState::getId).collect(Collectors.toList());
        ObservableList<Integer> idObs = FXCollections.observableArrayList(prgStateIDs);
        prgStateIDsFX.setItems(idObs);
        prgStateIDsFX.refresh();
    }

    private void setExecStackFX(PrgState selectedPrg) {
        Iterable<IStmt> execStack = selectedPrg.getExecStack().getStack();
        List<IStmt> execList = new ArrayList<>();
        execStack.forEach(execList::add);
        List<String> execStackStr = execList.parallelStream().map(IStmt::toString).collect(Collectors.toList());
        ObservableList<String> execObs = FXCollections.observableArrayList(execStackStr);
        execStackFX.setItems(execObs);
        execStackFX.refresh();
    }
}
