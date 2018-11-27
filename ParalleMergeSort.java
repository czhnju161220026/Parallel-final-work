import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubMergeSort implements Runnable {
    int begin;
    int end;
    public SubMergeSort(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    public void run() {
        NormalMergeSort.myMergeSort(Data.resultList,begin,end);
    }
}

public class ParalleMergeSort {
    private static void mergeBlocks(ArrayList<Integer>splits) {
        int[] index = new int[splits.size()];
        int[] tempRes = new int[Data.length];
        index[0] = 0;
        for(int i = 0;i < splits.size();i++) {
            if(i!=splits.size()-1) {
                index[i+1] = splits.get(i)+1;
            }
        }
        for(int i = 0;i < Data.length;i++) {
            int smallest = 0;
            int smallestIndex = 0;
            for(int j = 0; j < splits.size();j++) {
                if(index[j]<=splits.get(j)) {
                    smallest = Data.resultList[index[j]];
                    break;
                }
            }
            for(int j = 0;j<splits.size();j++) {
                if(index[j]<=splits.get(j)&&Data.resultList[index[j]]<smallest) {
                    smallest = Data.resultList[index[j]];
                    smallestIndex = j;
                }
            }
            index[smallestIndex]++;
            tempRes[i] = smallest;
        }

        for(int i = 0;i < Data.length;i++) {
            Data.resultList[i] = tempRes[i];
        }
    }
    public static float sort(int numOfThreads) {
        long startTime = System.nanoTime();
        int subLength = Data.length/numOfThreads;
        ArrayList<Integer> splits = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<numOfThreads;i++) {
            if(i==numOfThreads-1) {
                executorService.execute(new SubMergeSort(i*subLength,Data.length-1));
                splits.add(Data.length-1);
            }
            else {
                executorService.execute(new SubMergeSort(i*subLength,i*subLength+subLength-1));
                splits.add(i*subLength+subLength-1);
            }
        }
        executorService.shutdown();
        while(true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
        mergeBlocks(splits);
        long endTime = System.nanoTime();
        return (float)(endTime-startTime)/1000000;
    }
}
