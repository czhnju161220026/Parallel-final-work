import java.io.*;
import java.util.Scanner;

public class Main {
    private static  float time = 0;

    public static void main(String[] args) throws IOException{
        Data.init();
        //串行归并排序
        time = NormalMergeSort.sort();
        System.out.println("Normal MergeSort: "+time+" ms.");
        Data.outputFile("res1.txt");
        Data.fresh();
        //串行快排

        time = NormalQuickSort.sort();
        System.out.println("Normal QuickSort: "+time+" ms.");
        Data.outputFile("res2.txt");
        Data.fresh();
        //串行枚举排序
        time = NormalEnumSort.sort();
        System.out.println("Normal EnumSort: "+time+" ms.");
        Data.outputFile("res3.txt");
        Data.fresh();

        System.out.println("------------------------------------------");
        //并行归并排序
        time = ParalleMergeSort.sort(4);
        System.out.println("Paralle MergeSort: "+time+" ms.线程数4");
        Data.outputFile("res4.txt");
        Data.fresh();
        //并行快速排序
        time  = ParalleQuickSort.sort();
        System.out.println("Paralle QuickSort: "+time+" ms.线程数2");
        Data.outputFile("res5.txt");
        Data.fresh();
        //并行枚举排序
        time = ParallelEnumSort.sort(8);
        System.out.println("Parallel EnumSort: "+time+" ms.线程数8");
        Data.outputFile("res6.txt");
        Data.fresh();

    }
}
