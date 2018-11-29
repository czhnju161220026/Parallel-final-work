import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static  float time = 0;

    public static void main(String[] args) throws IOException{
        Data.init();

        ArrayList<Float>mSortRuntime = new ArrayList<>();
        ArrayList<Float>pMSortRuntime = new ArrayList<>();
        BufferedWriter logWriter = new BufferedWriter(new FileWriter(new File("Log.txt")));
        System.out.println("----------------Merge Sort----------------");
        logWriter.write("----------------Merge Sort----------------\n");
        for(int i = 0;i < 50;i++) {
            mSortRuntime.add(NormalMergeSort.sort());
            Data.fresh();
            pMSortRuntime.add(ParallelMergeSort.sort(4));
            Data.fresh();
        }
        for(int i = 0;i<50;i++) {
            logWriter.write("Normal Merge Sort: "+mSortRuntime.get(i)+" ms.\n");
            logWriter.write("Parallel Merge Sort: "+pMSortRuntime.get(i)+" ms.\n");
        }
        Collections.sort(mSortRuntime);
        Collections.sort(pMSortRuntime);
        System.out.println("The median of normal merge sort run time: "+mSortRuntime.get(24));
        System.out.println("The median of parallel merge sort run time: "+pMSortRuntime.get(24));
        System.out.println("Speedup Ratio: "+mSortRuntime.get(24)/pMSortRuntime.get(24));
        logWriter.write("The median of normal merge sort run time: "+mSortRuntime.get(24)+"\n");
        logWriter.write("The median of parallel merge sort run time: "+pMSortRuntime.get(24)+"\n");
        logWriter.write("Speedup Ratio: "+mSortRuntime.get(24)/pMSortRuntime.get(24)+"\n");

        logWriter.flush();
    }
}
