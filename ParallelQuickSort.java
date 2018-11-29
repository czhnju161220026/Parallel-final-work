import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubTask implements Runnable {
    private  int lowerBound;
    private  int upperBound;
    private ArrayList<Integer> targetList;
    public SubTask(int lowerBound,int upperBound,ArrayList<Integer>targetList) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.targetList = targetList;
    }
    public void run() {
        //System.out.println("["+lowerBound+","+upperBound+"]");
        for(int i = 0;i<Data.length-1;i++) {
            if(Data.resultList[i]>lowerBound&&Data.resultList[i]<upperBound) {
                targetList.add(Data.resultList[i]);
            }
        }
        int[] list = new int[targetList.size()];
        for(int i = 0;i < targetList.size();i++) {
            list[i] = targetList.get(i);
        }
        NormalQuickSort.myQsort(list,0,list.length-1);
        for(int i = 0;i<targetList.size();i++) {
            targetList.set(i,list[i]);
        }
    }
}

public class ParallelQuickSort {
    public static float sort(int numOfThreads) {
        ArrayList<Integer> samplesPoint = new ArrayList<>();
        ArrayList<ArrayList<Integer>> subList = new ArrayList<>();
        for(int i = 0;i<numOfThreads;i++) {
            subList.add(new ArrayList<Integer>());
        }
        int[] splitPoint = new int[numOfThreads-1];
        int sampleInterval = Data.length/100;
        int splitInterval = 100/(numOfThreads);
        ExecutorService executorService = Executors.newCachedThreadPool();
        long startTime,endTime;
        startTime = System.nanoTime();
        for(int i = 0;i<100;i++) {
            samplesPoint.add(Data.resultList[i*sampleInterval]);
        }
        Collections.sort(samplesPoint);
        for(int i = 0;i<numOfThreads-1;i++) {
            splitPoint[i] = samplesPoint.get((i+1)*splitInterval);
            //System.out.print(""+splitPoint[i]+",");
        }
        //System.out.println();
        executorService.execute(new SubTask(0x80000000,splitPoint[0],subList.get(0)));
        for(int i = 1;i<numOfThreads-1;i++) {
            executorService.execute(new SubTask(splitPoint[i-1]+1,splitPoint[i],subList.get(i)));
        }
        executorService.execute(new SubTask(splitPoint[numOfThreads-2]+1,0x7fffffff,subList.get(numOfThreads-1)));
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        int index= 0;
        for(int i = 0;i<numOfThreads;i++) {
            if(i==numOfThreads-1) {
                for(int j = 0;j < subList.get(i).size();j++) {
                    Data.resultList[index] = subList.get(i).get(j);
                    index++;
                }
            }
            else {
                for(int j = 0;j < subList.get(i).size();j++) {
                    Data.resultList[index] = subList.get(i).get(j);
                    index++;
                }
                Data.resultList[index] = splitPoint[i];
                index++;
            }
        }
        endTime = System.nanoTime();
        return (float)(endTime-startTime)/1000000;
    }
}
