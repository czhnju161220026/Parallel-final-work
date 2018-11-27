public class NormalQuickSort {
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static int split(int[] subArray, int begin, int end) {
        int splitPoint = begin;
        int pivot = subArray[begin];
        for(int i = begin+1;i<=end;i++) {
            if(subArray[i] < pivot) {
                splitPoint++;
                if(i!=splitPoint) {
                    swap(subArray,splitPoint,i);
                }
            }
        }
        swap(subArray,begin,splitPoint);
        return splitPoint;
    }

    private static void myQsort(int[] array,int begin,int end) {
        if(begin<end) {
            int splitpoint = split(array, begin, end);
            myQsort(array, begin, splitpoint);
            myQsort(array, splitpoint + 1, end);
        }
    }

    public static float sort() {
        long startTime = System.nanoTime();
        myQsort(Data.resultList,0,Data.length-1);
        long endTime = System.nanoTime();
        return (float)(endTime-startTime)/100000;
    }
}
