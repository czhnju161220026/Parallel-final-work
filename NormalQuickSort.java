public class NormalQuickSort {
    private static long startTime;
    private static long endTime;
    private static boolean started = false;
    private static final void swap(int[] array,int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    public static int partition(int[] array, int begin,int end){
        int splitPoint = begin;
        int pivot = array[begin];
        for(int i = begin;i<=end;i++) {
            if(array[i] < pivot) {
                splitPoint++;
                if(i!=splitPoint) {
                    swap(array,i,splitPoint);
                }
            }
        }
        array[begin] = array[splitPoint];
        array[splitPoint] = pivot;

        return splitPoint;
    }
    public static void myQsort(int[]array,int begin, int end) {
        if(begin<end) {
            int splitPoint = partition(array,begin,end);
            myQsort(array,begin,splitPoint-1);
            myQsort(array,splitPoint+1,end);
        }
    }

    public static float sort() {
        startTime = System.nanoTime();
        myQsort(Data.resultList,0,Data.length-1);
        endTime = System.nanoTime();
        return (float)(endTime-startTime)/1000000;
    }
}
