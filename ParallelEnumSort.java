import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubTask implements Runnable {
    private int begin;
    private int end;
    public SubTask(int begin,int end) {
        this.begin = begin;
        this.end = end;
    }
    public void run() {
        for(int i = begin;i<=end;i++) {
            int index = 0;
            for(int j = 0;j<Data.length;j++) {
                if(Data.rawData[j] < Data.rawData[i]) {
                    index++;
                }
            }
           Data.resultList[index] = Data.rawData[i];
        }
        return ;
    }
}
public class ParallelEnumSort {

    public static  float sort(int numOfThreads) {
        int subLength = Data.length/numOfThreads;
        ExecutorService executorService = Executors.newCachedThreadPool();
        long startTime = System.nanoTime();
        for(int i = 0;i<numOfThreads;i++) {
            if(i==numOfThreads-1) {
                executorService.execute(new SubTask(i*subLength,Data.length-1));
            }
            else {
                executorService.execute(new SubTask(i*subLength,i*subLength+subLength-1));
            }
        }
        executorService.shutdown();
        while(true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
        long endTime =System.nanoTime();
        return (float)(endTime-startTime)/1000000;
    }
}
