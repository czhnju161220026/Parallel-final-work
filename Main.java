import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static  float time = 0;

    public static void main(String[] args) throws IOException{
        Data.init();
        System.out.println("Please input the number of threads: ");
        int numofThreads;
        Scanner scanner = new Scanner(System.in);
        numofThreads = scanner.nextInt();
        ArrayList<Float>mSortRuntime = new ArrayList<>();
        ArrayList<Float>pMSortRuntime = new ArrayList<>();
        BufferedWriter logWriter = new BufferedWriter(new FileWriter(new File("Log.txt")));
        System.out.println("-----------------------Merge Sort----------------------");
        logWriter.write("-----------------------Merge Sort----------------------\n");
        for(int i = 0;i < 50;i++) {
            mSortRuntime.add(NormalMergeSort.sort());
            Data.outputFile("MergeSort.txt");
            Data.fresh();
            pMSortRuntime.add(ParallelMergeSort.sort(numofThreads));
            Data.outputFile("ParallelMergeSort.txt");
            Data.fresh();
        }
        for(int i = 0;i<50;i++) {
            logWriter.write("Normal Merge Sort: "+mSortRuntime.get(i)+" ms.\n");
            logWriter.write("Parallel Merge Sort: "+pMSortRuntime.get(i)+" ms.\n");
        }
        Collections.sort(mSortRuntime);
        Collections.sort(pMSortRuntime);
        System.out.println("The median of normal merge sort run time: "+mSortRuntime.get(24)+" ms.");
        System.out.println("The median of parallel merge sort run time: "+pMSortRuntime.get(24)+" ms.");
        System.out.println("Speedup Ratio: "+mSortRuntime.get(24)/pMSortRuntime.get(24));
        logWriter.write("The median of normal merge sort run time: "+mSortRuntime.get(24)+" ms."+"\n");
        logWriter.write("The median of parallel merge sort run time: "+pMSortRuntime.get(24)+" ms."+"\n");
        logWriter.write("Speedup Ratio: "+mSortRuntime.get(24)/pMSortRuntime.get(24)+"\n");
        logWriter.flush();
        Data.fresh();

        System.out.println("----------------------Quick Sort----------------------");
        logWriter.write("----------------------Quick Sort----------------------\n");
        ArrayList<Float> quickSortRuntime = new ArrayList<>();
        ArrayList<Float> pQuickSortRuntime = new ArrayList<>();
        for(int i = 0;i < 50;i++) {
            quickSortRuntime.add(NormalQuickSort.sort());
            Data.outputFile("QuickSort.txt");
            Data.fresh();
            pQuickSortRuntime.add(ParallelQuickSort.sort(numofThreads));
            Data.outputFile("ParallelQuickSort.txt");
            Data.fresh();
        }
        for(int i = 0;i<50;i++) {
            logWriter.write("Normal Quick Sort: "+quickSortRuntime.get(i)+" ms.\n");
            logWriter.write("Parallel Quick Sort: "+pQuickSortRuntime.get(i)+" ms.\n");
        }
        Collections.sort(quickSortRuntime);
        Collections.sort(pQuickSortRuntime);
        System.out.println("The median of normal quick sort run time: "+quickSortRuntime.get(24)+" ms.");
        System.out.println("The median of parallel quick sort run time: "+pQuickSortRuntime.get(24)+" ms.");
        System.out.println("Speedup Ratio: "+quickSortRuntime.get(24)/pQuickSortRuntime.get(24));
        logWriter.write("The median of normal quick sort run time: "+quickSortRuntime.get(24)+" ms."+"\n");
        logWriter.write("The median of parallel quick sort run time: "+pQuickSortRuntime.get(24)+" ms."+"\n");
        logWriter.write("Speedup Ratio: "+quickSortRuntime.get(24)/pQuickSortRuntime.get(24)+"\n");
        logWriter.flush();
        Data.fresh();

        System.out.println("----------------------Enum Sort----------------------");
        logWriter.write("----------------------Enum Sort----------------------\n");
        ArrayList<Float> enumSortRuntime = new ArrayList<>();
        ArrayList<Float> pEnumSortRuntime = new ArrayList<>();
        for(int i = 0;i<3;i++) {
            enumSortRuntime.add(NormalEnumSort.sort());
            Data.outputFile("EnumSort.txt");
            Data.fresh();
            pEnumSortRuntime.add(ParallelEnumSort.sort(numofThreads));
            Data.outputFile("ParallelEnumSort.txt");
            Data.fresh();
        }
        for(int i = 0;i<3;i++) {
            logWriter.write("Normal Enum Sort: "+enumSortRuntime.get(i)+" ms.\n");
            logWriter.write("Parallel Enum Sort: "+pEnumSortRuntime.get(i)+" ms.\n");
        }
        Collections.sort(enumSortRuntime);
        Collections.sort(pEnumSortRuntime);
        System.out.println("The median of normal enum sort run time: "+enumSortRuntime.get(1)+" ms.");
        System.out.println("The median of parallel enum sort run time: "+pEnumSortRuntime.get(1)+" ms.");
        System.out.println("Speedup Ratio: "+enumSortRuntime.get(1)/pEnumSortRuntime.get(1));
        logWriter.write("The median of normal enum sort run time: "+enumSortRuntime.get(1)+" ms."+"\n");
        logWriter.write("The median of parallel enum sort run time: "+pEnumSortRuntime.get(1)+" ms."+"\n");
        logWriter.write("Speedup Ratio: "+enumSortRuntime.get(1)/pEnumSortRuntime.get(1)+"\n");

        logWriter.flush();

    }


}
