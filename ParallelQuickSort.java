import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubQuickSort implements Runnable {
    private static int maxHeight = 1;
    private  int height = 0;
    private int begin;
    private int end;
    public SubQuickSort(int begin,int end,int height) {
        this.begin = begin;
        this.end = end;
        this.height = height;
    }
    public void run() {
        if(height==maxHeight) {
            NormalQuickSort.myQsort(Data.resultList,begin,end);
        }
        else {
            ExecutorService executorService = Executors.newCachedThreadPool();
            int left = begin;
            int right = end;
            int mid = (begin + end)/2;
            int splitPoint = -1;
            while(splitPoint!=mid) {
                splitPoint = NormalQuickSort.partition(Data.resultList,left,right);
                if(splitPoint<mid) {
                    left = splitPoint+1;
                }
                else if(splitPoint>mid) {
                    right = splitPoint-1;
                }
                else {
                    break;
                }
            }
            //System.out.println("Split point = "+splitPoint);
            executorService.execute(new SubQuickSort(begin,splitPoint-1,height+1));
            executorService.execute(new SubQuickSort(splitPoint+1,end,height+1));
            executorService.shutdown();
            while(true) {
                if(executorService.isTerminated()) {
                    break;
                }
            }
            return;
        }
    }
}
public class ParallelQuickSort {
    public static float sort() {
        long startTime = System.nanoTime();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new SubQuickSort(0,Data.length-1,0));
        executorService.shutdown();
        while(true) {
            if(executorService.isTerminated()) {
                break;
            }
        }
        long endTime = System.nanoTime();
        return (float)(endTime-startTime)/1000000;
        //return NormalQuickSort.sort();
    }
}
