package Controller;


import Model.FileData;
import Model.State.IOut;
import Repository.IRepository;
import Model.State.PrgState;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller
{
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository _repository, boolean _display){

        this.repository = _repository;
    }

//    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
//        return heap.entrySet().stream()
//                .filter(e->symTableValues.contains(e.getKey()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
//
//    void closeOpenedFiles(Map<Integer, FileData> fileTable) throws IOException {
//        fileTable.forEach((key, value) -> {
//            try {
//                value.getFileDescriptor().close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private void executeOneForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                this.repository.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::executeOne))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = this.executor.invokeAll(callList).stream()
                . map(prgStateFuture -> {
                    try {
                        return prgStateFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                this.repository.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.repository.setPrgList(prgList);
    }

    public void executeAll() throws InterruptedException, IOException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList = this.removeCompletedPrg(this.repository.getPrgList());
        while(prgList.size() > 0){
            this.executeOneForAllPrg(prgList);
            prgList = removeCompletedPrg(this.repository.getPrgList());
        }

        executor.shutdownNow();

        this.repository.setPrgList(prgList);

        this.repository.openLogFile();
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
